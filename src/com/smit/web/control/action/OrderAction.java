package com.smit.web.control.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.ProductControlService;
import com.smit.vo.Order;
import com.smit.vo.OrderAndOption;
import com.smit.vo.TestOption;
import com.smit.web.control.form.OrderForm;

public class OrderAction extends MappingDispatchAction{
	
	ProductControlService productService;

	public ProductControlService getProductService() {
		return productService;
	}

	public void setProductService(ProductControlService productService) {
		this.productService = productService;
	}
	
	public ActionForward showOrder(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		String type = request.getParameter("type");
		if(type.equals("add")){
			List<TestOption> options = productService.getOptions();
			request.setAttribute("options", options);
			return mapping.findForward("add");
		}else if(type.equals("delete")){
			return mapping.findForward("delete");
		}
		return null;
	}
	public ActionForward addOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		try{
			System.out.println("==== Begin to add order! =====");
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			OrderForm orderForm = (OrderForm) form;
			String order_code = orderForm.getManufacturer_code()+format.format(new Date());
			insertOrder(orderForm,order_code);
			
			response.setContentType("text/xml;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			StringBuffer sb = new StringBuffer();
			sb.append("<global>");
			sb.append("<order_code>"+order_code+"</order_code>");
			sb.append("</global>");
			response.getWriter().println(sb.toString());	
			System.out.println("===== Add order success! =======");
		}catch (Exception e){
			
		}
		return null;
	}

	public ActionForward queryOrder(ActionMapping mapping ,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String order_code = request.getParameter("order_code");
		String device_type = request.getParameter("production_code");
		try{
			List<Order> orders = productService.loadOrder(order_code,device_type);
			if(orders.size()>0){
				request.setAttribute("list", orders);
			}	
		}catch (Exception e){
			
		}
		return mapping.findForward("showOrders");
	}
	private void insertOrder(OrderForm orderForm,String order_code) {
		Order order = new Order();
		order.setName(orderForm.getName());
		order.setDevice_type(orderForm.getDevice_type());
		order.setOrder_code(order_code);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String start_time = orderForm.getStart_time();
		String end_time = orderForm.getEnd_time();
		if("".equals(start_time)|| null == start_time){
			order.setStart_time(format.format(date));
		}else{
			order.setStart_time(start_time);
			 
		}
		if("".equals(end_time)|| null == end_time){
			order.setEnd_time(format.format(DateUtils.addMonths(date, 1)));
		}else{
			order.setEnd_time(end_time);
		}
		
		order.setManufacturer_code(orderForm.getManufacturer_code());
		order.setProduction_code(orderForm.getProduction_code());
		order.setSn(orderForm.getSn());
		order.setMac(orderForm.getMac());
		order.setMachine_id(orderForm.getMachine_id());
		order.setMac(orderForm.getMac().toUpperCase());
		order.setMac_num(Integer.parseInt(orderForm.getNum()));
		order.setActive_num(orderForm.getActive_num());
		order.setActive_time(orderForm.getActive_time().replaceAll("/", ""));
		order.setExpire_time(orderForm.getExpire_time().replaceAll("/", ""));
		String[] options = orderForm.getSelOption().split(";");
		
		for(int i=0;i<options.length;i++){
			TestOption op =	productService.getOption(options[i]);
			OrderAndOption orderOption = new OrderAndOption();
			orderOption.setOrder_code(order_code);
			orderOption.setOption_id(op.getId());
			orderOption.setOption_name(op.getName());
			
			productService.insertOrderOption(orderOption);
		}

		productService.insertOrder(order);
	}
}
