<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'tile_link.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="./js/jquery.js"></script>
	<script type="text/javascript" src="./js/push.js"></script>
	<link rel="stylesheet" type="text/css" href="./css/header.css">
	<link rel="stylesheet" type="text/css" href="./css/content.css">
	<link rel="stylesheet" type="text/css" href="./css/aside.css">
  </head>
  
  <body>
    <div id="wrap">
    	<tiles:insert attribute="header"></tiles:insert>
    	<div style="margin-left: auto;margin-right: auto; width: 1000px;">
    	<form action="push.do?op=link" method="post" id="content-form">
    	<tiles:insert attribute="link_content"></tiles:insert>
    	<tiles:insert attribute="aside"></tiles:insert>
    	</form>
    	</div>
    </div>
  </body>
</html>
