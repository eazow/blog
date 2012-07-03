package com.eazow.blog.dao;

import java.util.Date;
import java.util.List;

import com.eazow.blog.entity.VisitRecord;

public interface VisitRecordDAO {
	public boolean addRecord(VisitRecord visitRecord);

	// �ܷ�����
	public int getTotalPageView();

	// ���շ�����
	public int getTodayPageView(Date today);

	// ������з��ʼ�¼
	public List<VisitRecord> getAllVisitRecords();

	public List<VisitRecord> getVisitRecords(int pageNum, int pageSize);

	public int getTotalPages(int pageSize);

	public boolean deleteVisitRecord(int id);
}
