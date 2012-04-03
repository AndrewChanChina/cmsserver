package com.smit.util;

public class SmitPage {
	
	public final static String CUR_PAGE = "CUR_PAGE";
	public final static String pageNumberParameterName = "cpage";
 	private int pageIndex;
 	private int pageSize = 2;
 	private int totalCount = 0;
 	private String url;
 	
 	public SmitPage(int pageIndex) {
        this.pageIndex = pageIndex;
    }
 	public SmitPage(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
 	public SmitPage(int pageIndex, int pageSize, String url) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.url = url;
    }
	public int getPageIndex() {
		if(this.pageIndex > getPageCount())
			this.pageIndex = getPageCount();
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {		
		this.pageIndex = pageIndex;		
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
 	
	public int getPageCount() {
        if (totalCount==0)
            return 0;
        return totalCount / pageSize + (totalCount % pageSize==0 ? 0 : 1);
    }
	
	public boolean isEmpty() {
        return totalCount==0;
    }

    public boolean getHasPrevious() {
        return pageIndex > 1;
    }

    public boolean getHasNext() {
        return pageIndex < getPageCount();
    }

}
