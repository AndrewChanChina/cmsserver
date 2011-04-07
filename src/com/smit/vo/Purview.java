package com.smit.vo;

import java.io.Serializable;

public class Purview implements Serializable {

	/**
	 * @author ligm
	 * @date 2011-3-31
	 * @class »®œﬁ¿‡
	 */
	private static final long serialVersionUID = 1L;
	private String purviewName;
	private String purviewInfo;
	public String getPurviewName() {
		return purviewName;
	}
	public void setPurviewName(String purviewName) {
		this.purviewName = purviewName;
	}
	public String getPurviewInfo() {
		return purviewInfo;
	}
	public void setPurviewInfo(String purviewInfo) {
		this.purviewInfo = purviewInfo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
