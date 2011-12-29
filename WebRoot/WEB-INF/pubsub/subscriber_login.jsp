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
    
    <title>My JSP 'subscriber_login.jsp' starting page</title>
    
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
    			<!--  <li><a href="/publish">发布者</a><li>
    			<li><a href="/subscribe">订阅者</a></li>-->
    			<li><a href="doc.do">说明</a></li>
    			<li><a href="about.do">关于</a></li>
    		</ul>
    		<div class="clear"></div>
    		<div id="main_bg">
    			<div id="main_top"><h2>Toolbox</h2></div>
    			<div id="content">
    				<p>Superfeedr is an <em>API based service</em>, which means that <strong>all</strong> the help you can find with these tools is available in the API (and more!). We strongly encourage you to look into the <a href="/documentation">documentation</a> to build your own tools.</p>
    				<div style="float: left; width: 49%; ">
    <h3><a href="show_sub.do">PubSubHubbub Console</a></h3>
    <p>
        <em>Subscribe</em> and <em>unsubscribe</em> to any feed. This tool is very convenient to debug your <a href="/documentation#pubsubhubbub">PubSubHubbub</a> callback url.
    </p>
    <h3><a href="/users/test889/feeds/xmpp_console">XMPP Console</a></h3>
    <p>
        <em>Subscribe</em> and <em>unsubscribe</em> to any feed and see what are the calls being performed to our <a href="/documentation#xmpp_pubsub">XMPP endpoint</a>. You can also <em>list existing subscriptions</em> to your endpoints. You will also be able to <em>see incoming notifications</em>.
    </p>
</div>
<div style="float: right; width: 49%;">
    <h3><a href="/feeds/lookup">Feed Status Retrieval</a></h3>
    <p>
        Retrieve the <a href="/documentation#pubsubhubbub_retrieve">status</a> of any feed to see their <em>last HTTP status</em>, <em>last fetch time</em>, period... This data is also available upon notification. 
    </p>
    
    <h3><a href="/users/test889/feeds/export">Export Subscribed feeds</a></h3>
    <p>
        Export of your feed subscriptions. Export is done in pages of 100 subscriptions. Add a <code>?page=2</code> for the second page, and further.
    </p>
</div><div class="clear"></div>
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
