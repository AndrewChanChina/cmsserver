<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>test option page</title>
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
 function add(){
 	var table = document.getElementById("optionTable");
 
}

 	

 
 function delTr(){
 }

</script>
</head>
<body>
<form action="">
<table align="center" border="1" id="optionTable">
<tr><th>设备名称</th><th>testID:</th><th>创建时间</th><th>操作</th></tr>
<tr><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="button" value="增加" onclick="add();"/><input type="button" value="删除" onclick="delTr();"></td></tr>
</table>
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="submit" value="提交"/>&nbsp&nbsp<input type="reset" value="取消"/>
</form>
</body>
</html>