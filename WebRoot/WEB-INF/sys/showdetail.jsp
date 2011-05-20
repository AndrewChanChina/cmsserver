<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'base.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="./css/admin.css" rel="stylesheet" type="text/css"/>
	<link href="./css/theme.css" rel="stylesheet" type="text/css"/>
	<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css"/>
	<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
	<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
		function showdetail(){
			window.location.href = "showAddDetail.do";
		}
	</script>
  </head>
  <body>
  <form method="post" action="queryDetail.do" >
  <table>
  <tr><td>CheckID:</td><td><input type="text" name="checkID"/></td><td><input type="submit" value="查询"/></td><td><input type="button" onclick="showdetail();" value="添加"/></td></tr>
  </table>

</form>
<form action="">
<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead"><tr>
	<th width="20"><input type='checkbox' onclick='Pn.checkbox("ids",this.checked)'/></th>
	<th>测试项</th>
	<th>测试状态</th>
	<th>备注</th>
	<th>创建时间</th>
	</tr></thead>
<tbody  class="pn-ltbody">
<logic:notEmpty name="devices">
 <logic:iterate id="device" name="devices">
	<tr>
	<td><input type="checkbox" onclick='Pn.checkbox("ids",this.checked)'/></td>
	<td><bean:write name="device" property="testOption.name"></bean:write></td>
	<td><bean:write name="device" property="testStatus"></bean:write></td>
	<td><bean:write name="device" property="note"></bean:write></td>
	<td><bean:write name="device" property="create_time"></bean:write></td>
	</tr>
	</logic:iterate>
	</logic:notEmpty>
	</tbody>
	</table>
</form>
  </body>
</html>
