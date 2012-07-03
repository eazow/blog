package com.eazow.blog.entity;

import java.util.Date;

public class Motto {
	private int id;
	private String content;
	private Date createdDate;

	public Motto(int id, String content, Date createdDate) {
		this.id = id;
		this.content = content;
		this.createdDate = createdDate;
	}

	public Motto(String content, Date createdDate) {
		this.content = content;
		this.createdDate = createdDate;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
}
