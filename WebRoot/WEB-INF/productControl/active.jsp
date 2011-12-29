<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<title>Active</title>

</head>
<body>
<div class="rhead">
		<div class="rpos">当前位置: 授权 - 激活</div>
		<div class="clear"></div>
</div>
<form action="active.do" method="post">
<div style="margin: 0 auto;margin-top: 50px">
<table style="margin: 0 auto;">
<tr><td><span style="font-size: 20px;">checkID:</span></td><td><input type="text" name="checkID" style="width: 230px"/></td></tr>
<tr><td><span style="font-size: 20px">machineID:</span></td><td><input type="text" style="width: 230px"/></td></tr>
<tr><td><input type="submit" value="提交"/></td><td><input type="reset" value="取消"/></td></tr>
</table></div>
</form>
</body>
</html>