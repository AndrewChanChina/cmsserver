<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

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
			href="./css/hotel_home.css">
		<script type="text/javascript" src="./js/jquery.js"></script>
		<script type="text/javascript" src="./js/hotel_home.js"></script>

		<script type="text/javascript">
			function SetWinHeight(obj) {
				var win = obj;
				if (document.getElementById) {
					if (win && !window.opera) {
						if (win.contentDocument
								&& win.contentDocument.body.offsetHeight)
							win.height = win.contentDocument.body.offsetHeight;
						else if (win.Document && win.Document.body.scrollHeight)
							win.height = win.Document.body.scrollHeight;
					}
				}
			}
		</script>
	</head>
	<body>
		<tiles:insert attribute="header"></tiles:insert>

		<div class='container'>
			<iframe name='mainframe' src="hotel.do?opt=main" scrolling="no"
				width="100%" frameborder="0" onload="SetWinHeight(this)">
			</iframe>
		</div>

		<tiles:insert attribute="footer"></tiles:insert>

	</body>
</html>