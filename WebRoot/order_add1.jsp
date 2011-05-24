<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
<title>add product order!</title>
</head>
<body>
<div class="rhead">
	<div class="rpos">当前位置: 生产管理 - 添加</div>
	<div class="clear"></div>
</div>
<html:form action="order.do" method="post">
<table>
<tr><td>名称</td><td><html:text property="name"></html:text></td><td>设备类型</td><td><html:text property="device_type"></html:text></td>
<tr><td>开始时间</td><td><html:text property="start_time"></html:text></td><td>结束时间</td><td><html:text property="end_time"></html:text></td></tr>
<tr><td>生产商代号</td><td><html:text property="manufacturer_code"></html:text></td><td>产品型号</td><td><html:text property="production_code"></html:text></td></tr>
<tr><td>序列号</td><td><html:text property="sn"></html:text></td><td>其他</td><td><html:text property="inf_code"></html:text></td></tr>
<tr><td><html:submit property="" value="提交"></html:submit></td><td><html:reset property="" value="取消"></html:reset></td></tr>
</table>
</html:form>
</body>
</html>