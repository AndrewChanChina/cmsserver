package com.smit.web.apk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.smit.vo.apk.ApkInfo;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

public class ApkInfoXmlParse {
	public static final String ITEM = "item";
	Document mDoc = null;
	Context context;
	String data;
	String mXmlPath = null;

	public ApkInfoXmlParse(Context context, String xmlPath) {
		mXmlPath = xmlPath;
		this.context = context;
	}

	public ApkInfoXmlParse(String d) {
		data = d;
	}

	public static String apkInfoToXmlItem(ApkInfo a) {

		StringBuilder sb = new StringBuilder();
		sb.append("<").append(ITEM).append(">");
		appendItem(sb, ApkInfo.APP_NAME, a.getAppName());
		appendItem(sb, ApkInfo.APP_PACKAGE_NAME, a.getPackageName());
		appendItem(sb, ApkInfo.APP_OPERATION, a.getOperation());
		appendItem(sb, ApkInfo.APP_APK_URL, a.getApkUrl());
		sb.append("</").append(ITEM).append(">");
		return sb.toString();

	}

	public static String allApkInfoToXmlItems(List<ApkInfo> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("<").append("root").append(">");
		for (ApkInfo a : list) {
			sb.append(apkInfoToXmlItem(a));
		}
		sb.append("</").append("root").append(">");
		return sb.toString();
	}

	private static StringBuilder appendItem(StringBuilder sb, String name,
			String value) {
		sb.append("<").append(name).append(">").append(value).append("</")
				.append(name).append(">");
		return sb;
	}
	
	private Boolean init(){
		if(mDoc != null){
			return true;
		}
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return false;
		}
		InputStream inputStream = null;
		try {
		
			inputStream = new ByteArrayInputStream(data.getBytes("UTF-8")); 
			
		} catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return false;
		}catch (IllegalStateException e) {
			e.printStackTrace();
			return false;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return false;
		}

		
		try {
			mDoc = db.parse(inputStream);
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		mDoc.getDocumentElement().normalize();
		return true;		
	}
	
	public List<ApkInfo> parse(){
		if(init()==false){
			return null;
		}
		NodeList nodeList = mDoc.getElementsByTagName(ITEM);
		if (nodeList != null) {
			List<ApkInfo> appInfoList = new ArrayList<ApkInfo>(nodeList.getLength());
			for(int i = 0; i < nodeList.getLength(); i++){
				ApkInfo a = new ApkInfo();
				Node node = nodeList.item(i);
				NodeList nodeList2 = node.getChildNodes();
				for(int j = 0; j<nodeList2.getLength(); j++){
					Node node2 = nodeList2.item(j);
					if(ApkInfo.APP_NAME.equals(node2.getNodeName()) ){
						a.setAppName(node2.getTextContent());
					}else if(ApkInfo.APP_PACKAGE_NAME.equals(node2.getNodeName())){
						a.setPackageName(node2.getTextContent());
					}else if(ApkInfo.APP_OPERATION.equals(node2.getNodeName())){
						a.setOperation(node2.getTextContent());
					}else if(ApkInfo.APP_APK_URL.equals(node2.getNodeName())){
						a.setApkUrl(node2.getTextContent());
					}				
				}
				appInfoList.add(a);
			}
			return appInfoList;
		}
		
		return null;
	}
	
	public static class Operation{
		public final static int NONE = -1;
		public final static int DELETE = 0;
		public final static int ADD = 1;
		public final static int UPDATE = 2;
		public final static int LIST = 3;
		public final static int RINGING = 4;
		public final static int DELALL = 5;
		
		public final static String s_add = "add";
		public final static String s_del = "delete";
		public final static String s_update = "update";
		public final static String s_list = "list";
		public final static String s_ring = "ring";
		public final static String s_delall = "del";
		
		public static int mapString(String op){
			int data = NONE;
			if(s_add.equals(op)){
				data = ADD;
			}else if(s_del.equals(op)){
				data = DELETE;
			}else if(s_update.equals(op)){
				data = UPDATE;
			}else if(s_list.equals(op)){
				data = LIST;
			}
			return data;
		}
	}

}
