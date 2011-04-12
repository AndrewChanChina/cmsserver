<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>Register</title>   
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
  </head>
  
<body>

<form method="post" action="register.do" id="jvForm">
<span class="pn-frequired">*</span>用户名:
<input type="text" maxlength="100" name="username" 
vld="{required:true,username:true,remote:'v_check_username.do',messages:{remote:'用户名已被使用'}}" maxlength="100"/>
<br>电子邮箱:
<input type="text" maxlength="100" name="email" class="email" size="30" maxlength="100"/>
<br><span class="pn-frequired">*</span>密码:
<input type="password" autocomplete="off" id="password" maxlength="100" name="password" class="required" maxlength="100"/>
<span class="pn-frequired">*</span>确认密码:
<input type="password" autocomplete="off" equalTo="#password"/><br>
<input type="submit" value="提交"/> &nbsp; <input type="reset" value="重置"/>

</form>
</body>
</html>
