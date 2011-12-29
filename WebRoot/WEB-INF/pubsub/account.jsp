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
    
    <title>My JSP 'account.jsp' starting page</title>
    
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
    			<a id="loginbutton">
    			<logic:present name="username"><bean:write name="username"/></logic:present>
    			<logic:notPresent name="username">Log in</logic:notPresent></a>
    			<div id="login_menu" style="display: none;">
    				<logic:present name="username">
    					<logic:equal name="role" value="publisher">
    					    <ul><li><a>Settings</a></li>
    					    	<li><a>Live Hub</a></li>
    					    	<li><a>Analytics</a></li>
    					    	<li><a href="pubsub_account.do">Account&Settings</a></li>
    					    	<li><a href="pubsub_logout.do">Log out</a></li>
    					    </ul>
    					</logic:equal>
    					<logic:equal name="role" value="subscriber">
    						<ul><li><a href="dashboard.do">Dashboard</a></li>
    					    	<li><a>Analytics</a></li>
    					    	<li><a href="pubsub_account.do">Account&Settings</a></li>
    					    	<li><a href="pubsub_logout.do">Log out</a></li>
    					    </ul>
    					</logic:equal>
    				</logic:present>
    			<logic:notPresent name="username">
    				<form action="pubsub_login.do" method="post">
    					<label for="login">Login</label>
    					<input type="text" id="login" name="login">
    					<label for="password">Password</label>
    					<input type="password" id="password" name="password">
    					<input name="commit" type="submit" value="Log in" id="submit">
    					<small><a href="forget_password">Forgot login or password?</a></small>
    				</form>
    			</logic:notPresent>
    			</div>
    		</div>
    		<ul id="navigation">
    			<logic:notPresent name="username">
    			<li><a href="publisher.do">发布者</a><li>
    			<li><a href="subscriber.do">订阅者</a></li>
    			</logic:notPresent>
    			<li><a href="doc.do">说明</a></li>
    			<li><a href="about.do">关于</a></li>
    		</ul>
    		<div class="clear"></div>
    		<div id="main_bg">
    		<div id="main_top"><h2>Settings for Cluo</h2></div>
    		<div id="content">
    			<p><a href="/change_password">Change Password</a> | <a href="/users/cluo/feeds/delete" onclick="if (confirm('Are you sure? There is absolutely no way back.')) { var f = document.createElement('form'); f.style.display = 'none'; this.parentNode.appendChild(f); f.method = 'POST'; f.action = this.href;var m = document.createElement('input'); m.setAttribute('type', 'hidden'); m.setAttribute('name', '_method'); m.setAttribute('value', 'delete'); f.appendChild(m);f.submit(); };return false;">Unsubscribe all feeds</a></p>
    			<p><strong>Email:</strong> luocheng8888@126.com</p>
    			<p><em>No settings available for you.</em></p>
    		</div>
    		<div id="footer"><ul id="footer_navigation">
    					<li><a href="/">Home</a></li>
    					<li><a href="/documentation">Documentation</a></li>
    					<li><a href="http://groups.google.com/group/superfeedr/web/frequently-asked-questions">Faq</a></li>
    					<li><a href="/terms">Terms</a></li>
    					<li><a href="/privacy">Privacy</a></li>
    					<li><a href="http://groups.google.com/group/superfeedr">Community</a></li>
    					<li><a href="http://github.com/superfeedr">Code</a></li>
    					<li><a href="http://blog.superfeedr.com">Blog</a></li>
    					<li><a href="/about">About</a></li>
    				</ul></div>
    		</div>
    	</div>
    </div>
  </body>
</html>
