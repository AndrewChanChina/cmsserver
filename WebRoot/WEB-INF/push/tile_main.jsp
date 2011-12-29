<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <title></title>
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/push.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/body.css">
    <link rel="stylesheet" type="text/css" href="./css/aside.css">
  </head>
  <body>
  	<div id="wrap" style="overflow: auto;margin: 0 auto;">
  		<tiles:insert attribute="header"></tiles:insert>
  		<div style="margin-left: auto;margin-right: auto; width: 1000px;overflow: auto" id="content_area">
  		<tiles:insert attribute="body"></tiles:insert>
  		<tiles:insert attribute="main_right"></tiles:insert>
  		</div>
  	</div>
  </body>
</html>
