package com.smit.service.push.packet;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.push.IPushManageService;

public class PresenceListener implements PacketListener {

private IPushManageService pushManageService = null;
	
	
	public void setPushManageService(IPushManageService pushManageService) {
		this.pushManageService = pushManageService;
	}
	private IPushManageService getService(){
		if(pushManageService == null){
			BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
			
			pushManageService = (IPushManageService)beanFactory.getBean("pushManage");		
			beanFactory = null;
		}
		return pushManageService;
				
	}
	
	@Override
	public void processPacket(Packet packet) {
		 
		Presence presence = (Presence)packet;
		String from = presence.getFrom();
		if (presence.getType() == Presence.Type.available){
			getService().updateUserPresence(from, true);
		} 
		else if (presence.getType() == Presence.Type.unavailable) {
			getService().updateUserPresence(from, false);
		}
		System.out.println("presence listener:"+from+presence.getType());
	}
}
