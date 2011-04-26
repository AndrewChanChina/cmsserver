<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<link href="./css/admin.css" rel="stylesheet" type="text/css" />
	<link href="./css/theme.css" rel="stylesheet" type="text/css" />
	<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css" />
	<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css" />
	<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<a href="pushservicemanage.do?opt=list">显示所有服务</a><br>
	<a href="pushservicemanage.do?opt=pushData">开发者推送服务</a><br>
	
	<form method="post" action="pushdata.do" id="jvForm">
			<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
				border="0">
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						服务类型：
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="radio" name="servicetype" value = "message" checked>文本消息&nbsp
						<input type="radio" name="servicetype" value = "nofity">提示消息&nbsp
						<input type="radio" name="servicetype" value = "alert">震动&nbsp
					</td>					
				</tr>
				<logic:notEmpty name='pushServiceList'>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						服务名称:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<select name="serviceName">
							<c:forEach items="${pushServiceList}" var="pushService">
								<option value="${pushService.id}" >								
								${pushService.serviceName}
								</option>	
							</c:forEach>						
						</select>						
					</td>
				</tr>
				</logic:notEmpty>
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
</html:html>
