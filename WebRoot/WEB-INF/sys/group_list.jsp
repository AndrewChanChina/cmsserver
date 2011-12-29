<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="tiles" %>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <base href="<%=basePath%>">
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	
	<link href="./css/admin.css" rel="stylesheet" type="text/css"/>
	<link href="./css/theme.css" rel="stylesheet" type="text/css"/>
	<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css"/>
	<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
	<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css"/>

	<script type="text/javascript">
		function getTableForm() {
			return document.getElementById('tableForm');
		}
		function optDelete() {
			if(Pn.checkedCount('ids')<=0) {
				alert("请选择您要操作的数据");
				return;
			}
			if(!confirm("您确定删除吗？")) {
				return;
			}
			var f = getTableForm();
			f.action="deletegroup.do";
			f.submit();
		}
		function optPriority() {
			var f = getTableForm();
			f.action="o_priority.do";
			f.submit();
		}
	</script>

  </head>
  
  <body>
   
   	<div class="body-box">
	<div class="rhead">
		<div class="rpos">前位置: 会员组管理 - 列表</div>
		<form class="ropt">
			<input type="submit" value="添加" onclick="this.form.action='gonewgroup.do';"/>
		</form>
		<div class="clear"></div>
	</div>
	<form id="tableForm" method="post">
	<input type="hidden" name="pageNo" value=""/>
	<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
			<tr>
				<th width="20"><input type='checkbox' onclick='Pn.checkbox("ids",this.checked)'/></th>
				<th>ID</th>
				<th>名称</th>
				<th>创建时间</th>				
				<th>默认组</th>
				<th>操作选项</th>
			</tr>
		</thead>
		<tbody  class="pn-ltbody">
			<c:forEach items="${grouplist}" var="group">		   
			<tr>
			<td><input type='checkbox' name='ids' value='1'/></td>
			<td>${group.id}</td>
			<td>${group.groupName}</td>	
			<td>${group.createtime}</td>		
			<td align="center">
			<c:forEach items="${group.purviews}" var="purview">
			${purview.purviewName}&nbsp
			</c:forEach>
			</td>						
			<td align="center">		<a href="gonewgroup.do?id=${group.id}" class="pn-opt">修改</a> | <a href="deletegroup.do?id=${group.id}" onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div><smitpage:page pager="${pb}"/></div>
	<div>
		<input type="button" value="删除" onclick="optDelete();"/>
		&nbsp; <input type="button" value="保存排列顺序" onclick="optPriority();"/>
	</div>
</form>
</div>
  </body>
</html:html>