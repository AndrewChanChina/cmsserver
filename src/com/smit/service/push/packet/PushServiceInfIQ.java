package com.smit.service.push.packet;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.packet.IQ;

public class PushServiceInfIQ extends IQ {

	private String pushServiceName;
	private String userAccount;
	
	// 接收到的数据
	private List<String> pushIds = new ArrayList<String>();
	
	public static String getElementName(){
    	return "openims";
    }
    public static String getNamespace(){
    	return "smit:iq:queryPushId";
    }
    
    @Override
	public String getChildElementXML() {		
		StringBuilder buf = new StringBuilder();
	       buf.append("<").append(getElementName()).append(" xmlns=\"").append(getNamespace()).append("\">");
	       buf.append(getXML());
	       buf.append("</").append(getElementName()).append(">");
     return buf.toString();
	}
	
	private String getXML(){
		StringBuilder buf = new StringBuilder();
		if(userAccount != null)
		buf.append("<userAccount>").append(userAccount).append("</userAccount>");
		
		if(pushServiceName != null)
			buf.append("<pushServiceName>").append(pushServiceName).append("</pushServiceName>");
		
		return buf.toString();
	}
	
	public String getPushServiceName() {
		return pushServiceName;
	}
	public void setPushServiceName(String pushServiceName) {
		this.pushServiceName = pushServiceName;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public List<String> getPushIds() {
		return pushIds;
	}
	public void setPushIds(List<String> pushIds) {
		this.pushIds = pushIds;
	}
	
	

}
