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
	<link href="./css/admin.css" rel="stylesheet" type="text/css"/>
	<link href="./css/theme.css" rel="stylesheet" type="text/css"/>
	<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css"/>
	<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
	<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css"/>
	
  </head>
  <body><form method="post" action="detailLog.do" id="detailForm" enctype="multipart/form-data"><p>&nbsp;</p><table border="0" width="200">
<tbody>
<!--  
<tr>
<td width="10%" class="pn-flabel pn-flabel-h" style="overflow:hidden;white-space:nowrap;">机器类型:</td>
<td>&nbsp;<input type="text" name="deviceType"></td></tr>
<tr>
<td  width="10%"style="overflow:hidden;white-space:nowrap;" class="pn-flabel pn-flabel-h">上传时间:</td>
<td>&nbsp;<input type="text" name="testStatus"></td></tr>
-->
<td style="overflow:hidden;white-space:nowrap;" class="pn-flabel pn-flabel-h">选择文件:</td>
<td>&nbsp;<input type="file" name="upload"></td></tr><tr><td valign="top"><input type="submit" value="确定" name="ok"></td><td valign="top"><input type="reset" value="取消" name="reset"></td></tr>
</tbody></table></form> 
    
  </body>
</html>
