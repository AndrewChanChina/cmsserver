package com.smit.service.webService;

public class ColumnItem implements IToXML{
	
	public final static String TYPE_LEAF = "leaf";
	public final static String TYPE_NODE = "node";

	private String type;
	private String name;
	private String key;
	
	
	@Override
	public String toXml() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("<item>");
		sb.append("<type>" + type + "</type>");
		sb.append("<name>" + name + "</name>");
		sb.append("<key>" + key + "</key>");
		sb.append("</item>");
		return sb.toString();		
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}

}
