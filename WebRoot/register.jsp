<%@ page language="java" pageEncoding="GB18030"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	用户注册
	<div class="body-box">
		<div class="rhead">			
			<form class="ropt">
				<input type="submit" value="返回登录"
					onclick="this.form.action='login.do';" />
			</form>
		</div>
		<form method="post" action="register.do" id="jvForm">
			<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
				border="0">
				<tr>
					<font color="red">
						<html:errors/>
					</font>
				</tr>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>用户名:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" maxlength="100" name="username"
							vld="{required:true,username:true,remote:'v_check_username.do',messages:{remote:'用户名已被使用'}}"
							maxlength="100" value="${userForm.userName}"/>
					</td>					
				</tr>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						电子邮箱:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" maxlength="100" name="email" class="email"
							size="30" maxlength="100" value="${userForm.email}"/>
					</td>
				</tr>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>密码:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" 
							maxlength="100" name="password" class="required" maxlength="100" 
							value="${userForm.password}"/>
					</td>					
				</tr>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>确认密码:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text"  name="password2"
						value="${userForm.password}"/>
					</td>
				</tr>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						电话:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" name="tel" value="${userForm.tel}"/>
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
						value="${userForm.hideId}"/>
			<input type="hidden" name="id"
						value="${userForm.id}"/>
		</form>
	</div>
</body>
</html:html>
