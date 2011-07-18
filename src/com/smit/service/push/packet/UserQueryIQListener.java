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
				if("true".equalsIgnoreCase(presences.get(i))){
					us.setPresence(true);
				}else{
					us.setPresence(false);
				}
				i++;
				list.add(us);

			}
			// TODO 目前是全部删除设备，这样造成删掉了登录时接收到的设备在线信息。
			// 解决的办法有：1、把设备的在线信息发过来；2、不要全部删除，只更新 
			// 2011-5-18 9:46:40 ANDREW
			if(i > 0){
				getService().updateUserAccountAllRes(list);
			}else if(userAccount != null && userAccount.isEmpty()==false){
				getService().delete(userAccount);
			}

			}			
//			if(i > 0){
//				getService().updateUserAccountAllRes(list);
//			}else if(userAccount != null && userAccount.isEmpty()==false){
//				getService().delete(userAccount);
//			}

		//}
	}

}
