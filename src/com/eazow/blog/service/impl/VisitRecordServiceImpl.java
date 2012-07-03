package com.eazow.blog.service.impl;

import java.util.Date;
import java.util.List;

import com.eazow.blog.dao.VisitRecordDAO;
import com.eazow.blog.entity.VisitRecord;
import com.eazow.blog.service.VisitRecordService;

public class VisitRecordServiceImpl implements VisitRecordService {
	private static VisitRecordService visitRecordService = null;

	private VisitRecordDAO visitRecordDAO;

	private VisitRecordServiceImpl(VisitRecordDAO visitRecordDAO) {
		this.visitRecordDAO = visitRecordDAO;
	}

	public static VisitRecordService getVisitRecordService(
			VisitRecordDAO visitRecordDAO) {
		if (null == visitRecordService)
			visitRecordService = new VisitRecordServiceImpl(visitRecordDAO);
		return visitRecordService;
	}

	public boolean addVisitRecord(VisitRecord visitRecord) {
		return this.visitRecordDAO.addRecord(visitRecord);
	}

	public int getTotalPageView() {
		return this.visitRecordDAO.getTotalPageView();
	}

	public int getTodayPageView(Date today) {
		return this.visitRecordDAO.getTodayPageView(today);
	}

	public List<VisitRecord> getAllVisitRecords() {
		return this.visitRecordDAO.getAllVisitRecords();
	}

	public List<VisitRecord> getVisitRecords(int pageNum, int pageSize) {
		return this.visitRecordDAO.getVisitRecords(pageNum, pageSize);
	}

	public int getTotalPages(int pageSize) {
		return this.visitRecordDAO.getTotalPages(pageSize);
	}

	public boolean deleteVisitRecord(int id) {
		return this.visitRecordDAO.deleteVisitRecord(id);
	}
}
