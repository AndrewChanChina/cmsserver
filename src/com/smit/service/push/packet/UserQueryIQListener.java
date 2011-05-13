package com.smit.service.push.packet;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.push.IPushManageService;
import com.smit.vo.UserAccountResource;

public class UserQueryIQListener implements PacketListener {

	@Override	
	public void processPacket(Packet packet) {
		System.out.print("packet.toXML()=" + packet.toXML());
		
		if (packet instanceof UserQueryIQ) {
			System.out.println("user query iq return");	
			
			UserQueryIQ iq = (UserQueryIQ)packet;
			BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
			IPushManageService pm = (IPushManageService)beanFactory.getBean("pushManage");
			
			List<UserAccountResource> list = new ArrayList<UserAccountResource>();
			List<String> resources = iq.getResources();
			List<String> deviceNames = iq.getDeviceNames();
			List<String> deviceIds = iq.getDeviceIds();
			String userAccount = iq.getUserAccount();
			int i = 0;
			for(String res : iq.getResources()){
				UserAccountResource us = new UserAccountResource();
				us.setUserAccount(userAccount);
				us.setResource(res);
				us.setDeviceId(deviceIds.get(i));
				us.setDeviceName(deviceNames.get(i));
				i++;
				list.add(us);
			}
			if(i > 0){
				pm.updateUserAccountAllRes(list);
			}	
			beanFactory = null;
		}
	}

}
