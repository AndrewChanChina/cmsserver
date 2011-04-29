package com.smit.dao;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.PushService;

public interface IPushServiceDao {

	public void save(PushService ps);
	public void update(PushService ps);
	public void delete(PushService ps);
	public PushService getById(Integer id);
	// for pagination
	// if page == null return all objects
	public List listAll(SmitPage page);
}
