package com.smit.service.webService;

import java.util.List;

public class XmlWrap implements IToXML{
	
	List<IToXML> items;	
	
	public List<IToXML> getItems() {
		return items;
	}
	public void setItems(List<IToXML> items) {
		this.items = items;
	}
	@Override
	public String toXml() throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<items>");
		for(IToXML i : items){
			sb.append(i.toXml());
		}
		sb.append("</items>");
		sb.append("</xml>");
		return sb.toString();
	}

}
