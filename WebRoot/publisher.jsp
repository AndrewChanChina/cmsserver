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
    
    <title>My JSP 'publisher.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./css/pubsub.css">
  </head>
  
  <body>
    <div id="bg">
    	<div id="wrap">
    			<div id="logo">
    	   		<h1><a href="/" class="home" title="Smit Pubsubhubbub provider">
    	   			<img alt="Smit Pubsubhubbub provider" src="./images/pubsub/superfeedr_complete.png">
    	   		</a></h1>
    	   	</div>
    		<div id="login_button">
    			<a id="loginbutton" href="#">Log in</a>
    			<div id="login_menu">
    				<form action="/session" method="post">
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
    			<li><a href="/publish">发布者</a><li>
    			<li><a href="/subscribe">订阅者</a></li>
    			<li><a href="/des">说明</a></li>
    			<li><a href="/about">关于</a></li>
    		</ul>
    		<div class="clear"></div>
    		<div id="main_bg">
    			<div id="main_top"><h2>Publisher</h2></div>
    			<div id="content">
    				<div class="flash flash_notice"></div>
    				<div id="content_left">
    					<div id="free_hub">
    						<h3 style="color: black;font-size: 22px;margin: 30px 0 10px;">Hosted hub</h3>
    						<h4 style="font-size: 1.2em;line-height: 1.2em;text-decoration: underline;">Free</h4>
    						<ul>
    							<li><strong>Realtime feeds</strong>: 100% PubSubHubbub compliant</li>
    							<li><strong>Subscriber Export</strong>: your data.Take it any time</li>
    							<li><strong>Analytics</strong>: numbers of subscribers.numbers of subscriptions</li>
    						</ul>
    					</div>
    					<div id="pro_hub">
    						<h3 style="color: black;font-size: 22px;margin: 30px 0 10px;">Pro Hub</h3>
    						<h4 style="font-size: 1.2em;line-height: 1.2em;text-decoration: underline;">$200 monthly</h4>
    						<ul>
    							<li><strong>Custom CNAME</strong>: use your own subdomain</li>
    							<li><strong>Custom Landing Page</strong>: redirect your hub landing page to your site.</li>
    							<li><strong>Subscription control</strong>: approve or deny subscriptions</li>
    						</ul>
    					</div>
    				</div>
    				<div id="content_right">
    					<div id="subscribe">
    						<h3>Get your hub now</h3>
    						<form action="/user">
    							<p><label for="Username">Username</label>
    								<input type="text" id="username" name="username">
    							</p>
    							<p><label for="Password">Password</label>
    								<input type="password" id="password" name="password">
    							</p>
    							<p><label for="Confirm Password">Confirm Password</label>
    								<input type="password" id="confirm_password" name="confirm_password">
    							</p>
    							<p><label for="Email">Email</label>
    								<input type="text" id="email" name="email">
    							</p>
    							<p><input name="user[terms_of_service]" type="hidden" value="0">
    								<input type="checkbox" value="1" id="user_terms_of_service" name="user[terms_of_service]">
    								I accept the <a href="">Terms of Service</a>
    							</p>
    							<p><input type="hidden" name="user[role]" value="publisher">
    								<input name="commit" type="submit" value="Sign up">
    							</p>
    						</form>
    					</div>
    				
    				</div>
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
