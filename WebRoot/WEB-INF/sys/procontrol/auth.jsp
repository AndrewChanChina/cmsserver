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

 <form method="post" action="loginAuth.do" id="authForm">
 <table>
 	<tr><td>machineID:<input type="text" name="machineId"><td><td>orderCode:<input type="text" name="order_code"></td></tr>
 </table>
 <input type="submit" value="提交"><input type="reset" value="取消">
 </form></body>
</html>