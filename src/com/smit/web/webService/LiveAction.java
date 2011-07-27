package com.smit.web.webService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.collection.CollectLiveTask;

public class LiveAction extends MappingDispatchAction{

	public ActionForward getChannelProgram(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
			try{
				String channelType = "";
				String channel = "";
				//经测试。post不会有乱码，但是get会有乱码
				if(request.getMethod().equalsIgnoreCase("get")){
					 System.out.println("run get !");
					 channelType = new String(request.getParameter("type").getBytes("iso-8859-1"),"utf-8");
					 channel = new String(request.getParameter("channel").getBytes("iso-8859-1"),"utf-8");
//					 channelType = request.getParameter("type");
//					 channel = request.getParameter("channel");
				}else if(request.getMethod().equalsIgnoreCase("post")){
					channelType = request.getParameter("type");
					channel = request.getParameter("channel");
				}
				File file = new File("D:"+File.separator+channelType+File.separator+channel+".txt");
				if(!file.exists()){
					CollectLiveTask ct = new CollectLiveTask();
					ct.download();
				}
				InputStream in = new FileInputStream(file);
				StringBuffer sb = streamToXML(in);
				response.setContentType("text/xml");
				response.getWriter().println(sb.toString());
			}catch (Exception e){
				e.printStackTrace();
				response.getWriter().println("bad params request!param type and channel are required!");
			}
			return null;
	}

	private StringBuffer streamToXML(InputStream in)
			throws UnsupportedEncodingException, IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(in,"GBK"));

		String result = "";
		String line = "";
		int count = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("<channel>");
		while((line=buffer.readLine())!=null){
			result = line;
			if(!"".equals(result)&&null!=result){
				String reg = "^\\d\\d{1}\\:\\d{2}";
				System.out.println(result);
				result = result.trim();
				String time = result.substring(0,5);
				
				if(!time.matches(reg)){
					count++;
					String date = result.trim().substring(0,8);
					String day = result.trim().substring(8,result.length());
					System.out.println("date is:"+date+";day is:"+day);
					if(count>1){
						sb.append("</entry>");
					}
					sb.append("<entry date='"+date+"' ");
					sb.append("day='"+day+"'>");
				}else{
					String name = result.substring(5, result.length());
					System.out.println("at"+time+",program is :"+name);
					sb.append("<item>");
					sb.append("<time>"+time+"</time>");
					sb.append("<program>"+name+"</program>");
					sb.append("</item>");
				}
			}
		}
		sb.append("</entry></channel>");
		return sb;
	}
	
	public ActionForward getChannelList(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		File file = new File("D:\\showtime.zip");
		if(!file.exists()){
			CollectLiveTask ct = new CollectLiveTask();
			ct.download();
		}
		File localFile = new File("D:\\地方频道");
		String[] list = localFile.list();
		StringBuffer sb = new StringBuffer();
		sb.append("<channels>");
		for(String s:list){
			if(s.endsWith(".txt")){
				String name = s.replace(".txt", "").replace("节目单", "");
				name = name.trim().replaceAll("\\（\\d{4}\\-\\d{4}\\）","");
				sb.append("<channel>"+name+"</channel>");
			}
		}
		sb.append("</channels>");
		response.setContentType("text/xml;charset=utf-8");
		response.getWriter().println(sb.toString());
		return null;
	}
}
