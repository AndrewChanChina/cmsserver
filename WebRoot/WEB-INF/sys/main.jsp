<%@ page contentType= "text/html;charset=UTF-8" language= "java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.smit.dao.*"%>
<%@ page import="com.smit.vo.*"%>
<%@ page import="org.hibernate.SessionFactory"%>
<%@ page import="java.util.HashMap"%>

<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>

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
	String classification = (String)request.getParameter("class");
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
		else if(classification.equalsIgnoreCase("user"))
		{
	%>
			<frame src="showLeftFrame.do?class=user" name="leftFrame" noresize="noresize" id="leftFrame" />
			<frame src="listuser.do" name="rightFrame" id="rightFrame" />
	<%
	}else if(classification.equalsIgnoreCase("log")){
	%>
			<frame src="showLeftFrame.do?class=log" name="leftFrame" noresize="noresize" id="leftFrame" />
			<frame src="showRightFrame.do?class=log" name="rightFrame" id="rightFrame" />
	<%
		}else if(classification.equalsIgnoreCase("auth")){
	 %>
	 		<frame src="showLeftFrame.do?class=auth" name="leftFrame" noresize="noresize" id="leftFrame" />
			<frame src="showRightFrame.do?class=auth" name="rightFrame" id="rightFrame" />
	<%
		}else if(classification.equalsIgnoreCase("content")){
	%>
		<frame src="showLeftFrame.do?class=content" name="leftFrame" noresize="noresize" id="leftFrame" />
		<frame src="showRightFrame.do?class=content" name="rightFrame" id="rightFrame" />
   <%
	}else if(classification.equalsIgnoreCase("upload")) {
	%>
		<frame src="showLeftFrame.do?class=upload" name="leftFrame" noresize="noresize" id="leftFrame" />
		<frame src="showRightFrame.do?class=upload" name="rightFrame" id="rightFrame" />
   <%
   }
   %>

</frameset>
<noframes><body></body></noframes>
</html>
