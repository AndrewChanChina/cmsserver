<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>

<!DOCTYPE html>
<html>
    <head>
    	<meta http-equiv='Content-Type' content='text/html; charset=gb2312' />
        <title>ox travel</title>
        <!--meta name="viewport"  content="initial-scale=0.5; maximum-scale=10;  minimum-scale=0.5;"/-->
        <meta name="viewport"  content="target-densitydpi=high-dpi;maximum-scale=10;  minimum-scale=0.5;"/>
        <link type="text/css" rel="stylesheet" media="screen" href="./css/apple.css">   
        <link type="text/css" rel="stylesheet" media="screen" href="./css/text.css">  
        <link type="text/css" rel="stylesheet" media="screen" href="./css/dialog.css">
        <link type="text/css" rel="stylesheet" media="screen" href="./css/clock_home.css">   
        <script type="text/javascript" src="./js/jquery.js"></script>
        <script type="text/javascript" src="./js/clock.js"></script>
        <script type="text/javascript" src="./js/dialog.js"></script>
        <script type="text/javascript" src="./js/notification.js"></script>		
        
    </head>
    <body>
	
    <div class='container'> 
    	<div id='main'>
    		<div id='sub_menu'> 
    			<div class="menu_item menu_item_sel"><a href="clock.do">条件查询</a></div>    		   			
    			<div class="menu_item"><a href="clock_editForm.do">添加闹钟</a></div>
    				
    		</div>
    		
    		<div class='dotline'></div>
    		<div id="show_clock_list">
    			<div><h3>条件查找</h3></div>
    			
    			<div>按房间号查找</div>
    			
    			<input name="find_room_num"></input><a href="#" id="find_roomnum">&nbsp;查找</a>
    				<br><br>
    			<div>按闹钟时间段查找（24小时制，格式如13:20)</div>
    			开始时间<input name="find_start_time">接收时间<input name='find_end_time'>
    			<a href="#" id="find_time">&nbsp;查找</a>
    			<br><br>
    			
    			
    		<div class='hide_class'>全部选择:<input type='checkbox'></input></div>
    		<div id='list_data'>
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
							状态
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
								<c:if test="${clock.rings == null}">默认</c:if>
								<a href='${clock.rings.localUrl}'>${clock.rings.name}</a>
							</td>
							<td>
								${clock.status}
							</td>
							<td align="center">
								<a href="clock_editForm.do?id=${clock.id}&name=555">修改</a>
								|
								<a href="clock_del.do?id=${clock.id}&roomNum=${clock.roomnum}">删除</a>
							</td>
						</tr>
					</c:forEach>	
				</tbody>	
			</table>
			<div>
				<smitpage:page pager="${pb}" />
			</div>
			</div>	
    			
    </div>   	
    </div>
     </div>
   
    </body>
</html>