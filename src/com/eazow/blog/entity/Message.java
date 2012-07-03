package com.eazow.blog.entity;

import java.util.Date;

import com.eazow.blog.util.DateUtil;

public class Message {
	private int id;
	private String nickname;
	private String content;
	private Date date;// 留言日期

	public Message(String nickname, String content, Date date) {
		this.nickname = nickname;
		this.content = content;
		this.date = date;
	}

	public Message(int id, String nickname, String content, Date date) {
		this.id = id;
		this.nickname = nickname;
		this.content = content;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	// 日期显示
	public String getDateDisplay() {
		return DateUtil.parseDateToString(date);
	}
}
