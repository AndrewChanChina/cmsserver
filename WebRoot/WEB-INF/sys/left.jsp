<%@ page language="java" pageEncoding="GB18030"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <base href="<%=basePath%>">
    
    <title>left.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="./css/admin.css" rel="stylesheet" type="text/css"/>
	<link href="./css/theme.css" rel="stylesheet" type="text/css"/>
	<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css"/>
	<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
	<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css"/>
	
	<script src="./js/fckeditor.js" type="text/javascript"></script>
	<script src="./js/WdatePicker.js" type="text/javascript"></script>
	<script src="./js/jquery.js" type="text/javascript"></script>
	<script src="./js/jquery.ext.js" type="text/javascript"></script>
	<script src="./js/pony.js" type="text/javascript"></script>
	<script src="./js/admin.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		Cms.lmenu('lmenu');
	});
</script>

  </head>
  
 <body class="lbody">
<ul id="lmenu">
<li><a href="./userright.do?show=grouplist" target="rightFrame">分组信息</a></li>
<li><a href="./listuser.do" target="rightFrame">用户信息</a></li>
<li><a href="/userright.do?show=role" target="rightFrame">权限信息</a></li>

</ul>
</body>
</html:html>
