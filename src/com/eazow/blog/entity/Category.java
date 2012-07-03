package com.eazow.blog.entity;

import java.util.List;

public class Category {
	private int id;
	private String name;
	private int articlesNum;

	// 辅助属性,不与数据库对应
	// 通过访问article表获得,与articlesNum对比,检查数据正确性
	private int realArticlesNum;
	private List<Article> articles;

	public Category(String name, int articlesNum) {
		this.name = name;
		this.articlesNum = articlesNum;
	}

	public Category(int id, String name, int articlesNum) {
		this.id = id;
		this.name = name;
		this.articlesNum = articlesNum;
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

	public int getArticlesNum() {
		return articlesNum;
	}

	public void setArticlesNum(int articlesNum) {
		this.articlesNum = articlesNum;
	}

	public int getRealArticlesNum() {
		return realArticlesNum;
	}

	public void setRealArticlesNum(int realArticlesNum) {
		this.realArticlesNum = realArticlesNum;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
