package com.smit.service.collection;

import java.util.List;

import com.smit.service.webService.IToXML;
import com.smit.util.SmitPage;
import com.smit.vo.Video;

public interface VideoService {
	
	public void save(Video video);
	public void update(Video video);
	public void delete(Video video);
	
	public List<Video> findByPartId(SmitPage page, Integer partId);
	public List<IToXML> findByPartIdXMl(SmitPage page, Integer partId);
	
	public Video getById(Integer id);
	public List<Video> listAll(SmitPage page);
	
	public void save(List<Video> listVideo);

	public List<Video> getVideos();
	public List<Object[]> getLatestVideos();
}
