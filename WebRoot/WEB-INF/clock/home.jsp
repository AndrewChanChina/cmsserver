<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
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
	<script type="text/javascript" src="../js/jquery.js"></script>
	<!-- 表单的逻辑 -->
	<script type="text/javascript">
	$(document).ready(function(){
		
			$("#list").css("background-color","red");
			$("#add").click(function(){
				//$("#list").hide();
				
				
			});
		});
		function getTableForm() {
		return document.getElementById('tableForm');
		
		}
		function optAdd(){
			if (!confirm("您确定删除吗？")) {
				return;
			}
			$("#result").load("clock_add.do?");
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
		f.action = "clock_del.do";
		f.submit();
	}
   </script>   
   </head>  
   
   <body>
   <div id="add">添加闹钟<br>
   <div id="result"></div>
   <form id=addForm
		 action="clock_add.do"
		 method=post>
		 <div>名称：</div>
		 <INPUT id="ii" name="hour">
		 <INPUT id="dd" type="submit" value="登录(L)" onclick="optAdd()">
   </form>
   </div><br>
   <div id="list">
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
							闹钟
						</th>
						<th>
							时间
						</th>
						<th>
							重复
						</th>
						<th>
							开启
						</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${clockList}" var="clock">
						<tr>
							<td>
								<input type='checkbox' name='ids' value='1' />
							</td>
							<td>
								${clock.id}
							</td>
							<td>
								${clock.name}
							</td>
							<td>
								${clock.hour} : ${clock.minutes}
							</td>
							<td>
								${clock.enable}
							</td>
							<td align="center">
								<a href="clock_update.do?id=${clock.id}&name=555" class="pn-opt">修改</a>
								|
								<a href="clock_del.do?id=${clock.id}"
									onclick="if(!confirm('您确定删除吗？')) {return false;}"
									class="pn-opt">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				
			</div>
		</form>
   </div>
   </body> 
   </html:html>