package com.smit.service.push.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;
import java.util.List;
import java.util.ArrayList;

public class PushServerIQProvider implements IQProvider {

	//@Override
	public IQ parseIQ(XmlPullParser parser) throws Exception {
		PushServerIQ pushServerIQ = new PushServerIQ();
		List<String> pushidList = new ArrayList<String>();
		 for (boolean done = false; !done;) {
	            int eventType = parser.next();
	            if (eventType == 2) {
	                if ("sendTo".equals(parser.getName())) {
	                   pushServerIQ.setToAll(parser.nextText());
	                }
	                if("pushID".equals(parser.getName())) {
	                	pushidList.add(parser.nextText());
	                }
	                if ("delayWhileIdle".equals(parser.getName())) {
	                    pushServerIQ.setDelay(parser.nextText());
	                }
	                if ("collapseKey".equals(parser.getName())) {
	                    pushServerIQ.setCollapseKey(parser.nextText());
	                }
	                if ("pushServiceName".equals(parser.getName())) {
	                    pushServerIQ.setPushServiceName(parser.nextText());
	                }
	                if ("title".equals(parser.getName())) {
	                   pushServerIQ.setTitle(parser.nextText());
	                }
	                if ("message".equals(parser.getName())) {
	                    pushServerIQ.setMessage(parser.nextText());
	                }
	                if ("ticker".equals(parser.getName())) {
	                    pushServerIQ.setTicker(parser.nextText());
	                }
	                if ("uri".equals(parser.getName())) {
	                   pushServerIQ.setUri(parser.nextText());
	                }
	            } else if (eventType == 3
	                    && PushServerIQ.getElementName().equals(parser.getName())) {
	                done = true;
	                pushServerIQ.setPushIDList(pushidList);
	            }
	        }
		 
		return pushServerIQ;
	}
}
