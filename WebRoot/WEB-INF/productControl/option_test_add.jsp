<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
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
<table  border="0" bordercolor="black" id="table" class="pn-ltable" >
<thead>
<tr><th class="pn-flabel pn-flabel-h" align="center">设备名称</th><th class="pn-flabel pn-flabel-h" align="center">testID:</th><th class="pn-flabel pn-flabel-h" align="center">操作</th></tr></thead>
<tbody id="tab">
<tr><td class="pn-fcontent"><input type="text" name="name"/></td><td class="pn-fcontent"><input type="text" name="test_id"/></td><td class="pn-fcontent"><input type="button" value="增加" onclick="add(0);" style="width: 100px"/><!--  <input type="button" value="删除"/>--></td></tr>
</tbody>
</table>
<table class="pn-ltable"><tr><td colspan="2" width="50%" align="center" class="pn-fcontent"><input type="submit" value="提交"/></td><td colspan="2" width="50%" align="center" class="pn-fcontent"><input type="reset" value="取消"/></td></tr></table>
</form>

</body>
</html>