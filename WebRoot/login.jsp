<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/jcaptcha.tld" prefix="jcaptcha" %> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>login</title>
		<link href="./css/admin.css" rel="stylesheet" type="text/css" />
		<link href="./css/theme.css" rel="stylesheet" type="text/css" />
		<link href="./css/jquery.validate.css" rel="stylesheet"
			type="text/css" />
		<link href="./css/jquery.treeview.css" rel="stylesheet"
			type="text/css" />
		<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css" />

		<style type="text/css">
body {
	margin: 0;
	padding: 0;
	font-size: 12px;
	background: url(./images/login/bg.jpg) top repeat-x;
}

.input {
	width: 150px;
	height: 17px;
	border-top: 1px solid #404040;
	border-left: 1px solid #404040;
	border-right: 1px solid #D4D0C8;
	border-bottom: 1px solid #D4D0C8;
}
</style>

		<script type="text/javascript">
		
		function refresh_jcaptcha(obj){ 
			//alert(obj); 
			obj.src="jcaptcha.do?" + Math.random();
		} 
		
	if (top != this) {
		top.location = this.location;
	}
	$(function() {
		$("#username").focus();
		$("#jvForm").validate();
	});
</script>
	</head>
	<body>
		
		<form id="jvForm" action="login_admin.do" method="post">
			<table width="750" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="200">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="423" height="280" valign="top">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<img src="./images/login/ltop.jpg" />
											</td>
										</tr>
										<tr>
											<td>
												<img src="./images/login/llogo.jpg" />
											</td>
										</tr>
									</table>
								</td>
								<td width="40" align="center" valign="bottom">
									<img src="./images/login/line.jpg" width="23" height="232" />
								</td>
								<td valign="top">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="90" align="center" valign="bottom">
												<img src="./images/login/ltitle.jpg" />
											</td>
										</tr>
										<tr>
											<td>

												<table width="100%" border="0" align="center"
													cellpadding="0" cellspacing="5">
													<tr>
														<font color="red"> <html:errors /> </font>
														<html:errors property="username"/>
													</tr>
													<tr>
														<td width="91" height="40" align="right">
															<strong>用户名：</strong>
														</td>
														<td width="211">
															<input type="input" id="userName" name="userName" vld=""
																maxlength="100" class="input" />
														</td>
													</tr>
													<tr>
														<td height="40" align="right">
															<strong>密码：</strong>
														</td>
														<td>
															<input name="passwd" type="password" id="passwd"
																maxlength="32" vld="{required:true}" class="input" />
														</td>
													</tr>

													<tr>
														<td height="40" align="right">
															<strong>验证码：</strong>
														</td>
														<td>
															<input name="jcaptcha_response" type="text"  vld=""
																class="input" />&nbsp;&nbsp;
																
															
														
														
														</td>
													</tr>
													<tr>
														<td align="center" colspan="2">
												         <img name ="jcaptcha" id ="jcaptcha" onclick ="refresh_jcaptcha(this)" src ="jcaptcha.do" alt ="click to refresh" style ="cursor:pointer;"/> 
												    	</td>
												    	
													</tr>
													<tr>
														<td height="40" colspan="2" align="center">
															<input type="image" src="./images/login/login.jpg"
																name="submit" />
															&nbsp; &nbsp;
															<img name="reg" style="cursor: pointer"
																src="./images/login/reset.jpg" onclick="" />
															&nbsp; &nbsp;<a href="register.jsp">注册</a>
															
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>

	</body>
</html>
