<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<title>multi file upload test</title>
<script src="./js/fckeditor.js" type="text/javascript"></script>
<script src="./js/WdatePicker.js" type="text/javascript"></script>
<script src="./js/jquery.js" type="text/javascript"></script>
<script src="./js/jquery.ext.js" type="text/javascript"></script>
<script src="./js/pony.js" type="text/javascript"></script>
<script src="./js/admin.js" type="text/javascript"></script>
<script src="./js/admin.js" type="text/javascript"></script>
<script type="text/javascript">
	var t = 1;
	function addFile(){
		var parent = document.getElementById("more");
		var div = document.createElement("div");
		var str = "div"+(t++);
		div.name= str;
		div.id= str;
		var input = document.createElement("input");
		var	button = document.createElement("input");
		input.type = "file";
		input.name = "upload";
		button.type = "button";
		button.value = "删除";
		button.onclick = function(){
			div.removeChild(input);
			div.removeChild(button);
		};
		alert("create success1!");
		div.appendChild(input);
		div.appendChild(button);
		alert("create success2!");
		parent.appendChild(div);

	}

</script>
</head>
<body>
<form action="logUpload.do" method="post" enctype="multipart/form-data">
	
	<div id="more">
	<input  type="file" name="upload"/>
	<input type="button" onclick="addFile()" value="增加"/> 
	</div>
	<input type="submit" value="提交" />
	<% List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		pageContext.setAttribute("list",list);
	%>
	<table>
	<logic:iterate id="test" name="list" >
		<tr><td><bean:write name="test"/></td></tr>
	</logic:iterate>
	</table>
	</form>
</body>
</html:html>