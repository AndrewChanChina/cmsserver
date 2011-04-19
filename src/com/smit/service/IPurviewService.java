package com.smit.service;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.Purview;

public interface IPurviewService {

	public void save(Purview purview);
	public void update(Purview purview);
	public void delete(Purview purview);
	public Purview getPurview(Integer id);
	// for pagination
	// if page == null return all purviews
	public List listAll(SmitPage page);
}
