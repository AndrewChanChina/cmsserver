<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
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
<div id="warp" style="overflow: auto;">
	<tiles:insert attribute="header"></tiles:insert>
	<form action="push.do?op=text" method="post" id="content-form">
	<tiles:insert attribute="text_content"></tiles:insert>
	<tiles:insert attribute="aside"></tiles:insert>
	</form>
</div>
</body>
</html>
