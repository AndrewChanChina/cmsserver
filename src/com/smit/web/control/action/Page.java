package com.smit.web.control.action;

public class Page {

	public  int size = 1;
	private int currentPage;//当前页
	private int totalRecord;//总记录数
	private int count;//总页数
	
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	public int pageCount(){
		if(totalRecord%size == 0){
			return totalRecord/size;
		}else{
			return totalRecord/size + 1;
		}
	}
	
	public int nextPage(){
		if(currentPage >= this.pageCount()){
			return this.pageCount();
		}else{
			return currentPage+1;
		}
	}
	
	public int prePage(){
		if(currentPage>1){
			return currentPage-1;
		}else{
			return currentPage;
		}
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
