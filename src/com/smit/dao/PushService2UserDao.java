package com.smit.dao;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.PushService;
import com.smit.vo.PushService2User;
import com.smit.vo.UserAccountResource;

public interface PushService2UserDao {

	public void save(PushService2User ps);
	public void update(PushService2User ps);
	public void delete(PushService2User ps);
	public PushService2User getById(Integer id);
	// for pagination
	// if page == null return all objects
	public List<PushService2User> listAll(SmitPage page,String []names, String []values);
	
	public List<PushService2User> listRoomNum();

}
