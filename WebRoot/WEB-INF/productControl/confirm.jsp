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
<title>Insert title here</title>
</head>
<body>
<div class="rhead">
		<div class="rpos">当前位置: 授权 - 确认</div>
		<div class="clear"></div>
</div>
<form action="confirm.do" method="post">
<table>
<tr><td>旧ckeckID:</td><td><input type="text" name="old_check_id"/></td><td>授权码:</td><td><input type="text" name="auth_code"/></td></tr>
<tr><td>新ckeckID:</td><td><input type="text" name="new_check_id"/></td></tr>
<tr><td><input type="submit" value="提交"/></td><td><input type="reset" value="取消"/></td></tr>
</table>
</form>
</body>
</html>