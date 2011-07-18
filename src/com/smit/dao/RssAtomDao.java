package com.smit.dao;

import java.util.List;

import com.smit.vo.AtomRecord;
import com.smit.vo.RssRecord;

public interface RssAtomDao {
	public void addRssRecord(RssRecord record);
	public void addAtomRecord(AtomRecord record);
	public List<RssRecord> findRssByFeed(String feed_url);
	public List<AtomRecord> findAtomByFeed(String feed_url);
}
