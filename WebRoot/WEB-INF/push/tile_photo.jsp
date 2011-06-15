<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
  <head>
    
    <title>My JSP 'tile_photo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="./js/jquery.js"></script>
	<script type="text/javascript" src="./js/push.js"></script>
	<script type="text/javascript" src="./js/ajaxfileupload.js"></script>
	<link rel="stylesheet" type="text/css" href="./css/header.css">
	<link rel="stylesheet" type="text/css" href="./css/content.css">
	<link rel="stylesheet" type="text/css" href="./css/aside.css">
  </head>
  
  <body>
  	<div id="wrap">
  		<tiles:insert attribute="header"></tiles:insert>
  		<div style="margin-left: auto;margin-right: auto; width: 1000px;overflow: auto;">
  		<form method="post" action="push.do?op=photo" id="content-form">
  			<tiles:insert attribute="photo_content"></tiles:insert>
  			<tiles:insert attribute="aside"></tiles:insert>
  		</form>
  		</div>
  	</div>
  </body>
</html>
