package com.smit.service.push.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.StringUtils;

import java.util.List;
/*
 * 这是服务器自己进行通信的IQ
 * author: chenyzpower@gmail.com
 */
public class PushServerIQ extends IQ {

	private boolean bAll=false;
	private List<String> pushIdList = null;
	private boolean bDelay = false;
	private String collapseKey;
	private String pushServiceName;
	
	private String title;
    private String message;
    private String uri;
    private String ticker;    
    
    public static String getElementName(){
    	return "server";
    }
    public static String getNamespace(){
    	return "smit:iq:dev:notification";
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
		String toAll = "false";
		if(this.bAll)
			toAll = "true";
		String delay = "false";
		if(this.bDelay)
			delay = "true";
		
		buf.append("<sendTo>").append(toAll).append("</sendTo>");
		if(pushIdList != null){
			for (String pushid : pushIdList) {
	            buf.append("<pushID>").append(StringUtils.escapeForXML(pushid)).append("</pushID>");
	        }
		}
		buf.append("<pushServiceName>").append(pushServiceName).append("</pushServiceName>");
		buf.append("<delayWhileIdle>").append(delay).append("</delayWhileIdle>");
		buf.append("<collapseKey>").append(collapseKey).append("</collapseKey>");
		buf.append("<title>").append(title).append("</title>");
		buf.append("<ticker>").append(ticker).append("</ticker>");
		buf.append("<uri>").append(uri).append("</uri>");
		buf.append("<message>").append("<![CDATA["+message+"]]>").append("</message>");
		//buf.append("<message>").append(message).append("</message>");
		//System.out.print("pushServerIQ:" + buf.toString());
		return buf.toString();
	}

   
	public void setToAll(String all){
		this.bAll = true;
		if("false".equals(all))
			this.bAll = false;
	}
	public boolean getToAll(){
		return bAll;
	}
	public void setDelay(boolean bdelay){
		this.bDelay = bdelay;
	}
	public void setDelay(String delay){
		this.bDelay = true;
		if("false".equals(delay))
			this.bDelay = false;
	}
	public boolean getDelay(){
		return this.bDelay;
	}
	public void setCollapseKey(String key){
		this.collapseKey = key;
	}
	public String getCollapseKey(){
		return this.collapseKey;
	}
	public void setPushServiceName(String name){
		this.pushServiceName = name;
	}
	public String getPushServiceName(){
		return pushServiceName;
	}
	public void setPushIDList(List<String> list){
		this.pushIdList = list;
	}
	public List getPushIDList(){
		return this.pushIdList;
	}
	public String getTitle() {
	
	        return title;
	}

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String url) {
        this.uri = url;
    }
    
    public void setTicker(String ticker){
    	this.ticker = ticker;
    }
    public String getTicker(){
    	return ticker;
    }
}
