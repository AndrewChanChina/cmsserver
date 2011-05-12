<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

com.smit.vo.Content content = new com.smit.vo.Content();

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<com.smit.vo.Part> parts = request.getAttribute("parts") ==null?null:(List<com.smit.vo.Part>)request.getAttribute("parts");

List contents = request.getAttribute("contents")==null?null:(List)request.getAttribute("contents");

if(contents != null && contents.get(0)!= null){
	content = (com.smit.vo.Content)contents.get(0);
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

<style type="text/css">
.sel-disabled{background-color:#ccc}
</style>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置:  内容管理 - 增加</div>
	<a class="ropt" href="content.do?op=list">返回列表</a>
	<div class="clear"></div>
</div>
<form method="post" action="content.do?op=save" id="jvForm">
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
<tr>


<td width="10%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>栏目:</td><td colspan="3" width="90%" class="pn-fcontent">
<div style="float:left">
<div>
<select name="pid">
	<option value="0" class="sel-disabled">--请选择--</option>
<%
  if(parts != null){
  Iterator iter = parts.iterator();
  while(iter.hasNext()){
  	com.smit.vo.Part part = (com.smit.vo.Part)iter.next();
  	request.setAttribute("part",part); 
 
%>
  <option value="${part.id}" class="sel-disabled" <% if(part.getTypename() != null && contents != null && contents.get(1) != null && part.getTypename().equals((String)contents.get(1))) {%> selected <%} %>>
 ${part.typename}
  </option>
<%
}
}
 %>

</select> 
</div>
</div>
<div style="clear:both"></div>
</td>
</tr>


<tr id="tr-title">
<td width="10%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>标题:</td><td colspan="3" width="90%" class="pn-fcontent">
<input type="text" maxlength="150" name="title" value="<% if(content.getTitle()!= null) out.print(content.getTitle());%>" class="required" size="70" maxlength="150"/>
</td>

</tr>


<tr id="tr-author">



<td width="10%" class="pn-flabel pn-flabel-h">短标题:</td><td colspan="1" width="40%" class="pn-fcontent">
<input type="text" maxlength="100" name="shortTitle" value="<% if(content.getShortTitle()!= null) out.print(content.getShortTitle());%>" maxlength="100"/></td>






<td width="10%" class="pn-flabel pn-flabel-h">内容类型:</td><td colspan="1" width="40%" class="pn-fcontent">
<input type="text" maxlength="100" name="typeName" value="<% if(content.getTypeName()!= null) out.print(content.getTypeName());%>" style="width:70px" maxlength="100"/> 
</td>


</tr>


<tr id="tr-tagStr">



<td width="10%" class="pn-flabel pn-flabel-h">Tag标签:</td><td colspan="3" width="90%" class="pn-fcontent"><input type="text" maxlength="255" name="tags" value="<% if(content.getTags()!= null) out.print(content.getTags());%>" size="35" maxlength="255"/> <span class="pn-fhelp">用","分开</span></td>


</tr>



<tr id="tr-description">



<td width="10%" class="pn-flabel pn-flabel-h">摘要:</td><td colspan="3" width="90%" class="pn-fcontent"><textarea cols="70" rows="3" name="excerpt" maxlength="255"><% if(content.getExcerpt()!= null) out.print(content.getExcerpt());%></textarea>
</td>


</tr><tr id="tr-author">



<td width="10%" class="pn-flabel pn-flabel-h">作者:</td><td colspan="1" width="40%" class="pn-fcontent">
<input type="text" maxlength="100" name="author" value="<% if(content.getAuthor()!= null) out.print(content.getAuthor());%>" maxlength="100"/></td>






<td width="10%" class="pn-flabel pn-flabel-h">来源:</td><td colspan="1" width="40%" class="pn-fcontent">
<input type="text" maxlength="100" name="source" value="<% if(content.getSource()!= null) out.print(content.getSource());%>" style="width:70px" maxlength="100"/> 
</td>


</tr>


<tr id="tr-topLevel">



<td width="10%" class="pn-flabel pn-flabel-h">置顶:</td><td colspan="1" width="40%" class="pn-fcontent">

<input type="radio" name="putter" value="1" <% if(content.getPutter()!= null && content.getPutter() == 1) { %> checked <% } %>/>置顶&nbsp;&nbsp;<input type="radio" name="putter" value="0" <% if(content.getPutter()!= null && content.getPutter() == 0) { %> checked <% } %>/>不置顶
</td>
<td width="10%" class="pn-flabel pn-flabel-h">语言类型:</td><td colspan="3" width="90%" class="pn-fcontent"><label><input type="radio" value="0" name="langType" <% if(content.getLangType()!= null &&content.getLangType()== 0) { %> checked <% } %>/>中文</label> <label><input type="radio" value="1" name="langType" <% if(content.getLangType()!= null && content.getLangType() == 1) { %> checked <% } %>/>英文</td>









</tr><tr id="tr-typeId">



<td width="10%" class="pn-flabel pn-flabel-h">推荐精华:</td><td colspan="1" width="40%" class="pn-fcontent">
<input type="radio" name="prime" value="1" <% if(content.getPrime() != null && content.getPrime() == 1) { %> checked <% } %>/>圈加精华&nbsp;&nbsp;<input type="radio" name="prime" value="0" <% if(content.getPrime()!=null && content.getPrime() == 0) { %> checked <% } %>/>不加精华
</td>






<td width="10%" class="pn-flabel pn-flabel-h">排序:</td><td colspan="1" width="40%" class="pn-fcontent">
 <input type="text" name="sortRank" value="<% if(content.getSortRank()!= null) out.print(content.getSortRank());%>" value="100"/>
</td>


</tr>
<!-- 
<tr id="tr-typeImg" >



<td id="typeImg" width="10%" class="pn-flabel">缩略图:</td><td colspan="1" width="40%" class="pn-fcontent">
<input type="text" id="uploadImgPath0" name="subImg" value="<% if(content.getSubImg()!= null) out.print(content.getSubImg());%>"/> <input type="button" value="预览" onclick="previewImg(0);"/><br/>
<span id="ufc0"><input type="file" id="uploadFile0" size="14" style="width:180px"/></span><label><input type="checkbox" onclick="$('#mark0').val(this.checked);"/>水印</label><input type="hidden" id="mark0" value="false"/> <input type="button" value="上传" onclick="upload(0);"/><br/>
宽: <input type="text" id="zoomWidth0" value="280" size="5"/> 高: <input type="text" id="zoomHeight0" value="200" size="5"/> <input type="button" value="裁剪" onclick="imgCut(0);"/> 
</td><td colspan="2" class="pn-fbutton">
<img id="preImg0" alt="预览" style=""/>
</td>


</tr>

 -->

<tr id="tr-txt">



<td width="10%" class="pn-flabel pn-flabel-h">内容:</td><td colspan="3" width="90%" class="pn-fcontent">
 <textarea id="editor_id" name="content" cols="100" rows="8" style="width:90%;height:300px;"><% if(content.getContent()!= null) out.print(content.getContent());%></textarea>

	
 <script charset="utf-8" src="./kindeditor/kindeditor.js"></script>
<script>		
        KE.show({
                id : 'editor_id',
                 width : '90%',
                 cssPath : './kindeditor/index.css',
                 height : '200px', 
                  filterMode : false, 
                  allowFileManager : true,
                  newlineTag : 'p',
                   resizeMode : 1 ,
                   shadowMode : false,
                   autoSetDataMode: false,
				syncType : 'form', //auto: 每次修改时都会同步; form:提交form时同步; 空:不会自动同步;
				allowPreviewEmoticons : false,
				afterCreate : function(id) {
					KE.event.add(KE.$('example'), 'submit', function() {
						KE.sync(id);
					});
				},
				afterChange : function(id) {
				
					KE.$('word_count2').innerHTML = KE.count(id, 'text');
				}
                
        });
</script>
 <br />
		
		您当前输入了 <span id="word_count2">0</span> 个文字。（字数统计包含纯文本、IMG、EMBED，不包含换行符，IMG和EMBED算一个文字。）
       <br/><input type="button" name="button" value="清空内容" onclick="javascript:KE.html('editor_id', '');" />
</td>


</tr>
<tr>

<td colspan="4" class="pn-fbutton">
    <input type="hidden" name="id" value="<% if(content.getId() != null) out.print(content.getId()); %>"/>
	<input type="submit" value="提交"/> &nbsp; <input type="reset" value="重置"/>
		
</td></tr></table>
</form>
</div>
</div>
</body>
</html>