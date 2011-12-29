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
    <base href="<%=basePath%>">
    
    <title>My JSP 'shangwu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./css/im.css">
	<%  BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		List<Menu>  menus = ps.findByType(6);
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
   			</ul></div>
   		</div> 
   		<div id="right">
   			<iframe name="main" src="./im/shangwu_list.jsp?type=qq" frameborder="0"></iframe>
   		</div>
   </div>
  </body>
</html>
