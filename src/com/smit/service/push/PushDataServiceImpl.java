package com.smit.service.push;

import java.util.List;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Packet;

import com.smit.service.push.packet.PushServerIQ;
import com.smit.service.push.packet.PushUserIQ;
import com.smit.service.push.packet.UserQueryIQ;
import com.smit.service.push.packet.UserQueryIQListener;

public class PushDataServiceImpl implements IPushDataService {
	
	private static String resource = "contentServer";	
	private XMPPConnection connection = null;
	
	@Override
	public boolean login(String host,String user,String password){
		ConnectionConfiguration config = new ConnectionConfiguration(host,5222, resource);
		connection = new XMPPConnection(config); 
		try{
	    	connection.connect();
	    	connection.login(user,password);
	    	initLogin();
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
			boolean bNotify){
		
		PushUserIQ iq = new PushUserIQ();
		if(userList == null){
			return false;
		}
		PushUserIQ.Type type = PushUserIQ.Type.alert;
		if(bNotify)
			type = PushUserIQ.Type.notification;
		iq.setInfType(type);
		iq.setUserList(userList);
		iq.setDelay(bDelay);
		iq.setCollapseKey(collapseKey);
		iq.setTitle(title);
		iq.setTicker(ticker);
		iq.setUri(uri);
		iq.setMessage(message);
		
		return this.sendPacket(iq);
	}
	
	@Override
	public XMPPConnection getConnection(){
		return this.connection;
	}
	@Override
	public boolean sendQueryResourceId(String userName) {
		UserQueryIQ iq = new UserQueryIQ();
		iq.setUserAccount(userName);
		iq.setOpCodeQuery();
		return this.sendPacket(iq);
	}
	
	private void initLogin(){
		
		 PacketFilter packetFilter = new PacketTypeFilter(
				 UserQueryIQ.class);                    
	        PacketListener packetListener = new UserQueryIQListener();
	        connection.addPacketListener(packetListener, packetFilter);
		
	}
}

