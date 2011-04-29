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

	@Override
	public void save(Content content) {
		try {
			contentDao.save(content);
			
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void delete(int id) {
		try {
			Content content = findById(id);
			contentDao.save(content);
			
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
	
	
	}

	@Override
	public void update(Content content) {
		try {
		
			contentDao.update(content);
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
	
	}

	@Override
	public Page findAll(int currentPage, int pageSize,int pid) {
		Page page = null;
		try {
			page = contentDao.getPage(currentPage, pageSize,pid);
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		return page;
	}
	
	
	@Override
	public Page findAll(int currentPage, int pageSize) {
		Page page = null;
		try {
			page = contentDao.getPage(currentPage, pageSize);
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		return page;
	}

	@Override
	public Content findById(int id) {
		Content content = null;
		try {
			content = findById(id);
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
		return content;
	}

	@Override
	public void saveorupdate(Content content) {
		try {
			contentDao.saveorupdate(content);
			
			
		}catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
		
	}

}
