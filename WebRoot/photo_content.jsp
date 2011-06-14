<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
<style type="text/css">
	
</style>
<div id="pb-main">
<h2 id="main-title">上传照片</h2>
<div id="post-area">
  <div id="photo-holder" class="post-section">
   <ol id="photo-list"></ol>
   <ol id="photo-error-list"></ol>
 </div>
 <div id="photo-pick-holder" class="post-section">
  <div id="photo-flash-holder">
  <object id="SWFUpload_0" type="application/x-shockwave-flash" data="http://s.libdd.com/js/ks-mod/swfupload/swfupload.swf" width="80" height="24" class="swfupload">
  <param name="wmode" value="transparent">
  <param name="movie" value="http://s.libdd.com/js/ks-mod/swfupload/swfupload.swf">
  <param name="quality" value="high">
  <param name="menu" value="false">
  <param name="allowScriptAccess" value="always"><param name="flashvars" value="movieName=SWFUpload_0&amp;uploadURL=http%3A%2F%2Fwww.diandian.com%2Fupload&amp;useQueryString=false&amp;requeueOnError=false&amp;httpSuccess=&amp;assumeSuccessTimeout=0&amp;params=imgsize%3Ds100&amp;filePostName=Filedata&amp;fileTypes=*.jpg%3B*.png%3B*.gif%3B*.jpeg%3B*.bmp%3B*.JPG%3B*.PNG%3B*.GIF%3B*.JPEG%3B*.BMP%3B*.Jpg%3B*.Png%3B*.Gif%3B*.Jpeg%3B*.Bmp&amp;fileTypesDescription=Images&amp;fileSizeLimit=8MB&amp;fileUploadLimit=40&amp;fileQueueLimit=40&amp;debugEnabled=false&amp;buttonImageURL=http%3A%2F%2Fs.libdd.com%2Fimg%2Felement%2Fadd_photo.png&amp;buttonWidth=80&amp;buttonHeight=24&amp;buttonText=&amp;buttonTextTopPadding=0&amp;buttonTextLeftPadding=0&amp;buttonTextStyle=color%3A%20%23000000%3B%20font-size%3A%2016pt%3B&amp;buttonAction=-110&amp;buttonDisabled=false&amp;buttonCursor=-2"></object>
 </div>
 <div id="photo-pick-tip">在文件列表中，按住Ctrl多选照片</div>
 <div id="pb-photo-upload-file-status"></div>
 <div id="photo-file-tip">JPG, GIF, PNG或BMP. 不超过8MB</div>
 <div id="pb-photo-upload-total-status"></div>
</div>
<div id="pb-photos-reblog-preview"></div>
<div id="photos-desc-holder" class="post-section">
<h3 class="pb-section-title">
描述
<span>(可不填)</span>
</h3>
<textarea id="pb-photos-desc" class="pb-input-text" style="visibility: hidden;display: none;"></textarea>
<span id="cke_pb-photos-desc" class="cke_skin_v2 cke_1 cke_editor_pb-photos-desc" dir="ltr" title="" lang="zh-cn" tabindex="2" role="application" aria-labelledby="cke_pb-photos-desc_arialbl"><span id="cke_pb-photos-desc_arialbl" class="cke_voice_label">所见即所得编辑器</span><span class="cke_browser_webkit" role="presentation"><span class="cke_wrapper cke_ltr" role="presentation"><table class="cke_editor" border="0" cellspacing="0" cellpadding="0" role="presentation"><tbody><tr role="presentation" style="-webkit-user-select: none; "><td id="cke_top_pb-photos-desc" class="cke_top" role="presentation"><div class="cke_toolbox" role="toolbar" aria-labelledby="cke_4" onmousedown="return false;"><span id="cke_4" class="cke_voice_label">工具栏</span><span id="cke_5" class="cke_toolbar" role="presentation"><span class="cke_toolbar_start"></span><span class="cke_toolgroup" role="presentation"><span class="cke_button"><a id="cke_6" class="cke_off cke_button_bold" "="" href="javascript:void('加粗')" title="加粗" tabindex="-1" hidefocus="true" role="button" aria-labelledby="cke_6_label" onkeydown="return CKEDITOR.tools.callFunction(0, 0, event);" onfocus="return CKEDITOR.tools.callFunction(1, 0, event);" onclick="CKEDITOR.tools.callFunction(4, this); return false;"><span class="cke_icon">&nbsp;</span><span id="cke_6_label" class="cke_label">加粗</span></a></span><span class="cke_button"><a id="cke_7" class="cke_off cke_button_underline" "="" href="javascript:void('下划线')" title="下划线" tabindex="-1" hidefocus="true" role="button" aria-labelledby="cke_7_label" onkeydown="return CKEDITOR.tools.callFunction(0, 1, event);" onfocus="return CKEDITOR.tools.callFunction(1, 1, event);" onclick="CKEDITOR.tools.callFunction(5, this); return false;"><span class="cke_icon">&nbsp;</span><span id="cke_7_label" class="cke_label">下划线</span></a></span><span class="cke_button"><a id="cke_8" class="cke_off cke_button_strike" "="" href="javascript:void('删除线')" title="删除线" tabindex="-1" hidefocus="true" role="button" aria-labelledby="cke_8_label" onkeydown="return CKEDITOR.tools.callFunction(0, 2, event);" onfocus="return CKEDITOR.tools.callFunction(1, 2, event);" onclick="CKEDITOR.tools.callFunction(6, this); return false;"><span class="cke_icon">&nbsp;</span><span id="cke_8_label" class="cke_label">删除线</span></a></span></span><span class="cke_separator" role="separator"></span><span class="cke_toolgroup" role="presentation"><span class="cke_button"><a id="cke_9" class="cke_off cke_button_numberedlist" "="" href="javascript:void('编号列表')" title="编号列表" tabindex="-1" hidefocus="true" role="button" aria-labelledby="cke_9_label" onkeydown="return CKEDITOR.tools.callFunction(0, 3, event);" onfocus="return CKEDITOR.tools.callFunction(1, 3, event);" onclick="CKEDITOR.tools.callFunction(7, this); return false;"><span class="cke_icon">&nbsp;</span><span id="cke_9_label" class="cke_label">编号列表</span></a></span><span class="cke_button"><a id="cke_10" class="cke_off cke_button_bulletedlist" "="" href="javascript:void('项目列表')" title="项目列表" tabindex="-1" hidefocus="true" role="button" aria-labelledby="cke_10_label" onkeydown="return CKEDITOR.tools.callFunction(0, 4, event);" onfocus="return CKEDITOR.tools.callFunction(1, 4, event);" onclick="CKEDITOR.tools.callFunction(8, this); return false;"><span class="cke_icon">&nbsp;</span><span id="cke_10_label" class="cke_label">项目列表</span></a></span><span class="cke_button"><a id="cke_11" class="cke_off cke_button_blockquote" "="" href="javascript:void('块引用')" title="块引用" tabindex="-1" hidefocus="true" role="button" aria-labelledby="cke_11_label" onkeydown="return CKEDITOR.tools.callFunction(0, 5, event);" onfocus="return CKEDITOR.tools.callFunction(1, 5, event);" onclick="CKEDITOR.tools.callFunction(9, this); return false;"><span class="cke_icon">&nbsp;</span><span id="cke_11_label" class="cke_label">块引用</span></a></span><span class="cke_button"><a id="cke_12" class="cke_off cke_button_source" "="" href="javascript:void('源码')" title="源码" tabindex="-1" hidefocus="true" role="button" aria-labelledby="cke_12_label" onkeydown="return CKEDITOR.tools.callFunction(0, 6, event);" onfocus="return CKEDITOR.tools.callFunction(1, 6, event);" onclick="CKEDITOR.tools.callFunction(10, this); return false;"><span class="cke_icon">&nbsp;</span><span id="cke_12_label" class="cke_label">源码</span></a></span></span><span class="cke_toolbar_end"></span></span></div></td></tr><tr role="presentation"><td id="cke_contents_pb-photos-desc" class="cke_contents" style="height:130px" role="presentation"><iframe style="width:100%;height:100%" frameborder="0" title="所见即所得编辑器, pb-photos-desc, 按 ALT+0 查看帮助。" src="" tabindex="-1" allowtransparency="true"></iframe></td></tr><tr role="presentation" style="-webkit-user-select: none; "><td id="cke_bottom_pb-photos-desc" class="cke_bottom" role="presentation"><div class="cke_resizer cke_resizer_vertical cke_resizer_ltr" title="拖拽以改变尺寸" onmousedown="CKEDITOR.tools.callFunction(3, event)"></div></td></tr></tbody></table><style>.cke_skin_v2{visibility:hidden;}</style></span></span></span>
</div>
<div id="pb-photos-title-wrap" style="display:none;">
<div id="pb-photos-add-title-holder" class="pb-post-section">
<a id="pb-photos-add-title">添加照片集标题</a>
</div>
<div id="pb-photos-title-holder" class="pb-post-section" style="display:none;">
<h3 class="pb-section-title">照片集标题</h3>
<input class="pb-input-text" id="pb-photos-title">
</div>
</div>
</div>
<div id="pb-action-holder">
<a class="blue-button" id="pb-submit">发布</a>
<a class="gray-button" id="pb-cancel">取消</a>
<span id="pb-submiting-tip" style="display:none;">正在发布...</span>
</div>
</div>
