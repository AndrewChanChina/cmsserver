package com.smit.service.collection;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.Video;

public interface VideoService {
	
	public void save(Video video);
	public void update(Video video);
	public void delete(Video video);
	
	public List<Video> findByPartId(SmitPage page, Integer partId);
	public Video getById(Integer id);
	public List<Video> listAll(SmitPage page);
	
	public void save(List<Video> listVideo);

}
