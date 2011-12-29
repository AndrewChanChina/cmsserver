package com.smit.test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.json.simple.JSONArray;

public class TestCharts extends MappingDispatchAction{

	public ActionForward testCharts(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		List list = new ArrayList();
		list.add(21);
		list.add(45);
		list.add(67);
		list.add(32);
		list.add(72);
		list.add(84);
		String jsonarray = JSONArray.toJSONString(list);
		response.getWriter().println(jsonarray);
		return null;
	}
}
