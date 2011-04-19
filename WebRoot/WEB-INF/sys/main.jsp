
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page contentType= "text/html;charset=UTF-8" language= "java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.smit.dao.*"%>
<%@ page import="com.smit.vo.*"%>
<%@ page import="org.hibernate.SessionFactory"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>jeecms-main</title>
</head>
<% 
	//HttpServletRequest request = ServletActionContext.getRequest(); 
	String classification = (String)request.getAttribute("class");
	System.out.println(classification);
 %>
<frameset cols="240,*" frameborder="0" border="0" framespacing="0">
	<%
		if(classification.equalsIgnoreCase("column"))
		{
	%>
			<frame src="showLeftFrame.do?class=column" name="leftFrame" noresize="noresize" id="leftFrame" />
			<frame src="showRightFrame.do?class=column" name="rightFrame" id="rightFrame" />
	<% 
		}
		else if(classification.equalsIgnoreCase("sysInfoAndLog"))
		{
	%>
			<frame src="showLeftFrame.do?class=sysInfoAndLog" name="leftFrame" noresize="noresize" id="leftFrame" />
			<frame src="showRightFrame.do?class=sysInfoAndLog" name="rightFrame" id="rightFrame" />
	<%
		}
	%>

</frameset>
<noframes><body></body></noframes>
</html>
