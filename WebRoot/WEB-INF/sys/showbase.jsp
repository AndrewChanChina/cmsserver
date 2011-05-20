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
  
    <title>My JSP 'base.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	
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
	function query(){
			window.location.href = "queryBase.do";
	}
		
	function upload(){
			window.location.href = "showAddBase.do";
	}
	$(document).ready(function(){
		$("a#curPage").click(function(){
			alert("hello!");
			$.post("queryBase.do",null,function(data){
				//alert(data);
			},"html");
		});
	});
	</script>
  </head>
  <body>
   <form method="post" action="queryBase.do" style="padding-top:5px;">
   <table border="0">
<tbody><tr>
<td>CheckID:</td><td><input type="text" name="checkID"></td>
<td><input type="submit" value="查询"></td>
<td><input type="button" value="上传"  onclick="upload();"></td></tr>
</tbody></table></form>
<form action="">
<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead"><tr>
	<th width="20"><input type='checkbox' onclick='Pn.checkbox("ids",this.checked)'/></th>
	<th>MachineID</th>
	<th>MachineType</th>
	<th>SystemVersion</th>
	<th>SoftwareVersion</th>
	<th>TestStatus</th>
	<th>CreateTime</th>
	</tr></thead>
<tbody  class="pn-ltbody">
<logic:notEmpty name="baseLogs">
 <logic:iterate id="baseLog" name="baseLogs">
	<tr>
	<td><input type="checkbox" onclick='Pn.checkbox("ids",this.checked)'/></td>
	<td><bean:write name="baseLog" property="machineID"></bean:write></td>
	<td><bean:write name="baseLog" property="machineType"></bean:write></td>
	<td><bean:write name="baseLog" property="sysVersion"></bean:write></td>
	<td><bean:write name="baseLog" property="softwareVersion"></bean:write></td>
	<td><bean:write name="baseLog" property="testStatus"></bean:write></td>
	<td><bean:write name="baseLog" property="create_time"></bean:write></td>
	</tr>
	</logic:iterate>
	</logic:notEmpty>
	</tbody>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td align="center" class="pn-sp">
	当前第<bean:write name="page" property="currentPage"/>页&nbsp;&nbsp;

	<a id="curPage" href="queryBase.do?currentPage=<bean:write name='page' property='currentPage'/>&checkID=<bean:write name='checkID'/>">上一页</a>
	<a href="content.do?op=list&pid=<%=request.getAttribute("pid") %>&pn=">下一页</a>&nbsp;&nbsp;
	共<bean:write name="page" property="count"/>页&nbsp;&nbsp;
	共<bean:write name="page" property="totalRecord"/>记录数&nbsp;&nbsp;
	跳转<input type="text" name="pn" id="pn"/>页<input type="hidden" />
	<input type="button" value="go" onclick="goPage()"/>
</td></tr></table>
</form>
</body>
</html>
