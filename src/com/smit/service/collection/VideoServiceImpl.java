package com.smit.service.collection;

import java.util.ArrayList;
import java.util.List;

import com.smit.dao.VideoDao;
import com.smit.service.webService.IToXML;
import com.smit.service.webService.VideoItem;
import com.smit.util.SmitPage;
import com.smit.vo.Video;

public class VideoServiceImpl implements VideoService {

	VideoDao videoDao;
	
	@Override
	public void save(Video video) {
		videoDao.save(video);
	}

	@Override
	public void update(Video video) {
		videoDao.update(video);
	}

	@Override
	public void delete(Video video) {
		videoDao.delete(video);
	}

	@Override
	public List<Video> findByPartId(SmitPage page, Integer partId) {
		return videoDao.findByPartId(page, partId);
	}

	@Override
	public Video getById(Integer id) {
		return videoDao.getById(id);		
	}

	@Override
	public List<Video> listAll(SmitPage page) {
		return videoDao.listAll(page);
	}

	public VideoDao getVideoDao() {
		return videoDao;
	}

	public void setVideoDao(VideoDao videoDao) {
		this.videoDao = videoDao;
	}

	@Override
	public void save(List<Video> listVideo) {
		for(Video v : listVideo){
			save(v);
		}
		
	}

	@Override
	public List<IToXML> findByPartIdXMl(SmitPage page, Integer partId) {
		List<Video> list = findByPartId(page,partId);
		List<IToXML> listXml = new ArrayList<IToXML>();
		for(Video v : list){
			VideoItem item = new VideoItem();
			item.setName(v.getTitle());
			item.setDescription(v.getDescription());
			List<String> urls = new ArrayList<String>();
			urls.add(v.getEnclosure_url());
			item.setUrls(urls);
			//add by luocheng
			List<String> pictures = new ArrayList<String>();
			pictures.add(v.getImg());
			item.setPictures(pictures);
			item.setTime(v.getFlag1());
			listXml.add(item);
		}
		return listXml;
	}

	@Override
	public List<Video> getVideos() {
		return videoDao.getVideos();
	}

	@Override
	public List<Object[]> getLatestVideos() {
		return videoDao.getLatestVideos();
	}
	
	
}
