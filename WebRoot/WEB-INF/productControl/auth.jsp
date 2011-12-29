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
<div>
<div class="rhead">
	<div class="rpos">当前位置: 登录鉴权 </div>
	<div class="clear"></div>
</div>
<div>
 <form method="post" action="loginAuth.do">
 <div style="margin-left: auto;margin-right: auto;margin-top:29px;text-align: center;">
 <span style="font-size: 20px;">machineID:</span><input type="text" name="machineID" style="width: 220px;vertical-align: middle;"><br>
 <span style="font-size: 20px;">orderCode:</span><input type="text" name="order_code" style="width: 220px;vertical-align: middle;"><br>
 <span style="font-size: 20px;">&nbsp&nbsp&nbspemmcID:</span><input type="text" name="emmcID" style="width: 220px;vertical-align: middle;"><br>
 
 <input type="submit" value="提交"><input type="reset" value="取消">
 </div>
 </form></div></div></body>
</html>