<%@page import="com.smit.util.Page"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
com.smit.util.Page mediasPage = (com.smit.util.Page)request.getAttribute("page");
List medias = new ArrayList();

if(mediasPage != null){
	medias = mediasPage.getList();
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
<form action="upload.do?op=search" method="post" style="padding-top:5px;">
<div>
文件名: <input type="text" name="title" value="" style="width:100px"/>
上传者: <input type="text" name="author" value="" style="width:70px"/>

<select name="typeName"><option value="" selected="selected">--所有类型--</option><option value="1">普通</option><option value="2">图文</option><option value="3">焦点</option><option value="4">头条</option></select>
<input type="submit" value="查询"/>
</div>


</form>
<form id="tableForm" method="post">
<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead"><tr>
	<th width="20"><input type='checkbox' onclick='Pn.checkbox("ids",this.checked)'/></th>
	<th>ID</th>
	<th>文件名</th>
	
	<th>路径</th>
	<th>上传者</th>
	<th>来源</th>
	<th>操作选项</th></tr></thead>
<tbody  class="pn-ltbody">
<%
	Iterator iter = medias.iterator();
	int i = 0;
	if(iter.hasNext()){
		com.smit.vo.Media media = (com.smit.vo.Media)iter.next();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yy-MM-dd");
		Date date = new Date(Long.parseLong(String.valueOf(media.getCreatetime())));
		String createtime = sdf.format(date);
		
		i++;
 %>
<tr>
	<td><input type='checkbox' name='ids' value='<%=media.getId()%>'/></td>
	<td><%=i %></td>
	<td>		
	
		<strong><%=media.getFileName() %></strong>
		<%=media.getFileName()%>
</td>

	<td align="right"><%=media.getPath() %></td>
	
	<td align="center"><%%></td>
	<td align="center"><%=media.getSource()%></td>
	
</td>
	<td align="center"><a href="content.do?op=manager&id=<%=media.getId() %>" class="pn-opt">修改</a> | 		<a href="content.do?op=delete&id=<%=media.getId() %>" onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a>
		</td>
</tr>

<%
}
 %>

</tbody>
</table>
<% if(mediasPage != null){ %>
<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td align="center" class="pn-sp">
	当前第<%=mediasPage.getCurrentPage() %>页&nbsp;&nbsp;

	<a href="content.do?op=list&pid=<%=request.getAttribute("pid") %>&pn=<%=mediasPage.getPerPage()%>">上一页</a>
	<a href="content.do?op=list&pid=<%=request.getAttribute("pid") %>&pn=<%=mediasPage.getNextPage()%>">上一页</a>&nbsp;&nbsp;
	共<%=mediasPage.pageCount() %>页&nbsp;&nbsp;
	共<%=mediasPage.getTotalRecord() %>记录数&nbsp;&nbsp;
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
	
	
	
</div>
</form>
</div>
</body>
</html>