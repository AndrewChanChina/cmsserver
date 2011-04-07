package com.smit.vo;

import java.io.Serializable;

public class SysInfo implements Serializable {

	/**
	 * @author ligm
	 * @date 2011-3-31
	 * @class ϵͳ�������
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String info_key;
	private String info_value;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getInfo_key() {
		return info_key;
	}
	public void setInfo_key(String info_key) {
		this.info_key = info_key;
	}
	public String getInfo_value() {
		return info_value;
	}
	public void setInfo_value(String info_value) {
		this.info_value = info_value;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
