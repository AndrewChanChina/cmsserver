<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>



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

<style type="text/css">
.sel-disabled{background-color:#ccc}
</style>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置: 上传 - 增加</div>
	<a class="ropt" href="content.do?op=list">返回列表</a>
	<div class="clear"></div>
</div>
<form method="post" action="upload.do?op=upload" enctype="multipart/form-data"  id="jvForm">
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">





<tr id="tr-author">



<td width="10%" class="pn-flabel pn-flabel-h">来源:</td><td colspan="1" width="40%" class="pn-fcontent">
<input type="text" maxlength="100" name="source" value="" maxlength="100"/></td>






<td width="10%" class="pn-flabel pn-flabel-h">资源类型:</td><td colspan="1" width="40%" class="pn-fcontent">
<input type="text" maxlength="100" name="tid" value="" style="width:70px" maxlength="100"/> 
</td>


</tr>


<tr id="tr-tagStr">



<td width="10%" class="pn-flabel pn-flabel-h">排序:</td><td colspan="3" width="90%" class="pn-fcontent"><input type="text" maxlength="255" name="sortRank" value="" maxlength="100"/></td>


</tr>
<tr id="tr-tagStr">



<td width="10%" class="pn-flabel pn-flabel-h">上传:</td><td colspan="3" width="90%" class="pn-fcontent"><input type="file" name="file"/><br/></td>


</tr>






<tr>

<td colspan="4" class="pn-fbutton">
   
	<input type="submit" value="提交"/> &nbsp; <input type="reset" value="重置"/>
		
</td></tr></table>
</form>
</div>
</div>
</body>
</html>