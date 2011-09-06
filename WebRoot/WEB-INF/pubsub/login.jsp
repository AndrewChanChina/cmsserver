<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
    			<div id="main_top"><h2>Log In</h2></div>
    			<div id="content">
    				<div class="flash flash_error"><div>
                        Couldn't log you in as 'test8898'
                        <span id="flash-close">X</span></div>
                	</div>
    				<form action="pubsub_login.do" method="post">
    					<p><label for="Login">Login</label><br><input type="text" name="login"></p>
    					<p><label for="password">Password</label><input type="password" name="password"><a href="/forgot_password">Forgot Password?</a></p>
    					<p><label for="remenber_me">Remenber me</label><input type="checkbox" value="1"></p>
    					<p><input type="submit" name="commit" value="log in"></p>
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
   			<div id="log" style="display: none;"></div>
    	  <span id="copyright">© 2009-2011 Smit - Realtime feeds and PubSubHubbub provider.</span>
   		</div>
  </body>
</html>
