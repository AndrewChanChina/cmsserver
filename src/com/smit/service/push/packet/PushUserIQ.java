package com.smit.service.push.packet;

import java.util.List;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smack.util.StringUtils;

/**
 * 这个是用来发送 push 的详细消息的IQ，限于用户自己使用
 * @author USER
 *
 */
public class PushUserIQ extends IQ {

	private Type type = Type.notification;
	private List<String> userList;
	private boolean bDelay = false;
	private String collapseKey;
	
	private String title;
    private String message;
    private String uri;
    private String ticker;    
    
    public static String getElementName(){
    	return "server";
    }
    public static String getNamespace(){
    	return "smit:iq:user:notification";
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
		
		String delay = "false";
		if(this.bDelay)
			delay = "true";
		
		buf.append("<type>").append(type).append("</type>");
		for (String groupName : userList) {
            buf.append("<user>").append(StringUtils.escapeForXML(groupName)).append("</user>");
        }
		buf.append("<delayWhileIdle>").append(delay).append("</delayWhileIdle>");
		buf.append("<collapseKey>").append(collapseKey).append("</collapseKey>");
		buf.append("<title>").append(title).append("</title>");
		buf.append("<ticker>").append(ticker).append("</ticker>");
		buf.append("<uri>").append(uri).append("</uri>");
		buf.append("<message>").append(message).append("</message>");
		return buf.toString();
	}

   
	public void setInfType(Type type){
		this.type = type;
	}
	public Type getInfType(){
		return type;
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
	public void setUserList(List<String> list){
		this.userList = list;
	}
	public List getUserList(){
		return this.userList;
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
    
    public enum Type {

        /**
         * (Default) a normal text message used in email like interface.
         */
    	notification,

        /**
         * Typically short text message used in line-by-line chat interfaces.
         */
    	alert;

        public static Type fromString(String name) {
            try {
                return Type.valueOf(name);
            }
            catch (Exception e) {
                return notification;
            }
        }

    }

}
