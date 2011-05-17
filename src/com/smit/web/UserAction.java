package com.smit.web;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.MappingDispatchAction;

import com.octo.captcha.module.config.CaptchaModuleConfigHelper;
import com.octo.captcha.module.struts.CaptchaServicePlugin;
import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;
import com.smit.service.IGroupManagerService;
import com.smit.service.IUserService;
import com.smit.service.push.IPushDataService;
import com.smit.util.Constants;
import com.smit.util.SmitPage;
import com.smit.util.WebUtil;
import com.smit.vo.Group;
import com.smit.vo.User;
import com.smit.web.form.RegisterForm;
import com.smit.web.form.SmitLoginForm;
import com.smit.web.form.UserForm;

public class UserAction extends MappingDispatchAction {

	private IUserService userService;
	private IGroupManagerService groupManager;
	private IPushDataService pushDataService;

	/**
	 * 个人用户的主页
	 */
	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// set some data
		return mapping.findForward("success");
	}
	/**
	 * 普通用户登录
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String loginSuc = (String) session.getAttribute(Constants.LOGIN_SUC);
		if (loginSuc != null) {
			//response.sendRedirect("home.do");
			response.sendRedirect("pushdata.do?opt=input");
			return null;
		}

		SmitLoginForm loginForm = (SmitLoginForm) form;

		System.out.println(loginForm.getPasswd());
		System.out.println(loginForm.getUserName());
		try {
			 if(pushDataService.login(Constants.PUSH_HOST,			 
			    loginForm.getUserName(), loginForm.getPasswd())){		
				// login smack success
				session.setAttribute(Constants.LOGIN_SUC, Constants.SUCCESS);
				session.setAttribute(Constants.CURUSERNAME,
						loginForm.getUserName());
				session.setAttribute(Constants.PUSH_CONNECTION, pushDataService);
				session.setAttribute(Constants.LEVEL, Constants.LEVEL_USER);
				User u = userService.findUserByName(loginForm.getUserName());
				if (u != null) {					
					session.setAttribute(Constants.CUR_USER_ID, u.getId());
				} else {
					userService.regUser(loginForm.getUserName(), loginForm.getPasswd());
					User u2 = userService.findUserByName(loginForm.getUserName());
					session.setAttribute(Constants.CUR_USER_ID, u2.getId());
				}
				//response.sendRedirect("home.do");
				response.sendRedirect("pushdata.do?opt=input");
				return null;
			} else {
				throw new Exception("dd");
			}

		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"ee42452"));
			this.saveErrors(request, errors);
			return mapping.findForward("fail");
		}
	}

	/**
	 * 普通用户登出
	 */
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute(Constants.LOGIN_SUC, null);
		pushDataService = (IPushDataService) session
				.getAttribute(Constants.PUSH_CONNECTION);
		if(pushDataService != null)
			pushDataService.getConnection().disconnect();
		response.sendRedirect("login_jsp.do");
		return null;
	}	
	/**
	 * register a user in 'developer' group
	 * 注册开发者
	 */
	public ActionForward register(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try{
			RegisterForm loginForm = (RegisterForm)form;
			userService.regDeveloper(loginForm.getUsername(), 
					loginForm.getPassword(), 
					loginForm.getEmail(), 
					loginForm.getTel());
					
		}catch (Exception e){
			return mapping.findForward("fail");
		}
		return mapping.findForward("success");		
	}
	/**
	 * 开发者登录
	 */
	public ActionForward login_developer(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		String loginSuc = (String) session.getAttribute(Constants.LOGIN_SUC);
		if (loginSuc != null) {
			response.sendRedirect("home_developer.do");
			return null;
		}

		SmitLoginForm loginForm = (SmitLoginForm) form;

		System.out.println(loginForm.getPasswd());
		System.out.println(loginForm.getUserName());
		try {
			if (userService.login(loginForm.getUserName(),
					loginForm.getPasswd())) {
				// save some login information
				session.setAttribute(Constants.LOGIN_SUC, Constants.SUCCESS);
				session.setAttribute("userName", loginForm.getUserName());
				session.setAttribute(Constants.LEVEL, Constants.LEVEL_DEVELOPER);
				User u = userService.findUserByName(loginForm.getUserName());
				if (u != null) {
					session.setAttribute(Constants.CUR_USER_ID, u.getId());
				}				
				// 登录服务器
				loginServer(session);
				response.sendRedirect("home_developer.do");
				return null;
			} else {
				ActionErrors errors = new ActionErrors();
				errors.add("fail", new ActionMessage("err.login.fail"));
				this.saveErrors(request, errors);
				return mapping.findForward("fail");
			}

		} catch (Exception e) {
			return mapping.findForward("exception");
		}
	}

	/**
	 * 开发者登出
	 */
	public ActionForward logout_developer(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		session.setAttribute(Constants.LOGIN_SUC, null);
		response.sendRedirect("loginDevj.do");
		//session.getServletContext().setAttribute(Constants.PUSH_CONNECTION, null);
		return null;
	}

	/**
	 * 管理员登录
	 */
	public ActionForward login_admin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();

		if (!isJcaptchaOK(request)) {
			return mapping.getInputForward();
		}

		SmitLoginForm loginForm = (SmitLoginForm) form;

		System.out.println(loginForm.getPasswd());
		System.out.println(loginForm.getUserName());
		try {
			//TODO 还有防止他们用用户的账号登陆
			if (userService.login(loginForm.getUserName(),
					loginForm.getPasswd())) {
				// save some login information
				session.setAttribute(Constants.LOGIN_SUC, Constants.SUCCESS);
				session.setAttribute("userName", loginForm.getUserName());
				session.setAttribute(Constants.LEVEL, Constants.LEVEL_ADMIN);
				User u = userService.findUserByName(loginForm.getUserName());
				if (u != null) {
					session.setAttribute(Constants.CUR_USER_ID, u.getId());
				}
				response.sendRedirect("index.do");
				return null;
			} else {
				ActionErrors errors = new ActionErrors();
				errors.add("fail", new ActionMessage(
						"err.login.fail"));
				this.saveErrors(request, errors);
				return mapping.findForward("fail");
			}

		} catch (Exception e) {			
			return mapping.findForward("exception");
		}
	}

	/**
	 * 管理员登出
	 */
	public ActionForward logout_admin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		session.setAttribute(Constants.LOGIN_SUC, null);
		return mapping.findForward("login_admin.do");
	}

	public ActionForward listUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SmitPage pager = new SmitPage(WebUtil.getIntByRequestParament(request,
				SmitPage.pageNumberParameterName, 1));
		int pageSize = 10;
		pager.setPageSize(pageSize);
		pager.setUrl("listuser.do?");

		System.out.print("hehhee");

		try {
			List list = userService.listAllUsers(pager);
			request.setAttribute("pb", pager);
			request.setAttribute("listUser", list);
		} catch (Exception e) {
			return mapping.findForward("fail");
		}
		return mapping.findForward("showuserlist");
	}

	public ActionForward deleteUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		User g = new User();
		g.setId(Integer.valueOf(id));

		try {
			this.userService.delete(g);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "can't delete user where id = "
					+ id + "with error:" + e.getMessage());
			request.setAttribute("backUrl", "listuser.do");
			return mapping.findForward("fail");
		}
		return mapping.findForward("userright_userlist");

	}

	public ActionForward goNewUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			UserForm userForm = new UserForm();
			userForm.setHideId(WebUtil.setHideId2Session(request.getSession()));

			String id = request.getParameter("id");

			if (id != null) {
				User g = userService.getUser(Integer.valueOf(id));
				BeanUtils.copyProperties(userForm, g);
				userForm.setGroupId(String.valueOf(g.getGroup().getId()));

			}
			List groupList = groupManager.listAllGroups(null);
			for (Object item : groupList) {
				Group g = (Group) item;
				System.out.println(g.getGroupName());
			}
			request.setAttribute("userForm", userForm);
			request.setAttribute("groupList", groupList);

		} catch (Exception e) {
			return mapping.findForward("fail");
		}

		return mapping.findForward("continue");
	}

	public ActionForward saveUpdateUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserForm userForm = (UserForm) form;
		if (WebUtil.isHideIdEqual(request.getSession(), userForm.getHideId()) == false) {
			return mapping.findForward("fail");
		}

		User u = new User();
		Group group = new Group();
		group.setId(Integer.valueOf(userForm.getGroupId()));
		BeanUtils.copyProperties(u, userForm);
		u.setUserName(userForm.getUserName());
		u.setGroup(group);

		try {
			if (userForm.getId() != null && userForm.getId().isEmpty() == false) {
				u.setId(Integer.valueOf(userForm.getId()));
				userService.update(u);
			} else {
				userService.save(u);
			}
		} catch (Exception e) {

			e.printStackTrace();
			return mapping.findForward("fail");
		}
		return mapping.findForward("userright_userlist");

	}

	public boolean isJcaptchaOK(HttpServletRequest request) {
		CaptchaService service = CaptchaServicePlugin.getInstance()
				.getService();
		String responseKey = CaptchaServicePlugin.getInstance()
				.getResponseKey();
		String captchaID = CaptchaModuleConfigHelper.getId(request);
		String challengeResponse = request.getParameter(responseKey);

		request.removeAttribute(responseKey);
		boolean isResponseCorrect = false;
		if (challengeResponse != null)
			try {
				isResponseCorrect = service.validateResponseForID(captchaID,
				challengeResponse).booleanValue();
			} catch (CaptchaServiceException e) {

				request.setAttribute(CaptchaServicePlugin.getInstance()
						.getMessageKey(), CaptchaModuleConfigHelper
						.getMessage(request));

				throw (e);// 抛出程序应用异常
			}
		if (isResponseCorrect) {

			return true;// 正确
		}

		ActionErrors errors = new ActionErrors();// 用ActionError替换了他自己的Message
		errors.add("jcaptcha_error_msg", new ActionMessage(
				"error.jcaptcha.error.inputInvalid"));
		this.addErrors(request, errors);
		return false;
	}
	
	private void loginServer(HttpSession session){
		ServletContext application = session.getServletContext();
		IPushDataService ps = (IPushDataService)application.getAttribute(Constants.PUSH_CONNECTION);
		if(ps == null){
			if(pushDataService.login(Constants.PUSH_HOST,			 
				    Constants.PUSH_SERVERNAME, Constants.PUSH_SERVERPASSWORD)){		
					// login smack success
					application.setAttribute(Constants.LOGIN_SUC, Constants.SUCCESS);				
					application.setAttribute(Constants.PUSH_CONNECTION, pushDataService);
			}
		}
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setGroupManager(IGroupManagerService groupManager) {
		this.groupManager = groupManager;
	}

	public void setPushDataService(IPushDataService pushDataService) {
		this.pushDataService = pushDataService;
	}

}
