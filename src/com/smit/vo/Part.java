package com.smit.vo;

import java.io.Serializable;

public class Part implements Serializable {

	/**
	 * @author ligm
	 * @date 2011-3-31
	 * @class ��Ŀ��
	 * @params topid:������ĿID,father_id:�ϼ���ĿID��typename:��Ŀ����
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String typename;
	private Integer topid;
	private Integer father_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public Integer getTopid() {
		return topid;
	}
	public void setTopid(Integer topid) {
		this.topid = topid;
	}
	public Integer getFather_id() {
		return father_id;
	}
	public void setFather_id(Integer father_id) {
		this.father_id = father_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 

}
