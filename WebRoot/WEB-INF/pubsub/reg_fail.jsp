<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
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
    
    <title>My JSP 'reg_fail.jsp' starting page</title>
    
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
    			<a id="loginbutton">Log in</a>
    			<div id="login_menu" style="display: none;">
    				<form action="pubsub_login.do" method="post">
    					<label for="login">Login</label>
    					<input type="text" id="login" name="login">
    					<label for="password">Password</label>
    					<input type="password" id="password" name="password">
    					<input name="commit" type="submit" value="Log in" id="submit">
    					<small><a href="/forget_password">Forgot login or password?</a></small>
    				</form>
    			</div>
    		</div>
    		<ul id="navigation">
    			<li><a href="publisher.do">发布者</a><li>
    			<li><a href="subscriber.do">订阅者</a></li>
    			<li><a href="doc.do">说明</a></li>
    			<li><a href="about.do">关于</a></li>
    		</ul>
    		<div class="clear"></div>
    		<div id="main_bg">
    			<div id="main_top"><h2>Join the Movement!</h2></div>
    			<div id="content">
    				<div class="errorExp" id="errorExp">
    					<h2>Your info couldn't be saved</h2>
    					<p>Please fix the following: </p>
    					<ul>
    					<logic:notEmpty name="list">
    						<logic:iterate id="list" name="list">
    						<li><bean:write name="list"/></li>
    						</logic:iterate>
    					</logic:notEmpty>
    					<!--  <li>Email has already been taken</li>-->
    					</ul>
    				</div>
    				<form action="pubsub_regist.do" method="post">
    					<p><label for="Username">Username</label><br><input type="text" name="username"></p>
    					<p><label for="Email">Email</label><br><input type="text" name="email"></p>
    					<p></p>
    					<p><label for="Password">Password</label><br><input type="text" name="password"></p>
    					<p><label for="password_confirm">Confirm Password</label><input type="text" name="password_confirm"></p>
    					<p><input type="hidden" value="0" name="termsService">
    						<input type="checkbox" value="1" name="termsService">I accept the<a href="terms">Terms of Service</a>
    					</p>
    					<p><input type="hidden" value="publisher" name="role">
    						<input type="submit" name="commit" value="Sign up">
    					</p>
    				</form>
    				
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
    	<div id="log" style="display: none;"></div>
    	<span id="copyright">@2009-2011 Smit - Realtime feeds and PubSubHubbub provider.</span>
    </div>
  </body>
</html>
