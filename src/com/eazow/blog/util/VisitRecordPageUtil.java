package com.eazow.blog.util;

public class VisitRecordPageUtil
{
	private int currentPage;
	private int totalPages;
	private int pageSize = 10;
	
	public int getCurrentPage()
	{
		return currentPage;
	}
	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}
	public int getTotalPages()
	{
		return totalPages;
	}
	public void setTotalPages(int totalPages)
	{
		this.totalPages = totalPages;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
}
