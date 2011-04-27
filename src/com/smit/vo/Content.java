package com.smit.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Content implements Serializable {

	/**
	 * @author ligm
	 * @date 2011-3-31
	 * @class ������
	 * @params   tid:�������ڵ���ĿID,title:���ݱ���,excerpt:����ժҪ
	 *           tags:���ݱ�ǩ,content:����,author_id:���ݵ�����ID,putter:�����Ƿ��ö�;onclickcount:���ݷ�����
	 *           source:������Դ,langtype:������������,sortrank:��������,prime:�����Ƿ�Ӿ���,cteatetime:���ݴ���ʱ��
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String shortTitle;
	private Integer isCheck;
	private Part part;
	private String excerpt;
	private String typeName;
	private String tags;
	private String source;
	private Integer author_id;
	private String content;
	private Integer putter;//0���ǣ�1����
	private Integer onclickCount;
	private Integer langType;//0:����;1:Ӣ��
	private Integer prime;//0���ǣ�1����
	private Integer sortRank;
	private Integer createtime;
	private String subImg;
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
	public String getTitle() {
		return title;
	}
	
	public Integer getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	public String getExcerpt() {
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(Integer author_id) {
		this.author_id = author_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPutter() {
		return putter;
	}
	public void setPutter(Integer putter) {
		this.putter = putter;
	}
	public Integer getOnclickCount() {
		return onclickCount;
	}
	public void setOnclickCount(Integer onclickCount) {
		this.onclickCount = onclickCount;
	}
	public Integer getLangType() {
		return langType;
	}
	public void setLangType(Integer langType) {
		this.langType = langType;
	}
	public Integer getPrime() {
		return prime;
	}
	public void setPrime(Integer prime) {
		this.prime = prime;
	}
	public Integer getSortRank() {
		return sortRank;
	}
	public void setSortRank(Integer sortRank) {
		this.sortRank = sortRank;
	}

	
	public Integer getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Integer createtime) {
		this.createtime = createtime;
	}
	public String getSubImg() {
		return subImg;
	}
	public void setSubImg(String subImg) {
		this.subImg = subImg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
 
}
