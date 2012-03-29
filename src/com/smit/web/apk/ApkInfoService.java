package com.smit.web.apk;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.apk.ApkInfo;

public interface ApkInfoService {
	
	public void save(ApkInfo apkInfo);
	public void update(ApkInfo apkInfo);
	public void delete(ApkInfo apkInfo);
	
	public List<ApkInfo> findByPartId(SmitPage page, Integer partId);
	public ApkInfo getById(Integer id);
	public List<ApkInfo> listAll(SmitPage page);
	
	public List<ApkInfo> getAllItems();
	
	public ApkInfo findByPackageName(String packageName);
	
	public List<ApkInfo> findByNamedParam(final SmitPage page, final String[] names,
			final String[] values);
	
	
	
}
