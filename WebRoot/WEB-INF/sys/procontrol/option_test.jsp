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
    function add(r){
    		var tab = document.getElementById("table");
    	    var rows = tab.rows;
    	    
         	alert("rows is:"+rows.length);
         	//alert(tab.cells.length/rows);
         	alert(rows[0].cells.length);
         	var cols = rows[0].cells.length;
         	var tr = document.all.table.insertRow();
         	var childNode = document.all.table.childNodes[0].childNodes[0].cells.length;
         	for (var i = 0;i <childNode;i ++ ){
				var td = tr.insertCell(i);
				var names = new Array("name","test_id","create_time");
				td.innerHTML = "<input type='text' name='"+names[i]+"'>";
				//alert(td.innerHtml);
				if(i== childNode-1){
				td.innerHTML = "<input type='button' value='增加' onclick='add();'><input type='button' value='删除' onclick='delRow();'>"
				}
			}
         }
    	
    	function delRow(){
    		document.all.table.deleteRow();
    	}
    
</script>
</head>
<body>
<div class="rhead">
	<div class="rpos">当前位置: 生产管理 - 添加</div>
	<div class="clear"></div>
</div>
<br>
<form action="testOption.do" method="post">
	<table align="center" border="1" bordercolor="black" id="table">
	<thead>
	<tr><th>设备名称</th><th>testID:</th><th>创建时间</th><th>操作</th></tr></thead>
	<tbody id="tab">
	<tr><td><input type="text" name="name"/></td><td><input type="text" name="test_id"/></td><td><input type="text" name="create_time"/></td><td><input type="button" value="增加" onclick="add(0);"/><!--  <input type="button" value="删除"/>--></td></tr>
	</tbody>
	</table>
	<br>
	<table><tr><td colspan="3" width="50%" style="margin-left: 200px;"><input type="submit" value="提交"/></td><td colspan="1" width="50%"><input type="reset" value="取消"/></td></tr></table>
	<br>
</form>
</body>
</html>