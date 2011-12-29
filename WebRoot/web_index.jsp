<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ page import="com.smit.vo.Menu" %>
<%@ page import="org.springframework.beans.factory.BeanFactory" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="com.smit.service.ProductControlService"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>index.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<link rel="stylesheet" type="text/css" href="./css/im.css">
	 <%  BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		List<Menu>  menus = ps.findByType(4);
		pageContext.setAttribute("menus",menus);
		%>
  </head>
  
  <body>
   <div id="mainbox">
   		<div id="left">
   			<div id="top"><img src="./images/im/Arrow-Left.png" id="togglebtn"></div>
   			<div id="menus">
   			<ul id="menu">
   			<c:forEach var="menu" items="${menus}">
   				<li><dl><dt class="one"><a href="${menu.href}" target="main">${menu.menu_name}</a></dt></dl></li>
   			</c:forEach>
   				<!--  <li><dl><dt class="one"><a href="mingwang.jsp" target="main">名站导航</a></dt></dl></li>
   				<li><dl><dt class="one"><a href="lvyou.jsp" target="main">旅游导航</a></dt></dl></li>
   				<li><dl><dt class="one"><a href="shiping.jsp" target="main">视频导航</a></dt></dl></li>
   				<li><dl><dt class="one"><a href="caijing.jsp" target="main">财经导航</a></dt></dl></li>
   				<li><dl><dt class="one"><a href="news.jsp" target="main">新闻导航</a></dt></dl></li>
   				<li><dl><dt class="one"><a href="junshi.jsp" target="main">军事导航</a></dt></dl></li>
   				<li><dl><dt class="one"><a href="yule.jsp" target="main">娱乐导航</a></dt></dl></li>
   				<li><dl><dt class="one"><a href="tiyu.jsp" target="main">体育导航</a></dt></dl></li>
   				<li><dl><dt class="one"><a href="dushu.jsp" target="main">读书导航</a></dt></dl></li>
   				<li><dl><dt class="one"><a href="tuangou.jsp" target="main">团购导航</a></dt></dl></li>
   			-->
   			</ul></div>
   		</div> 
   		<div id="right">
   			<iframe name="main" src="mingwang.jsp" frameborder="0"></iframe>
   		</div>
   </div>
  </body>
</html>
