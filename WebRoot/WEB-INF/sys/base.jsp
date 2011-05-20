<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>product order</title>
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
</head>
<body>
<div class="rhead">
		<div class="rpos">当前位置: 基本信息 - 上传</div>
		<div class="clear"></div>
</div>
<form method="post" action="logUpload.do" id="logForm" enctype="multipart/form-data">
<table border="0" class="pn-ftable">
<tbody><tr>
<td class="pn-flabel pn-flabel-h">&nbsp;MachineID</td>
<td class="pn-fcontent">&nbsp;<input type="text" name="machineID"></td>
<td class="pn-flabel pn-flabel-h">&nbsp;MachineType</td>
<td class="pn-fcontent">&nbsp;<input type="text" name="machineType"></td></tr>
<tr>
<td class="pn-flabel pn-flabel-h">&nbsp;SystemVersion</td>
<td class="pn-fcontent">&nbsp;<input type="text" name="systemVersion"></td>
<td class="pn-flabel pn-flabel-h">&nbsp;SoftWareVersion</td>
<td class="pn-fcontent">&nbsp;<input type="text" name="softwareVersion"></td></tr>
<tr>
<td class="pn-flabel pn-flabel-h">&nbsp;TestStatus</td>
<td class="pn-fcontent">&nbsp;<input type="text" name="testStatus"></td>
<td class="pn-flabel pn-flabel-h">&nbsp;CheckID</td>
<td class="pn-fcontent">&nbsp;<input type="text" name="checkID"></td></tr>
<tr>
<td class="pn-flabel pn-flabel-h" colspan="1" align="left">&nbsp;FilePath</td>
<td class="pn-fcontent">&nbsp;<input  type="file" name="upload"/></td><td class="pn-fcontent" colspan="2"></td></tr>
<tr>
<td class="pn-fcontent" colspan="2" align="center">&nbsp;<input type="submit" value="提交" name="tijiao"></td>
<td class="pn-fcontent" colspan="2" align="center">&nbsp;<input type="reset" value="取消" name="qvxiao"></td></tr>
</tbody></table></form></body>
</html>
