<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%
	int[] hourArray = new int[24];
	int[] minuteArray = new int[60];
	for (int i = 0; i < 24; i++) {
		hourArray[i] = i;
	}
	for (int i = 0; i < 60; i++) {
		minuteArray[i] = i;
	}
	int[] lastTime = new int[] { 1, 2, 3, 4, 5, 8, 10, 15, 20, 30 };
%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=gb2312' />
		<title>ox travel</title>
		<!--meta name="viewport"  content="initial-scale=0.5; maximum-scale=10;  minimum-scale=0.5;"/-->
		<meta name="viewport"
			content="target-densitydpi=high-dpi;maximum-scale=10;  minimum-scale=0.5;" />
		<link type="text/css" rel="stylesheet" media="screen"
			href="./css/apple.css">
		<link type="text/css" rel="stylesheet" media="screen"
			href="./css/text.css">
		<link type="text/css" rel="stylesheet" media="screen"
			href="./css/dialog.css">
		<link type="text/css" rel="stylesheet" media="screen"
			href="./css/clock_home.css">
		<script type="text/javascript" src="./js/jquery.js"></script>
		<script type="text/javascript" src="./js/clock.js"></script>
		<script type="text/javascript" src="./js/dialog.js"></script>
		<script type="text/javascript" src="./js/notification.js"></script>

	</head>
	<body>
		<tiles:insert attribute="header"></tiles:insert>
		<div class='container'>
		
			<div id='main'>
				<div class='sub_menu'>
					<div class="menu_item">
						<a id='showaddclock'>添加闹钟</a>
					</div>
					<div class="menu_item">
						<a href="clock.do">条件查询</a>
					</div>
					<div class="menu_item">
						<a href="ring.do">声音管理</a>
					</div>
				</div>
				<div id="addclock">
					<div>
						<div class="">
							编辑闹钟
						</div>
						<form class="" action='clock_add.do' method='post'
							id="add_clock_form" onsubmit="return checkAdd()">
							<input type="hidden" name="id" value="${clock.id}">
							<table>
								<tr>
									<td class="col_1">
										闹钟名称
									</td>
									<td>
										<input name='name' id="f_name" value="${clock.name}">
									</td>
								</tr>
								<tr>
									<td class="col_1">
										房间号
									</td>
									<td>
										<select id="select_room" name='roomnum'>
											<option value="${clock.roomnum}">
												${clock.roomnum}
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="col_1">
										闹钟时间
									</td>
									<td>
										<select id="selectNode1" name='hour'>
											<c:forEach items="<%=hourArray%>" var="i">
												<option value="${i}"
													<c:if test="${i == clock.hour}">selected="selected"</c:if>>
													${i}
												</option>
											</c:forEach>
										</select>
										:
										<select id="selectNode2" name='minutes'>
											<c:forEach items="<%=minuteArray%>" var="i">
												<option value="${i}"
													<c:if test="${i == clock.minutes}">selected="selected"</c:if>>
													${i}
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td class="col_1">
										重复周期
									</td>
									<td>
										<input type='checkbox' name='week_sun' id='f_week_sun'
											<c:if test="${clock.dayofWeekArray[6]}">checked="checked"</c:if>>
										周日
										<input type='checkbox' name='week_mon' id='f_week_mon'
											<c:if test="${clock.dayofWeekArray[0]}">checked="checked"</c:if>>
										周一
										<input type='checkbox' name='week_tus' id='f_week_tus'
											<c:if test="${clock.dayofWeekArray[1]}">checked="checked"</c:if>>
										周二
										<input type='checkbox' name='week_wed' id='f_week_wed'
											<c:if test="${clock.dayofWeekArray[2]}">checked="checked"</c:if>>
										周三
										<input type='checkbox' name='week_thurs' id='f_week_thurs'
											<c:if test="${clock.dayofWeekArray[3]}">checked="checked"</c:if>>
										周四
										<input type='checkbox' name='week_fri' id='f_week_fri'
											<c:if test="${clock.dayofWeekArray[4]}">checked="checked"</c:if>>
										周五
										<input type='checkbox' name='week_satur' id='f_week_satur'
											<c:if test="${clock.dayofWeekArray[5]}">checked="checked"</c:if>>
										周六

									</td>
								</tr>
								<tr>
									<td class="col_1">
										持续时长
									</td>
									<td>
										<select id="selectNode2" name='last_time'>
											<c:forEach items="<%=lastTime%>" var="i">
												<option value="${i}"
													<c:if test="${i == clock.lastLong}">selected="selected"</c:if>>
													${i}分钟
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td class="col_1">
										重复次数
									</td>
									<td>
										<input type='radio' name='repeat_time' value="0"
											<c:if test="${0 == clock.repeatTime}">checked</c:if>>
										不重复
										<input type="radio" name='repeat_time' value="1"
											<c:if test="${1 == clock.repeatTime}">checked</c:if>>
										一次
										<input type='radio' name='repeat_time' value="2"
											<c:if test="${2 == clock.repeatTime}">checked</c:if>>
										两次
										<input type='radio' name='repeat_time' value="3"
											<c:if test="${3 == clock.repeatTime}">checked</c:if>>
										三次
									</td>
								</tr>
								<tr>
									<td class="col_1">
										时间间隔
									</td>
									<td>
										<select id="selectNode2" name='next_time'>
											<c:forEach items="<%=lastTime%>" var="i">
												<option value="${i}"
													<c:if test="${i == clock.nextTime}">selected="selected"</c:if>>
													${i}分钟
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td class="col_1">
										设置音乐
									</td>
									<td>
										<input type='radio' class='f_music' name='music'
											value='default'
											<c:if test="${clock.rings == null}">checked</c:if>>
										使用设备默认音乐
										<br>
										<input type='radio' class='f_music' name='music'
											value='server'
											<c:if test="${clock.rings != null}">checked</c:if>>
										使用服务器资源
										<input type="hidden" name="server_music" value="1">
										&nbsp;&nbsp;
										<a href="ring.do">上传声音资源</a>
										<div id="show_music_for_sel">
											<c:if test="${clock.rings != null}">选择了: ${clock.rings.name}</c:if>
										</div>
										<br>
										<div id="show_waiting" class='hide_class'>
											Waiting
										</div>

									</td>
								</tr>
								<tr>
									<td class="col_1">
										是否震动
									</td>
									<td>
										<input type='checkbox' name='vibrate'
											<c:if test="${clock.vibrate == 1}">checked</c:if>>
										震动
										</div>
									</td>
								</tr>
								<tr>
									<td class="col_1"></td>
									<td>
										<input type='submit'>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class='dotline'></div>


			</div>
			<tiles:insert attribute="footer"></tiles:insert>
	</body>
</html>