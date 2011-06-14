<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<div id="main">
	  
			<div id="publish" class="pic">
				<div class="blog-photo">
					<a href="" id="blog-avatar">cluo</a>
				</div>
				<div id="photo-action">
					<div id="pb-triangle"></div>
					<div id="pb-action">
						<ul class="ph" >
							<li><a href="push.do?op=showLink&type=text">文字</a></li>
							<li><a href="push.do?op=showLink&type=photo" style="background-position: -106px 0;">图片</a></li>
							<li><a href="push.do?op=showLink&type=music" style="background-position: -212px 0;">音乐</a></li>
							<li><a href="push.do?op=showLink&type=link" style="background-position: -318px 0;">链接</a></li>
							<li><a href="push.do?op=showLink&type=vedio" style="background-position: -424px 0;">视频</a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div id="friend-list" style="display: none;"></div>
			<div id="tumblelog-member" style="display: "></div>
			<div id="inboxblog" style="display: none;"></div>
			<div id="feed-list">
				
				
				<logic:present name="list">
					<logic:iterate id="list" name="list">
							<div class="feed-text">
						<div class="feed-photo"><a href="" id="blog-avatar">cluo</a></div>
				<div class="pb-content">
					<!--  <div id="pb-triangle"></div>-->
						<div class="pb-comment">
							<logic:equal value="url" name="list" property="content_type">
								<a href="<bean:write name="list" property="url"/>" class="feed-title"><bean:write name="list" property="title"/></a>
							</logic:equal>
							<logic:equal value="text" name="list" property="content_type">
								<h4 class="feed-title"><bean:write name="list" property="title"/></h4>
								<div class="feed-comment"><p><bean:write name="list" property="content"/></p></div>
							</logic:equal>
							<logic:equal value="picture" name="list" property="content_type">
							  <div class="photo-list">
								<logic:iterate id="photo" name="list" property="photos">
								<div class="feed-img"><img class="img" src="./images/<bean:write name='photo'/>"
								width="77" height="77"/></div>
								</logic:iterate>
							 </div>
							 <div class="photo-desc"><bean:write name="list" property="des"/></div>
							</logic:equal>
							<logic:equal value="audio" name="list" property="content_type">
								<h3><bean:write name="list" property="des"/></h3>
								<div>音乐链接地址：<a href="<bean:write name="list" property="url"/>"><bean:write name="list" property="url"/></a></div>
							</logic:equal>
							<logic:equal value="vedio" name="list" property="content_type">
								<h3><bean:write name="list" property="des"/></h3>
								<div>视频链接地址：<a href="<bean:write name="list" property="url"/>"><bean:write name="list" property="url"/></a></div>
							</logic:equal>
						</div>
							<div class="feed-act">
								<a class="feed-del">删除</a>
								<a href="" class="feed-edit">编辑</a>
								<a class="feed-cmt">回应</a>
							</div>
							
				</div>
				</div>
					</logic:iterate>
				</logic:present>
				<div style="text-align: center;" id="nextpage">共 页   当前第<bean:write name="currentpage"/> 页  
				<a href="push.do?op=content&type=pre&currentpage=<bean:write name="currentpage"/>">上一页</a>  
				<a href="push.do?op=content&type=next&currentpage=<bean:write name="currentpage"/>">下一页</a> </div>
			</div>
		</div>

