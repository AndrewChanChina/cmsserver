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
    
    <title>My JSP 'top.jsp' starting page</title>
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
    			<logic:present name="username">
    				<bean:write name="username"/>
    			</logic:present>
    			<logic:notPresent name="username">Log in</logic:notPresent>
    			</a>
    			<div id="login_menu" style="display: none;">
    				<logic:present name="username">
    					<logic:equal name="role" value="publisher">
    					    <ul><li><a>Settings</a></li>
    					    	<li><a>Live Hub</a></li>
    					    	<li><a>Analytics</a></li>
    					    	<li><a>Account&Settings</a></li>
    					    	<li><a href="pubsub_logout.do">Log out</a></li>
    					    </ul>
    					</logic:equal>
    					<logic:equal name="role" value="subscriber">
    						<ul><li><a href="dashboard.do">Dashboard</a></li>
    					    	<li><a>Analytics</a></li>
    					    	<li><a>Account&Settings</a></li>
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
    			<div id="main_top">
    				<h2>We push realtime data at scale.</h2>
    				<div id="counter" style="width:427px;">
    					<div id="counter_left"></div>
    					<div id="count" style="width:292px;">
    						<span id="c4">9</span>
    						<span id="c3">469</span>
    						<span id="c2">027</span>
    						<span id="c1">191</span>
    					</div>
    					<div id="counter_right"></div>
    				</div>
    			</div>
    			<div class="grid">
    				<div id="publisher" class="left role"><h3><a href="publisher.do" title="Host your PubSubHubbub hub">Are you a Publisher?</a></h3>
    					<p><a href="publisher.do">If your app publishes feeds, start pushing them too »</a></p>
    				</div>
    				<div id="subscriber" class="right role">
						<h3><a href="subscriber.do" title="Get feeds pushed to you via PubSubHubbub and XMPP">Are you a subscriber?</a></h3>
						<p><a href="subscriber.do">Stop wasting your resources, offload your feed polling »</a></p>
					</div>
					<div class="clear"></div>
					<div class="left_benefits">
						<div class="benefit">
							<h3 id="distribute">Distribute your content</h3>
							<p>Push your content with PubSubHubbub</p>
						</div>
						<div class="benefit">
							<h3 id="control">Control your own hub</h3>
							<p>Customize it, get analytics, export at any time</p>
						</div>
					</div>
					<div class="right_benefits">
						<div class="benefit">
							<h3 id="speed">Speed up your data collection</h3>
							<p>Delegate the fetching to Superfeedr</p>
						</div>
						<div class="benefit">
							<h3 id="track">Track Keywords</h3>
							<p>Normalized content, digest notifications</p>
						</div>
					</div>
    			</div>
    			<div id="bottom_grid" class="grid">
    				<div id="examples_from" class="left">
		   				 <h2>We push updates from publishers including:</h2>
				        <div id="example_from" class="example"><a href="http://etsy.com/" title="Etsy"><img src="./images/pubsub/etsy.png?1313499764" alt="Etsy"></a><a href="http://www.tumblr.com/" title="Tumblr"><img src="./images/pubsub/tumblr.png?1313499764" alt="Tumblr"></a><a href="http://gawker.com/" title="Gawker"><img src="./images/pubsub/gawker.png?1313499764" alt="Gawker"></a><div class="clear" style="height:10px; width:100%;">&nbsp;</div><a href="http://gowalla.com/" title="Gowalla"><img src="./images/pubsub/gowalla.png?1313499764" alt="Gowalla"></a><a href="http://www.huffingtonpost.com/" title="The Huffington Post"><img src="./images/pubsub/huffingtonpost.png?1313499764" alt="HuffingtonPost"></a><a href="http://posterous.com/" title="Posterous"><img src="./images/pubsub/posterous.png?1313499764" alt="Posterous"></a>
						    <div class="clear" style="height:10px; width:100%;">&nbsp;</div>
				        </div>
					</div>
					<div id="examples_to" class="right">
	    				<div id="arrow"></div>
					    <h2>To subscribers including:</h2>
					    <div id="example_to" class="example"><a href="http://www.peerindex.net/" title="PeerIndex"><img src="./images/pubsub/peerindex.png?1313499764" alt="PeerIndex"></a><a href="http://www.google.com/" title="Google"><img src="./images/pubsub/google.png?1313499764" alt="Google"></a><a href="http://tarpipe.com/" title="Tarpipe"><img src="./images/pubsub/tarpipe.png?1313499764" alt="Tarpipe"></a><div class="clear" style="height:10px; width:100%;">&nbsp;</div><a href="http://fanvibe.com/" title="Fanvibe"><img src="./images/pubsub/fanvibe.png?1313499764" alt="Fanvibe"></a><a href="http://digg.com/" title="Digg"><img src="./images/pubsub/digg.png?1313499764" alt="Digg"></a><a href="http://status.net/" title="StatusNet"><img src="./images/pubsub/statusnet.png?1313499764" alt="Status.net"></a>
						    <div class="clear" style="height:10px; width:100%;">&nbsp;</div>
					    </div>
					</div>
				</div>
		 		<div class="clear"></div>
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
