package com.smit.web.pubsubhubbub;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.HtmlEmail;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.PubSubService;
import com.smit.vo.PubSubUser;
import com.smit.vo.Publisher;

public class PubSubProcessAction extends MappingDispatchAction{
	
	private PubSubService service;
	public ActionForward publisher(ActionMapping mapping,ActionForm from,
			HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("publisher");
	}
	public ActionForward subscriber(ActionMapping mapping,ActionForm from,
			HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("subscriber");
	}
	public ActionForward about(ActionMapping mapping,ActionForm from,
			HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("about");
	}
	public ActionForward doc(ActionMapping mapping,ActionForm from,
			HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("doc");
	}
	public ActionForward login(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		List<PubSubUser> userList = service.getByName(login);
		if(userList.size()>0){
			//has regist
			PubSubUser user = userList.get(0);
			if(login.equals(user.getUsername())&&password.equals(user.getPassword())){
				request.getSession().setAttribute("username", login);
				request.getSession().setAttribute("role", user.getRole());
				request.getSession().setMaxInactiveInterval(60*5);
				if("publisher".equals(user.getRole())){
					List<Publisher> list = service.findByName(login);
					if(list.size()>0){
						request.setAttribute("publisher", list.get(0));
						return mapping.findForward("hub_setting");
					}else{
						return mapping.findForward("publisher_login");
					}
				}else if("subscriber".equals(user.getRole())){
					 return mapping.findForward("subscriber_login");
				}
			}
		}
		return mapping.findForward("login");
	}
	public  ActionForward regist(ActionMapping mapping,ActionForm from,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password_confirm = request.getParameter("confirm_password");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		List<PubSubUser> userlist = service.getByName(username);
		if(userlist.size()>0){
			//this username has already regist!
			PubSubUser user = userlist.get(0);
			List<String> msgs = new ArrayList<String>();
			msgs.add("username has already been taken!");
			if(email.equals(user.getEmail())){
				msgs.add("email has already been taken!");
			}
			request.setAttribute("list", msgs);
			return mapping.findForward("reg_fail");
		}else{
			//sucecess,go to email to active!
			//save user regist info
			if(!password.equals(password_confirm)){
				List<String> msgs = new ArrayList<String>();
				msgs.add("password not equal!");
				String reg = "^.+@.+$";
				if(!email.matches(reg)){
					msgs.add("email address are not correct!");
				}
				request.setAttribute("list", msgs);
				return mapping.findForward("reg_fail");
			}
			PubSubUser regUser = new PubSubUser();
			regUser.setUsername(username);
			regUser.setPassword(password);
			regUser.setRole(role);
			regUser.setEmail(email);
			SimpleDateFormat formater = new SimpleDateFormat();
			regUser.setCreate_time(formater.format(new Date()));
			//设置状态为未激活
			regUser.setStatus(0);
			String key = RandomStringUtils.randomNumeric(4);
			regUser.setActive_code(key);
			service.addUser(regUser);
			
			//send a email
			HtmlEmail hemail = new HtmlEmail();
			hemail.setHostName("smtp.126.com");
			hemail.setAuthentication("luocheng8888@126.com", "492513");
			hemail.setCharset("utf-8");
			hemail.addTo(email);
			hemail.setFrom("luocheng8888@126.com","lc");
			hemail.setSubject("active");
			//需要随即生成一个字符串激活码，保存在数据库，便于激活时进行比较
			String url = "http://localhost:8080/pring/pubsub_active.do?username="+username+"&key="+key;
			hemail.setHtmlMsg("please click this url to active your account!<a href="+url+">http://localhost:8080/pring/pubsub_active.do?username="+username+"&key="+key+"</a>");
			hemail.send();
			return mapping.findForward("reg_success");
		}
	}
	
	//激活,返回登录界面
	public ActionForward active(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		String username = request.getParameter("username");
		String key = request.getParameter("key");
		List<PubSubUser> userlist = service.getByName(username);
		if(userlist.size()>0){
			PubSubUser user = userlist.get(0);
			if(key.equals(user.getActive_code())){
				user.setStatus(1);
				service.updateUser(user);
//				if("publisher".equals(user.getRole())){
//					return mapping.findForward("publisher_login");
//				}else if("subscriber".equals(user.getRole())){
//					return mapping.findForward("subscriber_login");
//				}
			}
		}
		return mapping.findForward("login");
	}
	
	
	public ActionForward logout(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
		return mapping.findForward("main");
	}
	
	public ActionForward dashboard(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("dashboard");
	}
	public ActionForward show_sub(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		return mapping.findForward("show_sub");
	}
	public ActionForward show_pub(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		String url = request.getParameter("url");
		System.out.println(url);
		//send request to this feed url,if response code=200;success,else fail
		try{
			HttpClient httpclient = new HttpClient();
			PostMethod postmethod = new PostMethod(url);
			httpclient.executeMethod(postmethod);
			int code = postmethod.getStatusCode();
			if(code==HttpServletResponse.SC_OK){
				Publisher p = new Publisher();
				p.setUsername((String) request.getSession().getAttribute("username"));
				p.setFeed_url(url);
				service.addPublisher(p);
				return mapping.findForward("show_pub");
			}
		}catch (Exception e){
			e.printStackTrace();
			List<String> list = new ArrayList<String>();
			list.add("We could not fetch your feed (HTTP response is 0). Make sure it is accessible.");
			request.setAttribute("list", list);
		}
		return mapping.findForward("publisher_login");
	}
	public ActionForward updatePublish(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		String domain = request.getParameter("domain");
		String server_name = request.getParameter("name");
		String server_url = request.getParameter("url");
		String server_logo = request.getParameter("server_logo");
		String bg_color = request.getParameter("bg_color");
		String font_color = request.getParameter("font_color");
		Publisher p = new Publisher();
		p.setDomain(domain);
		p.setService_name(server_name);
		p.setService_url(server_url);
		p.setService_logo(server_logo);
		p.setBg_color(bg_color);
		p.setFont_color(font_color);
		service.updatePublisher(p);
		
		request.setAttribute("publisher", p);
		return mapping.findForward("edit_hub");
	}
	public ActionForward account(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		return mapping.findForward("account");
	}
	public PubSubService getService() {
		return service;
	}
	public void setService(PubSubService service) {
		this.service = service;
	}
	
	
}
