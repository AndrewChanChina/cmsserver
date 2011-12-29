package com.smit.service;

import com.smit.dao.MediaDao;
import com.smit.util.DaoException;
import com.smit.util.Page;
import com.smit.util.ServiceException;
import com.smit.vo.Media;

public class MediaServiceImpl implements MediaService {
    private MediaDao mediaDao;
    
	public void setMediaDao(MediaDao mediaDao) {
		this.mediaDao = mediaDao;
	}

	public void save(Media media) {
		try {
			mediaDao.save(media);
			
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
	}

	public void delete(int id) {
		try {
			Media media = findById(id);
			mediaDao.delete(media);
			
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
	
	
	}

	public void update(int id) {
		try {
			Media media = findById(id);
			mediaDao.update(media);
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
	
	}

	public Page findAll(int currentPage, int pageSize) {
		Page page = null;
		try {
			page = mediaDao.getPage(currentPage, pageSize);
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		return page;
	}

	public Media findById(int id) {
		Media media = null;
		try {
			media = mediaDao.getById(id);
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
		return media;
	}

}
