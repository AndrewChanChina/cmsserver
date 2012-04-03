<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
    			<div class="menu_item menu_item_sel"><a href="clock_room.do">添加分组</a></div>    		   			
    			<div class="menu_item"><a href="clock_group.do">分组管理</a></div>
    				
    		</div>
    		
    		<div class='dotline'></div>
    		<div id="show_clock_list">
    			<div><h3>添加分组</h3></div>
    			
    			<table>
    			<tbody>
    			<tr>
    			<td class="col_1">分组名称</td>
    			<td><input name="groupName"></td>
    			</tr>
    			<tr>
    			<td class="col_1">已选房间</td>
    			<td>
    			<div id="group_sel">
    				<div class="room_item inline_class">201</div><div class="room_item inline_class">201</div>
    			</div>
    			</td>
    			</tr>
    			<tr>
    			<td class="col_1"></td>
    			<td><input type="submit">
    			</tr>
    			</tbody>
    			</table>
    			
    			
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
							叫醒服务
						</th>
						<th>
							APK管理
						</th>
						<th>
							所属分组
						</th>
					</tr>
				</thead>
				
				<tbody class="pn-ltbody">	
					<c:forEach items="${roomList}" var="room">				
						<tr>
							<td>
								<input type='checkbox' name='ids' value='1' />
							</td>
							<td>
								${room.roomNum}
							</td>
							<td>
							查看分组
							</td>
							
						</tr>
					</c:forEach>	
				</tbody>	
			</table>
			<!--<div>
				<smitpage:page pager="${pb}" />
			</div>
			-->
			</div>	
    			
    </div>   	
    </div>
     </div>
   
    </body>
</html>