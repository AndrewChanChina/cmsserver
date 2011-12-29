<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>

<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="tiles" %>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
	<logic:empty name='PurviewForm' property='id'>
		<div>
			创建新的权限：
		</div>
	</logic:empty>
	<logic:notEmpty name='PurviewForm' property='id'>
		<div>
			修改修改：
		</div>
	</logic:notEmpty>

	<form method="post" action="saveupdatepurview.do" id="jvForm">
			<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
				border="0">
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>名称:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" maxlength="100" name="purviewName"
							vld="{required:true,username:true,remote:'v_check_username.do',messages:{remote:'用户名已被使用'}}"
							maxlength="100" value="${PurviewForm.purviewName}"/>
					</td>					
				</tr>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						描述:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" maxlength="100" name="purviewInfo"
							size="30" maxlength="100" value="${PurviewForm.purviewInfo}"/>
					</td>
				</tr>				
				<tr>
					<td>
					<td colspan="4" class="pn-fbutton">
						<input type="submit" value="提交" />
						&nbsp;
						<input type="reset" value="重置" />
					</td>
				</tr>
			</table>
			<input type="hidden" name="hideId"
						value="${PurviewForm.hideId}"/>
			<input type="hidden" name="id"
						value="${PurviewForm.id}"/>
		</form>
</body>
</html:html>
