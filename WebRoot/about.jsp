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
    
    <title>My JSP 'about.jsp' starting page</title>
    
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
    			<div id="main_top"><h2>About Us</h2></div>
    			<div id="content">
    				<div id="content_left">
    					<form action="" method="post">
    						<h2 style="color: #333;font-size: 160%;">Contact Form</h2>
    						<ul>
    							<li id="fo2li1" class="focused">
    								<label for="Field1" id="title1" class="desc" style="color: black;font-size: 95%;">Message<span id="req_1" class="req" style="color: red !important;">*</span></label>
    								<div>
									<textarea id="Field1" name="Field1" class="field textarea medium" spellcheck="true" rows="10" cols="50" tabindex="1" onkeyup="handleInput(this); " onchange="handleInput(this);" required=""></textarea>
									</div>
									<p class="instruct" id="instruct1"><small>This field is required.</small></p>
    							</li>
    							<li id="fo2li2" class="">
    								<label class="desc" id="title2" for="Field2">Email Address</label>
    								<div>
									<input id="Field2" name="Field2" type="email" spellcheck="false" class="field text large" value="" maxlength="255" tabindex="2" onkeyup="handleInput(this);" onchange="handleInput(this);"> 
									</div>
									<p class="instruct" id="instruct2"><small>We won't share this with strangers.</small></p>
    							</li>
    							<li><div>
								<input type="hidden" name="currentPage" id="currentPage" value="JYRd4WNSh3DIb5IZKDJapYxFVhqvrSTPIblyDRoC50A=">
								<input id="saveForm" name="saveForm" class="btTxt submit" type="submit" value="Send" onmousedown="doSubmitEvents();" tabindex="21"></div>
								</li>
    						</ul>
						</form>
    				</div>
    				<div id="content_right">
    					<h2 id="us">Team</h2>
    					<p>
        				We're a bunch of 20 and 30-something hackers who think real-time technologies are about to change the web. We've been financed by <a href="http://blogmaverick.com/">Mark Cuban</a> and <a href="http://betaworks.com/">Betaworks</a> and we're based in San Francisco, California!
   		 				</p>
   		 				<h2 id="contact">Get in touch</h2>
   		 				<ul>
        				<li><strong>Email</strong>: info@superfeedr.com</li>
        				<li><strong>Phone</strong>: +1 (415) 830 6574</li>
        				<li><strong>Twitter</strong>: <a href="http://twitter.com/superfeedr">@superfeedr</a></li>
    					</ul>
    				</div>
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
