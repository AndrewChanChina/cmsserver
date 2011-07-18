package com.smit.service;

import java.util.List;

import com.smit.vo.AtomRecord;
import com.smit.vo.RssRecord;

public interface RssAtomService {
	public void addRssRecord(RssRecord record);
	public void addAtomRecord(AtomRecord record);
	public List<RssRecord> findRsssByFeed(String  feed_url);
	public List<AtomRecord> findAtomByFeed(String feed_url);
	public void addListRss(List<RssRecord> list);
	public void addListAtom(List<AtomRecord> list);
}
