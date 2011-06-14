<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div id="pb-main">
	<h2 id="main-title">发布文字</h2>
	<div id="post-area">
		<div id="text-title" class="post-section">
			<h3 class="pb-section-title">标题<span id="nl">(可不填)</span></h3>
			<input type="text" class="input-text" id="text-title" name="title"> 
		</div>
		<div id="text-holder" class="post-section">
			<h3 class="pb-section-title">内容</h3>
				<textarea id="pb-text-textarea" class="pb-input-text" name="content"></textarea>
		</div>
	</div>
	<div id="pb-action-holder">
		<a class="blue-button" id="pb-submit">发布</a>
		<a class="gray-button" id="pb-cancel">取消</a>
		<span id="pb-submiting-tip" style="display:none;">正在发布...</span>
	</div>
</div>
