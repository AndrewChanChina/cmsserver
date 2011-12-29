package com.smit.service;

import java.util.List;

import com.smit.dao.IPurviewDao;
import com.smit.util.SmitPage;
import com.smit.vo.Purview;

public class PurviewServiceImpl implements IPurviewService {

	IPurviewDao purviewDao;
	
	public void save(Purview purview) {
		purviewDao.save(purview);
	}

	public void update(Purview purview) {
		purviewDao.update(purview);
	}

	public void delete(Purview purview) {
		purviewDao.delete(purview);
	}

	public Purview getPurview(Integer id) {
		return purviewDao.getPurview(id);
	}

	public List listAll(SmitPage page) {
		return purviewDao.listAll(page);
	}

	public IPurviewDao getPurviewDao() {
		return purviewDao;
	}

	public void setPurviewDao(IPurviewDao purviewDao) {
		this.purviewDao = purviewDao;
	}

}
