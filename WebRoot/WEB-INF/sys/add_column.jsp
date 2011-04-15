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
				//$("#jvForm").validate();
				//$("#columnName").validate();
			});
		</script>
	</head>
	<body>
	
	<%
	//ColumnDao columnDao = new ColumnDaoImpl();
	//List<Part> allColumns = columnDao.queryAllColumns();
	List<Part> allColumns = (List<Part>)request.getAttribute("allColumns");
%>
	
<script type="text/javascript">

function getTableForm() {
	return document.getElementById('tableForm');
}

function addColumnOptDelete() {
	if(Pn.checkedCount('ids')<=0) {
		alert("请选择您要删除的数据");
		return;
	}
	if(!confirm("您确定删除吗？")) {
		return;
	}
	var f = getTableForm();
	var idList = getCheckedItemId("ids");
	var paramsStr = "";
	
	for(var i=0; i<idList.length; i++)
	{
		paramsStr += idList[i] + "_";
	}
	f.action="deleteColumn.do?id=null&idList=" + paramsStr;
	f.submit();
}

function addColumnOptSaveContent() {
	if(Pn.checkedCount('ids')<=0) {
		alert("请选择您要保存的数据");
		return;
	}
	if(!confirm("您确定更新吗？")) {
		return;
	}
	var f = getTableForm();
	var idList = getCheckedItemId("ids");
	var paramsStr = "";
	var spliter = "^^^";
	for(var i=0; i<idList.length; i++)
	{
		var allContent = getInputKeyAndValueAndStateById(idList[i]);
		//var item = idList[i] + spliter;
		var item = "";
		for(var j=0; j<allContent.length; j++)
		{
			item += allContent[j];
			item += spliter;
		}
		paramsStr += item + "___";
	}
	f.action="updateColumn.do?itemList=" + paramsStr;
	f.submit();
}

function getCheckedItemId(name)
{
	var batchChecks = document.getElementsByName(name);
	var idList = [];
	var count = 0;
	for (var i = 0;i < batchChecks.length; i++) {
		if (batchChecks[i].checked) {
			idList[count] = batchChecks[i].value;
			count ++;
		}
	}
	return idList;
}

function getInputKeyAndValueAndStateById(id)
{
	var array = [];
	var inputId = document.getElementById("id"+id);
	var inputColumnName = document.getElementById("columnName"+id);
	var inputParentID = document.getElementById("parentID"+id);
	var inputPath = document.getElementById("path"+id);
	array[0] = inputId.value;
	array[1] = inputColumnName.value;
	array[2] = inputParentID.value;
	array[3] = inputPath.value;
	return array;
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
							<select name="parentID" onchange="window.alert(this.value);">
							<%
								for(int i = 0; i<allColumns.size(); i++)
								{
									Part part = allColumns.get(i);
									Integer id = part.getId();
									Integer topid = part.getTopid();
									Integer parentid = part.getFather_id();
									String className = part.getTypename();
									%>
										<option value="<%=id%>">
											<%=className%>
										</option>
									<%
									
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
							<input id="columnName" type="text" maxlength="100" name="classToAdd" class="required"
								maxlength="100"/>
						</td>
						<td width="10%" class="pn-flabel pn-flabel-h">
							访问路径:
						</td>
						<td colspan="1" width="40%" class="pn-fcontent">
							<input type="text" maxlength="30" name="path" class="required" maxlength="30" width="100%"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="pn-fbutton">
							<input type="hidden" name="root" value="1" />
							<input type="hidden" name="modelId" value="1" />
							<input type="submit" value="提交" />&nbsp;
							<input type="reset" value="重置" />
						</td>
					</tr>
				</table>
			</form>
			
			<form id="tableForm" method="post">
				<input type="hidden" name="pageNo" value="" />
				<table class="pn-ltable" style="" width="100%" cellspacing="1"
					cellpadding="0" border="0">
					<thead class="pn-lthead">
						<tr>
							<th width="20">
								<input type='checkbox' onclick='Pn.checkbox("ids",this.checked)' />
							</th>
							<th>
								序号
							</th>
							<th>
								栏目名称
							</th>
							<th>
								父栏目名称
							</th>
							<th>
								链接路径
							</th>
							<th>
								操作选项
							</th>
						</tr>
					</thead>
					<tbody class="pn-ltbody">
						<%
							HashMap<Integer, String> idAndNameMap = new HashMap<Integer, String>();
							for(int j = 0; j<allColumns.size(); j++)
							{
								Part p = allColumns.get(j);
								idAndNameMap.put(p.getId(), p.getTypename());
							}
							Collection<String> columnNameValues = idAndNameMap.values();
							Set<Integer> columnNameKeys = idAndNameMap.keySet();
							Object [] keyArray = columnNameKeys.toArray();
							Object [] valueArray = columnNameValues.toArray();
							for (int i = 0; i<allColumns.size(); i++) 
							{
								Part part = allColumns.get(i);
								Integer id = part.getId();
								Integer topid = part.getTopid();
								Integer parentid = part.getFather_id();
								String columnName = part.getTypename();
								String columnPath = part.getPath();
								String parentColumnName = idAndNameMap.get(parentid);
								
						%>
								<tr>
									<td>
										<input type="checkbox" name="ids" value="<%=id%>" />
									</td>
									<td>
										<%=i + 1%>
										<input type="hidden" id="id<%=id%>" name="id" value="<%=id%>" />
									</td>
									<td align="center">
										<input type="text" id="columnName<%=id%>" name="columnName"
											value="<%=columnName%>" class="required" maxlength="255"
											style="width: 120px" />
									</td>
									<td align="center">
											<% 
												if(parentColumnName == null)
												{
												}
												else
												{
											%>
													<select id="parentID<%=id%>" name="parentID" onchange="window.alert(this.value);">
											<%
													for(int k=0; k<valueArray.length; ++k)
													{
														Integer mapID = (Integer)keyArray[k];
														String mapName = (String)valueArray[k];
														if(parentColumnName.equals(mapName))
														{
											%>
															<option value="<%=mapID%>" selected="selected">
																当前:<%=mapName%>
															</option>
											<%
														}
														else
														{
											%>
															<option value="<%=mapID%>">
																<%=mapName%>
															</option>
											<%
														}
													
													}
											%>
													</select>
											<%
												}
											%>
									</td>
									<td align="center">
										<input type="text" id="path<%=id%>" name="path"
											value="<%=columnPath%>" class="required" maxlength="1024"
											style="width: 500px" />
									</td>
									<td align="center">
										<a href="deleteColumn.do?id=<%=id%>&idList=null"
											onclick="if(!confirm('您确定删除吗？')) {return false;}"
											class="pn-opt">删除</a>
									</td>
								</tr>
						<%
							}
						%>
					</tbody>
				</table>
				<div>
					<input type="button" value="删除" onclick="addColumnOptDelete();" />
					&nbsp;
					<input type="button" value="保存内容" onclick="addColumnOptSaveContent();" />
				</div>
			</form>
		</div>
	</body>
</html>