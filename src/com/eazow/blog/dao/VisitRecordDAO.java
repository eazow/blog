package com.eazow.blog.dao;

import java.util.Date;
import java.util.List;

import com.eazow.blog.entity.VisitRecord;

public interface VisitRecordDAO {
	public boolean addRecord(VisitRecord visitRecord);

	// 总访问量
	public int getTotalPageView();

	// 今日访问量
	public int getTodayPageView(Date today);

	// 获得所有访问记录
	public List<VisitRecord> getAllVisitRecords();

	public List<VisitRecord> getVisitRecords(int pageNum, int pageSize);

	public int getTotalPages(int pageSize);

	public boolean deleteVisitRecord(int id);
}
