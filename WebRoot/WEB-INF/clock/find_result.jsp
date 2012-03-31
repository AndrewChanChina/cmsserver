<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>

<table class="pn-ltable" style="" width="100%" cellspacing="1"	cellpadding="0" border="0">
<thead class="pn-lthead">
	<tr>
		<th width="20">
			
		</th>
		<th>
			房间号
		</th>
		<th>
			名称
		</th>
		<th>
			时间
		</th>
		<th>
			持续时间
		</th>
		<th>
			重复次数
		</th>
		<th>
			时间间隔
		</th>
		<th>
			重复
		</th>
		<th>
			是否震动
		</th>
		<th>
			音乐
		</th>
		<th>
			操作
		</th>
	</tr>
</thead>

<tbody class="pn-ltbody">	
	<c:forEach items="${clockList}" var="clock">				
<tr>
	<td>
		<input type='checkbox' name='ids' value='1' />
	</td>
	<td>
		${clock.roomnum}
</td>
<td>
	${clock.name}
</td>
<td class="clock_time">
	${clock.hour}:${clock.minutes}
</td>
<td>
 	${clock.lastLong}分钟
</td>
<td>
	${clock.repeatTime}次
</td>
<td>
	${clock.nextTime}分钟
</td>
<td>
	<c:if test="${clock.dayofweek == 0}">无重复</c:if>
<c:if test="${clock.dayofweek != 0}">周</c:if>
<c:if test="${clock.dayofWeekArray[6] == true}">日</c:if>
<c:if test="${clock.dayofWeekArray[0] == true}">一</c:if>
<c:if test="${clock.dayofWeekArray[1] == true}">二</c:if>
<c:if test="${clock.dayofWeekArray[2] == true}">三</c:if>
<c:if test="${clock.dayofWeekArray[3] == true}">四</c:if>
<c:if test="${clock.dayofWeekArray[4] == true}">五</c:if>
<c:if test="${clock.dayofWeekArray[5] == true}">六</c:if>							
	
</td>
<td>
	<c:if test="${clock.vibrate == 1}">震动</c:if>
<c:if test="${clock.vibrate == 0}">不震动</c:if>
</td>
<td>
	
	<a href='${clock.rings.localUrl}'>${clock.rings.name}</a>
</td>
<td align="center">
	<a href="clock_update.do?id=${clock.id}&name=555">修改</a>
|
<a href="clock_del.do?id=${clock.id}">删除</a>
	</td>
</tr>
</c:forEach>	
	</tbody>	
</table>
			