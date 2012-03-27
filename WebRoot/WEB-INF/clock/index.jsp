<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <script type="text/javascript" src="./js/jquery.js"></script>
        <script type="text/javascript" src="./js/clock.js"></script>
        <script type="text/javascript" src="./js/dialog.js"></script>		
        
    </head>
    <body>
    <div id="head_bg">
     	<div class='container'>
    		<div id='header'>酒店叫醒系统</div>
    	</div>
  	</div>
    <div class='container'>    	
    	<div id='side_left'>
    		<div>菜单</div>
    		<div>
    			<ul>
						<li>Coffee</li>
						<li>Milk</li>
					</ul>
    		</div>
    	</div>
    	<div id='main'>
    		
    		<div id="addclock">
    			<div>
    				<h3 id='showaddclock'>添加闹钟</h3>
    				<form action='clock_add.do' method='post' id="add_clock_form" onsubmit="return checkAdd()">
    					<div>闹钟名称：<input name='name' id="f_name"></input></div>
    					<div>房间号：
    					<select id="select_room" name='roomnum'>
							<c:forEach items="${roomList}" var="room">
					        	<option value="${room.id}">${room.deviceName}</option>
					        </c:forEach>    						
    					</select>
    					<div>闹钟时间：
    					<select id="selectNode1" name='hour'>
					        <option value="1">1</option>
					        <option value="2">2</option>
					        <option value="3">3</option>
					        <option value="4">4</option>
					        <option value="5">5</option>
					        <option value="6">6</option>
					        <option value="7">7</option>
					        <option value="8">8</option>
					        <option value="9">9</option>
					        <option value="10">10</option>
					        <option value="11">11</option>
					        <option value="12">12</option>
					        <option value="13">13</option>
					        <option value="14">14</option>
					        <option value="15">15</option>
					        <option value="16">16</option>
					        <option value="17">17</option>
					        <option value="18">18</option>
					        <option value="19">19</option>
					        <option value="20">20</option>
					        <option value="21">21</option>
					        <option value="22">22</option>
					        <option value="23">23</option>
					    </select>:
						<select id="selectNode2" name='minutes'>
					        <option value="1">1</option>
					        <option value="2">2</option>
					        <option value="3">3</option>
					        <option value="4">4</option>
					        <option value="5">5</option>
					        <option value="6">6</option>
					        <option value="7">7</option>
					        <option value="8">8</option>
					        <option value="9">9</option>
					        <option value="10">10</option>
					        <option value="11">11</option>
					        <option value="12">12</option>
					        <option value="13">13</option>
					        <option value="14">14</option>
					        <option value="15">15</option>
					        <option value="16">16</option>
					        <option value="17">17</option>
					        <option value="18">18</option>
					        <option value="19">19</option>
					        <option value="20">20</option>
					        <option value="21">21</option>
					        <option value="22">22</option>
					        <option value="23">23</option>
					        <option value="24">24</option>
					        <option value="25">25</option>
					        <option value="26">26</option>
					        <option value="27">27</option>
					        <option value="28">28</option>
					        <option value="29">29</option>
					        <option value="30">30</option>
					        <option value="31">31</option>
					        <option value="32">32</option>
					        <option value="33">33</option>
					        <option value="34">34</option>
					        <option value="35">35</option>
					        <option value="36">36</option>
					        <option value="37">37</option>
					        <option value="38">38</option>
					        <option value="39">39</option>
					        <option value="40">40</option>
					        <option value="41">41</option>
					        <option value="42">42</option>
					        <option value="43">43</option>
					        <option value="44">44</option>
					        <option value="45">45</option>
					        <option value="46">46</option>
					        <option value="47">47</option>
					        <option value="48">48</option>
					        <option value="49">49</option>
					        <option value="50">50</option>
					        <option value="51">51</option>
					        <option value="52">52</option>
					        <option value="53">53</option>
					        <option value="54">54</option>
					        <option value="55">55</option>
					        <option value="56">56</option>
					        <option value="57">57</option>
					        <option value="58">58</option>
					        <option value="59">59</option>					        
					    </select>
						</div>
    					<div>重复周期：<input type='checkbox' name='week_sun' id='f_week_sun' value="7">周日
    					  <input type='checkbox' name='week_mon' id='f_week_mon'>周一
    						<input type='checkbox' name='week_tus' id='f_week_tus'>周二
    						<input type='checkbox' name='week_wed' id='f_week_wed'>周三
    						<input type='checkbox' name='week_thurs' id='f_week_thurs'>周四
    						<input type='checkbox' name='week_fri' id='f_week_fri'>周五
    						<input type='checkbox' name='week_satur' id='f_week_satur'>周六
    					</div>
    					<div>设置音乐：
    						<input type='radio' class='f_music' name='music' value='defult' checked>使用设备默认音乐<br> 
    						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    						<input type='radio' class='f_music' name='music' value='server'>使用服务器资源&nbsp;&nbsp;选择
    						<input type="hidden" name="server_music">
					        &nbsp;&nbsp;<a href="ring.do">上传声音资源</a><br>    					  
    						<div id="show_waiting" class='hide_class'>Waiting</div>
    						<div id="show_music_for_sel"></div>
    					</div>
    				  <div>是否震动：<input type='checkbox' name='vibrate'>震动</div>  					 					
    					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    					<input type='submit'>   
    				</form>    				
    			</div>
    		</div>
    		<div class='dotline'></div>
    		<div id="show_clock_list">
    			<div><h3>条件查找</h3></div>
    			
    			<div>按房间号查找</div>
    			<input name="room_num"></input><a href="find.do">&nbsp;查找</a>
    				<br><br>
    			<div>按闹钟时间段查找（24小时制，格式如13:20)</div>
    			开始时间<input name="begin_time">接收时间<input name='end_time'><a href="find.do">&nbsp;查找</a>
    			<br><br>
    			
    			
    		<div class='hide_class'>全部选择:<input type='checkbox'></input></div>
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
								<a href='music.do'>您好</a>
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
    			
    </div>   	
    </div>
     </div>
     <div id="footer_bg">
     	<div class='container'>
    		<div style="text-align:center;vertical-align:middle;line-height:50px;margin-bottom:10px;">版权所有</div>
    	</div>
  	</div>
    </body>
</html>