<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shangwu_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./css/im.css">
  </head>
  
  <body>
    	<div id="mainbox">
    	<ul id="business_box">
    	<% String type = request.getParameter("type");
    		   if(type.equals("kehuan")){
    	%>
    	<li><a href="#"><img src="./images/im/kehuan1.jpg" width="80" height="80"><span>电影名称</span></a></li>
    	<li><a href="#"><img src="./images/im/kehuan2.jpg" width="80" height="80"><span>电影名称</span></a></li>
    	<li><a href="#"><img src="./images/im/kehuan3.jpg" width="80" height="80"><span>电影名称</span></a></li>
    	<li><a href="#"><img src="./images/im/kehuan4.jpg" width="80" height="80"><span>电影名称</span></a></li>
    	<li><a href="#"><img src="./images/im/kehuan5.jpg" width="80" height="80"><span>电影名称</span></a></li>
    	<li><a href="#"><img src="./images/im/kehuan6.jpg" width="80" height="80"><span>电影名称</span></a></li>
    	<li><a href="#"><img src="./images/im/kehuan7.jpg" width="80" height="80"><span>电影名称</span></a></li>
    	<li><a href="#"><img src="./images/im/kehuan8.jpg" width="80" height="80"><span>电影名称</span></a></li>
    	<li><a href="#"><img src="./images/im/kehuan5.jpg" width="80" height="80"><span>电影名称</span></a></li>
    	<%
    	}else if(type.equals("email")){
    	 %>
    	<li><a href="#"><img src="../images/liaotian1.jpg" width="80" height="80"><span>QQ邮箱</span></a></li>
    	<li><a href="#"><img src="../images/liaotian1.jpg" width="80" height="80"><span>163邮箱</span></a></li>
    	<li><a href="#"><img src="../images/liaotian1.jpg" width="80" height="80"><span>126邮箱</span></a></li>
    	<li><a href="#"><img src="../images/liaotian1.jpg" width="80" height="80"><span>139邮箱</span></a></li>
    	<%
    	}else if(type.equals("office")){
    	 %>
    	 <li><a href="#"><img src="../images/liaotian1.jpg" width="80" height="80"><span>Excel</span></a></li>
    	 <li><a href="#"><img src="../images/liaotian1.jpg" width="80" height="80"><span>PowerPoint</span></a></li>
    	 <li><a href="#"><img src="../images/liaotian1.jpg" width="80" height="80"><span>Word</span></a></li>
    	 <li><a href="#"><img src="../images/liaotian1.jpg" width="80" height="80"><span>PDF</span></a></li>
    	 <li><a href="#"><img src="../images/liaotian1.jpg" width="80" height="80"><span>计算器</span></a></li>
    	 <%
    	 }else if(type.equals("cidian")){
    	  %>
    	  <li><a href="#"><img src="../images/liaotian1.jpg" width="80" height="80"><span>有道翻译</span></a></li>
    	  <li><a href="#"><img src="../images/liaotian1.jpg" width="80" height="80"><span>金山词霸</span></a></li>
    	  <li><a href="#"><img src="../images/liaotian1.jpg" width="80" height="80"><span>拼音大师</span></a></li>
    	  <%}else if(type.equals("")){ %>
    	  <li><a href="#"><img src="../images/liaotian1.jpg" width="80" height="80"><span>QQ</span></a></li>
    	<%} %>
    	</ul>
    	</div>
  </body>
</html>
