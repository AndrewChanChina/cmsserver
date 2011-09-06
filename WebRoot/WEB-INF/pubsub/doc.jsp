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
    
    <title>My JSP 'doc.jsp' starting page</title>
    
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
    			<div id="main_top"><h2>Smit Documentation</h2></div>
    			<div id="content">
    				<h2>Summary</h2>
    				<ol id="doc_summary">
    <li><a href="#publisher">Publisher</a></li>
    <ul>
        <li><a href="#discovery">Discovery</a></li>
        <li><a href="#ping">Ping (New Content Notification)</a></li>
    </ul>
    <li><a href="#subscriber">Subscriber</a></li>
    <ul>
        <li><a href="#introduction">Introduction</a></li>
        <li><a href="#what_api">What API to choose?</a></li>
        <li><a href="#xmpp_pubsub">XMPP PubSub</a>
            <ul>
                <li><a href="#xmpp_adding_feed">Adding a Feed</a></li>
                <li><a href="#xmpp_removing_feed">Removing a Feed</a></li>
                <li><a href="#xmpp_listing_feed">Listing Feeds</a></li>
                <li><a href="#xmpp_notifications">Notifications</a></li>
                <li><a href="#xmpp_digest">Digest</a></li>
                <li><a href="#xmpp_api_wrapper">API Wrappers</a></li>
            </ul>
        </li>
        <li><a href="#pubsubhubbub">PubSubHubbub API</a>
            <ul>
                <li><a href="#pubsubhubbub_implementation">Implementation</a></li>
                <li><a href="#pubsubhubbub_notifications">Notifications</a></li>
                <li><a href="#pubsubhubbub_retrieve">Retrieving Feed</a></li>
                <li><a href="#pubsubhubbub_digest">Digest</a></li>
            </ul>
        </li>
    </ul>
    <li><a href="#schema">Schema</a>
        <ul>
            <li><a href="#status_schema">Status</a></li>
            <li><a href="#entry_schema">Entry</a></li>
            <li><a href="#json_schema">JSON</a></li>
        </ul>
    </li>
    <li><a href="#misc">Miscellaneous</a>
        <ul>
            <li><a href="#track">Track</a></li>
            <li><a href="#notification_threshold">Notification threshold (spam filter)</a></li>
            <li><a href="#list_of_ips">List of IPs</a></li>
        </ul>
    </li>
</ol>
		<h2 id="publisher">Publisher</h2>
		<p>
   		 The <strong>PubSubHubbubb</strong> hubs that Superfeedr hosts are compliant with the <a href="http://pubsubhubbub.googlecode.com/svn/trunk/pubsubhubbub-core-0.3.html">core spec</a>. In order to enable the hub as a publisher, you need to perform 2 actions : <a href="#discovery">discovery</a> and <a href="#ping">ping</a>.
		</p>
		<h3 id="discovery">Discovery</h3>
		<p>
    	Discovery is aimed at informing your current (and future) feed subscribers (who poll your feed) that they can get content your Superfeedr hosted hub. 
		</p>
		<ul>
        <li>For RSS</li>
        <code><span class="sh_preproc">&lt;?xml</span> <span class="sh_type">version</span><span class="sh_symbol">=</span><span class="sh_string">"1.0"</span><span class="sh_preproc">?&gt;</span>
<span class="sh_keyword">&lt;rss&gt;</span>
 <span class="sh_keyword">&lt;channel&gt;</span>
  <span class="sh_keyword">&lt;title&gt;</span>...<span class="sh_keyword">&lt;/title&gt;</span>
  <span class="sh_keyword">&lt;description&gt;</span>...<span class="sh_keyword">&lt;/description&gt;</span>
  <span class="sh_keyword">&lt;link&gt;</span>...<span class="sh_keyword">&lt;/link&gt;</span>
  
  <span class="sh_comment">&lt;!-- PubSubHubbub Discovery --&gt;</span>
  <span class="sh_keyword">&lt;link</span> <span class="sh_type">rel</span><span class="sh_symbol">=</span><span class="sh_string">"hub"</span>  <span class="sh_type">href</span><span class="sh_symbol">=</span><span class="sh_string">"http://&lt;your-hub-name&gt;.superfeedr.com/"</span> <span class="sh_type">xmlns</span><span class="sh_symbol">=</span><span class="sh_string">"http://www.w3.org/2005/Atom"</span> <span class="sh_keyword">/&gt;</span>
  <span class="sh_keyword">&lt;link</span> <span class="sh_type">rel</span><span class="sh_symbol">=</span><span class="sh_string">"self"</span> <span class="sh_type">href</span><span class="sh_symbol">=</span><span class="sh_string">"&lt;your-feed-url&gt;"</span> <span class="sh_type">xmlns</span><span class="sh_symbol">=</span><span class="sh_string">"http://www.w3.org/2005/Atom"</span> <span class="sh_keyword">/&gt;</span>
  <span class="sh_comment">&lt;!-- End Of PubSubHubbub Discovery --&gt;</span>
  ...
 <span class="sh_keyword">&lt;/channel&gt;</span>
<span class="sh_keyword">&lt;/rss&gt;</span></code></pre>
        <li>For Atom</li>
        <pre class="sh_xml documentation inbound sh_sourceCode"><code><span class="sh_preproc">&lt;?xml</span> <span class="sh_type">version</span><span class="sh_symbol">=</span><span class="sh_string">"1.0"</span> <span class="sh_type">encoding</span><span class="sh_symbol">=</span><span class="sh_string">"UTF-8"</span><span class="sh_preproc">?&gt;</span>
<span class="sh_keyword">&lt;feed</span> <span class="sh_type">xmlns</span><span class="sh_symbol">=</span><span class="sh_string">"http://www.w3.org/2005/Atom"</span><span class="sh_keyword">&gt;</span>
 <span class="sh_keyword">&lt;title&gt;</span>...<span class="sh_keyword">&lt;/title&gt;</span>
 <span class="sh_keyword">&lt;link</span> <span class="sh_type">href</span><span class="sh_symbol">=</span><span class="sh_string">"..."</span> <span class="sh_type">rel</span><span class="sh_symbol">=</span><span class="sh_string">"self"</span> <span class="sh_type">type</span><span class="sh_symbol">=</span><span class="sh_string">"application/atom+xml"</span><span class="sh_keyword">/&gt;</span>

 <span class="sh_comment">&lt;!-- PubSubHubbub Discovery --&gt;</span>
 <span class="sh_keyword">&lt;link</span> <span class="sh_type">rel</span><span class="sh_symbol">=</span><span class="sh_string">"hub"</span> <span class="sh_type">href</span><span class="sh_symbol">=</span><span class="sh_string">"http://&lt;your-hub-name&gt;.superfeedr.com/"</span> <span class="sh_keyword">/&gt;</span>
 <span class="sh_comment">&lt;!-- End Of PubSubHubbub Discovery --&gt;</span>

 <span class="sh_keyword">&lt;updated&gt;</span>...<span class="sh_keyword">&lt;/updated&gt;</span>
 <span class="sh_keyword">&lt;id&gt;</span>...<span class="sh_keyword">&lt;/id&gt;</span>
 ...
<span class="sh_keyword">&lt;/feed&gt;</span></code></pre>
    </ul>
    <h3 id="ping">Ping (New Content Notification)</h3>
    <p>
    The next step is to ping us whenever you update the content of a feed. This will allow us to fetch this specific feed, identify what is new vs what is old in it and finally push the updates to the subscribers to this feed.
	</p>
    <ul>
    <li>Send an <code>POST</code> request to <code>http://&lt;your-hub&gt;.superfeedr.com</code>, with the following params : 
        <ul>
            <li><code>hub.mode</code> : <code>publish</code></li>
            <li><code>hub.url</code> : the url of the feed that was updated</li>
        </ul>
    </li>
    <li>If somebody subscribed to your feed, then, we will fetch it. You should see a request from Superfeedr in your logs.</li>
</ul>	
    		<ul>
    <li>Send an <code>POST</code> request to <code>http://&lt;your-hub&gt;.superfeedr.com</code>, with the following params : 
        <ul>
            <li><code>hub.mode</code> : <code>publish</code></li>
            <li><code>hub.url</code> : the url of the feed that was updated</li>
        </ul>
    </li>
    <li>If somebody subscribed to your feed, then, we will fetch it. You should see a request from Superfeedr in your logs.</li>
</ul>
		<p>At Superfeedr, we wanted to <strong>make it simple</strong> for you to implement the <strong>PubSubHubbub</strong> protocol, so if <em>you have implemented any type of ping</em>, please get in touch, as we're probably able to receive these too. For example, our <a href="http://ping.superfeedr.com/rpc">XML-RPC endpoint is here</a>.</p>
    	<h2 id="subscriber">Subscriber</h2>	
    	<h3 id="introduction">Introduction</h3>
    	<p>
   		 Superfeedr is a tool which will push you content to which you can subscribe online. Our <em>historical</em> use case is to allow you to subscribe to RSS or Atom feeds and get that content pushed to you in realtime, but you <strong>can obviously subscribe to any HTTP resource accessible via a GET request</strong>. For example, you can subscribe to <a href="http://www.json.org/">JSON document</a>, VCards, or even HTML pages.
		</p>
		<p>
    	 If you have any question about this documentation or if you still need help with the Superfeedr, please join our <a href="http://groups.google.com/group/superfeedr">Google Group</a>, read our <a href="http://groups.google.com/group/superfeedr/web/frequently-asked-questions">Frequently Asked Questions</a>. We also strongly suggest the use of our dashboard (you must be logged in) to debug the real-time notification.
		</p>	
		<h4>Color Code</h4>
		<pre class="outbound"><code>Outbound (requests) traffic have a grey background.</code></pre>
		<pre class="inbound"><code>Inbound (responses + notifications) traffic have a green background.</code></pre>
		<h3 id="what_api">What API to choose?</h3>
		<p>
   		 Superfeedr offers 2 different API : <a href="#xmpp_pubsub">XMPP PubSub</a> and <a href="#pubsubhubbub">HTTP PubSubHubbub</a>. They have both purposes for which they've been created and, based on your goals using Superfeedr, you might want to select one or another. Here is a small chart that will help you choose the most appropriate in your case. (In this chart, the 'I' represent the endpoint that will receive the notifications).
		</p>
		<img alt="Api-decision-chart" src="/images/api-decision-chart.png?1313499764" style="margin-left:auto; margin-right:auto">
		<h3 id="xmpp_pubsub">XMPP PubSub</h3>
		<p>
    You can connect your jabber client (or component) to superfeedr by using the JID <em>login-@superfeedr.com</em> and your superfeedr password. You can also specify another JID in your settings.</p>
    <p>The XMPP API is based on the <a href="http://xmpp.org/extensions/xep-0060.html#subscriber-subscribe">XEP-0060 syntax</a>. Additional data has been included within a superfeedr-specific namespace.
	</p>
	<!--  <h4 id="xmpp_adding_feed">Subscribing to a Resource</h4>-->
	
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
