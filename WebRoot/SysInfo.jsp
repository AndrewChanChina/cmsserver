<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page contentType= "text/html;charset=GB2312" language= "java"%> 
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'SysInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <html:form action="addSysInfo.do">
    	Add System Config Info Key: <html:text property="sysInfoKey"></html:text></br>
    	Add System Config Info Value:<html:text property="sysInfoValue"></html:text></br>
    	<html:submit value= "Add Key-Value pair to system info config"/> 
    </html:form>
  </body>
</html>
