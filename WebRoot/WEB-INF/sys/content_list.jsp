<%@page import="com.smit.util.Page"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
com.smit.util.Page contentsPage = (com.smit.util.Page)request.getAttribute("page");
List contents = new ArrayList();

if(contentsPage != null){
	contents = contentsPage.getList();
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
<link href="./css/admin.css" rel="stylesheet" type="text/css"/>
<link href="./css/theme.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<script src="./js/fckeditor.js" type="text/javascript"></script>
<script src="./js/WdatePicker.js" type="text/javascript"></script>
<script src="./js/jquery.js" type="text/javascript"></script>
<script src="./js/jquery.ext.js" type="text/javascript"></script>
<script src="./js/pony.js" type="text/javascript"></script>
<script src="./js/admin.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	$("#rejectDialog").dialog({
		autoOpen: false,
		modal: true,
		width: 380,
		height: 200,
		position: ["center",50],
		buttons: {
			"OK": function() {
				rejectSubmit();
			}
		}
	});
});
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
	f.action="content.do?op=delete";
	f.submit();
}
function optCheck() {
	if(Pn.checkedCount('ids')<=0) {
		alert("请选择您要操作的数据");
		return;
	}
	var f = getTableForm();
	f.action="content.do?op=check";
	f.submit();
}
function optReject() {
	if(Pn.checkedCount('ids')<=0) {
		alert("请选择您要操作的数据");
		return;
	}
	$("#rejectDialog").dialog("open");
}
function rejectSubmit() {
	$("input[name=rejectOpinion]").val($("#rejectOpinion").val());
	$("input[name=rejectStep]").val($("#rejectStep").val());
	$("#rejectDialog").dialog("close");
	var f = getTableForm();
	f.action="o_reject.do";
	f.submit();
}
function chgStatus() {
	var queryStatus = $("input[name=queryStatus]:checked").val();
	location.href="v_list.do?cid=&queryStatus=" + queryStatus;
}
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置: 内容管理 - 列表</div>
	<div class="clear"></div>
</div>
<form action="content.do?op=search" method="post" style="padding-top:5px;">
<div>
标题: <input type="text" name="title" value="" style="width:100px"/>
发布者: <input type="text" name="author" value="" style="width:70px"/>
<label><input type="checkbox" name="putter" value="1"/>置顶</label>
<label><input type="checkbox" name="prime" value="1"/>推荐精华</label>
<label><input type="checkbox" name="ischeck" value="1"/>己审核</label>
<select name="typename"><option value="" selected="selected">--所有类型--</option><option value="1">普通</option><option value="2">图文</option><option value="3">焦点</option><option value="4">头条</option></select>
<input type="submit" value="查询"/>
</div>


</form>
<form id="tableForm" method="post">
<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead"><tr>
	<th width="20"><input type='checkbox' onclick='Pn.checkbox("ids",this.checked)'/></th>
	<th>ID</th>
	<th>标题</th>
	<th>类型</th>
	
	<th>点击</th>
	<th>发布时间</th>
	<th>推荐精华</th>
	<th>审梳状态</th>
	<th>操作选项</th></tr></thead>
<tbody  class="pn-ltbody">
<%
	Iterator iter = contents.iterator();
	int i = 0;
	while(iter.hasNext()){
		com.smit.vo.Content content = (com.smit.vo.Content)iter.next();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yy-MM-dd");
		Date date = new Date(Long.parseLong(String.valueOf(content.getCreatetime())));
		String createtime = sdf.format(date);
		
		i++;
 %>
<tr>
	<td><input type='checkbox' name='ids' value='<%=content.getId()%>'/></td>
	<td><%=i %></td>
	<td>		
	
		<strong><%=content.getTypeName() %></strong>
		<%=content.getTitle() %>
</td>

	<td align="right"><%=content.getTypeName() %></td>
	
	<td align="center"><%=content.getOnclickCount()%></td>
	<td align="center"><%=createtime%></td>
		<td align="center"><% if(content.getPrime()!=null && content.getPrime() == 1){ %>	是<% }else  %>否</td>
	<td align="center"><% if(content.getIsCheck()!= null && content.getIsCheck()== 1){ %>	己审核 <% }else  %>未审核
</td>
	<td align="center">		<a href="content.do?op=view&id=<%=content.getId() %>" class="pn-opt">查看</a> | 		<a href="content.do?op=manager&id=<%=content.getId() %>" class="pn-opt">修改</a> | 		<a href="content.do?op=delete&id=<%=content.getId() %>" onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a>
		</td>
</tr>

<%
}
 %>

</tbody>
</table>
<% if(contentsPage != null){ %>
<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td align="center" class="pn-sp">
	当前第<%=contentsPage.getCurrentPage() %>页&nbsp;&nbsp;

	<a href="content.do?op=list&pid=<%=request.getAttribute("pid") %>&pn=<%=contentsPage.getPerPage()%>">上一页</a>
	<a href="content.do?op=list&pid=<%=request.getAttribute("pid") %>&pn=<%=contentsPage.getNextPage()%>">上一页</a>&nbsp;&nbsp;
	共<%=contentsPage.pageCount() %>页&nbsp;&nbsp;
	共<%=contentsPage.getTotalRecord() %>记录数&nbsp;&nbsp;
	跳转<input type="text" name="pn" id="pn"/>页
	<input type="button" value="go" onclick="goPage()"/>
</td></tr></table>
<%} %>
<script type="text/javascript">
function goPage() {
	var pn = document.getElementById("pn");
	Location.href="content.do?op=list&pn=" + pn.value();
	
}
</script>
<div>
	
	<input type="button" value="审核" onclick="opCheck();"/>
	
</div>
</form>
</div>
</body>
</html>