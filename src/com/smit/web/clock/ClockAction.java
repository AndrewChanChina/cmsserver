package com.smit.web.clock;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.clock.ClockService;
import com.smit.util.Constants;
import com.smit.util.ParamsString;
import com.smit.vo.alarmclock.Clock;

public class ClockAction extends MappingDispatchAction {
	
	private ClockService clockService;

	public void setClockService(ClockService clockService) {
		this.clockService = clockService;
	}	

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("success");
	}
	
	// 接收客户端返回的状态
	public ActionForward postStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter(ParamsString.UUID);
		String status = request.getParameter(ParamsString.STATUS);
		ServletContext application = request.getSession().getServletContext();
		application.setAttribute(id, status);
		response.getOutputStream().println("ok");
		return null;
	}
	// 接受浏览器查询，状态的情况
	public ActionForward getStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter(ParamsString.UUID);
		ServletContext application = request.getSession().getServletContext();
		String status = (String)application.getAttribute(id);
		System.out.print(id + ":" + status);
		response.getOutputStream().println(status);
		return null;
	}
	
	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Clock> clockList = clockService.getAllItems();
		request.setAttribute("clockList", clockList);
		return mapping.findForward("home");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");
		Clock clock = new Clock();
		clock.setId(Integer.valueOf(id));
		try{
			clockService.delete(clock);
			request.setAttribute("status", Constants.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("status", Constants.FAIL);
		}
		
		return mapping.findForward("status");
	}
	// 添加的数据到数据库，然后发消息给客户端，写当前的状态
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ClockForm clockForm = (ClockForm)form;
		Clock c = new Clock();
		c.setName(clockForm.getName());
		c.setHour(clockForm.getHour());
		String status = Constants.SUCCESS;
		
		try{
			clockService.save(c);
			
		}catch(Exception e){
			e.printStackTrace();
			status = Constants.FAIL;
		}
		// send to client
		
		
		// write to application
		ServletContext application = request.getSession().getServletContext();
		String uuid = "123456";
		application.setAttribute(uuid, status);
		response.getOutputStream().print(uuid + "=" + status);
		return null;
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ClockForm clockForm = (ClockForm)form;
		Clock c = new Clock();
		c.setId(clockForm.getId());
		c.setName(clockForm.getName());
		c.setHour(clockForm.getHour());
		
		try{
			clockService.update(c);
			request.setAttribute("status", Constants.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("status", Constants.FAIL);
		}
		
		return mapping.findForward("status");
	}
	
	public ActionForward webservice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String roomNum = (String)request.getParameter("roomNum");
		String data = (String)request.getParameter("data");
		System.out.println("clock webservice " + roomNum);
		System.out.println("clock webservice " + data);
		
		AlarmXmlParse axp = new AlarmXmlParse(data);
		List<Alarm> la = axp.parse();
		if(la == null){
			return mapping.findForward("status");
		}
		for(Alarm a : la){
			switch(AlarmXmlParse.Operation.mapString(a.operation)){
				case AlarmXmlParse.Operation.ADD:
				case AlarmXmlParse.Operation.UPDATE:
					Clock clock = clockService.getByIdLocal(a.id);
					if(clock == null){
						clock = new Clock();
					}
					mapAlarm2Clock(a,clock);
					clock.setRoomnum(roomNum);
					if(clock.getId() == 0){
						clockService.save(clock);
					}else{
						clockService.update(clock);
					}
					break;
				case AlarmXmlParse.Operation.DELETE:
					Clock clock2 = clockService.getByIdLocal(a.id);
					if( clock2 != null){
						clockService.delete(clock2);
					}
					break;
				case AlarmXmlParse.Operation.LIST:
					break;
				default:
					break;				
			}
		}
		
		return mapping.findForward("status");
	}
	
	
	public static void mapAlarm2Clock(Alarm a, Clock clock){
		clock.setId_local(a.id);
		clock.setHour(a.hour);
		clock.setMinutes(a.minutes);
		clock.setDayofweek(a.daysOfWeek.getCoded());
		clock.setAlarmtime(String.valueOf(a.time));
		clock.setAlert(a.alert);
		clock.setEnable(a.enabled?1:0);
		clock.setLabel(a.label);
		clock.setMusic(a.musicPath);
	}

}
