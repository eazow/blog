package com.eazow.blog.service;

import java.util.Date;
import java.util.List;

import com.eazow.blog.entity.VisitRecord;

public interface VisitRecordService {
	public boolean addVisitRecord(VisitRecord visitRecord);

	public int getTotalPageView();

	public int getTodayPageView(Date today);

	public List<VisitRecord> getAllVisitRecords();

	public List<VisitRecord> getVisitRecords(int pageNum, int pageSize);

	public int getTotalPages(int pageSize);

	public boolean deleteVisitRecord(int id);
}
