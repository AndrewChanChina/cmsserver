package com.smit.service.push.packet;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.push.IPushManageService;
import com.smit.vo.UserAccountResource;

public class UserQueryIQListener implements PacketListener {

	private IPushManageService pushManageService = null;
	private XMPPConnection connection;
	
	public void setPushManageService(IPushManageService pushManageService) {
		this.pushManageService = pushManageService;
	}
	public void setXmppConnection(XMPPConnection connection){
		this.connection = connection;
	}
	private IPushManageService getService(){
		if(pushManageService == null){
			BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
			
			pushManageService = (IPushManageService)beanFactory.getBean("pushManage");		
			beanFactory = null;
		}
		return pushManageService;
				
	}
	public void processPacket(Packet packet) {
		System.out.print("packet.toXML()=" + packet.toXML());
		
		if (packet instanceof UserQueryIQ) {
			System.out.println("user query iq return");	
			
			UserQueryIQ iq = (UserQueryIQ)packet;
			
			
			List<UserAccountResource> list = new ArrayList<UserAccountResource>();
			List<String> resources = iq.getResources();
			List<String> deviceNames = iq.getDeviceNames();
			List<String> deviceIds = iq.getDeviceIds();
			List<String> presences = iq.getPresences();
			String userAccount = iq.getUserAccount();
			int i = 0;
			for(String res : resources){
				UserAccountResource us = new UserAccountResource();
				us.setUserAccount(userAccount);
				us.setResource(res);
				us.setDeviceId(deviceIds.get(i));
				us.setDeviceName(deviceNames.get(i));
				Presence presence = connection.getRoster().getPresenceResource(
						userAccount+"/"+res);
				if (presence.getType() == Presence.Type.available){
					us.setPresence(true);
					System.out.print(userAccount + " presence");
					us.setFlag("online");
				} 
				else if (presence.getType() == Presence.Type.unavailable) {
					us.setPresence(false);
					System.out.print(userAccount + " unpresence");
					us.setFlag("offline");
				}				
				i++;
				list.add(us);

			}
			if(i > 0){
				getService().updateUserAccountAllRes(list);
			}else if(userAccount != null && userAccount.isEmpty()==false){
				getService().delete(userAccount);
			}

		}			

	}

}
