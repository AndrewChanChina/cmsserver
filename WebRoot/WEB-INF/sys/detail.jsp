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
  <body><form method="get" action="logAction" name="detailForm"><p>&nbsp;</p><table border="0" width="200">
<tbody><tr>
<td>DeviceType</td>
<td>&nbsp;<input type="text" name="deviceType"></td></tr>
<tr>
<td>&nbsp;TestStatus</td>
<td>&nbsp;<input type="text" name="testStatus"></td></tr>
<tr>
<td>&nbsp;Note</td>
<td>&nbsp;<input type="text" name="note"></td></tr>
<tr>
<td>&nbsp;FilePath</td>
<td>&nbsp;<input type="text" name="filePath"></td></tr><tr><td valign="top"><input type="submit" value="确定" name="ok"></td><td valign="top"><input type="reset" value="取消" name="reset"></td></tr>
</tbody></table></form> 
    
  </body>
</html>
