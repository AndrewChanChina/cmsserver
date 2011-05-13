package com.smit.service.push;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.smit.dao.IPushServiceDao;
import com.smit.util.SmitPage;
import com.smit.util.WebUtil;
import com.smit.vo.PushService;
import com.smit.vo.UserAccountResource;

public class PushManageImpl implements IPushManage {

	IPushServiceDao pushServiceDao;
	
	/**
	 * you just need to set service name and user id in PushService object
	 * others will be create by this function
	 */
	@Override
	public void save(PushService ps) {

		ps.setCreatetime(new Timestamp(new Date().getTime()));
		ps.setServiceId(generatePushServiceId());
		pushServiceDao.save(ps);
	}

	@Override
	public void update(PushService ps) {
		pushServiceDao.update(ps);
	}

	@Override
	public void delete(PushService ps) {
		pushServiceDao.delete(ps);
	}

	@Override
	public PushService getById(Integer id) {
		return pushServiceDao.getById(id);
	}

	@Override
	public List listAll(SmitPage page) {		
		return pushServiceDao.listAll(page);
	}

	public IPushServiceDao getPushServiceDao() {
		return pushServiceDao;
	}

	public void setPushServiceDao(IPushServiceDao pushServiceDao) {
		this.pushServiceDao = pushServiceDao;
	}	

	private String generatePushServiceId(){
		return WebUtil.randomString(32);
	}

	@Override
	public void saveOrUpdate(UserAccountResource us) {
		this.pushServiceDao.saveOrUpdate(us);		
	}

	@Override
	public void delete(UserAccountResource us) {
		this.pushServiceDao.delete(us);
	}

	@Override
	public List<UserAccountResource> listAllResource() {
		return this.pushServiceDao.listAllResource();
	}

	/**
	 * 相当于做数据的同步，先删除相关的数据，然后插入相关的数据
	 */
	@Override
	public void updateUserAccountAllRes(List<UserAccountResource> list) {
		UserAccountResource ua = list.get(0);
		if(ua != null)
			this.pushServiceDao.deleteByAccount(ua.getUserAccount());
		for(UserAccountResource u : list){
			pushServiceDao.saveOrUpdate(u);
		}		
	}

	
}
