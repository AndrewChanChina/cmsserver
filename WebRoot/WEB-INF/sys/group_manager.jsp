<%@ page language="java" pageEncoding="GB18030"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link href="./css/admin.css" rel="stylesheet" type="text/css" />
	<link href="./css/theme.css" rel="stylesheet" type="text/css" />
	<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css" />
	<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css" />
	<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<logic:empty name='groupForm' property='id'>
		<div>
			创建分组：
		</div>
	</logic:empty>
	<logic:notEmpty name='groupForm' property='id'>
		<div>
			修改分组：
		</div>
	</logic:notEmpty>

	<form method="post" action="saveupdategroup.do" id="jvForm">
		<span class="pn-frequired">*</span> 分组名:
		<input type="text" maxlength="100" name="groupName"
			value="${groupForm.groupName}" />
		<br>
		<input type="hidden" name="id" value="${groupForm.id}" />
		<input type="hidden" name="hideId" value="${groupForm.hideId}" />
		<input type="submit" value="提交" />
		&nbsp;
		<input type="reset" value="重置" />
	</form>
</body>
</html:html>
