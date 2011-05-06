package com.smit.web.control.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.ProductControlService;
import com.smit.vo.Order;
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
			return mapping.findForward("add");
		}else if(type.equals("delete")){
			return mapping.findForward("delete");
		}
		return null;
	}
	public ActionForward addOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println("success come to here!");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String order_code = format.format(new Date());
		OrderForm orderForm = (OrderForm) form;
		insertOrder(orderForm,order_code);
		response.setCharacterEncoding("utf-8");
		response.getWriter().println("添加成功！生产号为："+ order_code);
		return null;
	}

	public ActionForward queryOrder(ActionMapping mapping ,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String order_code = request.getParameter("order_code");
		String device_type = request.getParameter("device_type");
		List<Order> orders = productService.loadOrder(order_code);
		if(orders.size()>0){
			request.setAttribute("list", orders);
		}
				
		return mapping.findForward("showOrders");
	}
	private void insertOrder(OrderForm orderForm,String order_code) {
		Order order = new Order();
		order.setName(orderForm.getName());
		order.setDevice_type(orderForm.getDevice_type());
		order.setOrder_code(order_code);
		order.setStart_time(orderForm.getStart_time());
		order.setEnd_time(orderForm.getEnd_time());
		order.setManufacturer_code(orderForm.getManufacturer_code());
		order.setProduction_code(orderForm.getProduction_code());
		order.setSn(orderForm.getSn());
		order.setInf_code(orderForm.getInf_code());
		productService.insertOrder(order);
	}
}
