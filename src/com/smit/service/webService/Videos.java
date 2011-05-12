package com.smit.service.webService;

import java.util.List;

public class Videos {
	List<VideoItem> items;

	public List<VideoItem> getItems() {
		return items;
	}

	public void setItems(List<VideoItem> items) {
		this.items = items;
	}
	
	public String toXml(){
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<items>");
		for(VideoItem i : items){
			sb.append(i.toXml());
		}
		sb.append("</items>");
		sb.append("</xml>");
		return sb.toString();
	}

}
