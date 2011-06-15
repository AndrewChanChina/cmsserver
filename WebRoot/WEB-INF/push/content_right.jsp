<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<div id="pb-aside">
	<div id="post-holder">
		<label for="post-to-select" style="font-size: 14px">发布至</label>
		<select id="post-select" class="aside-item">
			<logic:notEmpty name="list">
				<logic:iterate id="resource" name="list">
					<option value="${resource.deviceName}">${resource.deviceName}</option>
				</logic:iterate>
			</logic:notEmpty>
			<!--  <option value="cluo">cluo</option>-->
		</select>
	</div>
	<hr class="separator">
	<div id="privacy-holder">
		<!--  <select id="privacy-select" class="aside-item">
			<option value="0">所有人可见</option>
			<option value="1">仅自己可见</option>
		</select>
		-->
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
	<hr class="separator" id="pb-sync-sep" style="display: none; ">
	<div id="sync-to-weibo-holder" class="aside-item pb-side-opt pb-side-sync-opt" style="display:none;">
	<label><input type="checkbox" id="pb-sync-to-weibo" checked="">同时发布到我的新浪微博</label>
	</div>
	<div id="sync-to-qqweibo-holder" class="aside-item pb-side-opt pb-side-sync-opt" style="display:none;margin: 0 padding: 0">
	<label><input type="checkbox" id="pb-sync-to-qqweibo" checked="">同时发布到我的腾讯微博</label>
	</div>
</div>