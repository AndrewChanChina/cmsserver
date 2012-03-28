package com.smit.web.apk;

import java.util.List;

import com.smit.dao.clock.ClockDao;
import com.smit.util.SmitPage;
import com.smit.vo.apk.ApkInfo;


public class ApkInfoServiceImpl implements ApkInfoService {
	
	private ApkInfoDao apkInfoDao;
	

	public void setApkInfoDao(ApkInfoDao apkInfoDao) {
		this.apkInfoDao = apkInfoDao;
	}

	public void save(ApkInfo apkInfo) {
		apkInfoDao.save(apkInfo);
	}

	public void update(ApkInfo apkInfo) {
		apkInfoDao.update(apkInfo);

	}

	public void delete(ApkInfo apkInfo) {
		apkInfoDao.delete(apkInfo);
	}

	public List<ApkInfo> findByPartId(SmitPage page, Integer partId) {
		return apkInfoDao.findByPartId(page, partId);
	}

	public ApkInfo getById(Integer id) {
		return apkInfoDao.getById(id);
	}

	public List<ApkInfo> listAll(SmitPage page) {
		return apkInfoDao.listAll(page);
	}

	public List<ApkInfo> getAllItems() {
		return apkInfoDao.getAllItems();
	}

	public ApkInfo findByPackageName(String packageName) {
		return apkInfoDao.findByPackageName(packageName);
	}
	
}
