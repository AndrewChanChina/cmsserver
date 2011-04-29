<%@page import="org.hibernate.impl.SessionFactoryImpl"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
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
<title>jeecms-left</title>
<link href="./css/admin.css" rel="stylesheet" type="text/css"/>
<link href="./css/theme.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<script src="./js/fckeditor.js" type="text/javascript"></script>
<script src="./js/WdatePicker.js" type="text/javascript"></script>
<script src="./js/jquery.js" type="text/javascript"></script>
<script src="./js/jquery.ext.js" type="text/javascript"></script>
<script src="./js/pony.js" type="text/javascript"></script>
<script src="./js/admin.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	Cms.lmenu('lmenu');
});
</script>
</head>
<body class="lbody">


<ul id="lmenu">
<% 
	String opCode= (String)request.getParameter("class");
	if(opCode.equalsIgnoreCase("sysInfoAndLog"))
	{
%>
		<li><a href="showSysInfo.do" target="rightFrame">基本信息维护</a></li>
		<li><a href="showLogInfo.do" target="rightFrame">日志</a></li>
<%	

	}else if(opCode.equalsIgnoreCase("user")){
%>
		<li><a href="listuser.do" target="rightFrame">用户管理</a></li>
		<li><a href="grouplist.do" target="rightFrame">分组管理</a></li>
		<li><a href="listpurview.do" target="rightFrame">权限管理</a></li>
<%	

	}else if(opCode.equalsIgnoreCase("sysInfoAndLog")){
%>
		<li><a href="listuser.do" target="rightFrame">用户管理</a></li>
		<li><a href="grouplist.do" target="rightFrame">分组管理</a></li>
		<li><a href="listpurview.do" target="rightFrame">权限管理</a></li>
<%	
	}else if(opCode.equalsIgnoreCase("log")){
%>
		<li><a href="showBaseLog.do?class=base" target="rightFrame">基本信息</a></li>
		<li><a href="showBaseLog.do?class=detail" target="rightFrame">细分状态</a></li>
		<li><a href="showBaseLog.do?class=fileupload" target="rightFrame">log上传</a></li>
<%
	}else if(opCode.equalsIgnoreCase("auth")){
 %>
 		<li><a href="" target="rightFrame">请求授权</a></li>
 		<li><a href="" target="rightFrame">激活成功</a></li>
 <%
 	}else if(opCode.equalsIgnoreCase("content")){
%>
      	 <li><a href="content.do?op=add" target="rightFrame">发布内容</a></li>
         <li><a href="content.do?op=list" target="rightFrame">内容管理</a></li>
      
<%
   }else if(opCode.equalsIgnoreCase("upload")){
 %>
 		<li><a href="uploadfile.do" target="rightFrame">上传资源</a></li>
        <li><a href="upload.do?op=list" target="rightFrame">资源管理</a></li>
  <%
  }
 %>
</ul>
</body>
</html>