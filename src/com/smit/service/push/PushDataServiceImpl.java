package com.smit.service.push;

import java.util.List;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.NotFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.provider.ProviderManager;

import com.smit.service.push.packet.FeedUpdateIQ;
import com.smit.service.push.packet.PresenceListener;
import com.smit.service.push.packet.PushServerIQ;
import com.smit.service.push.packet.PushServiceInfIQ;
import com.smit.service.push.packet.PushServiceInfIQListener;
import com.smit.service.push.packet.PushServiceInfIQProvider;
import com.smit.service.push.packet.PushUserIQ;
import com.smit.service.push.packet.UserQueryIQ;
import com.smit.service.push.packet.UserQueryIQListener;
import com.smit.service.push.packet.UserQueryIQProvider;

public class PushDataServiceImpl implements IPushDataService {
	
	private static String resource = "contentServer";	
	private XMPPConnection connection = null;	
	private IPushManageService pushManageService = null;
	private String user;

	@Override
	public boolean login(String host,String user,String password){
		this.user = user;
		ConnectionConfiguration config = new ConnectionConfiguration(host,5222, resource);
		connection = new XMPPConnection(config); 
		try{
	    	connection.connect();
	    	connection.login(user,password);
	    	initLogin();
	    	getInitInf();
	    }catch(XMPPException e){
	    	e.printStackTrace();
	    	return false;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return false;
	    }
	    return true;	
	}
	@Override
	public boolean logout(){
		if(connection == null)
			return false;
		connection.disconnect();
		return true;
	}
	@Override
	public boolean isConnected(){
		if(connection == null)
			return false;
		if(connection.isConnected() &&
		   connection.isAuthenticated()){
			return true;
		}
		return false;			
	}
	
	private boolean sendPacket(Packet packet){
		if(!isConnected()){
			return false;
		}
		connection.sendPacket(packet);
		return true;
	}
	/*
	 * send data to push server
	 */
	@Override
	public boolean sendPushDataFromDevToAll(
			String pushServiceName,
			boolean bDelay,
			String collapseKey,
			String title,
			String ticker,
			String uri,
			String message){
		return this.sendPushDataFromDev(pushServiceName,null, bDelay, collapseKey,
				title, ticker, uri, message);
		
		
	}
	/*
	 * if pushIdList == null,it will send the data to all
	 */
	@Override
	public boolean sendPushDataFromDev(
			String pushServiceName,
			List<String> pushIdList,
			boolean bDelay,
			String collapseKey,
			String title,
			String ticker,
			String uri,
			String message){
		PushServerIQ iq = new PushServerIQ();
		if(pushIdList == null){
			iq.setToAll("all");
		}else{
			iq.setToAll("false");
			iq.setPushIDList(pushIdList);
		}
		iq.setPushServiceName(pushServiceName);
		iq.setDelay(bDelay);
		iq.setCollapseKey(collapseKey);
		iq.setTitle(title);
		iq.setTicker(ticker);
		iq.setUri(uri);
		iq.setMessage(message);
		iq.toXML();
		return this.sendPacket(iq);
	}
	
	@Override
	public boolean sendPushDataFromUser(
			List<String> userList,
			boolean bDelay,
			String collapseKey,
			String title,
			String ticker,
			String uri,
			String message,
			String type) throws Exception{
		
		PushUserIQ iq = new PushUserIQ();
		if(userList == null){
			return false;
		}
		
		iq.setIQType(type);
		iq.setUserList(userList);
		iq.setDelay(bDelay);
		iq.setCollapseKey(collapseKey);
		iq.setTitle(title);
		iq.setTicker(ticker);
		iq.setUri(uri);
		iq.setMessage(message);
		System.out.println(iq.toXML());
		return this.sendPacket(iq);
	}
	
	@Override
	public XMPPConnection getConnection(){
		return this.connection;
	}
	@Override
	public boolean sendQueryResourceId(String userName) {
//		UserQueryIQ iq1 = new UserQueryIQ();
//		iq1.setUserAccount(userName);
//		iq1.setOpCodeSave();
//		iq1.setDeviceId("111111");
//		iq1.setDeviceName("111111");
//		iq1.setResource("1111111");
//		this.sendPacket(iq1);
		
		UserQueryIQ iq = new UserQueryIQ();
		iq.setUserAccount(userName);
		iq.setOpCodeQuery();
		return this.sendPacket(iq);
	}
	
	private void initLogin(){
		
		 // packet provider
        ProviderManager.getInstance().addIQProvider(UserQueryIQ.getElementName(),
        		UserQueryIQ.getNamespace(),
                new UserQueryIQProvider());
        ProviderManager.getInstance().addIQProvider(PushServiceInfIQ.getElementName(),
        		PushServiceInfIQ.getNamespace(),
                new PushServiceInfIQProvider());
		
		 PacketFilter packetFilter = new PacketTypeFilter(
				 UserQueryIQ.class); 
		 UserQueryIQListener uqlistener = new UserQueryIQListener();
		 uqlistener.setPushManageService(pushManageService);
		 uqlistener.setXmppConnection(connection);
        connection.addPacketListener(uqlistener, packetFilter);
        
        connection.addPacketListener(new PushServiceInfIQListener(),
        		new PacketTypeFilter(PushServiceInfIQ.class));
        
        PacketFilter charFilter = new MessageTypeFilter(Message.Type.chat);	       	        
        // TODO-ANDREW test need to delete
        NotFilter notFilter = new NotFilter(charFilter);
        connection.addPacketListener(new PacketListener(){
        	public void processPacket(Packet packet) {
        		System.out.println(packet.toXML());
        	}
        },notFilter);  
        
        PacketFilter presenceFilter = new PacketTypeFilter(Presence.class);
        PresenceListener presenceListener = new PresenceListener();
        connection.addPacketListener(presenceListener,presenceFilter);
        presenceListener.setPushManageService(pushManageService);
		
	}
	private void getInitInf(){
		//获取用户的所有的resource id
		sendQueryResourceId(user+"@"+connection.getServiceName());
		//sendPushServiceInf("web","test2");
	}
	@Override
	public boolean sendPushServiceInf(String serviceName, String account) {
		PushServiceInfIQ iq = new PushServiceInfIQ();
		iq.setUserAccount(account);
		iq.setPushServiceName(serviceName);
		this.sendPacket(iq);
		return true;
	}
	
	@Override
	public boolean sendNoteFromCms(String url) {
		FeedUpdateIQ iq = new FeedUpdateIQ();
		iq.setType(IQ.Type.SET);
		iq.setFeed(url);
		this.sendPacket(iq);
		return true;
	}
	public IPushManageService getPushManageService() {
		return pushManageService;
	}
	public void setPushManageService(IPushManageService pushManageService) {
		this.pushManageService = pushManageService;
	}
	public String getServerName(){
		return connection.getServiceName();
	}
	
}

