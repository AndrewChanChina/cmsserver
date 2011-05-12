<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>

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

	
	<script type="text/javascript">
	function getTableForm() {
		return document.getElementById('tableForm');
	}
	function optDelete() {
		if (Pn.checkedCount('ids') <= 0) {
			alert("请选择您要操作的数据");
			return;
		}
		if (!confirm("您确定删除吗？")) {
			return;
		}
		var f = getTableForm();
		f.action = "pushservicemanage.do?opt=delete";
		f.submit();
	}
</script>

</head>

<body>

	<div class="body-box">
		<div class="rhead">
			<div class="rpos">
				当前位置: 权限管理 - 列表
			</div>
			<a href="home_developer.do">返回</a>
			<form class="ropt" method="post">				
				<input type="submit" value="添加"
					onclick="this.form.action='pushservicemanage.do?opt=view';" />
			</form>
			<div class="clear"></div>
		</div>
		<form id="tableForm" method="post">
			<input type="hidden" name="pageNo" value="" />
			<table class="pn-ltable" style="" width="100%" cellspacing="1"
				cellpadding="0" border="0">
				<thead class="pn-lthead">
					<tr>
						<th width="20">
							<input type='checkbox' onclick='Pn.checkbox("ids",this.checked)' />
						</th>
						<th>
							ID
						</th>
						<th>
							服务名称
						</th>
						<th>
							服务ID
						</th>
						<th>
							创建时间
						</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pushServiceList}" var="pushService">
						<tr>
							<td>
								<input type='checkbox' name='ids' value='1' />
							</td>
							<td>
								${pushService.id}
							</td>
							<td>
								${pushService.serviceName}
							</td>
							<td>
								${pushService.serviceId}
							</td>
							<td>
								${pushService.createtime}
							</td>
							<td align="center">
								<a href="pushservicemanage.do?opt=view&id=${pushService.id}" class="pn-opt">修改</a>
								|
								<a href="pushservicemanage.do?opt=delete&id=${pushService.id}"
									onclick="if(!confirm('您确定删除吗？')) {return false;}"
									class="pn-opt">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<smitpage:page pager="${pb}" />
			</div>
		</form>
	</div>
</body>
</html:html>
