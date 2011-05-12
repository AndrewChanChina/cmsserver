<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
	<logic:empty name='pushService'>
		<div>
			创建新的服务：
		</div>
	</logic:empty>
	<logic:notEmpty name='pushService'>
		<div>
			修改服务：
		</div>
	</logic:notEmpty>

	<form method="post" action="pushservicemanage.do?opt=save" id="jvForm">
		<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
			border="0">
			<tr>
				<td width="12%" class="pn-flabel pn-flabel-h">
					<span class="pn-frequired">*</span>服务名称:
				</td>
				<td colspan="1" width="38%" class="pn-fcontent">
					<input type="text" maxlength="100" name="serviceName"
						maxlength="100" value="${pushService.serviceName}" />
				</td>
			</tr>
			<logic:notEmpty name='pushService'>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						服务ID:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" name="serviceId" readonly
							value="${pushService.serviceId}" />
					</td>
				</tr>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						创建时间:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" name="createtime" readonly
							value="${pushService.createtime}" />
					</td>
				</tr>
			</logic:notEmpty>
			<tr>
				<td>
				<td colspan="4" class="pn-fbutton">									
					<input type="hidden" name="org.apache.struts.taglib.html.TOKEN"
					value="${sessionScope['org.apache.struts.action.TOKEN']}"></input>
					<input type="submit" value="提交" />
					&nbsp;
					<input type="reset" value="重置" />
				</td>
			</tr>
		</table>
	</form>
	<a href="home_developer.do">返回</a>
</body>
</html:html>
