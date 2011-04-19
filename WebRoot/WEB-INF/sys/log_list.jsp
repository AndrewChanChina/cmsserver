<%@page import="org.hibernate.impl.SessionFactoryImpl"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page contentType= "text/html;charset=UTF-8" language= "java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.smit.dao.*"%>
<%@ page import="com.smit.vo.*"%>
<%@ page import="org.hibernate.SessionFactory"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
function getTableForm() {
	return document.getElementById('tableForm');
}
function optDelete() {
	if(Pn.checkedCount('ids')<=0) {
		alert("è¯·éæ©æ¨è¦æä½çæ°æ®");
		return;
	}
	if(!confirm("æ¨ç¡®å®å é¤åï¼")) {
		return;
	}
	var f = getTableForm();
	f.action="o_delete.do";
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
	<div class="rpos">å½åä½ç½®: ä¼åç»ç®¡ç - åè¡¨</div>
	<form class="ropt">
		<input type="submit" value="æ·»å " onclick="this.form.action='v_add.do';"/>
	</form>
	<div class="clear"></div>
</div>
<form id="tableForm" method="post">
<input type="hidden" name="pageNo" value=""/>
<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead"><tr>
	<th width="20"><input type='checkbox' onclick='Pn.checkbox("ids",this.checked)'/></th>
	<th>ID</th>
	<th>åç§°</th>
	<th>æ¯æ¥éä»¶æ»å°ºå¯¸</th>
	<th>æå¤§éä»¶å°ºå¯¸</th>
	<th>æåé¡ºåº</th>
	<th>é»è®¤ç»</th>
	<th>è¯è®ºéè¦å®¡æ ¸</th>
	<th>è¯è®ºéè¦éªè¯ç </th>
	<th>æä½éé¡¹</th></tr></thead>
<tbody  class="pn-ltbody"><tr>
	<td><input type='checkbox' name='ids' value='1'/></td>
	<td>1</td>
	<td>æ®éä¼å</td>
	<td align="right">4096 KB</td>
	<td align="right">1024 KB</td>
	<td align="center">		<input type="text" name="priority" value="10" style="width:40px"/>
		<input type="hidden" name="wids" value="1"/>
</td>
	<td align="center">		<input type="radio" name="regDefId" value="1" checked="checked"/>
</td>
	<td align="center">æ¯</td>
	<td align="center">æ¯</td>
	<td align="center">		<a href="v_edit.do?id=1&pageNo=" class="pn-opt">ä¿®æ¹</a> | <a href="o_delete.do?ids=1&pageNo=" onclick="if(!confirm('æ¨ç¡®å®å é¤åï¼')) {return false;}" class="pn-opt">å é¤</a></td>
</tr>
<tr>
	<td><input type='checkbox' name='ids' value='2'/></td>
	<td>2</td>
	<td>é«çº§ç»</td>
	<td align="right">ä¸éå¶</td>
	<td align="right">ä¸éå¶</td>
	<td align="center">		<input type="text" name="priority" value="10" style="width:40px"/>
		<input type="hidden" name="wids" value="2"/>
</td>
	<td align="center">		<input type="radio" name="regDefId" value="2"/>
</td>
	<td align="center">æ¯</td>
	<td align="center">æ¯</td>
	<td align="center">		<a href="v_edit.do?id=2&pageNo=" class="pn-opt">ä¿®æ¹</a> | <a href="o_delete.do?ids=2&pageNo=" onclick="if(!confirm('æ¨ç¡®å®å é¤åï¼')) {return false;}" class="pn-opt">å é¤</a></td>
</tr></tbody>
</table>
<div>
	<input type="button" value="å é¤" onclick="optDelete();"/>
&nbsp; <input type="button" value="ä¿å­æåé¡ºåº" onclick="optPriority();"/>
</div>
</form>
</div>
</body>
</html>