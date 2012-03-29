<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'developer_pushData.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <form method="post" action="pushdata.do?opt=pushDev" id="jvForm">
			<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
				border="0">
				<tr>
				<td>
				</td>
				<td>
				<p>这是开发者测试发送页面</p>
				<a href="logoutDev.do">登出</a><br>
				<a href="home_developer.do">返回</a>
				</td>
				</tr>	
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						服务名称:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<select name="serviceName">
							<c:forEach items="${pushServiceList}" var="pushService">
								<option value="${pushService.serviceId}" >								
								${pushService.serviceName}
								</option>	
							</c:forEach>						
						</select>
						<logic:empty name="pushServiceList">
						你的服务列表是空的，请添加！
						</logic:empty>						
					</td>
				</tr>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						输入用户的push id:（以分号隔开）
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" name="pushIds">
					</td>
				</tr>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						消息唯一标示:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" name="collapseKey">
					</td>
				</tr>	
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						是否需要支持离线信息:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="radio" name="isDelay" value="yes" checked>是 &nbsp
						<input type="radio" name="isDelay" value="no">否 &nbsp
					</td>
				</tr>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						标题:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" name="title">
					</td>
				</tr>	
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						传单标题:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" name="ticket">
					</td>
				</tr>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						超链接:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" name="uri">
					</td>
				</tr>	
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						消息内容:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<textarea cols ="30" rows = "5" name="message"></textarea>
					</td>
				</tr>				
				<tr>
					<td>&nbsp;
					<td colspan="4" class="pn-fbutton">
						<input type="submit" value="提交" />
						&nbsp;
						<input type="reset" value="重置" />
					</td>
				</tr>
				
			</table>
			<input type="hidden" name="hideId"
						value="${groupForm.hideId}"/>			
		</form>
  </body>
</html>
