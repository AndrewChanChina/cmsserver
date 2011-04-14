<%@page import="org.hibernate.impl.SessionFactoryImpl"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page contentType= "text/html;charset=UTF-8" language= "java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.smit.dao.*"%>
<%@ page import="com.smit.vo.*"%>
<%@ page import="com.smit.vo.Part"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title></title>
		<link href="./css/admin.css" rel="stylesheet" type="text/css" />
		<link href="./css/theme.css" rel="stylesheet" type="text/css" />
		<link href="./css/jquery.validate.css" rel="stylesheet"
			type="text/css" />
		<link href="./css/jquery.treeview.css" rel="stylesheet"
			type="text/css" />
		<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css" />
		<script src="./js/fckeditor.js" type="text/javascript"></script>
		<script src="./js/WdatePicker.js" type="text/javascript"></script>
		<script src="./js/jquery.js" type="text/javascript"></script>
		<script src="./js/jquery.ext.js" type="text/javascript"></script>
		<script src="./js/pony.js" type="text/javascript"></script>
		<script src="./js/admin.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function() {
				$("#jvForm").validate();
			});
		</script>
	</head>
	<body>
	
	<%
	ColumnDao columnDao = new ColumnDaoImpl();
	List<Part> allColumns = columnDao.queryAllColumns();
	//for(int i = 0; i<allColumns.size(); i++)
	//{
		//Part part = allColumns.get(i);
		//Integer id = part.getId();
		//Integer topid = part.getTopid();
		//Integer parentid = part.getFather_id();
		//if(parentid == null)
		//{
			//parentid = 0;
		//}
		//String name = part.getTypename();

		//System.out.println("id = " + id);
		//System.out.println("topid = " + topid);
		//System.out.println("parentid = " + parentid);
		//System.out.println("name = " + name);
		//System.out.println("======== ");
	//}
%>
	
<script type="text/javascript">
	function previewImg() {

	}
</script>
		<form id="uploadForm" action="#" method="post"
			enctype="multipart/form-data" target="hiddenIframe"
			style="display: none; width: 0px; height: 0px;">
			<span id="fileContent"></span>
			<input id="ufWidth" type="hidden" name="zoomWidth" />
			<input id="ufHeight" type="hidden" name="zoomHeight" />
			<input id="ufFileName" type="hidden" name="fileName" />
			<input id="ufMark" type="hidden" name="mark" />
			<input id="uploadNum" type="hidden" name="uploadNum" />
		</form>
		<iframe name="hiddenIframe" frameborder="0" border="0"
			style="display: none; width: 0px; height: 0px;"></iframe>
		<div class="body-box">
			<div class="rhead">
				<div class="rpos">
					当前位置: 栏目管理 - 添加
				</div>
				<form class="ropt">
					<!--<input type="submit" value="返回列表" />-->
					<!-- onclick="this.form.action='v_list.do'";-->
				</form>
				<div class="clear"></div>
			</div>
			<form method="post" action="addColumn.do" id="jvForm">
				<table width="100%" class="pn-ftable" cellpadding="2"
					cellspacing="1" border="0">
					<tr>
						<td width="10%" class="pn-flabel pn-flabel-h">
							上级栏目:
						</td>
						<td colspan="3" width="90%" class="pn-fcontent">
							<select name="parentID">
							<%
								for(int i = 0; i<allColumns.size(); i++)
								{
									Part part = allColumns.get(i);
									Integer id = part.getId();
									Integer topid = part.getTopid();
									Integer parentid = part.getFather_id();
									if(parentid == null)
									{
										parentid = 0;
									}
									String className = part.getTypename();
									if(i == 0)//id = 0: all columns
									{
									}
									else if(i == 1 && parentid == 1)
									{
									%>
										<option value="<%=id%>" selected="selected">
											<%=className%>
										</option>
									<% 
									}
									else
									{
									%>
										<option value="<%=id%>">
											<%=className%>
										</option>
									<%
									}
								}
							%>
							</select>
						</td>
					</tr>
					<tr>
						<td width="10%" class="pn-flabel pn-flabel-h">
							<span class="pn-frequired">*</span>栏目名称:
						</td>
						<td colspan="1" width="40%" class="pn-fcontent">
							<input type="text" maxlength="100" name="classToAdd" class="required"
								maxlength="100"/>
						</td>
						<td width="10%" class="pn-flabel pn-flabel-h">
							<span class="pn-frequired">*</span>访问路径:
						</td>
						<td colspan="1" width="40%" class="pn-fcontent">
							<input type="text" maxlength="30" name="path" class="required"
								maxlength="30" />
						</td>
					</tr>
					<tr>
						<td colspan="4" class="pn-fbutton">
							<input type="hidden" name="root" value="1" />
							<input type="hidden" name="modelId" value="1" />
							<input type="submit" value="提交" />
							&nbsp;
							<input type="reset" value="重置" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>