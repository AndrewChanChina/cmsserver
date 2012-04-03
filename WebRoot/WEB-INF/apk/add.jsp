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
	

	
	 <div class='container'>    	
    	
    	<div id='main'>
    		<div id='sub_menu'>    			
    				<div class="menu_item "><a href="apk.do">管理APK</a></div>
					<div class="menu_item menu_item_sel"><a href="hotel_apk_add.do">上传APK</a></div>    				
    			</div>
				
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
    	</div>
    </div>
	
	</body>
</html>