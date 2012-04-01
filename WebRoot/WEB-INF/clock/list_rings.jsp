<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>
<div id="show_clock_list">

	<h3>
		所有铃声资源
	</h3>
	<div class='hide_class'>
		全部选择:
		<input type='checkbox'></input>
	</div>
	<table class="pn-ltable" style="" width="100%" cellspacing="1"
	cellpadding="0" border="0">
	<thead class="pn-lthead">
		<tr>
			<th>
				名称
			</th>
			<th>
				文件名
			</th>
			<!-- th>
						时间长度
					</th>
					<th>
						服务器路径
					</th-->
			<th>
				选择
			</th>
		</tr>
	</thead>

	<tbody class="pn-ltbody">
		<c:forEach items="${listRings}" var="ring">
			<tr>
				<td>
					<div id='${ring.id}'>${ring.name}</div>
				</td>
				<td>
					${ring.fileName}
				</td>
				<td align="center">
					<input type='radio' name='rings_sel' value='${ring.id}' />
				</td>
			</tr>
		</c:forEach>
			<tr>
			<td></td>
			<td></td>
			<td>
			<div id="sel_rings_ok">确定</div><div id="sel_rings_cancel">取消</div>
			</td>
			</tr>
		</tbody>
	</table>
</div>
