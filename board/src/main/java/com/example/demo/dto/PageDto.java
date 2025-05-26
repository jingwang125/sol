package com.example.demo.dto;

public class PageDto {
	private int pageNum;
	private int pageSize;
	private int totalCount;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public PageDto(int pageNum, int pageSize, int totalCount) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		
		// 전체 페이지 수 계산
		this.totalPages = (int)Math.ceil((double)totalCount / pageSize);
		
		//시작페이지, 끝페이지 계산
		this.startPage = ((pageNum - 1)/10)*10 + 1;
		this.endPage = Math.min(startPage + 9, totalPages);
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
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
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	
}
