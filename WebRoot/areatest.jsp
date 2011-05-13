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
function getFieldSelection(id) 
{ 
var select_field = document.getElementById(id); 
     word=''; 
    if (document.selection) { 
         var sel = document.selection.createRange(); 
        if (sel.text.length > 0) { 
             word = sel.text; 
         } 
     }    /*ie浏览器*/ 
    else if (select_field.selectionStart || select_field.selectionStart == '0') { 
         var startP = select_field.selectionStart; 
         var endP = select_field.selectionEnd; 
        if (startP != endP) { 
             word = select_field.value.substring(startP, endP); 
         } 
     }   /*标准浏览器*/ 
    return word; 
} 
//alert(getFieldSelection('txtarea'))
	function getText(){
	document.getElementById("text").value=getFieldSelection("txtarea");
	}
</script> 
</head>
<body>
<textarea id="txtarea" rows="3" cols="20">选择我,然后点击下面的按钮</textarea> 
<br/> 
<br/> 
<input type="text" name="option" id="text"><input type="text" name="ordername">
<button onclick="getText();">button_click</button>

</body>
</html:html>