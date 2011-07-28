package com.smit.web.control.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.AuthCache;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.ProductControlService;
import com.smit.util.TripleDESHelper;
import com.smit.vo.Device;
import com.smit.vo.Order;

public class WinCEAuthAction extends MappingDispatchAction{
	
	private ProductControlService service;
	public ActionForward getAuthCode(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		String machineId = request.getParameter("machineId");
		String order_code = request.getParameter("orderCode");
		try{
			//同一生产批次，同一个machineID，只能激活一次
			List<Device> dlist = service.findByMidCode(machineId, order_code);
			if(dlist.size()>0){
				response.getWriter().println("501");
				return null;
			}
			//如果同一个生产批次的有授权失败的，则直接返回失败的；
			//有多个失败的，取sn最小的一个
			List<Device> devices = service.getFailCode(order_code, 1);
			if(devices.size()>0){
				Device device = devices.get(0);
				String authCode = device.getAuth_code();
				//更改此auth_code状态为成功
				device.setAuth_status(0);
				device.setMachineID(machineId);
				service.updateDevice(device);
				response.getWriter().println(new TripleDESHelper("123456789ABCDEFGHIJKLMNO", "ECB").ebcEncode(authCode));
				return null;
			}
			
			//根据生产代号查找订单,生成新的sn，mac
			List<Order> list = service.loadOrder(order_code);
			Order order = list.get(0);
			String authCode = "";
			String type = order.getDevice_type();
			String product_code = order.getProduction_code();
			String m_code = order.getManufacturer_code();
			String sn = order.getSn();
			String start_mac = order.getMac();
			int num = order.getMac_num();
			Calendar calendar = Calendar.getInstance();
			String time = String.valueOf(calendar.get(calendar.YEAR));
			List<Object[]> objs = service.findMaxSn(order_code);
			Device device = new Device();
			if(objs.size()>0){
				Object[] properties = objs.get(0);
				long max_sn = Long.parseLong((String) properties[1]);
				System.out.println(max_sn);
				if(max_sn<Integer.parseInt(sn)+(num-1)){
					long mac = Long.parseLong((String) properties[2],16) ;
					long gen_mac = mac+1;
					device.setOrder_code(order_code);
					String mac_str = Long.toHexString(gen_mac);
					mac_str = formatMac(mac_str);
					String gen_sn = String.format("%08d", max_sn+1);
					authCode = type+product_code+m_code+gen_sn+mac_str+time;
					device.setMac(mac_str);
					device.setSn(gen_sn);
					device.setMachineID(machineId);
					device.setAuth_code(authCode.toUpperCase());
					SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					device.setCreate_time(formater.format(new Date()));
					service.addDevice(device);
				}else{
					authCode = "500";
				}
			}else{
				authCode = type+product_code+m_code+sn+start_mac+time;
				device.setMachineID(machineId);
				device.setOrder_code(order_code);
				device.setAuth_code(authCode);
				device.setSn(sn);
				device.setMac(formatMac(start_mac));
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				device.setCreate_time(formater.format(new Date()));
				service.addDevice(device);
			}
			TripleDESHelper helper = new TripleDESHelper("123456789ABCDEFGHIJKLMNO","ECB");
			response.getWriter().println(helper.ebcEncode(authCode.toUpperCase()));
		}catch (Exception e){
			e.printStackTrace();
			response.getWriter().println(500);
		}
		return null;
	}
	private String formatMac(String mac_str) {
		if(mac_str.length()<12){
			int length = mac_str.length();
			for(int i=0;i<12-length;i++){
				mac_str = "0"+mac_str;
			}
		}
		return mac_str;
	}
	
	public ActionForward confirmAuthCode(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		String machineId = request.getParameter("machineId");
		String orderCode = request.getParameter("orderCode");
		String status = request.getParameter("status");
		List<Device> lists = service.findByMidCode(machineId, orderCode);
		try{
			if(lists.size()<=0||!("success".equals(status))&&!("fail".equals(status))){
				response.getWriter().println("400");
				return null;
			}
			if("fail".equals(status)){
				Device device = lists.get(0);
				device.setAuth_status(1);
				service.updateDevice(device);
			}
			response.getWriter().println("200");
		}catch (Exception e){
			e.printStackTrace();
			response.getWriter().println("200");
		}
		return null;
	}
	public ProductControlService getService() {
		return service;
	}
	public void setService(ProductControlService service) {
		this.service = service;
	}

}
