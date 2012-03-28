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
        <link type="text/css" rel="stylesheet" media="screen" href="./css/apk_index.css">
        <script type="text/javascript" src="./js/jquery.js"></script>
        <script type="text/javascript" src="./js/dialog.js"></script>
        <script type="text/javascript" src="./js/apk/apk_index.js"></script>		
        
    </head>
    <body>
    <div id="head_bg">
     	<div class='container'>
    		<div id='header'>Apk远程控制系统</div>
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
    				<h3 id='showaddclock'>Apk上传</h3>    								
    			</div>
    		</div>
    		<div class='dotline'></div>
    		<div id="show_clock_list">
    			<div><h3>条件查找</h3></div>
    			
    			<div>按房间号查找</div>
    			<input name="room_num"></input><a href="find.do">&nbsp;查找</a>
    				<br><br>
    		
    		<div>当前房间：<div id="cur_room_num">201</div></div>
    		<input type="radio" name="install" value="had_install" checked><div id="had_install" class="btn">已经安装</div>
    		<input type="radio" name="install" value="not_install"><div id="not_install" class="btn">未安装</div>
    		<br>
    		
    		    		
    		<div id='btn_uninstall' class="btn">卸载选择中Apk</div>
    		<div id='btn_install' class="btn hide_class">安装选择中Apk</div>
    		<div id='btn_unsel_all' class='btn'>取消全选<input type='checkbox' name="all_unsel"></input></div>
    		<div id='btn_sel_all' class='btn'>全选<input type='checkbox' name="all_sel"></input></div>
    		<br>
    		<div id="show_area">
    			<c:forEach items="${apkList}" var="apkInfo">
    				<div class='item' >
    					<img alt="" src="./images/apk/apk_default.png">
    					<div class='name'>${apkInfo.appName}</div>
    					<div class="hide_class"></div>
    					<input type="checkbox" name="sel" value="${apkInfo.id}">
    				</div>
				</c:forEach>
    		</div>
    			
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