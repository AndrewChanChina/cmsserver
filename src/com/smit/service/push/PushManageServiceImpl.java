package com.smit.service.push;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.smit.dao.PushServiceDao;
import com.smit.util.SmitPage;
import com.smit.util.WebUtil;
import com.smit.vo.PushService;
import com.smit.vo.UserAccountResource;

public class PushManageServiceImpl implements IPushManageService {

	PushServiceDao pushServiceDao;
	
	/**
	 * you just need to set service name and user id in PushService object
	 * others will be create by this function
	 */
	public void save(PushService ps) {

		ps.setCreatetime(new Timestamp(new Date().getTime()));
		ps.setServiceId(generatePushServiceId());
		pushServiceDao.save(ps);
	}

	public void update(PushService ps) {
		pushServiceDao.update(ps);
	}

	public void delete(PushService ps) {
		pushServiceDao.delete(ps);
	}

	public PushService getById(Integer id) {
		return pushServiceDao.getById(id);
	}

	public List listAll(SmitPage page) {		
		return pushServiceDao.listAll(page);
	}

	public PushServiceDao getPushServiceDao() {
		return pushServiceDao;
	}

	public void setPushServiceDao(PushServiceDao pushServiceDao) {
		this.pushServiceDao = pushServiceDao;
	}	

	private String generatePushServiceId(){
		return WebUtil.randomString(32);
	}

	public void saveOrUpdate(UserAccountResource us) {
		this.pushServiceDao.saveOrUpdate(us);		
	}

	public void delete(UserAccountResource us) {
		this.pushServiceDao.delete(us);
	}
	
	public void delete(String userAccount){
		this.pushServiceDao.deleteByAccount(userAccount);
	}

	public List<UserAccountResource> listAllResource(String userName) {
		return this.pushServiceDao.listAllResource(userName);
	}

	/**
	 * 相当于做数据的同步，先删除相关的数据，然后插入相关的数据
	 */
	public void updateUserAccountAllRes(List<UserAccountResource> list) {
		UserAccountResource ua = list.get(0);
		if(ua != null)
			this.pushServiceDao.deleteByAccount(ua.getUserAccount());
		for(UserAccountResource u : list){
			pushServiceDao.saveOrUpdate(u);
		}		
	}

	public void updateUserPresence(String fromUser, boolean presence) {
		int n = fromUser.lastIndexOf("/");
		String user = fromUser.substring(0,n);
		String resource = fromUser.substring(n+1);
		pushServiceDao.updateUserPresence(user, resource, presence);
	}

	
}
