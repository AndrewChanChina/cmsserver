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
    
    <title>My JSP 'subscriber.jsp' starting page</title>
    
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
    			<div id="main_top"><h2>Subscribers</h2></div>
    			<div id="content">
    				<div id="content_left">
    					<ul style="list-style: none;">
    						<li><h3>Realtime Push</h3>
    							<p>Give smit your feed urls and new entries will be pushed to you.via 
    							<a href="http://superfeedr.com/documentation#pubsubhubbub">PubSubHubbub</a>or
    							<a href="http://superfeedr.com/documentation#xmpp_pubsub">XMPP PubSub</a></p>
    						</li>
    						<li>
                				<h3>Normalization</h3>
               					 <p>No more nightmares with gazillions of formats. Superfeedr will send data in strict <a href="http://en.wikipedia.org/wiki/Atom_(standard)">ATOM format</a> or <a href="http://superfeedr.com/documentation#json_schema">JSON</a>, regardless of the original feed format.</p>
           					</li>
           					<li>
								<h3>Cost Saving</h3> 
								<p>We will <a href="http://superfeedr.com/cost">match or beat the cost</a> of your existing system. No matter what, you can test Superfeedr for free with the 25,000 free notifications that we granted to your account. Use <a href="/orders/new">our simulation tool</a> to assess your monthly cost.</p>
							</li>
							<li>
              					<h3>No More Polling </h3>
                				<p>Don't waste your time and resources fetching old data. We implemented <a href="http://code.google.com/p/pubsubhubbub/">PubSubHubbub</a>, <a href="http://rsscloud.org/">RSSCloud</a>, <a href="http://rpc.weblogs.com/changes.xml">changes.xml</a> and host a very big <a href="http://blog.superfeedr.com/XML-RPC/Ping/PubSubHubbub/XMPP/Real-time/XMLRPC-Ping-to-PubSubHubbub/">XML-RPC ping server</a>. We still poll the feeds, as often as every minute for high frequency feeds and we poll each feed at least every 15 minutes so you never get update later than that.</p>
            				</li>
            				<li>
              					<h3>Cloud Infrastructure</h3>
                				<p>Superfeedr technology is built to scale as demand increases in order to maintain service level guarantees.</p>
            				</li>
    					</ul>
    				</div>
    				<div id="content_right">
    					<div id="subscribe">
    						<h3>Free SignUp</h3>
    						<form action="pubsub_regist.do" method="post">
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
    							<p><input type="hidden" name="role" value="subscriber">
    								<input name="commit" type="submit" value="Sign up">
    							</p>
    						</form>
    					</div>
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
    	<span id="copyright">@2009-2011 Smit - Realtime feeds and PubSubHubbub provider.</span>
    </div>
  </body>
</html>
