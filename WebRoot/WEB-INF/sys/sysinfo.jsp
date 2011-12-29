<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="org.hibernate.impl.SessionFactoryImpl"%>
<%@ page import="com.smit.dao.*"%>
<%@ page import="com.smit.vo.*"%>
<%@ page import="org.hibernate.SessionFactory"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="./css/admin.css" rel="stylesheet" type="text/css" />
		<link href="./css/theme.css" rel="stylesheet" type="text/css" />
		<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css" />
		<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css" />
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
	$(getTableForm()).validate();
});

function getTableForm() {
	return document.getElementById('tableForm');
}

function optDelete() {
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
	f.action="deleteSysInfo.do?id=null&idList=" + paramsStr;
	f.submit();
}

function optSaveContent() {
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
	var spliter = "^";
	for(var i=0; i<idList.length; i++)
	{
		var allContent = getInputKeyAndValueAndStateById(idList[i]);
		var item = idList[i] + spliter;
		for(var j=0; j<allContent.length; j++)
		{
			item += allContent[j];
			item += spliter;
		}
		paramsStr += item + "_";
	}
	f.action="updateSysInfo.do?itemList=" + paramsStr;
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
	var inputKey = document.getElementById("key"+id);
	var inputValue = document.getElementById("value"+id);
	var inputState = document.getElementById("checkbox"+id);
	array[0] = inputKey.value;
	array[1] = inputValue.value;
	array[2] = inputState.value;
	return array;
}

function setCheck(id)
{
	var opCheckbox = document.getElementById("checkbox" + id);
	var trueOrFalse = true;
	if(opCheckbox != null && opCheckbox != "undefined")
	{
		trueOrFalse = opCheckbox.checked;
	}
	var f = getTableForm();
	f.action="enableSysInfo.do?id=" + id + "&enable=" + trueOrFalse;
	f.submit();
}

</script>
	</head>
	<body>
		<div class="body-box">
			<div class="rhead">
				<div class="rpos">
					当前位置: 基础数据 - 列表
				</div>
				<form class="ropt">
				</form>
				<div class="clear"></div>
			</div>

			<form id="jvForm" action="addSysInfo.do" method="post"
				style="padding: 5px 0 0 5px">
				键:
				<input type="text" name="sysInfoKey" class="required"
					maxlength="255" style="width: 120px" />
				值:
				<input type="text" name="sysInfoValue" class="required"
					maxlength="255" style="width: 500px" />
				<input type="submit" value="添加" />
			</form>

			<%
				//Get all SysInfos from db;
				//SysInfoDao sysInfoDao = new SysInfoDaoImpl();
				//List<SysInfo> list = sysInfoDao.queryAllSysInfo();
				List<SysInfo> list = (List<SysInfo>)request.getAttribute("allSysInfos");
				if (list == null) {
					return;
				}
				int length = list.size();
			%>

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
								键
							</th>
							<th>
								值
							</th>
							<th>
								启用
							</th>
							<th>
								操作选项
							</th>
						</tr>
					</thead>
					<tbody class="pn-ltbody">
						<%
							int i = 0;
							for (; i < length; i++) {
								SysInfo info = (SysInfo) list.get(i);
								Integer info_id = info.getId();
								String info_key = info.getInfo_key();
								String info_value = info.getInfo_value();
								String info_state = info.getInfo_state();
						%>
						<tr>
							<td>
								<input type="checkbox" name="ids" value="<%=info_id%>" />
							</td>
							<td><%=i + 1%><input type="hidden" name="id"
									value="<%=info_id%>" />
							</td>
							<td align="center">
								<input type="text" id="key<%=info_id%>" name="key"
									value="<%=info_key%>" class="required" maxlength="255"
									style="width: 120px" />
							</td>
							<td align="center">
								<input type="text" id="value<%=info_id%>" name="value"
									value="<%=info_value%>" class="required" maxlength="255"
									style="width: 500px" />
							</td>
							<td align="center">
								<input type="checkbox" id="checkbox<%=info_id%>"
									<%if (info_state.equalsIgnoreCase("y")) {
					System.out.println(info_id + " checked!!!!");%>
									checked="checked"
									<%} else {
					System.out.println(info_id + " NOT checked!!!!");
				}%>
									onclick="setCheck(<%=info_id%>)" />
								<input type="hidden" id="disabled<%=info_id%>>" name="disabled"
									value="false" />
							</td>
							<td align="center">
								<a href="deleteSysInfo.do?id=<%=info_id%>&idList=null"
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
					<input type="button" value="删除" onclick="optDelete();" />
					&nbsp;
					<input type="button" value="保存内容" onclick="optSaveContent();" />
				</div>
			</form>
		</div>
	</body>
</html>