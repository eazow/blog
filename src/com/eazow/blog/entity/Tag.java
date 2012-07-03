package com.eazow.blog.entity;

import java.util.List;

public class Tag {
	private int id;
	private String name;

	// ∏®÷˙ Ù–‘
	private List<Article> articles;

	public Tag(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Tag(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
