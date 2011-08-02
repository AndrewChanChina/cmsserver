<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
  </head>
  
  <body>
    <div class="rhead">
		<div class="rpos">当前位置: 设备查询 - 列表</div>
		<div class="clear"></div>
	</div>
	<form action="queryDevice.do" method="post" style="padding-top:5px;" id="orderForm">
生产代号: <input type="text" name="order_code" value="" style="width:100px"/>
产品型号: <input type="text" name="production_code" value="" style="width:100px" maxlength="255"/>
<!-- 
生产商代号: <input type="text" name="production_code" value="" style="width:100px" maxlength="255"/> -->
			<input type="submit" value="查询"/><input type="button" value="添加" onclick="add();"/><input type="button" value="修改"/><input type="button" value="删除"/>
</form>
<form action="order.do" method="post">
	
<table class="pn-ltable"  width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead"><tr>
	<th width="20"><input type='checkbox' onclick='Pn.checkbox("ids",this.checked)'/></th>
	<th>ID</th>
	<th>设备名</th>
	<th>设备号</th>
	<th>生产代号</th>
	<th>生产商代号</th>
	<th>产品型号</th>
	<th>序列号</th>
	<th>MAC地址</th>
	<th>激活时间</th>
	</tr></thead>
	<tbody  class="pn-ltbody">
		<logic:notEmpty name="list">
			<logic:iterate id="test" name="list">
				<tr>
				<td><input type="checkbox"/>
				<td><bean:write name="test" property="id"/></td>
				<td><bean:write name="test" property="name"/></td>
				<td><bean:write name="test" property="machineID"/></td>
				<td><bean:write name="test" property="order_code"/></td>
				<td><bean:write name="test" property="username"/></td>
				<td><bean:write name="test" property="password"/></td>
				<td><bean:write name="test" property="sn"/></td>
				<td><bean:write name="test" property="mac"/></td>
				<td><bean:write name="test" property="create_time"/></td>
				</tr>
			</logic:iterate>
		</logic:notEmpty>
		</tbody>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td align="center" class="pn-sp">
	当前第<bean:write name="page" property="currentPage"/>页&nbsp;&nbsp;
	共<bean:write name="page" property="count"/>页&nbsp;&nbsp;
	<a id="curPage" href="pageDevice.do?type=pre&currentPage=<bean:write name='page' property='currentPage'/>">上一页</a>
	<a id="prePage" href="pageDevice.do?type=next&currentPage=<bean:write name='page' property='currentPage'/>">下一页</a>&nbsp;&nbsp;
	</td></tr></table>
</form>	
  </body>
</html>
