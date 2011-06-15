package com.smit.service;

import java.util.List;

import com.smit.dao.PushContentDao;
import com.smit.vo.PushContent;

public class PushServiceImpl implements PushService{
	
	private PushContentDao pushDao;
	
	public PushContentDao getPushDao() {
		return pushDao;
	}

	public void setPushDao(PushContentDao pushDao) {
		this.pushDao = pushDao;
	}

	public  boolean insertContent(PushContent content) {
		if(pushDao.insertContent(content)){
			return true;
		}
		return false;
	}
	
	public List<PushContent> getContent(int start,int num){
		return pushDao.queryContent(start,num);
	}

	@Override
	public List<PushContent> getContent() {
		
		return pushDao.queryContent();
	}

	@Override
	public void deleteContent(PushContent content) {
		pushDao.deleteContent(content);
	}

	@Override
	public PushContent getById(int id) {
		PushContent pc = pushDao.getById(id);
		return pc;
	}
	
}
