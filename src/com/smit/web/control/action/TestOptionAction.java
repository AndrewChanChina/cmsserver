package com.smit.web.control.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.ProductControlService;
import com.smit.vo.TestOption;

public class TestOptionAction extends MappingDispatchAction{
	
	ProductControlService service;

	public ProductControlService getService() {
		return service;
	}

	public void setService(ProductControlService service) {
		this.service = service;
	}
	
	public ActionForward addOption(ActionMapping mapping ,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String[] names = request.getParameterValues("name");
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
		for(int i=0;i<ids.length;i++){
			set.add(ids[i]);
		}
		if(set.size() == ids.length){
			for(int i=0;i<names.length;i++){
				TestOption option = new TestOption();
				option.setName(names[i]);
				option.setTest_id(ids[i]);
				option.setCreate_time(times[i]);
				service.insertOption(option);
			}
			response.getWriter().println("success!");
		}else{
			response.getWriter().println("test_id can't repeat! please try again!");
		}
	}
	

}
