<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<title>multi file upload test</title>
<script type="text/javascript">
	var t = 1;
	function addFile(){
	alert("hello!");
		var parent = document.getElementById("more");
		alert("hello!");
		var br = document.createElement("br");
		var input = document.createElement("input");
		var	button = document.createElement("input");
		alert("go to here!");
		input.type = "file";
		input.name = "uploadFile[" + (t++) + "].file";
		button.type = "button";
		button.value = "删除";
		button.onclick = Function(){
			parent.removeChild("br");
			parent.removeChild("input");
			parent.removeChild("button");
		};
		
		parent.appendChild("br");
		parent.appendChild("input");
		parent.appendChild("button");
	}

</script>
</head>
<body>
	<input id="more" type="file" name="uploadFile[0].file"/><input type="button" onclick="addFile()" value="增加"/>
	
</body>
</html:html>