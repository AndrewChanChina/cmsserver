package com.smit.service.push;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.PushService;
import com.smit.vo.UserAccountResource;

public interface IPushManageService {

	public void save(PushService ps);
	public void update(PushService ps);
	public void delete(PushService ps);
	public PushService getById(Integer id);
	// for pagination
	// if page == null return all objects
	public List listAll(SmitPage page);
	
	public void saveOrUpdate(UserAccountResource us);
	public void updateUserAccountAllRes(List<UserAccountResource> list);
	public void delete(UserAccountResource us);
	public List<UserAccountResource> listAllResource();
}
