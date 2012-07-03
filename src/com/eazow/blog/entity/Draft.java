package com.eazow.blog.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Draft {
	// 与表对应属性
	private int id;
	private String title;
	private String content;
	private Date postDate;
	private int categoryId;
	private String tags;

	// 不与表对应属性,方便处理
	private Category category;

	// 构造Article对象,避免有的属性未初始化
	public Draft(String title, String content, Date postDate, int categoryId,
			String tags) {
		this.title = title;
		this.content = content;
		this.postDate = postDate;
		this.categoryId = categoryId;
		this.tags = tags;
	}

	public Draft(int id, String title, String content, Date postDate,
			int categoryId, String tags) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.postDate = postDate;
		this.categoryId = categoryId;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	// 不与表对应属性方法
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPostDateDisplay() {
		// 24小时制
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(postDate);
	}
}
