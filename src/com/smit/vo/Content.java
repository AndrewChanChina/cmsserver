package com.smit.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Content implements Serializable {

	/**
	 * @author ligm
	 * @date 2011-3-31
	 * @class 内容类
	 * @params   tid:内容所在的栏目ID,title:内容标题,excerpt:内容摘要
	 *           tags:内容标签,content:内容,author_id:内容的作者ID,putter:内容是否置顶;onclickcount:内容访问量
	 *           source:内容来源,langtype:内容语言类型,sortrank:内容排列,prime:内容是否加精华,cteatetime:内容创建时间
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private Part part;
	private String excerpt;
	private String tags;
	private String source;
	private Integer author_id;
	private String content;
	private Integer putter;//0：是，1：否
	private Integer onclickCount;
	private Integer langType;//0:中文;1:英文
	private Integer prime;//0：是，1：否
	private Integer sortRank;
	private Timestamp createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
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
