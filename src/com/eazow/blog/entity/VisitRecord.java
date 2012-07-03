package com.eazow.blog.entity;

import java.util.Date;

public class VisitRecord {
	private int id;
	private String sourceIP;
	private Date visitDate;

	public VisitRecord(String sourceIp, Date visitDate) {
		this.sourceIP = sourceIp;
		this.visitDate = visitDate;
	}

	public VisitRecord(int id, String sourceIp, Date visitDate) {
		this.id = id;
		this.sourceIP = sourceIp;
		this.visitDate = visitDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSourceIP() {
		return sourceIP;
	}

	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
}
