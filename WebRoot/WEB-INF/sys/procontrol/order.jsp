<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>product order</title>
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
	//document.getElementById("orderForm").submit();
	window.location.href="showOrder.do?type=add";
	}
</script>
</head>
<body>
<div class="rhead">
	<div class="rpos">当前位置: 生产管理 - 列表</div>
	<div class="clear"></div>
</div>
<form action="queryOrder.do" method="post" style="padding-top:5px;" id="orderForm">
生产代号: <input type="text" name="order_code" value="" style="width:100px"/>
产品型号: <input type="text" name="production_code" value="" style="width:100px"/>
<input type="submit" value="查询"/><input type="button" value="添加" onclick="add();"/><input type="button" value="修改"/><input type="button" value="删除"/>
</form>
<form action="order.do" method="post">
	
<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead"><tr>
	<th width="20"><input type='checkbox' onclick='Pn.checkbox("ids",this.checked)'/></th>
	<th>ID</th>
	<th>生产代号</th>
	<th>开始时间</th>
	<th>结束时间</th>
	<th>设备类型</th>
	<th>生产商代号</th>
	<th>产品型号</th>
	<th>序列号</th>
	<th>其他信息</th>
	</tr></thead>
<tbody  class="pn-ltbody">
<logic:notEmpty name="list">
 <logic:iterate id="test" name="list">
	<tr>
	<td><input type="checkbox" onclick='Pn.checkbox("ids",this.checked)'/></td>
	<td><bean:write name="test" property="id"></bean:write></td>
	<td><bean:write name="test" property="order_code"></bean:write></td>
	<td><bean:write name="test" property="start_time"></bean:write></td>
	<td><bean:write name="test" property="end_time"></bean:write></td>
	<td><bean:write name="test" property="device_type"></bean:write></td>
	<td><bean:write name="test" property="manufacturer_code"></bean:write></td>
	<td><bean:write name="test" property="production_code"></bean:write></td>
	<td><bean:write name="test" property="sn"></bean:write></td>
	<td><bean:write name="test" property="inf_code"></bean:write></td>
	</tr>
	</logic:iterate>
	</logic:notEmpty>
	</tbody>
	</table>
</form>
</body>
</html>