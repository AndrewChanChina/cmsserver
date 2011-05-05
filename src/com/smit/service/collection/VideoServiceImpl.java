package com.smit.service.collection;

import java.util.List;

import com.smit.dao.VideoDao;
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
	

}
