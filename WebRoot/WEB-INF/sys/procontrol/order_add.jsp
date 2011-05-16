<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
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
<script type="text/javascript">
	function back(){
		window.history.back();
	}
	
	 function add(){
	 	var options = document.getElementById("sel");
    	var op = document.getElementById("op");
    		for (var i=options.options.length-1; i>=0; i--){
       			if (options.options[i].selected){
           		 op.value += options.options[i].value+";";
       			 }
   			 }
     }
    	
    function del(){
    	var options = document.getElementById("sel");
    	var op = document.getElementById("op");
    	for (var i=options.options.length-1; i>=0; i--){
    			var seltext = options.options[i];
       			if (seltext.selected){
           			 var str = op.value.split(seltext.value+";");
           			 op.value = str[0]+str[1];
       			 }
       		 }
    }
    
</script>
<title>add product order!</title>
</head>
<body>
<div class="rhead">
	<div class="rpos">当前位置: 生产管理 - 添加</div>
	<div class="clear"></div>
</div>
<html:form action="order.do" method="post">
<table id="table">
<tr><td>名称</td><td><html:text property="name" value=""></html:text></td><td>设备类型</td><td><html:text property="device_type" value=""></html:text></td>
<tr><td>开始时间</td><td><html:text property="start_time" value=""></html:text></td><td>结束时间</td><td><html:text property="end_time" value=""></html:text><label style="color: red">&nbsp&nbsp*注意：开始和结束时间格式必须类似：2011/05/08</label></td></tr>
<tr><td>生产商代号</td><td><html:text property="manufacturer_code" value=""></html:text></td><td>产品型号</td><td><html:text property="production_code" value=""></html:text></td></tr>
<tr><td>序列号</td><td><html:text property="sn" value=""></html:text></td><td>其他</td><td><html:text property="inf_code" value=""></html:text></td></tr>
<tr><td>测试选项</td><td>
	<input type="text" name="selOption" value=""  id="op"/></td><td>
	<select name="option" multiple="multiple" onclick="add();" id="sel" > 
<logic:iterate id="option" name="options">
	<option value="${option.name}"><bean:write name="option" property="name"/></option>
</logic:iterate></select></td><td><input type="button" value="增加" onclick="add();"><input type="button" value="删除" onclick="del();"></td></tr>
</table>
<table>
<tr style="top:50px"><td style="left:30%" colspan="2" align="center"><html:submit property="" value="提交" ></html:submit></td><td><html:reset property="" value="取消"></html:reset></td><td><html:button property="" value="返回" onclick="back();"></html:button></td></tr>
</table>
</html:form>
</body>
</html>