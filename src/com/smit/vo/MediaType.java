package com.smit.vo;

import java.io.Serializable;

public class MediaType implements Serializable {

	/**
	 * @author ligm
	 * @date 2011-3-31
	 * @class ý����Դ������
	 * @params typename:ý����Դ���� ,topid:ý����Դ����ID��father_id:ý����Դ�ϼ�ID��isspecial:�Ƿ���ר��
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String typeName;
	private Integer topid;
	private Integer father_id;
	private Integer isspecial;//0:��ר����1:����ר��
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	public Integer getIsspecial() {
		return isspecial;
	}
	public void setIsspecial(Integer isspecial) {
		this.isspecial = isspecial;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
