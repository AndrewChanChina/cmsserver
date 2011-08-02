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
    $(document).ready(function(){
   			
   		 $("#device-type").change(function(){
   		  	var type = $("#device-type").val();
   		 	if(type.length!=1){
   		 		alert("设备类型只能为1位！");
   		 	}
   		 });
   		 $("#manu-code").change(function(){
   		  	var manu_code = $("#manu-code").val();
   		 	if(manu_code.length!=3){
   		 		alert("生产商代号必须为3位！");
   		 	}
   		 });
   		 $("#pro-code").change(function(){
   			 var pro_code = $("#pro-code").val();
   		 	if(pro_code.length!=4){
   		 		alert("产品型号必须为4位！");
   		 	}
   		 });
   		 $("#sn").change(function(){
   		 	 var sn = $("#sn").val();
   		 	var reg = new RegExp(/\d{8}/);
   		 	if(sn.length!=8||!reg.test(sn)){
   		 		alert("产品序列号必须为8位纯数字");
   		 	}
   		 });
   		 $("#mac").change(function(){
   		 	var mac = $("#mac").val();
   		    if(mac.length!=12){
   		    	alert("mac地址必须为12位16进制数！");
   		    }
   		 });
    });
</script>
<title>add product order!</title>
</head>
<body>
<div class="rhead">
	<div class="rpos">当前位置: 生产管理 - 添加</div>
	<div class="clear"></div>
</div>
<html:form action="order.do" method="post">
<table id="table" class="pn-ltable">
<tr><td class="pn-flabel pn-flabel-h">名称</td><td class="pn-fcontent"><html:text property="name" value=""></html:text></td><td class="pn-flabel pn-flabel-h">设备类型</td><td class="pn-fcontent"><html:text property="device_type" value="" title="设备类型只允许为1位" styleId="device-type"></html:text></td></tr>
<tr><td class="pn-flabel pn-flabel-h">开始时间</td><td class="pn-fcontent"><html:text property="start_time" value=""></html:text></td><td class="pn-flabel pn-flabel-h">结束时间</td><td class="pn-fcontent"><html:text property="end_time" value=""></html:text><label style="color: red">&nbsp&nbsp*注意：开始和结束时间格式必须类似：2011/05/08</label></td></tr>
<tr><td class="pn-flabel pn-flabel-h">生产商代号</td><td class="pn-fcontent"><html:text property="manufacturer_code" value="" title="生产商代号只允许为3位" styleId="manu-code"></html:text></td><td class="pn-flabel pn-flabel-h">产品型号</td><td class="pn-fcontent"><html:text property="production_code" value="" title="产品型号必须为4位" styleId="pro-code"></html:text></td></tr>
<tr><td class="pn-flabel pn-flabel-h">序列号</td><td class="pn-fcontent"><html:text property="sn" value="" title="序列号不允许超过8位，且必须为纯数字！" styleId="sn"></html:text></td><td class="pn-flabel pn-flabel-h">MAC地址</td><td class="pn-fcontent"><html:text property="mac" value="" title="mac地址必须为12位，且必须是16进制数" styleId="mac"></html:text></td></tr>
<tr><td class="pn-flabel pn-flabel-h">产量</td><td class="pn-fcontent"><html:text property="num" value=""></html:text></td><td class="pn-flabel pn-flabel-h">其他</td><td class="pn-fcontent"><html:text property="inf_code" value=""></html:text></td></tr>
<tr><td class="pn-flabel pn-flabel-h">测试选项</td><td class="pn-fcontent">
	<input type="text" name="selOption" value=""  id="op" class="required" style="width: 180px"/></td><td>
	<select name="option" multiple="multiple" onclick="add();" id="sel" > 
<logic:iterate id="option" name="options">
	<option value="${option.name}"><bean:write name="option" property="name"/></option>
</logic:iterate></select></td><td class="pn-fcontent"><input type="button" value="增加" onclick="add();"><input type="button" value="删除" onclick="del();"></td></tr>
<tr style="top:50px"><td class="pn-flabel pn-flabel-h" colspan="2" align="center"><html:submit property="" value="提交" ></html:submit></td><td><html:reset property="" value="取消"></html:reset></td><td><html:button property="" value="返回" onclick="back();"></html:button></td></tr>
</table>

</html:form>
</body>
</html>