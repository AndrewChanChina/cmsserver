package com.smit.web.control.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.ProductControlService;
import com.smit.util.StringUtils;
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
		String machineID = request.getParameter("machineID");
		String order_code = request.getParameter("order_code");
		if(!("".equals(machineID))&& null!= machineID){
			machineID = StringUtils.formatMachineId(machineID,16);
			List<Order> orderList = productService.loadOrder(order_code);
			processOrder(response, machineID, order_code, orderList);
		}else{
			createResult(response, "", "fail");
		}
		return null;
	}

	private void processOrder(HttpServletResponse response, String machineID,
			String order_code, List<Order> orderList) throws ParseException,
			IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		Order order = orderList.get(0);
		if(order != null && date.before(format.parse(order.getEnd_time()))){
			String checkID = StringUtils.getCheckId(machineID);
			insertDevice(order_code, format, date, checkID,machineID);
			createResult(response, checkID,"success");
		}else{
			createResult(response, "", "fail");
		}
	}

	/**
	 * machineid = chipid(16位)+ mac地址(12位)
	 * 生成checkid的时候我们只取最后的16位
	 * @param machineID
	 * @return
	 */
	

	private void insertDevice(String order_code, SimpleDateFormat format,
			Date date, String checkID,String machineID) {
		Device device = new Device();
		device.setCheck_id(checkID);
		device.setOrder_code(order_code);
		device.setCreate_time(format.format(date));
		device.setMachineID(machineID);
		List<Order> orders = productService.loadOrder(order_code);
		if(orders.size()>0){
			device.setOrder(orders.get(0));
		}
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

	
	//请求授权
	public ActionForward reqAuth(ActionMapping mapping , ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String machineID = request.getParameter("machineId");
		String checkID = request.getParameter("checkId");
		List<Device> list = productService.getDevice(checkID.trim());
		sendAuthCode(response, machineID, list);
		return null;
	}

	private void sendAuthCode(HttpServletResponse response, String machineID,
			List<Device> list) throws IOException  {
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		StringBuffer sb = new StringBuffer();
		sb.append("<global>");
		if(list.size()>0){
			Order order = productService.loadOrder(list.get(0).getOrder().getId());
			machineID = StringUtils.formatMachineId(machineID, 32);
			String m_code = StringUtils.formatMachineId(order.getManufacturer_code(), 8);
			String p_code = StringUtils.formatMachineId(order.getProduction_code(), 8);
			String authCode = machineID + m_code + p_code;
			
			sb.append("<authCode>"+authCode+"</authCode>");
			Device device = list.get(0);
			device.setAuth_code(authCode);
			productService.updateDevice(device);
		}else{
			sb.append("<statusCode>105</statusCode>");
		}
		sb.append("</global>");
		response.getWriter().println(sb.toString());
		
	}
	
	//激活
	public ActionForward active(ActionMapping mapping ,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String checkID = request.getParameter("checkId");
		List<Device> list = productService.getDevice(checkID);
		response.setContentType("text/xm" +
				"l;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		StringBuffer sb = new StringBuffer();
		sb.append("<global>");
		if(list.size()>0){
			sb.append("<statusCode>200</statusCode>");
			Device device = list.get(0);
			device.setActive_status("200");
			productService.updateDevice(device);
		}else{
			sb.append("<statusCode>106</statusCode>");
		}
		sb.append("</global>");
		response.getWriter().println(sb.toString());
		return null;
	}
	
}
