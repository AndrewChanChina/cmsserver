package com.smit.dao;

import java.util.List;

import com.smit.vo.PushContent;

public interface PushContentDao {
	public boolean insertContent(PushContent content);
	public List<PushContent> queryContent(int start,int num,String username);
	public List<PushContent> queryByName(String username);
	public List<PushContent> queryContent(String username);
	
	public void deleteContent(PushContent content);
	public PushContent getById(int id);
}
