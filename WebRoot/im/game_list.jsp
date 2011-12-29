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
    		   if(type.equals("local")){
    	%>
    	<li><a href="#"><img src="./images/im/jiaose1.jpg" width="80" height="80"><span>游戏1</span></a></li>
    	<li><a href="#"><img src="./images/im/jiaose2.png" width="80" height="80"><span>游戏2</span></a></li>
    	<li><a href="#"><img src="./images/im/jiaose3.png" width="80" height="80"><span>游戏3</span></a></li>
    	<li><a href="#"><img src="./images/im/jiaose4.png" width="80" height="80"><span>游戏4</span></a></li>
    	<li><a href="#"><img src="./images/im/jiaose5.png" width="80" height="80"><span>游戏5</span></a></li>
    	<li><a href="#"><img src="./images/im/jiaose6.png" width="80" height="80"><span>游戏6</span></a></li>
    	<li><a href="#"><img src="./images/im/jiaose7.png" width="80" height="80"><span>游戏7</span></a></li>
    	<li><a href="#"><img src="./images/im/jiaose8.jpg" width="80" height="80"><span>游戏8</span></a></li>
    	<li><a href="#"><img src="./images/im/jiaose9.jpg" width="80" height="80"><span>游戏9</span></a></li>
    	<%
    	}else if(type.equals("net")){
    	 %>
    	<li><a href="#"><img src="./images/im/jiaose1.jpg" width="80" height="80"><span>网游1</span></a></li>
    	<li><a href="#"><img src="./images/im/jiaose2.png" width="80" height="80"><span>网游2</span></a></li>
    	<li><a href="#"><img src="./images/im/jiaose3.png" width="80" height="80"><span>网游3</span></a></li>
    	<li><a href="#"><img src="./images/im/jiaose4.png" width="80" height="80"><span>网游4</span></a></li>
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
