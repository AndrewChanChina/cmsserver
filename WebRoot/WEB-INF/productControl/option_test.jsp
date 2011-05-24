<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
	function addOption(){
	window.location.href="showAddOption.do";
	}
	function del(){
	window.location.href="showDelOption.do";
	}
</script>
<title>test option</title>
</head>
<body>
<div class="rhead">
		<div class="rpos">当前位置: 测试 - 测试选项</div>
		<div class="clear"></div>
</div>
<form action="queryOption.do" method="post">
<table class="pn-ftable">
<tr><td class="pn-flabel pn-flabel-h">生产代号</td><td class="pn-fcontent"><select name="order_code" ><option value="请选择">请选择</option><logic:iterate id="order" name="orders"><option value="${order.order_code}"><bean:write name="order" property="order_code"></bean:write></logic:iterate></select></td><td class="pn-fcontent"><input type="submit" value="查询"/><input type="button" value="添加" onclick="addOption();"/></td><td class="pn-fcontent"><input type="button" value="删除" onclick="del();"/></td></tr>
</table>
</form>
</body>
</html>