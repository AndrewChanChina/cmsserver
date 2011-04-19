<%@ page language="java" pageEncoding="GB18030"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
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

	<script src="./js/fckeditor.js" type="text/javascript"></script>
	<script src="./js/WdatePicker.js" type="text/javascript"></script>
	<script src="./js/jquery.js" type="text/javascript"></script>
	<script src="./js/jquery.ext.js" type="text/javascript"></script>
	<script src="./js/pony.js" type="text/javascript"></script>
	<script src="./js/admin.js" type="text/javascript"></script>
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
		f.action = "deletegroup.do";
		f.submit();
	}
	function optPriority() {
		var f = getTableForm();
		f.action = "o_priority.do";
		f.submit();
	}
</script>

</head>

<body>

	<div class="body-box">
		<div class="rhead">
			<div class="rpos">
				当前位置: 用户管理 - 列表
			</div>
			<form class="ropt">
				<input type="submit" value="添加"
					onclick="this.form.action='gonewuser.do';" />
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
							姓名
						</th>
						<th>
							密码
						</th>
						<th>
							邮件
						</th>
						<th>
							电话
						</th>
						<th>
							所属分组
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${listUser}" var="user">
						<tr>
							<td>
								<input type='checkbox' name='ids' value='1' />
							</td>
							<td>
								${user.id}
							</td>
							<td>
								${user.userName}
							</td>
							<td>
								${user.password}
							</td>
							<td>
								${user.email}
							</td>
							<td>
								${user.tel}
							</td>
							<td>
								${user.group.groupName}
							</td>
							<td align="center">
								<a href="gonewuser.do?id=${user.id}" class="pn-opt">修改</a> |
								<a href="deleteuser.do?id=${user.id}"
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
			<div>
				<input type="button" value="删除" onclick="optDelete();" />
				&nbsp;
				<input type="button" value="保存排列顺序" onclick="optPriority();" />
			</div>
		</form>
	</div>
</body>
</html:html>
