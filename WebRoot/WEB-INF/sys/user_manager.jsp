<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="tiles" %>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
	<logic:empty name='userForm' property='id'>
		<div>
			创建用户：
		</div>
	</logic:empty>
	<logic:notEmpty name='userForm' property='id'>
		<div>
			修改用户：
		</div>
	</logic:notEmpty>

	<div class="body-box">
		<div class="rhead">
			<div class="rpos">
				当前位置: 管理员(全站) - 添加
			</div>
			<form class="ropt">
				<input type="submit" value="返回列表"
					onclick="this.form.action='listuser.do';" />
			</form>
			<div class="clear"></div>
		</div>
		<form method="post" action="saveupdateuser.do" id="jvForm">
			<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
				border="0">
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>用户名:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" maxlength="100" name="userName"
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
					<td width="12%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>会员组:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<select name="groupId">
							<c:forEach items="${groupList}" var="group">
								<option value="${group.id}" 
								<c:if test="${group.id==userForm.groupId}">selected</c:if> >
								${group.groupName}
								</option>	
							</c:forEach>						
						</select>
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
