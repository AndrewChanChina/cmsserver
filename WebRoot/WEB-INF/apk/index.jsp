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
        <script type="text/javascript" src="./js/ajaxfileupload.js"></script>
    </head>
    <body>
    <div id="head_bg">
     	<div class='container'>
    		<div id='header'>Apk远程控制系统</div>
    	</div>
  	</div>
    <div class='container'>    	
    	<div id='side_left' class='hide_class'>
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
    			<form action='apk_add.do' method='post' id="add_apk_form"
							onsubmit="return check_add_apk_form()">
    			<table>
    			<tr>
    			<td class="col_1">应用名称</th></td>
    			<td><input name="appName"></td>
    			</tr>
    			<tr>
    			<td class="col_1">包名</td>
    			<td><input name="packageName"></td>
    			</tr>
    			<tr>
    			<td class="col_1">上传apk</td>
    			<td>
    			<input type='file' name='file' id='up_apk'/>
    			<input type="hidden" name='apk_file' id='apk_name'/>
    			<div id='apk-pick-tip'></div>
    			</td>
    			</tr>
    			<tr>
    			<td class="col_1">上传ICON</td>
    			<td>
    			<input type="file" name="file" id="up-icon" />
    			<input type="hidden" name='icon_file'/>
    			<div id='icon-pick-tip'></div>
    			</td>
    			</tr>
    			<tr>
    			<td><br><br></td>
    			<td><input type="submit"></td>
    			</tr>
    			</table>
    			</form>
    			
    			
    			
    			
    		</div>
    		<div class='dotline'></div>
    		<div id="show_clock_list">
    			<div><h3>条件查找</h3></div>
    			
    			<div>按房间号查找</div>
    			<input name="room_num"></input><a href="find.do">&nbsp;查找</a>
    				<br><br>
    		
    		<div>当前房间：<div id="cur_room_num">201</div></div>
    		
    		    		
    		
    		<form action='apk_pushData.do' method='post' id="install_apk_form"
							onsubmit="return check_install_sel()">
							
				<input type="radio" name="install" value="had_install"
	    		<c:if test="${type != 'uninstall'}">checked</c:if>    		
	    		><div id="had_install" class="btn">已经安装</div>
	    		
	    		<input type="radio" name="install" value="not_install"
	    		<c:if test="${type == 'uninstall'}">checked</c:if>
	    		><div id="not_install" class="btn">未安装</div>
	    		<br>
    		    		
	    		<c:if test="${type != 'uninstall'}">
	    		<div id='btn_uninstall' class="btn">卸载选择中Apk</div>
				</c:if>
	    		<c:if test="${type == 'uninstall'}">
	    		<div id='btn_install' class="btn">安装选择中Apk</div>
	    		</c:if>
	    		
				
	    		<div id='btn_unsel_all' class='btn'>全不选<input type='checkbox' name="all_unsel"></input></div>
	    		<div id='btn_sel_all' class='btn'>全选<input type='checkbox' name="all_sel"></input></div>
	    		<br>
	    		<div id="show_area">
	    			<c:forEach items="${apkList}" var="apkInfo">
	    				<div class='item' >
	    					<img alt="" src="./images/apk/apk_default.png">
	    					<div class='name'>${apkInfo.appName}</div>
	    					<div class="hide_class"></div>
	    					<input type="checkbox" name="ids" value="${apkInfo.id}">
	    				</div>
					</c:forEach>
	    		</div>
	    		<input type="hidden" name="roomnum" value="${roomnum}">
	    		<input type="hidden" name="roomnum" value="${roomnum}">
	    		
	    		<input type="submit">
    		</form>	
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