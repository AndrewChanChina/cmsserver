<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>

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
			href="./css/apk_index.css">
		<script type="text/javascript" src="./js/jquery.js"></script>
		<script type="text/javascript" src="./js/dialog.js"></script>
		<script type="text/javascript" src="./js/apk/apk_index.js"></script>
		<script type="text/javascript" src="./js/ajaxfileupload.js"></script>
	</head>
	<body>

		
		<div class='container'>

			<div id='main'>
				<div id='sub_menu'>    			
    				<div class="menu_item menu_item_sel"><a href="apk.do">管理APK</a></div>
					<div class="menu_item"><a href="hotel_apk_add.do">上传APK</a></div>    				
    			</div>
				

				<form action='apk_pushData.do' method='post' id="install_apk_form"
					onsubmit="return check_install_sel()">

					<input type="radio" name="install" value="had_install"
						<c:if test="${type != 'uninstall'}">checked</c:if>>
					<div id="had_install" class="btn">
						已经安装
					</div>

					<input type="radio" name="install" value="not_install"
						<c:if test="${type == 'uninstall'}">checked</c:if>>
					<div id="not_install" class="btn">
						未安装
					</div>
					<br>

					<c:if test="${type != 'uninstall'}">
						<div id='btn_uninstall' class="btn">
							卸载选择中Apk
						</div>
					</c:if>
					<c:if test="${type == 'uninstall'}">
						<div id='btn_install' class="btn">
							安装选择中Apk
						</div>
					</c:if>


					<div id='btn_unsel_all' class='btn'>
						全不选
						<input type='checkbox' name="all_unsel"></input>
					</div>
					<div id='btn_sel_all' class='btn'>
						全选
						<input type='checkbox' name="all_sel"></input>
					</div>
					<br>
					<div id="show_area">
						<c:forEach items="${apkList}" var="apkInfo">
							<div class='item'>
								<img alt="" src="./images/apk/apk_default.png">
								<div class='name'>
									${apkInfo.appName}
								</div>
								<div class="hide_class"></div>
								<input type="checkbox" name="ids" value="${apkInfo.id}">
							</div>
						</c:forEach>
					</div>
					<input type="hidden" name="roomnum" value="${roomnum}">
					<input type="hidden" name="roomnum" value="${roomnum}">

					<input type="submit">
				</form>
				<div>
					<smitpage:page pager="${pb}" />
				</div>
			</div>
		</div>

		
	</body>
</html>