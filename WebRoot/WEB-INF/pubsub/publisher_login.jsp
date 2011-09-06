<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'publisher_login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="./js/jquery.js"></script>
	<script type="text/javascript" src="./js/pubsub.js"></script>
	<link rel="stylesheet" type="text/css" href="./css/pubsub.css">
  </head>
  
  <body>
    <div id="bg">
    	<div id="wrap">
    		<div id="logo">
    	   		<h1><a href="main.jsp" class="home" title="Smit Pubsubhubbub provider">
    	   			<img alt="Smit Pubsubhubbub provider" src="./images/pubsub/superfeedr_complete.png">
    	   		</a></h1>
    	   	</div>
    		<div id="login_button">
    			<a id="loginbutton"><bean:write name="username"/></a>
    			<div id="login_menu" style="display: none;">
    				<ul>
    					<li><a href="">Settings</a></li>
    					<li><a href="">Live Hub</a></li>
    					<li><a href="">Analytics</a></li>
    					<li><a href="pubsub_account.do">Account & Settings</a></li>
    					<li><a href="pubsub_logout.do">Log out</a></li>
    				</ul>
    			</div>
    		</div>
    		<ul id="navigation">
    		<!--  
    			<li><a href="/publish">发布者</a><li>
    			<li><a href="/subscribe">订阅者</a></li>-->
    			<li><a href="doc.do">说明</a></li>
    			<li><a href="about.do">关于</a></li>
    		</ul>
    		<div class="clear"></div>
    		<div id="main_bg">
    			<div id="main_top"><h2>Setup your hub</h2></div>
    			<div id="content">
    				<div class="flash flash_error">
    					<div>
    						<logic:present name="list">
    						<logic:iterate id="list" name="list">
    						<bean:write name="list"/>
    						</logic:iterate>
    						</logic:present>
    					</div>
    				</div>
    				<form action="show_pub.do" method="post">
    					<p>
   					 	This wizzard will help you <strong>setup you hub</strong>. Enter one of the feed urls you will be using for your hub. Superfeedr will extract the main parameters to setup your hub. You will also be <em>able to customize these parameters</em>.
						</p>
						<p>
    					<label for="Feed_url_:_">Feed url : </label>
    					<input id="url" name="url" size="64" type="text" style="display: inline;"> &nbsp;
    				    <input name="commit" type="submit" value="Extract" style="display: inline;"> &nbsp;
    				    <a href="/users/test886/hub/new">Skip to advanced</a>
						</p>
    				</form>
    				<div class="clear"></div>
    			</div>
    			<div id="footer">
    				<ul id="footer_navigation">
    					<li><a href="/">Home</a></li>
    					<li><a href="/documentation">Documentation</a></li>
    					<li><a href="http://groups.google.com/group/superfeedr/web/frequently-asked-questions">Faq</a></li>
    					<li><a href="/terms">Terms</a></li>
    					<li><a href="/privacy">Privacy</a></li>
    					<li><a href="http://groups.google.com/group/superfeedr">Community</a></li>
    					<li><a href="http://github.com/superfeedr">Code</a></li>
    					<li><a href="http://blog.superfeedr.com">Blog</a></li>
    					<li><a href="/about">About</a></li>
    				</ul>
    			</div>
    		</div>
    	</div>
    </div>
  </body>
</html>
