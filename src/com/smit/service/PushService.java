package com.smit.service;

import java.util.List;

import com.smit.vo.PushContent;

public interface PushService {
	public boolean insertContent(PushContent content);
	public List<PushContent> getContent(int start,int num,String name);
	public List<PushContent> getContent(String username);
	public void deleteContent(PushContent content);
	public PushContent getById(int id);
}
