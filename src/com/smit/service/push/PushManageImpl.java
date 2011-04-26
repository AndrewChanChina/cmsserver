package com.smit.service.push;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.smit.dao.IPushServiceDao;
import com.smit.util.SmitPage;
import com.smit.vo.PushService;

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
	
	@SuppressWarnings("deprecation")
	private String generatePushServiceId(){
		String id = new Timestamp(new Date().getTime()).toGMTString();
		return id;
	}

	
}
