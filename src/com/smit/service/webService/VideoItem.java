package com.smit.service.webService;

import java.util.List;

public class VideoItem implements IToXML {
	
	private String name;
	private String description;
	private List<String> pictures;
	private List<String> urls;
	
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
	
	public String toXml(){
		StringBuilder sb = new StringBuilder();
		sb.append("<item>");
		sb.append("<name>" + name + "</name>");
		
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
		
		sb.append("<description><![CDATA[" + description + "]]></description>");
		sb.append("</item>");
		return sb.toString();
	}
}
