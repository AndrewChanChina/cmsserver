package com.smit.web.webService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.NewsService;
import com.smit.util.SmitPage;
import com.smit.vo.News;


public class NewsAction extends MappingDispatchAction{

	private NewsService newsService;
	public ActionForward news(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		try{
			String columnKey = request.getParameter("columnKey");
			int partId = Integer.parseInt(columnKey);
			SmitPage page = new SmitPage(1);
			page.setPageSize(20);
			List<News> listNews = newsService.findByPartId(page,partId);
			sendNewsXml(response, listNews);
			
		}catch (Exception e){
			e.printStackTrace();
			sendErrorXml(response, e);
		}
		return null;
		
	}
	private void sendErrorXml(HttpServletResponse response, Exception e)
			throws IOException {
		response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<ErrorData>"+e.getMessage()+"</ErrorData>");
		sb.append("<Exception>"+e.getClass().getName()+"</Exception>");
		sb.append("</xml>");
		pw.println(sb.toString());
	}
	private void sendNewsXml(HttpServletResponse response, List<News> listNews)
			throws IOException {
			response.setContentType("text/xml");
			response.setCharacterEncoding("utf-8");
			PrintWriter pw = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("<xml>");
			sb.append("<items>");
			for(News n:listNews){
				sb.append("<item>");
				sb.append("<title>"+n.getTitle()+"</title>");
				sb.append("<description>"+n.getDescription()+"</description>");
				sb.append("<link>"+n.getLink()+"</link>");
				sb.append("<author>"+n.getAuthor()+"</author>");
				sb.append("<comments>"+n.getComments()+"</comments>");
				sb.append("<category>"+n.getCategory()+"</category>");
				sb.append("<pubDate>"+n.getPubDate()+"</pubDate>");
				sb.append("</item>");
			}
			sb.append("</items>");
			sb.append("</xml>");
			pw.println(sb.toString());
		
	}
	public NewsService getNewsService() {
		return newsService;
	}
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
}
