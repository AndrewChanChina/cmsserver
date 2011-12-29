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
    
    <title>My JSP 'subscribe.jsp' starting page</title>
    
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
    					<li><a href="dashboard.do">Dashboard</a></li>
    					<li><a href="">Analytics</a></li>
    					<li><a href="pubsub_account.do">Account & Settings</a></li>
    					<li><a href="pubsub_logout.do">Log out</a></li>
    				</ul>
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
    			<div id="main_top"><h2>Debug PubSubHubbub Subscriptions</h2></div>
    			<div id="content">
    				<p>This interface uses the <a href="/documentation#pubsubhubbub">PubSubHubbub Procotol</a> to subscribe and unsubscribe. It should help you setup your callback url.
					</p>
					<h3>Request</h3>
					<form action="subscribe.do" method="post">
					<p><label for="mode">Mode:</label> 
    					<select id="mode" style="display: inline;" name="hub.mode"> 
       						 <option value="subscribe" selected="selected">Subscribe</option> 
        					<option value="unsubscribe">Unsubscribe</option> 
    					</select></p>
    				<p><label for="verify">Verify: </label> 
  					 	<select id="mode" style="display: inline;" name="hub.verify"> 
       						 <option value="sync" selected="selected">Sync</option> 
        					<option value="async">Async</option> 
    					</select>
  					 </p>
    				<p><label for="topic">Topic: <em>(the feed URL)</em></label> 
  					 	 <input type="text" id="topic" size="64" value="" style="display: inline;" name="hub.topic"></p>
    				<p><label for="callback">Callback: <em>(the subscriber URL)</em></label> 
    					<input type="text" id="callback" size="64" value="" style="display: inline;" name="hub.callback"></p>
    				<p>This tool only uses <em>synchronous</em> verification, as it makes it much easier to debug your callback. You can obviously use <em>asynchronous</em> when using the API.
					</p>
					<p><input type="submit" value="Submit" id="pubsubhubbub"></p>
					</form>
					<h3>Result</h3>
					<p id="result"><em>Please, perform your request.</em></p>
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
