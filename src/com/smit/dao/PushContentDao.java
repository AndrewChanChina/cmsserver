package com.smit.dao;

import java.util.List;

import com.smit.vo.PushContent;

public interface PushContentDao {
	public boolean insertContent(PushContent content);
	public List<PushContent> queryContent(int start,int num);
	public List<PushContent> queryByName(String username);
	public List<PushContent> queryContent();
	
	public void deleteContent(PushContent content);
	public PushContent getById(int id);
}
