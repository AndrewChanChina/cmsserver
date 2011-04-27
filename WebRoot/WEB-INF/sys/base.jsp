<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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

  </head>
  
  <body>
   <form method="get" action="BaseLogAction" name="baseLogForm"><p>&nbsp;</p><table border="0" width="297" height="302">
<tbody><tr>
<td>&nbsp;MachineID</td>
<td>&nbsp;<input type="text" name="machineID"></td></tr>
<tr>
<td>&nbsp;MachineType</td>
<td>&nbsp;<input type="text" name="machineID"></td></tr>
<tr>
<td>&nbsp;SystemVersion</td>
<td>&nbsp;<input type="text" name="machineID"></td></tr>
<tr>
<td>&nbsp;SoftWareVersion</td>
<td>&nbsp;<input type="text" name="machineID"></td></tr>
<tr>
<td>&nbsp;TestStatus</td>
<td>&nbsp;<input type="text" name="machineID"></td></tr>
<tr>
<td>&nbsp;FilePath</td>
<td>&nbsp;<input type="text" name="filePath"></td></tr>
<tr>
<td>&nbsp;<input type="submit" value="提交" name="tijiao"></td>
<td>&nbsp;<input type="reset" value="取消" name="qvxiao"></td></tr>
</tbody></table></form></body>
</html>
