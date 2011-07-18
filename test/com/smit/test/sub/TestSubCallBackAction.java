package com.smit.test.sub;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.xml.sax.DocumentHandler;

public class TestSubCallBackAction extends MappingDispatchAction {

	public ActionForward sendResponseToHub(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		String method = request.getMethod();
		request.setCharacterEncoding("UTF-8");
		System.out.println(request.getCharacterEncoding());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(method.equals("POST")){
			InputStream in = request.getInputStream();
			response.getWriter().println();
			byte[] b = new byte[1024];
			String result = "";
			String line = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
			while((line=reader.readLine())!=null){
				result+= line;
			}
			System.out.println("recived text from hub is"+result);
			Document doc = DocumentHelper.parseText(result);
			System.out.println(doc.asXML());
		}else if(method.equals("GET")){
			System.out.println("recive verify post request from hub!");
			String verify = request.getParameter("hub.verify");
			System.out.println("recived verify is"+verify);
			response.getWriter().println(verify);
		}
		
		return null;
		
	}
}
