<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div id="pb-main">
<h2 id="main-title">分享音乐</h2>
<div id="post-area">
<div id="audio-search-holder" class="pb-post-section">
<h3 class="pb-section-title">
搜索音乐库
<span>(输入音乐链接地址)</span>
</h3>
<textarea class="pb-input-text" id="pb-audio-desc-input" style="visibility: hidden; display: none; "></textarea>
<div class="pb-input-tip" id="pb-audio-search-tip"></div>
<input type="text" class="input-text" id="pb-link-url-input" name="url">
</div>
<div id="audio-preview-holder" class="pb-post-media-preview clearfix" style="display:none;">
<a id="pb-audio-repick-btn" class="pb-post-media-preview-close">重新选择音乐</a>
<img id="pb-audio-thumb">
<div id="pb-audio-player"></div>
</div>
<div id="pb-audio-desc-holder" class="pb-post-section">
<h3 class="pb-section-title">
描述
<span>(可不填)</span>
</h3>
<textarea class="pb-input-text" id="pb-text-textarea" name="des"></textarea>
</div>
<div id="pb-action-holder">
<a class="blue-button" id="pb-submit">发布</a><a class="gray-button" id="pb-cancel">取消</a>
<span id="pb-submiting-tip" style="display:none;">正在发布...</span>
</div>
</div>
</div>