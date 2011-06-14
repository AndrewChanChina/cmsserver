<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style type="text/css">
	#pb-aside {
		width: 200px;
		padding: 22px 18px;
		float: right;
		display: block;
	}
	#post-select,#privacy-select {
		border: 1px solid #bbb;
		padding: 5px;
		font-size: 14px;
		white-space: pre;
	}
	#separator {
		display: block;
		border-top: 1px solid #E2E6E8;
		border-width: 1px 0;
		margin: 15px 0;
		border-style: inset;
	}
	#post-tag {
		border: 1px solid #bbb;
		width: 188px;
		height: 90px;
		padding: 5px;
		overflow: hidden;
	}
	#tag-input {
		padding: 0;
		margin: 0;
		border: 0;
		width: 188px;
		outline: none;
	}
</style>
<div id="pb-aside">
	<div id="post-holder">
		<label for="post-to-select" style="font-size: 14px">发布至</label>
		<select id="post-select" class="aside-item">
			<option value="cluo">cluo</option>
		</select>
	</div>
	<hr class="separator">
	<div id="privacy-holder">
		<select id="privacy-select" class="aside-item">
			<option value="0">所有人可见</option>
			<option value="1">仅自己可见</option>
		</select>
	</div>
	<hr class="separator">
	<div id="tag-holder" style="display: block;">
		<div id="post-tag">
			<ul id="tag-list" style="clear: both;visibility: hidden;display: block;height: 0;"></ul>
			<div id="tag-input-holder">
				<input type="text" id="tag-input">
			</div>
		</div>
	</div>
	<hr class="separator">
	<div id="top-holder" style="color: #666;display: block;">
		<label style="font-size: 12px;font-weight: bold;"><input type="checkbox" id="top-post">文章置顶</label>
		<p id="post-tip" style="padding-top: 5px;color:#999;font-size: 12px;margin: 0;">每个博客只能置顶一篇文章</p>
	</div>
</div>