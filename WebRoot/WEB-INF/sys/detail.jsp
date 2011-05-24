<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>product order</title>
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
	$(document).ready(
		$("#file").blind("click", function(){
		//alert("34654");
			var checkid = $("#checkid");
			if("".equals(checkid)|| null === checkid){
				alert("请输入checkid！");
			}
		})
	);

</script>
</head>
<body>
<div class="rhead">
		<div class="rpos">当前位置: 细分状态 - 上传</div>
		<div class="clear"></div>
</div>
<form method="post" action="detailLog.do" id="detailForm" enctype="multipart/form-data"><table border="0" width="200">
<tbody>
<tr>
<td>CheckID:</td><td><input type="text" name="checkID" id="checkid"></td>
<td style="overflow:hidden;white-space:nowrap;" class="pn-flabel pn-flabel-h">选择文件:</td>
<td>&nbsp;<input type="file" name="upload" id="file"></td></tr><tr><td valign="top"><input type="submit" value="确定" name="ok"></td><td valign="top"><input type="reset" value="取消" name="reset"></td></tr>
</tbody></table></form> 
    
  </body>
</html>
