package com.smit.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页组件类
 * @author ligm
 * @date 2011-4-1
 *
 */
public class Page {
	private List list = new ArrayList();
	private int pageSize;//每页显示多少
	private int currentPage;//当前第几页
	private int totalRecord;//总记录数
	/**
	 * 总页数
	 */
	
	private int pageCount(){
		return (totalRecord%pageSize == 0)?(totalRecord/pageSize):(totalRecord/pageSize+1);
	}
	
	/**
	 * 下一页
	 * @return
	 */
	public int getNextPage(){
		return currentPage >= this.pageCount()? this.pageCount():(currentPage+1);
	}
	
	/**
	 * 上一页
	 * @return
	 */
	public int getPerPage(){
		return currentPage > 1?(currentPage-1):1;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	
	
	

}
