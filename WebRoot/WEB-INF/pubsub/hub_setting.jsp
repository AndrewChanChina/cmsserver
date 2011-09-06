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
    
    <title>My JSP 'hub_setting.jsp' starting page</title>
    
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
    					<li><a href="">Settings</a></li>
    					<li><a href="">Live Hub</a></li>
    					<li><a href="">Analytics</a></li>
    					<li><a href="pubsub_account.do">Account & Settings</a></li>
    					<li><a href="pubsub_logout.do">Log out</a></li>
    				</ul>
    			</div>
    		</div>
    		<ul id="navigation">
    		<!--  
    			<li><a href="/publish">发布者</a><li>
    			<li><a href="/subscribe">订阅者</a></li>-->
    			<li><a href="doc.do">说明</a></li>
    			<li><a href="about.do">关于</a></li>
    		</ul>
    		<div class="clear"></div>
    		<div id="main_bg">
    			<div id="main_top"><h2>Edit your hub</h2></div>
    			<div id="content">
    				<p style="margin: 20px 20px 0px 20px; padding: 15px 20px 15px 20px; background-color: #A7E3A8; border: solid 1px #94C796;">Once your hub is ready on this end, please 
    				<strong>make sure</strong> you <a href="/users/cluo888/hub/install">proceed to installation</a> in your publishing application.</p>
				<form action="update_publisher.do" method="post">
					<logic:present name="publisher">
						<div id="left_column" class="columns" style="text-align: left;">
						<h3>General Settings</h3>
						<div id="subdomain_help" style="display: none;"></div>
						<p>
							<label for="hub_sub_domain_help">Sub-domain for your hub*</label>
							<a href="" class="thickbox"><!--  <img alt="help" src="" style="margin: 0px 0px 0px 3px">--></a><br>
							<input id="hub_subdomain" name="domain" size=48 type="text" value="<bean:write name="publisher" property="domain"/>">
						</p>
						<p>
							<label for="hub_name">Name of your service or application</label><br>
							<input id="hub_name" name="name" size="48" type="text" value="<bean:write name="publisher" property="service_name"/>">
						</p>
						<p>
							<label for="hub_url">Url of your service or application</label><br>
							<input id="hub_url" name="url" size="48" type="text" value="<bean:write name="publisher" property="service_url"/>">
						</p>
					</div>
					<div id="right_column" class="columns" style="text-align: left;">
						<h3>Style</h3>
						<div id="img_help" style="display: none;"></div>
						<p>
							<label for="hub_logo">Logo of your service</label>
							<a href="" class="thickbox"></a><br>
							<input id="hub_img" name="server_logo" size="30" type="text" value="<bean:write name="publisher" property="service_logo"/>">
						</p>
						<p>
        					<label for="hub_Background Color">Background color</label><a href="#TB_inline?height=400&amp;width=600&amp;inlineId=background_help" class="thickbox"><!--  <img alt="Help" src="/images/icons/help.png?1313499764" style="margin:0px 0px 0px 3px">--></a><br>
        					<input id="hub_background" name="bg_color" size="30" type="text" value="<bean:write name="publisher" property="bg_color"/>">
    					</p>
    					<p>
        					<label for="hub_Font Color">Font color</label><a href="#TB_inline?height=400&amp;width=600&amp;inlineId=fontcolor_help" class="thickbox"><!--  <img alt="Help" src="/images/icons/help.png?1313499764" style="margin:0px 0px 0px 3px">--></a><br>
        					<input id="hub_fontcolor" name="font_color" size="30" type="text" value="<bean:write name="publisher" property="font_color"/>">
   						 </p>
					</div>
					</logic:present>
					<logic:notPresent name="publisher">
						<div id="left_column" class="columns" style="text-align: left;">
						<h3>General Settings</h3>
						<div id="subdomain_help" style="display: none;"></div>
						<p>
							<label for="hub_sub_domain_help">Sub-domain for your hub*</label>
							<a href="" class="thickbox"><!--  <img alt="help" src="" style="margin: 0px 0px 0px 3px">--></a><br>
							<input id="hub_subdomain" name="domain" size=48 type="text" value="">
						</p>
						<p>
							<label for="hub_name">Name of your service or application</label><br>
							<input id="hub_name" name="name" size="48" type="text" value="">
						</p>
						<p>
							<label for="hub_url">Url of your service or application</label><br>
							<input id="hub_url" name="url" size="48" type="text" value="">
						</p>
					</div>
					<div id="right_column" class="columns" style="text-align: left;">
						<h3>Style</h3>
						<div id="img_help" style="display: none;"></div>
						<p>
							<label for="hub_logo">Logo of your service</label>
							<a href="" class="thickbox"></a><br>
							<input id="hub_img" name="server_logo" size="30" type="text" >
						</p>
						<p>
        					<label for="hub_Background Color">Background color</label><a href="#TB_inline?height=400&amp;width=600&amp;inlineId=background_help" class="thickbox"><!--  <img alt="Help" src="/images/icons/help.png?1313499764" style="margin:0px 0px 0px 3px">--></a><br>
        					<input id="hub_background" name="bg_color" size="30" type="text">
    					</p>
    					<p>
        					<label for="hub_Font Color">Font color</label><a href="#TB_inline?height=400&amp;width=600&amp;inlineId=fontcolor_help" class="thickbox"><!--  <img alt="Help" src="/images/icons/help.png?1313499764" style="margin:0px 0px 0px 3px">--></a><br>
        					<input id="hub_fontcolor" name="font_color" size="30" type="text">
   						 </p>
					</div>
				</logic:notPresent>
					
					<p style="clear:both;"><small>* : required</small></p>
					<h3>Advanced settings</h3>
					<p><strong>Warning</strong> : these <strong>optional</strong> settings are for <em>very advanced users</em>, you may break your hub if you don't implement them correctly on your end, please make sure you understand the implication of what you're doing. 
					</p>
					<p>
   					 <label for="hub_CNAME">Cname</label><a href="#TB_inline?height=400&amp;width=600&amp;inlineId=cname_help" class="thickbox"><!--  <img alt="Help" src="/images/pubsub/help.png?1313499764" style="margin:0px 0px 0px 3px">--></a><br>
   					 <input id="hub_cname" name="cname" size="64" type="text">
					</p>
					<p><label for="hub_Subscription callback">Subscription callback</label><a href="#TB_inline?height=400&amp;width=600&amp;inlineId=subscription_callback_help" class="thickbox"><!--  <img alt="Help" src="/images/pubsub/help.png?1313499764" style="margin:0px 0px 0px 3px">--></a><br>
                  <input id="hub_subscription_callback" name="hub[subscription_callback]" size="64" type="text"></p>
					<p><label for="hub_Fat Ping's secret">Fat ping's secret</label><a href="#TB_inline?height=400&amp;width=600&amp;inlineId=fat_pings_help" class="thickbox"><!--  <img alt="Help" src="/images/pubsub/help.png?1313499764" style="margin:0px 0px 0px 3px">--></a><br>
    <input id="hub_secret" name="hub[secret]" readonly="readonly" size="64" type="text" value="0f4cce1e147548517639613d1444cd2e726faa51"></p>
					<p>
   						<input id="hub_user_name" name="hub[user_name]" type="hidden" value="test886">
    					<input id="hub_public" name="hub[public]" type="hidden" value="true">
    					<input id="hub_submit" name="commit" type="submit" value="Save">
					</p>
				</form>
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
