package com.smit.service.push.packet;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class PushUserIQProvider implements IQProvider {

	public IQ parseIQ(XmlPullParser parser) throws Exception {
		PushUserIQ pushUserIQ = new PushUserIQ();
		List<String> List = new ArrayList<String>();
		 for (boolean done = false; !done;) {
	            int eventType = parser.next();
	            if (eventType == 2) {
	                if ("type".equals(parser.getName())) {
	                	pushUserIQ.setInfType(PushUserIQ.Type.fromString(parser.nextText()));
	                }
	                if("user".equals(parser.getName())) {
	                	List.add(parser.nextText());
	                }
	                if ("delayWhileIdle".equals(parser.getName())) {
	                	pushUserIQ.setDelay(parser.nextText());
	                }
	                if ("collapseKey".equals(parser.getName())) {
	                	pushUserIQ.setCollapseKey(parser.nextText());
	                }
	                if ("title".equals(parser.getName())) {
	                	pushUserIQ.setTitle(parser.nextText());
	                }
	                if ("message".equals(parser.getName())) {
	                	pushUserIQ.setMessage(parser.nextText());
	                }
	                if ("ticker".equals(parser.getName())) {
	                	pushUserIQ.setTicker(parser.nextText());
	                }
	                if ("uri".equals(parser.getName())) {
	                	pushUserIQ.setUri(parser.nextText());
	                }
	            } else if (eventType == 3
	                    && PushServerIQ.getElementName().equals(parser.getName())) {
	                done = true;
	                pushUserIQ.setUserList(List);
	            }
	        }
		 
		return pushUserIQ;
	}

}
