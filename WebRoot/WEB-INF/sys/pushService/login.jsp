<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<HTML xmlns="http://www.w3.org/1999/xhtml">

<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<link href="./dev/css/login.css" rel="stylesheet" type="text/css" />

	<SCRIPT type=text/javascript>
	var xajaxRequestUri = "http://192.9.50.253/bugfree/Login.php";
	var xajaxDebug = false;
	var xajaxStatusMessages = false;
	var xajaxWaitCursor = true;
	var xajaxDefinedGet = 0;
	var xajaxDefinedPost = 1;
	var xajaxLoaded = false;
	function xajax_xCheckUserLogin() {
		return xajax.call("xCheckUserLogin", arguments, 1);
	}
	function xajax_xSelectLanguage() {
		return xajax.call("xSelectLanguage", arguments, 1);
	}
	</SCRIPT>

	<SCRIPT src="./dev/js/xajax.js" type=text/javascript></SCRIPT>
	<SCRIPT src="./dev/js/Main.js" type=text/javascript></SCRIPT>

	<META content="MSHTML 6.00.2900.2180" name=GENERATOR>
</HEAD>
	
	<BODY>
		<DIV id=LoginContainer>
			<IMG class=loginBgImage src="./dev/img/login_bg_left.gif">
			<DIV id=LoginMain>
				<DIV id=LoginLogo>
					<SPAN id=Version>2.1.2</SPAN>
				</DIV>
				<DIV id=LoginFormContainer>
					<FORM id=LoginForm
						action="devlogin.do"
						method=post>
						<P>
							<LABEL for=TestUserName>
								<STRONG id=ForTestUserName>用户名:</STRONG>
							</LABEL>
							<INPUT class=TxtInput id=TestUserName name=userName>
						</P>
						<P>
							<LABEL for=TestUserPWD>
								<STRONG id=ForTestUserPWD>密&nbsp;&nbsp;&nbsp;码:</STRONG>
							</LABEL>
							<INPUT class=TxtInput id=TestUserPWD type=password
								name=passwd>
						</P>
						<!--P>
							<LABEL for=Language>
								<STRONG id=ForLanguage>选择语言:</STRONG>
							</LABEL>
							<SELECT class="NormalSelect MyInput select" id=Language
								onchange="xajax_xSelectLanguage(this.value);return false;"
								name=Language>
								<OPTION value=ZH_CN_UTF-8 selected>
									简体中文
								</OPTION>
								<OPTION value=EN_UTF-8>
									English
								</OPTION>
							</SELECT>
						</P-->
						<P>
							<LABEL>
								&nbsp;
							</LABEL>
							<INPUT id=RememberLoginStatus type=checkbox value=1
								name=RememberLoginStatus>
							<SPAN id=ForRememberMe style="MARGIN: 20px 0px 0px">记住密码</SPAN>
						</P>
						<P>
							<INPUT class=loginSubmit id=SubmitLoginBTN accessKey=L
								type=submit value=登录(L)>
							<INPUT type=hidden name=HttpRefer>
						</P>
						<DIV class=notice id=ActionMessage style="DISPLAY: none"></DIV>
					</FORM>
				</DIV>
			</DIV>
			<IMG class=loginBgImage src="./dev/img/login_bg_right.gif">
			<BR class=clear>
			<DIV id=shadow>
				<IMG class=loginBgImage src="./dev/img/login_shadow_left.gif">
				<DIV id=ShadowCenter>
					<CENTER>
						<BR>
					</CENTER>
					<CENTER>
						<BR>
					</CENTER>
				</DIV>
				<IMG class=loginBgImage src="./dev/img/login_shadow_right.gif">
			</DIV>
		</DIV>
		<SCRIPT src="./dev/js/pngfix.js" defer type=text/javascript></SCRIPT>

		<SCRIPT type=text/javascript>
	//         
	if (TestCookie.read('BFUserName') == '') {
		xajax.$('TestUserName').focus();
	} else {
		xajax.$('TestUserPWD').focus();
	}
	//
</SCRIPT>
	</BODY>
</HTML>
