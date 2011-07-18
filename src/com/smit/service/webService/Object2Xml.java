package com.smit.service.webService;

import java.util.List;

public class Object2Xml implements IToXML {

	private List<IToXML> toXMLs;

	public List<IToXML> getToXMLs() {
		return toXMLs;
	}
	public void setToXMLs(List<IToXML> toXMLs) {
		this.toXMLs = toXMLs;
	}

	@Override
	public String toXml() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<items>");
		for(IToXML i : toXMLs){
			sb.append(i.toXml());
		}
		sb.append("</items>");
		sb.append("</xml>");
		return sb.toString();
	}

}
