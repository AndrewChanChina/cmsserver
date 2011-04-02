package com.smit.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Media implements Serializable {

	/**
	 * @author ligm
	 * @date 2011-3-11
	 * @class ý����Դ��
	 * @params tid:ý����Դ���ڵķ������ר����fileName���ļ����� ��path���ļ����·����source���ļ���Դ��sortRank�����У�createtime������ʱ��
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private MediaType mediaType;
	private String fileName;
	private String path;
	private String source;
	private Integer sortRank;
	private Timestamp createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public MediaType getMediaType() {
		return mediaType;
	}
	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getSortRank() {
		return sortRank;
	}
	public void setSortRank(Integer sortRank) {
		this.sortRank = sortRank;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
