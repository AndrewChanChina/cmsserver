package com.smit.service.push;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Type;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.packet.DiscoverItems.Item;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.Node;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.Subscription;

public class PubSubServiceImpl implements IPubSubService {

    private Map<String,LeafNode> topicMap = null;
    private XMPPConnection connection = null;
    
	public void setConnection(XMPPConnection con) {
		if(this.connection == null)
    	{
    		this.connection = con;
    		topicMap = new HashMap<String,LeafNode>();
        	List<String> list = getTopicNode();
        	listenTopic(list);
    	}
	}

	public List<String> getTopics() {
		ArrayList<String> list = new ArrayList<String>();
		for(String s:topicMap.keySet()){
			list.add(s);
		}
		return list;
	}

	public boolean createTopic(String topicName) {
		LeafNode leafNode = null;
		PubSubManager manager = new PubSubManager(connection,"pubsub.smitnn");
		try 
		{
			ConfigureForm f = new ConfigureForm(FormType.submit);    		
			
			f.setDeliverPayloads(true);
			f.setAccessModel(AccessModel.open);
			f.setPublishModel(PublishModel.open);
			f.setSubscribe(true);
			f.setPersistentItems(true);      
			
			Node eventNode = manager.createNode(topicName,f); 
			leafNode = (LeafNode)eventNode;				
			//Log.d(LOGTAG,TAG+"create new node "+topic);
			
		} catch (XMPPException e2)
		{    			
			e2.printStackTrace();
			return false;
		}
	
		/*leafNode.addItemEventListener(
				new SubListener(XmppManager.this)); */
		
		// you will need this first time only
		try {
			StringBuilder user = new StringBuilder(connection.getUser().trim());
			user.delete(user.lastIndexOf("/"), user.length());
			Subscription sst = leafNode.subscribe(user.toString());
			//Log.d(LOGTAG,TAG+connection.getUser()+"subscription");
		} catch (XMPPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			 System.out.println("XMPPClient errore durante la subscribe al nodo"+ e1.toString());
			 return false;
		}
		topicMap.put(topicName, leafNode);
		return true;
	}

	public boolean deleteTopic(String topic) {
		PubSubManager manager = new PubSubManager(connection,"pubsub.smitnn");
		try{
			manager.deleteNode(topic);
		}catch(XMPPException e){
			e.printStackTrace();
			return false;
		}
		topicMap.remove(topic);
		return true;
	}

	public void sendTopic(String topic, String message) {
LeafNode leafNode = (LeafNode)topicMap.get(topic);
    	
    	if(leafNode==null){
    		leafNode = getLeafNode(topic);
    		if(leafNode == null){
    			//Log.e(LOGTAG,TAG+"can't get topic" + topic);
    			return;
    		}else{
    			topicMap.put(topic, leafNode);
    		}
    	}
		
		SimplePayload payload = new SimplePayload("event", null,
	            "<all>" +
	            message +
	            "</all>");

		PayloadItem payloadItem = new PayloadItem(null, payload);
		
		try {
			leafNode.send(payloadItem);
			//Log.d(LOGTAG,TAG+"Send info" + message);
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//broadcastStatus(Constants.PUSH_STATUS_SENDFAIL);
			// 发送失败
		}

	}
	
	
	private List<String> getTopicNode(){
    	PubSubManager manager = new PubSubManager(connection, "pubsub.smitnn");
    	List<String> nodes = new ArrayList<String>();
    	try {
			DiscoverItems dItems = manager.discoverNodes(null);
			Iterator<Item> it = dItems.getItems();
			Item item;
			while(it.hasNext()){
				item = it.next();
				//Log.d(LOGTAG,TAG+item.toXML());
				nodes.add(item.getNode());
			}
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nodes;
    }
    private boolean listenTopic(List<String> topics){
    	
    	boolean b = true;
    	topicMap.clear();
    	StringBuilder user = new StringBuilder(connection.getUser().trim());
		user.delete(user.lastIndexOf("/"), user.length());
    	for(int i=0;i<topics.size();i++){
    		PubSubManager manager = new PubSubManager(connection,"pubsub.smitnn");    		
    		LeafNode leafNode = null;    		
    		try
    		{
    			String topic = topics.get(i);
    			Node node = manager.getNode(topic);
    			if(node == null){
    				b = false;
    				continue;
    			}    				
    			node.subscribe(user.toString());
    			/*node.addItemEventListener(
    					new SubListener(XmppManager.this,topic)); */
    			leafNode = (LeafNode)node;
    			topicMap.put(topic, leafNode);
    			leafNode = null;
    			//Log.d(LOGTAG,TAG+"subscription topic:" + topic);
    		} catch(XMPPException e1){
    			e1.printStackTrace();
    			b = false;    			
    		} catch(Exception e){
    			e.printStackTrace();
    			b = false;
    		}
    	}
    	return b;
    }
    private LeafNode getLeafNode(String topic){
    	
    	LeafNode leafNode = null;
		PubSubManager manager = new PubSubManager(connection,"pubsub.smitnn");
		//Log.i(LOGTAG,TAG+connection.getHost());
		try
		{
			Node node = manager.getNode(topic);
			leafNode = (LeafNode)node;			
		} catch(XMPPException e1){
			e1.printStackTrace();
			XMPPError error = e1.getXMPPError();
			Type type = error.getType();
			try 
			{
				ConfigureForm f = new ConfigureForm(FormType.submit);    		
				
				f.setDeliverPayloads(true);
				f.setAccessModel(AccessModel.open);
				f.setPublishModel(PublishModel.open);
				f.setSubscribe(true);
				f.setPersistentItems(true);      
				
				Node eventNode = manager.createNode(topic,f); 
				leafNode = (LeafNode)eventNode;				
				//Log.d(LOGTAG,TAG+"create new node "+topic);
				
			} catch (XMPPException e2)
			{    			
				e2.printStackTrace();
				return null;
			}
		}
		/*leafNode.addItemEventListener(
				new SubListener(XmppManager.this)); */
		
		// you will need this first time only
		try {
			StringBuilder user = new StringBuilder(connection.getUser().trim());
			user.delete(user.lastIndexOf("/"), user.length());
			Subscription sst = leafNode.subscribe(user.toString());
			//Log.d(LOGTAG,TAG+connection.getUser()+"subscription");
		} catch (XMPPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			 System.out.println("XMPPClient errore durante la subscribe al nodo"+ e1.toString());
		}
		return leafNode;
    }

}
