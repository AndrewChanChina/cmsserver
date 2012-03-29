package com.smit.web.pubsubhubbub;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.DefaultHttpAsyncClient;
import org.apache.http.impl.nio.codecs.HttpResponseParser;
import org.apache.http.nio.client.HttpAsyncClient;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.SAXReader;



import com.smit.service.RssAtomService;
import com.smit.service.SubscriberService;
import com.smit.service.push.IPushDataService;
import com.smit.util.ApplicationCache;
import com.smit.util.Constants;
import com.smit.vo.AtomRecord;
import com.smit.vo.RssRecord;
import com.smit.vo.Subscriber;

public class PubsubHubbubAction extends MappingDispatchAction{
	
	private SubscriberService subService;
	private RssAtomService rmServcie;
	private IPushDataService pushService;
	//handle publish request
	public ActionForward publish(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		String hubMode = request.getParameter("hub.mode");
		String huburl = request.getParameter("hub.url");
		if(!"".equals(hubMode)&& !"".equals(huburl)){
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			response.getWriter().println();
			//modify by luocheng 2011-08-03;
			//send notification to openfire
			sendNoteToOpenfire(huburl);
			//to get feed 
			//send http get request to url which has update ,get content and send to subscriber
			try{
				HttpClient client = new HttpClient();
				GetMethod getmethod = new GetMethod(huburl);
				client.executeMethod(getmethod);
				InputStream in = getmethod.getResponseBodyAsStream();
				//String result = getmethod.getResponseBodyAsString();
				SAXReader saxReader = new SAXReader();
				Document doc = saxReader.read(in);
				//parse xml end send content to sub
				Map<String, String> nameSpaceMap = new HashMap<String, String>();
			    nameSpaceMap.put("atom", "http://www.w3.org/2005/Atom");   
			    saxReader.getDocumentFactory().setXPathNamespaceURIs(nameSpaceMap); 
				String url = getFeedUrl(doc);
				System.out.println(url);
				if("".equals(url)||null== url){
					url = huburl;
				}
				parseXML(doc, url);
				System.out.println("ok,execute success!");
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				
			}
		}else{
			response.getWriter().println("hub param isn't correct");
		}
		return null;
	}

	private void sendNoteToOpenfire(String huburl) {
		ApplicationCache app = ApplicationCache.getInstance();
		IPushDataService ps = (IPushDataService) app.getAttribute(Constants.PUSH_CONNECTION);
		if(ps==null || ps.isConnected()==false){
			if(pushService.login(Constants.PUSH_HOST, Constants.PUSH_SERVERNAME, Constants.PUSH_SERVERPASSWORD)){
				ps = pushService;
			}
		}
		ps.sendNoteFromCms(huburl);
	}

	private String getFeedUrl(Document doc) {
		String expression;
		String url = "";
		try{
			expression = "//link[@rel='self']";
			List linklist = doc.selectNodes(expression);
			if(linklist.size()==0){
				expression = "//atom:link[@rel='self']";
				linklist = doc.selectNodes(expression);
			}
			Element  linkele = (Element) linklist.get(0);
			url = linkele.attributeValue("href");
		}catch (Exception e){
			
		}
		return url;
	}

	private void parseXML(Document doc, String url)
			throws Exception{
		String expression;
		String result = "";
		//atom
		expression = "/feed";
		List listfeed = doc.selectNodes(expression);
		int entryNum = 0;
		if(listfeed.size()>0){
			//去除重复的
			List listEntry = doc.selectNodes("/feed//atom:entry");
			List<AtomRecord> alist = rmServcie.findAtomByFeed(url);
			List<AtomRecord> lists = new ArrayList<AtomRecord>();
			for(Iterator i = listEntry.iterator();i.hasNext();){
				Element entry = (Element) i.next();
				int count = 0;
				for(AtomRecord r: alist){
					if(r.getAtom_id().equals(entry.elementText("id"))){
						entry.detach();
						entryNum++;
						count++;
					}
				}
				System.out.println("feed count is:"+count);
				if(count<=0){
					AtomRecord record = new AtomRecord();
					record.setTitle(entry.elementText("title"));
					record.setLink(entry.element("link").attributeValue("href"));
					record.setAtom_id(entry.elementText("id"));
					record.setUpdate_time(entry.elementText("updated"));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
					record.setCreate_time(sdf.format(new Date()));
					record.setFeed_url(url);
					lists.add(record);
				}
			}
			rmServcie.addListAtom(lists);
			result = doc.asXML();
			//有了更新才发通知
			if(entryNum != listEntry.size()){
				postNotifytoSub(result, url);
			}
			return;
		}
		//rss
		expression = "/rss";
		List listrss = doc.selectNodes(expression);
		if(listrss.size()>0){
			//去除重复的
			List listItem = doc.selectNodes("/rss/channel/item");
			List<RssRecord> rlist = rmServcie.findRsssByFeed(url);
			List<RssRecord> lists = new ArrayList<RssRecord>();
			for(Iterator i = listItem.iterator();i.hasNext();){
				Element item = (Element) i.next();
				int count = 0;
				for(RssRecord r: rlist){
					if(r.getGuid().equals(item.elementText("guid"))){
						item.detach();
						entryNum++;
						count++;
					}
				}
				System.out.println(count);
				if(count<=0){
					RssRecord record = new RssRecord();
					record.setTitle(item.elementText("title"));
					record.setLink(item.elementText("link"));
					record.setAuthor(item.elementText("author"));
					record.setDescription(item.elementText("description"));
					record.setPubDate(item.elementText("pubDate"));
					record.setGuid(item.elementText("guid"));
					record.setFeed_url(url);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
					record.setCreate_time(sdf.format(new Date()));
					lists.add(record);
				}
			}
			rmServcie.addListRss(lists);
			result = doc.asXML();
			System.out.println("del distinct item,the result is:"+result);
			//有更新才发通知给sub
			System.out.println(entryNum);
			if(entryNum!=listItem.size()){
				postNotifytoSub(result, url);
			}
		}
	}

	private void postNotifytoSub(String result, String url)
			throws UnsupportedEncodingException, IOException, HttpException {
		List<Subscriber> subs = subService.findByCallUrl(url);
		for(Subscriber s: subs){
				HttpClient subclient = new HttpClient();
				PostMethod postmethod = new UTF8PostMethod(s.getCallback_url());
				RequestEntity requestEntity = new StringRequestEntity(result, "text/xml", "UTF-8");
				postmethod.setRequestEntity(requestEntity);
				postmethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
				subclient.executeMethod(postmethod);
				postmethod.releaseConnection();
		}
	}

	//handle subscribe request
	public ActionForward subscribe(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		String hubmode = request.getParameter("hub.mode");
		String hubtopic = request.getParameter("hub.topic");
		String hubverify = request.getParameter("hub.verify");
		String hubcallback = request.getParameter("hub.callback");
		System.out.println(hubmode);
		if("".equals(hubmode)||"".equals(hubverify)||"".equals(hubtopic)||"".equals(hubcallback)){
			//send response to subscriber
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("param not correct!");
			return null;
		}
		if(!"sync".equals(hubverify)&&!"async".equals(hubverify)){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("param not correct!");
			return null;
		}
		//同步验证
		if("sync".equals(hubverify)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String verify = sdf.format(new Date())+RandomStringUtils.randomNumeric(4);
			HttpClient httpClient = new HttpClient();
			GetMethod getMethod = new GetMethod(hubcallback+"?hub.verify="+verify);
			//getMethod.s
			httpClient.executeMethod(getMethod);
			System.out.println("execute here! send verify!");
			//收到200就处理，否则可以无视；
			if(getMethod.getStatusCode()==HttpServletResponse.SC_OK){
				String verify_back = getMethod.getResponseBodyAsString();
				System.out.println(verify_back);
				if(verify.equals(verify_back.trim())){
					//save subscriber
					System.out.println("hello came here !");
					addSub(hubtopic, hubcallback);
					response.getWriter().println("200");
				}
			}
		}else if("async".equals(hubverify)){
			//异步验证
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String verify = sdf.format(new Date())+RandomStringUtils.randomNumeric(4);
			HttpAsyncClient httpclient = new DefaultHttpAsyncClient();
			httpclient.start();
			try{
				HttpGet getmethod = new HttpGet(hubcallback+"?hub.verify="+verify);
				Future<HttpResponse> future = httpclient.execute(getmethod, null);
				HttpResponse resp = future.get();
				if(resp.getStatusLine().getStatusCode()==HttpServletResponse.SC_OK){
					InputStream in = resp.getEntity().getContent();
					StringBuffer sb = new StringBuffer();
					byte[] b = new byte[1024];
					int n = 0;
					while((n=in.read(b))!=-1){
						sb.append(new String(b,0,n));
					}
					if(verify.equals(sb.toString())){
						addSub(hubtopic, hubcallback);
					}
					System.out.println(sb.toString());
				}
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				httpclient.shutdown();
			}
		}
		return null;
	}

	private void addSub(String hubtopic, String hubcallback) {
		List<Subscriber> listsubs = subService.findByTopicCall(hubtopic,hubcallback);
		if(listsubs.size()==0){
			Subscriber sub = new Subscriber();
			sub.setFeed_url(hubtopic);
			sub.setCallback_url(hubcallback);
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sub.setCreate_time(formater.format(new Date()));
			subService.addSubscriber(sub);
			System.out.println("insert success");
		}
	}

	public SubscriberService getSubService() {
		return subService;
	}

	public void setSubService(SubscriberService subService) {
		this.subService = subService;
	}
	
	public RssAtomService getRmServcie() {
		return rmServcie;
	}

	public void setRmServcie(RssAtomService rmServcie) {
		this.rmServcie = rmServcie;
	}

	public static class UTF8PostMethod extends PostMethod{

		public UTF8PostMethod(String url){ 
		    super(url); 
		} 
		@Override
		public String getRequestCharSet() {
			// TODO Auto-generated method stub
			return "UTF-8";
		}
		
	}

	public IPushDataService getPushService() {
		return pushService;
	}

	public void setPushService(IPushDataService pushService) {
		this.pushService = pushService;
	}
	
}
