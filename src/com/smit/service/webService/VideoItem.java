package com.smit.service.webService;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class VideoItem implements IToXML {
	
	private String name;//title
	private String description;//description
	private List<String> pictures;//img
	private List<String> urls;//url
	private String time;//播放时长
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getPictures() {
		return pictures;
	}
	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}
	public List<String> getUrls() {
		return urls;
	}
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String toXml() throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("<item>");
		sb.append("<name>" +"<![CDATA["+ name +"]]>"+ "</name>");
		
		sb.append("<pictures>");
		if( pictures != null){
			for(String s : pictures){
				sb.append("<picture>" + s + "</picture>");
			}			
		}
		sb.append("</pictures>");
		
		sb.append("<urls>");
		if( urls != null){
			for(String s : urls){
				sb.append("<url>" + s + "</url>");
			}
		}
		sb.append("</urls>");
		
		sb.append("<description><![CDATA[" + getDes(description) + "]]></description>");
		sb.append("<time>" + time + "</time>");
		sb.append("</item>");
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	public String getDes(String s) throws Exception{
		Document doc = DocumentHelper.parseText("<root>" + description +"</root>");
		Element root = doc.getRootElement();
		List<Element> pEles = root.elements("p");
		String des = null;
		for(Element e:pEles){
			String text = e.getTextTrim();
			if(!"".equals(text)&&null!=text){
				des = text;
			}
		}
		return des;
	}
}
