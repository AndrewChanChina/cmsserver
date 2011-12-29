package com.smit.service;

import com.smit.dao.ContentDao;
import com.smit.util.DaoException;
import com.smit.util.Page;
import com.smit.util.ServiceException;
import com.smit.vo.Content;
import com.smit.vo.Media;

public class ContentServiceImpl implements ContentService {
    private ContentDao contentDao;
    
	
	public void setContentDao(ContentDao contentDao) {
		this.contentDao = contentDao;
	}

	public void save(Content content) {
		try {
			contentDao.save(content);
			
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
	}

	public void delete(int id) {
		try {
			Content content = findById(id);
			contentDao.delete(content);
			
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
	
	
	}

	public void update(Content content) {
		try {
		
			contentDao.update(content);
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
	
	}

	public Page findAll(int currentPage, int pageSize,int pid) {
		Page page = null;
		try {
			page = contentDao.getPage(currentPage, pageSize,pid);
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		return page;
	}
	
	
	public Page findAll(int currentPage, int pageSize) {
		Page page = null;
		try {
			page = contentDao.getPage(currentPage, pageSize);
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		return page;
	}

	public Content findById(int id) {
		Content content = null;
		try {
			content = contentDao.getById(id);
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
		return content;
	}

	public void saveorupdate(Content content) {
		try {
			contentDao.saveorupdate(content);
			
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
		
	}

}
