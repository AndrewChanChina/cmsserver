<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/xml;charset=UTF-8" language="java"%>
<%@ page import="com.smit.service.webService.IToXML" %>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="tiles" %>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

IToXML xml = (IToXML)request.getAttribute("xmlObject");
if(xml != null){
	out.print(xml.toXml());
}else{
	out.print("<xml>");	
	out.print("<ErrorData>" + pageContext.getErrorData() + "</ErrorData>");
	out.print("<exception>" + pageContext.getException() + "</exception>");
	out.print("</xml>");
}

%>
