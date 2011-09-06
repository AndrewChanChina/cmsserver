<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reg_success.jsp' starting page</title>
    
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
    			<div id="main_top"><h2>Welcome</h2></div>
    			<div id="content">
    				<div class="flash flash_notice">
                    <div>
                        Thanks for signing up!  We're sending you an email with your activation code.
                        <span id="flash-close">X</span>
                    </div>
                </div>
                 <h2>We're sending you an email with your activation code.</h2>
                    <p>Once activated, <strong>we will assist you</strong> while setting up your hub.</p>
                    <p>However, if you want, you can already start implementing the PubSubHubbub protocol (publisher part) on your end :</p>
                    <ul>
   					 	<li>If you use a common Content Management System (such as Wordpress, Movabletype or Drupal...), please start by <a href="http://code.google.com/p/pubsubhubbub/wiki/PublisherClients#Plugins_for_different_CMS_systems">installing the right plugin</a>.</li> 
    					<li>If you have something custom, check <a href="http://code.google.com/p/pubsubhubbub/wiki/PublisherClients#Clients_for_different_languages">these libraries</a> in the most popular languages (Ruby, Perl, Python, PHP, Java...).</li>
					</ul>
					<div class="clear"></div>
    			</div>
    			<div id="footer"></div>
    		</div>
    	</div>
    	<div id="log" style="display: none;"></div>
    	<span id="copyright">© 2009-2011 Smit - Realtime feeds and PubSubHubbub provider.</span>
    </div>
  </body>
</html>
