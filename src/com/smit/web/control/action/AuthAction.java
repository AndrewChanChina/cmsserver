package com.smit.web.control.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
import com.smit.vo.CertifiedProduct;
import com.smit.vo.Device;
import com.smit.vo.Order;

public class AuthAction extends MappingDispatchAction{
	
	ProductControlService productService;
	
	public ProductControlService getProductService() {
		return productService;
	}

	public void setProductService(ProductControlService productService) {
		this.productService = productService;
	}

	//	登录鉴权
	public ActionForward loginAuth(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		String machineID = request.getParameter("machineId");
		String order_code = request.getParameter("order_code");
		List<Order> orderList = productService.loadOrder(order_code);
		if(orderList.size()!= 0){
			Order order = orderList.get(0);
			String end_time = order.getEnd_time();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			if(date.before(format.parse(end_time))){
				String checkID = getCheckId(machineID);
				insertDevice(order_code, format, date, checkID);
				createResult(response, checkID,"success");
			}else{
				createResult(response, "","fail");
			}
		}else{
			response.setCharacterEncoding("utf-8");
			response.getWriter().println("请先填写测试情况!");
		}
		
		return null;
	}

	private void insertDevice(String order_code, SimpleDateFormat format,
			Date date, String checkID) {
		Device device = new Device();
		device.setCheck_id(checkID);
		device.setOrder_code(order_code);
		device.setCreate_time(format.format(date));
		productService.addDevice(device);
	}

	private void createResult(HttpServletResponse response, String checkID,String type)
			throws IOException {
		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<global>");
		if(type.equals("success")){
			sb.append("<checkID>"+checkID+"</checkID>");
		}else if(type.equals("fail")){
			sb.append("<statusCode>101</statusCode>");
		}
		sb.append("</global>");
		pw.println(sb.toString());
	}

	private String getCheckId(String machineID){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(date);
		String timeStr = time.substring(2,time.length());
		System.out.println("get time is :"+ timeStr);
		int romdanNum = 5678;
		return timeStr+romdanNum+machineID;
	}
	
	//请求授权
	public ActionForward reqAuth(ActionMapping mapping , ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		return null;
	}
	
	//激活
	public ActionForward active(ActionMapping mapping ,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return null;
	}
}
