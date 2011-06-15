<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
<div id="pb-main">
<h2 id="main-title">上传照片</h2>
<div id="post-area">
  <div id="photo-holder" class="post-section">
   <ol id="photo-list"></ol>
   <ol id="photo-error-list"></ol>
 </div>
 <div id="photo-pick-holder" class="post-section">
  <div id="photo-flash-holder">
  <!-- 
  <object id="SWFUpload_0" type="application/x-shockwave-flash" data="http://s.libdd.com/js/ks-mod/swfupload/swfupload.swf" width="110" height="34" class="swfupload">
  <param name="wmode" value="transparent">
  <param name="movie" value="http://s.libdd.com/js/ks-mod/swfupload/swfupload.swf">
  <param name="quality" value="high">
  <param name="menu" value="false">
  <param name="allowScriptAccess" value="always"><param name="flashvars" value="movieName=SWFUpload_0&amp;uploadURL=http%3A%2F%2Fwww.diandian.com%2Fupload&amp;useQueryString=false&amp;requeueOnError=false&amp;httpSuccess=&amp;assumeSuccessTimeout=0&amp;params=imgsize%3Ds100&amp;filePostName=Filedata&amp;fileTypes=*.jpg%3B*.png%3B*.gif%3B*.jpeg%3B*.bmp%3B*.JPG%3B*.PNG%3B*.GIF%3B*.JPEG%3B*.BMP%3B*.Jpg%3B*.Png%3B*.Gif%3B*.Jpeg%3B*.Bmp&amp;fileTypesDescription=Images&amp;fileSizeLimit=8MB&amp;fileUploadLimit=40&amp;fileQueueLimit=40&amp;debugEnabled=false&amp;buttonImageURL=http%3A%2F%2Fs.libdd.com%2Fimg%2Felement%2Fadd_photo.png&amp;buttonWidth=80&amp;buttonHeight=24&amp;buttonText=&amp;buttonTextTopPadding=0&amp;buttonTextLeftPadding=0&amp;buttonTextStyle=color%3A%20%23000000%3B%20font-size%3A%2016pt%3B&amp;buttonAction=-110&amp;buttonDisabled=false&amp;buttonCursor=-2"></object>
	 -->
	 
	 	<input type="file" name="file" id="up-photo"/>
	 	<input type="hidden" name="photos" id="photo-names">
	 
	</div>
 <div id="photo-pick-tip"></div>
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
<textarea id="pb-text-textarea" class="pb-input-text" name="desc"></textarea>

</div>
</div>
<div id="pb-action-holder">
<a class="blue-button" id="pb-submit">发布</a>
<a class="gray-button" id="pb-cancel">取消</a>
<span id="pb-submiting-tip" style="display:none;">正在发布...</span>
</div>
</div>
