package com.smit.service.push.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class PushServiceInfIQProvider implements IQProvider {

	public IQ parseIQ(XmlPullParser parser) throws Exception {

		PushServiceInfIQ pushServiceInfIQ = new PushServiceInfIQ();
		
		 for (boolean done = false; !done;) {
	            int eventType = parser.next();
	            if (eventType == 2) {
	               
	                if("pushServiceName".equals(parser.getName())) {
	                	pushServiceInfIQ.setPushServiceName(parser.nextText());
	                }
	                if ("userAccount".equals(parser.getName())) {
	                	pushServiceInfIQ.setUserAccount(parser.nextText());
	                }
	                if ("pushID".equals(parser.getName())) {
	                	pushServiceInfIQ.getPushIds().add(parser.nextText());
	                }
	                
	            } else if (eventType == 3
	                    && pushServiceInfIQ.getElementName().equals(parser.getName())) {
	                done = true;	               
	            }
	        }
		 
		return pushServiceInfIQ;
	}
	
}
