package com.smit.web.control.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import antlr.collections.impl.LList;

import com.smit.service.ProductControlService;
import com.smit.vo.Order;
import com.smit.vo.OrderAndOption;
import com.smit.vo.TestOption;

public class TestOptionAction extends MappingDispatchAction{
	
	ProductControlService service;

	public ProductControlService getService() {
		return service;
	}

	public void setService(ProductControlService service) {
		this.service = service;
	}
	
	public ActionForward showOption(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		List<Order> orders = service.loadOrder();
		if(orders.size()>0){
			request.setAttribute("orders", orders);
		}
		return mapping.findForward("showOption");
	}
	
	public ActionForward showAddOption(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("showAddOption");
	}
	
	public ActionForward showDelOption(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		List<TestOption> options = service.getOptions();
		if(options.size()>0){
			request.setAttribute("options", options);
		}
		return mapping.findForward("showDelOption");
	}
	
	public ActionForward queryOption(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		String order_code = request.getParameter("order_code");
		//List<Order> order = service.loadOrder(order_code);
		createXMLResult(response, order_code);
		return null;
	}

	private void createXMLResult(HttpServletResponse response, String order_code)
			throws IOException {
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<global>");
		
		List<OrderAndOption> list = service.getOptionsByCode(order_code);
		if(list.size()>0){
			//订单号唯一，根据订单号只能找到一个订单；
			
			for(OrderAndOption oo : list){
				System.out.println("********=====>");
				sb.append("<item>"+oo.getOption_name()+"</item>");
			}
			//sb.append("<checkID>")
		}else{
			sb.append("<statusCode>109</statusCode>");
		}
		sb.append("</global>");
		pw.println(sb.toString());
	}
	
	public ActionForward addOption(ActionMapping mapping ,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String[] names = request.getParameterValues("name");
		System.out.println("+============="+names[0]);
		String[] ids = request.getParameterValues("test_id");
		String[] times = request.getParameterValues("create_time");
		System.out.println(names.length+">======"+ids.length);
		
		checkAndAddOption(response, names, ids,times);
		return null;
	}

	private void checkAndAddOption(HttpServletResponse response, String[] names,
			String[] ids,String[] times) throws IOException {
		Arrays.sort(ids);
		Set<String> set = new HashSet<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		for(int i=0;i<ids.length;i++){
			set.add(ids[i]);
		}
		if(set.size() == ids.length){
			Date date = new Date();
			for(int i=0;i<names.length;i++){
				if(!("".equals(names[i]))){
					TestOption option = new TestOption();
					option.setName(names[i]);
					option.setTest_id(ids[i]);
					option.setCreate_time(format.format(date));
					service.insertOption(option);	
				}
			}
			response.getWriter().println("success!");
		}else{
			response.getWriter().println("test_id can't repeat! please try again!");
		}
	}
	
	public ActionForward deleteOption(ActionMapping mapping ,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		return null;
	}

}
