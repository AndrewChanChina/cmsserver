package com.smit.service;

import java.util.List;

import com.smit.dao.RssAtomDao;
import com.smit.vo.AtomRecord;
import com.smit.vo.RssRecord;

public class RssAtomServiceImpl implements RssAtomService {

	private RssAtomDao rmDao;
	@Override
	public void addRssRecord(RssRecord record) {
		rmDao.addRssRecord(record);
	}

	@Override
	public void addAtomRecord(AtomRecord record) {
		rmDao.addAtomRecord(record);
	}

	@Override
	public List<RssRecord> findRsssByFeed(String feed_url) {
		return rmDao.findRssByFeed(feed_url);
	}

	@Override
	public List<AtomRecord> findAtomByFeed(String feed_url) {
		return rmDao.findAtomByFeed(feed_url);
	}

	public void addListRss(List<RssRecord> list){
		for(RssRecord rss: list){
			addRssRecord(rss);
		}
	}
	
	public void addListAtom(List<AtomRecord> list){
		for(AtomRecord atom:list){
			addAtomRecord(atom);
		}
	}
	public RssAtomDao getRmDao() {
		return rmDao;
	}

	public void setRmDao(RssAtomDao rmDao) {
		this.rmDao = rmDao;
	}

	
}
