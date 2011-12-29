package com.smit.web.control.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.ProductControlService;
import com.smit.util.KeyedDigestMD5;
import com.smit.util.SmitPage;
import com.smit.util.StringUtils;
import com.smit.util.TripleDESHelper;
import com.smit.vo.CertifiedProduct;
import com.smit.vo.Device;
import com.smit.vo.Order;

public class AuthAction extends MappingDispatchAction {

	ProductControlService productService;

	public ProductControlService getProductService() {
		return productService;
	}

	public void setProductService(ProductControlService productService) {
		this.productService = productService;
	}

	// 登录鉴权
	public ActionForward loginAuth(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		System.out.println("====== Begin to get checkid !========");
		String machineID = request.getParameter("machineID");
		String order_code = request.getParameter("order_code");
		String emmcID = request.getParameter("emmcID");
		System.out.println("recived machine num is:" + machineID);
		try {
			if (machineID.equals("") || machineID == null) {
				sendErrorResult(response, "101");
				return null;
			}
			List<Order> orderList = productService.loadOrder(order_code);
			processOrder(response, machineID, order_code, orderList,emmcID);
			System.out.println("===== Get checkid Success!=======");
		} catch (Exception e) {
			e.printStackTrace();
			sendErrorResult(response, "101");
		}

		return null;
	}

	private void processOrder(HttpServletResponse response, String machineID,
			String order_code, List<Order> orderList,String emmcID) throws ParseException,
			IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		Order order = orderList.get(0);
		if (order != null && date.before(format.parse(order.getEnd_time()))) {
			String format_id = StringUtils.formatMachineId(machineID, 16);
			String checkID = StringUtils.getCheckId(format_id);
			insertDevice(order, format, date, checkID, machineID,emmcID);
			createResult(response, checkID, "success");
		} else {
			createResult(response, "", "fail");
		}
	}

	/**
	 * machineid = chipid(16位)+ mac地址(12位) 生成checkid的时候我们只取最后的16位
	 * 
	 * @param machineID
	 * @return
	 */

	private void insertDevice(Order order, SimpleDateFormat format, Date date,
			String checkID, String machineID,String emmcID) {
		Device device = new Device();
		device.setCheck_id(checkID);
		device.setOrder_code(order.getOrder_code());
		device.setCreate_time(format.format(date));
		device.setMachineID(machineID);
		device.setAuth_status(2);// 1激活失败；0激活成功;2 未授权
		device.setOrder(order);
		device.setEmmcID(emmcID);
		productService.addDevice(device);
	}

	private void createResult(HttpServletResponse response, String checkID,
			String type) throws IOException {
		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();
		StringBuffer sb = new StringBuffer();
		TripleDESHelper helper = new TripleDESHelper("123456789ABCDEFG");
		sb.append("<global>");
		if (type.equals("success")) {
			sb.append("<checkID>" + helper.encode(checkID) + "</checkID>");
		} else if (type.equals("fail")) {
			sb.append("<statusCode>101</statusCode>");
		}
		sb.append("</global>");
		System.out.println(sb.toString());
		pw.println(sb.toString());
	}

	// 请求授权
	public ActionForward reqAuth(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("======Begin to auth !=======");
		String machineID = request.getParameter("machineID");
		String checkID = request.getParameter("checkID");
		try {
			List<Device> list = productService.getDevice(checkID.trim());
			sendAuthCode(response, machineID, list, checkID);
		} catch (Exception e) {
			e.printStackTrace();
			sendErrorResult(response, "105");
		}
		return null;
	}

	private void sendErrorResult(HttpServletResponse response, String code)
			throws IOException {
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		StringBuffer sb = new StringBuffer();
		sb.append("<global>");
		sb.append("<statusCode>" + code + "</statusCode>");
		sb.append("</global>");
		response.getWriter().println(sb.toString());
	}

	private void sendAuthCode(HttpServletResponse response, String machineID,
			List<Device> list, String checkid) throws Exception {
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		StringBuffer sb = new StringBuffer();
		TripleDESHelper helper = new TripleDESHelper("123456789ABCDEFG");
		sb.append("<global>");
		Device device = list.get(0);
		// 首先判断是否已经激活，一台机器只准激活一次
		if (device.getAuth_status() == 0 && device.getAuth_code() != null) {
			sb.append("<statusCode>110</statusCode>");
			sb.append("</global>");
			response.getWriter().println(sb.toString());
			System.out.println("==========this machine has already authrized!=========");
			return;
		}
		
		Order order = device.getOrder();
		
		// 判断是否需要分配mac地址
		StringBuffer bf = null;
		String authCode = null;
		String mac = order.getMac();
		bf = new StringBuffer();
		bf.append(order.getActive_time().substring(2)).append(order.getExpire_time().substring(2))
		.append(String.format("%04d", Integer.parseInt(order.getActive_num())));
		String define = bf.toString();
		String m_code = StringUtils.formatMachineId(order.getManufacturer_code(), 8);
		String p_code = StringUtils.formatMachineId(order.getProduction_code(),8);
		String emmcID = device.getEmmcID();
		if (!mac.equals("0")) {
			System.out.println("======Begin to generate mac,sn! ================");
			// 不为0，则需要分配mac地址，
			// 如果同一个生产批次的有授权失败的，则直接返回失败的mac，sn；
			// 有多个失败的，取sn,mac最小的一个,生成authcode
			List<Device> devices = productService.getFailCode(
					order.getOrder_code(), 1);
			if (devices.size() > 0) {
				System.out.println("============ Use failed mac and sn! =========");
				Device dv = devices.get(0);
				bf = new StringBuffer();
				String mcstr = machineID.substring(0, 16) + dv.getMac();
				bf.append(m_code).append(p_code).append(StringUtils.formatMachineId(mcstr, 32))
						.append(define);
				authCode = bf.toString();
				String sign = KeyedDigestMD5.getKeyedDigest(authCode, "123456789ABCDEFG");
				device.setAuth_code(authCode);
				device.setAuth_status(1);
				device.setMachineID(machineID);
				device.setMac(dv.getMac());
				device.setSn(dv.getSn());
				// 先修改本台设备信息，再修改旧的信息；即把auth_status设置为4，表示该mac
				productService.updateDevice(device);
				dv.setAuth_status(4);
				productService.updateDevice(dv);

				// 需要分配mac地址，要把mac地址也发给客户端
				sb.append("<authCode>" + helper.encode(authCode+sign)
						+ "</authCode>");
				sb.append("<mac>" + helper.encode(device.getMac()) + "</mac>");
				sb.append("</global>");
				response.getWriter().println(sb.toString());
				
				System.out.println("======Success!,Send mac and authCode to client!=====");
				return;
			}

			// 根据生产代号查找订单,生成新的sn，mac
			String sn = order.getSn();
			String start_mac = order.getMac();
			int num = order.getMac_num();
			System.out.println("======= Generate new MAC,sn ! =======");
			// 自定义16位
			List<Object[]> objs = productService.findMaxSn(order.getOrder_code());
			Object[] properties = objs.get(0);
			if (!"".equals(properties[1]) && null != properties[1]) {
				long max_sn = Long.parseLong((String) properties[1]);
				if (max_sn < Integer.parseInt(sn) + (num - 1)) {
					long max_mac = Long.parseLong((String) properties[2], 16);
					long gen_mac = max_mac + 1;
					String mac_str = Long.toHexString(gen_mac);
					String mac_new = formatMac(mac_str);
					String gen_sn = String.format("%08d", max_sn + 1);
					String mstr = machineID.substring(0, 16) + mac_new;

					bf = new StringBuffer();
					bf.append(m_code);
					bf.append(p_code);
					bf.append(StringUtils.formatMachineId(mstr, 32));
					bf.append(define);
					authCode = bf.toString();
					String sign = KeyedDigestMD5.getKeyedDigest(authCode, "123456789ABCDEFG");
					device.setMac(mac_str.toUpperCase());
					device.setSn(gen_sn);
					device.setAuth_code(authCode.toUpperCase());
					SimpleDateFormat formater = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					device.setCreate_time(formater.format(new Date()));
					device.setAuth_status(1);
					productService.updateDevice(device);

					sb.append("<authCode>");
					sb.append(helper.encode(authCode+sign));
					sb.append("</authCode>");	
					sb.append("<mac>");
					sb.append(helper.encode(mac_str));
					sb.append("</mac>");
					
					System.out.println("====== Success!,mac,sn has generate!!=====");
				} else {
					// 该批次激活的设备数已经和量产数相等
					sb.append("<statusCode>105</statusCode>");
					System.out.println("===Can't auth more! ===");
				}
			} else {
				System.out.println("===First auth of this order! =====");
				// 为空，表示是该批次第一台请求授权的设备
				String str = machineID.substring(0, 16) + start_mac;
			    bf = new StringBuffer();
				bf.append(m_code);
				bf.append(p_code);
				bf.append(StringUtils.formatMachineId(str, 32));
				bf.append(define);
				authCode = bf.toString();
				String sign = KeyedDigestMD5.getKeyedDigest(authCode, "123456789ABCDEFG");
				device.setAuth_code(authCode.toUpperCase());
				device.setSn(sn);
				device.setMac(formatMac(start_mac));
				SimpleDateFormat formater = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				device.setCreate_time(formater.format(new Date()));
				device.setAuth_status(1);
				productService.updateDevice(device);

				sb.append("<authCode>");
				sb.append(helper.encode(authCode+sign));
				sb.append("</authCode>");
				sb.append("<mac>");
				sb.append(helper.encode(start_mac));
				sb.append("</mac>");
				
				System.out.println("===First auth success! =====");
			}
		} else {
			// mac=0,则不需要分配mac地址
			// 判断激活的设备数是否已经超过本批次产量，超过了就授权失败；
			System.out.println("=======Don't need generate mac! ======");
			List<Device> activeDevices = productService.getFailCode(
					order.getOrder_code(), 0);
			if (activeDevices.size() < order.getMac_num()) {
				machineID = StringUtils.formatMachineId(machineID, 32);
				System.out.println("===== begin get auth code =====");
			    bf = new StringBuffer();
				bf.append(m_code).append(p_code).append(machineID)
						.append(define).append(emmcID);
				authCode = bf.toString();
				String sign = KeyedDigestMD5.getKeyedDigest(authCode, "123456789ABCDEFG");
				System.out.println("auth_code is:"+ authCode+sign);
				sb.append("<authCode>");
				sb.append(helper.encode(authCode+sign));
				sb.append("</authCode>");
				System.out.println("===begin to update device!===");
				device.setAuth_code(authCode);
				// 设置状态为激活失败；注意：授权时设置为1，若激活了才修改为0
				device.setAuth_status(1);
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// 以激活时间为准
				device.setCreate_time(formater.format(new Date()));
				productService.updateDevice(device);
				
			} else {
				sb.append("<statusCode>105</statusCode>");
			}
		}

		sb.append("</global>");
		response.getWriter().println(sb.toString());
		System.out.println("===== Success authrorized!=====");
	}

	private String formatMac(String mac_str) {
		if (mac_str.length() < 12) {
			int length = mac_str.length();
			for (int i = 0; i < 12 - length; i++) {
				mac_str = "0" + mac_str;
			}
		}
		return mac_str;
	}

	// 激活
	public ActionForward active(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("begin to active machine!");
		String checkID = request.getParameter("checkID");
		try {
			List<Device> list = productService.getDevice(checkID);
			response.setContentType("text/xml;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			StringBuffer sb = new StringBuffer();
			sb.append("<global>");
			if (list.size() > 0) {
				System.out.println("====  start to active =======");
				sb.append("<statusCode>200</statusCode>");
				Device device = list.get(0);
				device.setAuth_status(0);
				productService.updateDevice(device);
			} else {
				sb.append("<statusCode>106</statusCode>");
			}
			sb.append("</global>");
			response.getWriter().println(sb.toString());
			System.out.println("end of active");
		} catch (Exception e) {
			System.out.println("exception when active:"+e.toString());
			sendErrorResult(response, "106");
		}

		return null;
	}

	//客户重新授权
	public ActionForward customerAuth(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		System.out.println("=====Begin to customer auth~=====");
		String emmcID = request.getParameter("emmcID");
		List<Device> list = productService.queryDevice(emmcID);
		response.setContentType("text/xml");
		StringBuffer sb = new StringBuffer();
		sb.append("<global>");
		if(list.size()>0){
			TripleDESHelper helper = new TripleDESHelper("123456789ABCDEFG");
			Device device = list.get(0);
			String sign = KeyedDigestMD5.getKeyedDigest(device.getAuth_code(), "123456789ABCDEFG");
			sb.append("<authCode>");
			sb.append(helper.encode(device.getAuth_code()+sign));
			sb.append("</authCode>");
		}else{
			sb.append("<statusCode>120</statusCode>");
		}
		sb.append("</global>");
		response.getWriter().println(sb.toString());
		System.out.println("====End of customer auth~======");
		return null;
	}
	public ActionForward queryDevice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String orderCode = request.getParameter("orderCode");
		String productCode = request.getParameter("productCode");
		System.out.println(orderCode + ";" + productCode);
		String manuCode = request.getParameter("manuCode");
		Page page = new Page();
		List<Device> list = productService.queryDevice(orderCode, productCode,
				manuCode);
		int count = list.size();
		if (count > page.size) {
			list = list.subList(0, 10);
		}
		page.setCurrentPage(1);
		page.setTotalRecord(count);
		Device device = new Device();
		device.setOrder_code(orderCode);
		device.setMachineID(productCode);
		request.setAttribute("list", list);
		request.getSession().setAttribute("page", page);
		request.getSession().setAttribute("device", device);
		return mapping.findForward("queryDevice");
	}

	public ActionForward getPageDevice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Device device = (Device) request.getSession().getAttribute("device");
		String orderCode = device.getOrder_code();
		String productCode = device.getMachineID();
		System.out.println(orderCode);
		String manuCode = request.getParameter("manuCode");
		Page page = (Page) request.getSession().getAttribute("page");
		String curStr = request.getParameter("currentPage");
		String type = request.getParameter("type");
		int currentPage = Integer.parseInt(curStr);
		int start = 0;
		if ("pre".equals(type)) {
			if (currentPage > 1) {
				// currentPage = currentPage-1;
				page.setCurrentPage(currentPage - 1);
			}
			int begin = (currentPage - 2) * 10;
			start = begin >= 0 ? begin : 0;
		} else if ("next".equals(type)) {
			if (currentPage < page.getCount()) {
				start = currentPage * (10);
				// currentPage = currentPage+1;
				page.setCurrentPage(currentPage + 1);
			} else {
				// currentPage = page.getCount();
				page.setCurrentPage(page.getCount());
				start = (currentPage - 1) * (10);
			}
		}
		List<Device> list = productService.queryPageDevice(orderCode,
				productCode, manuCode, start, page.size);
		System.out.println("list size is:" + list);
		request.setAttribute("list", list);
		request.getSession().setAttribute("page", page);
		return mapping.findForward("querypageDevice");
	}

}
