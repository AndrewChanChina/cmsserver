package com.smit.service.push.packet;

import org.jivesoftware.smack.packet.IQ;

public class FeedUpdateIQ extends IQ{

	private String feed;
	public static String getElementName(){
    	return "server";
    }
	public String getNameSpace(){
		return "smit:pubsub:publish";
	}
	@Override
	public String getChildElementXML() {
		StringBuilder buf = new StringBuilder();
	    buf.append("<").append(getElementName()).append(" xmlns=\"").append(getNameSpace()).append("\">");
	    buf.append("<feed>"+feed+"</feed>");
	    buf.append("</").append(getElementName()).append(">");
	    return buf.toString();
	}
	
	public String getFeed() {
		return feed;
	}
	public void setFeed(String feed) {
		
		this.feed = feed;
	}

}
