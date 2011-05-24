package com.smit.web.control.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.ProductControlService;
import com.smit.util.StringUtils;
import com.smit.vo.CertifiedProduct;
import com.smit.vo.Device;

public class ProductAction extends MappingDispatchAction{
	ProductControlService productService;

	public ProductControlService getProductService() {
		return productService;
	}

	public void setProductService(ProductControlService productService) {
		this.productService = productService;
	}
	
	public ActionForward queryProduct(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		String auth_code = request.getParameter("auth_code");
		String checkID = request.getParameter("checkId");
		List<Device> list = productService.getDevice(checkID);
		processQuery(response, auth_code, checkID, list);
		return null;
	}

	private void processQuery(HttpServletResponse response, String auth_code,
			String checkID, List<Device> list) throws IOException {
		if(list.size()>0 && list.get(0).getAuth_code().equals(auth_code)){
			Device device = list.get(0);
			String machineID = StringUtils.formatMachineId(device.getMachineID(), 16);
			String newCheckID = StringUtils.getCheckId(machineID);
			insertProduct(checkID, device, newCheckID);
			sendXML(response, newCheckID,"success");
		}else{
			sendXML(response, "", "fail");
		}
	}

	private void insertProduct(String checkID, Device device, String newCheckID) {
		CertifiedProduct cp = new CertifiedProduct();
		cp.setDevice(device);
		cp.setNew_check_id(newCheckID);
		cp.setOld_check_id(checkID);
		cp.setStatus("success");
		cp.setCreate_time(new Date().toString());
		
		productService.insertProduct(cp);
	}

	private void sendXML(HttpServletResponse response, String newCheckID,String type)
			throws IOException {
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		StringBuffer sb = new StringBuffer();
		sb.append("<global>");
		if(type.equals("success")){
			sb.append("<checkID>"+newCheckID+"</checkID>");
		}else if(type.equals("fail")){
			sb.append("<statusCode>107</statusCode>");
		}else if(type.equals("confirm_suc")){
			sb.append("<statusCode>200</statusCode>");
		}else if(type.equals("confirm_fail")){
			sb.append("<statusCode>108</statusCode>");
		}
		sb.append("</global>");
		response.getWriter().println(sb.toString());
	}
	
	public ActionForward confirm(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		String old_check_id = request.getParameter("old_check_id");
		String new_check_id = request.getParameter("new_check_id");
		String auth_code = request.getParameter("auth_code");
		List<Device> list = productService.getDevice(old_check_id);
		if(list.size()>0){
			Device device = list.get(0);
			device.setCheck_id(new_check_id);
			productService.updateDevice(device);
			sendXML(response, "", "confirm_suc");
		}else{
			sendXML(response, "", "confirm_fail");
		}
		return null;
	}
}
