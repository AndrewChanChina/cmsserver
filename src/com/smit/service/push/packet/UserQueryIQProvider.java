package com.smit.service.push.packet;


import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class UserQueryIQProvider implements IQProvider {

	public IQ parseIQ(XmlPullParser parser) throws Exception {
		UserQueryIQ userQueryIQ = new UserQueryIQ();
		
		 for (boolean done = false; !done;) {
	            int eventType = parser.next();
	            if (eventType == 2) {
	               
	            	 if("userAccount".equals(parser.getName())) {
		                	userQueryIQ.setUserAccount(parser.nextText());
		                	 //done = true;
		                }
	                if("resource".equals(parser.getName())) {
	                	userQueryIQ.getResources().add(parser.nextText());
	                }
	                if ("deviceName".equals(parser.getName())) {
	                	userQueryIQ.getDeviceNames().add(parser.nextText());
	                }
	                if ("deviceId".equals(parser.getName())) {
	                	userQueryIQ.getDeviceIds().add(parser.nextText());
	                }	
	                if ("presence".equals(parser.getName())) {
	                	userQueryIQ.getPresences().add(parser.nextText());
	                }	
	                if ("status".equals(parser.getName())) {
	                	userQueryIQ.setStatus(parser.nextText());
	                }	
	            } else if (eventType == 3
	                    && userQueryIQ.getElementName().equals(parser.getName())) {
	                done = true;	               
	            }
	        }
		 
		return userQueryIQ;
	}

}
