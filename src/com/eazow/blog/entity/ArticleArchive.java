package com.eazow.blog.entity;

import java.util.List;

public class ArticleArchive {
	private int id;
	private int year;
	private int month;
	private int articlesNum;

	// ��������,�������ݿ��Ӧ
	// ͨ������article����,��articlesNum�Ա�,���������ȷ��
	private int realArticlesNum;
	private List<Article> articles;

	@Override
	public String toString() {
		return year + "��" + month + "��";
	}

	public ArticleArchive(int year, int month, int articlesNum) {
		this.year = year;
		this.month = month;
		this.articlesNum = articlesNum;
	}

	public ArticleArchive(int id, int year, int month, int articlesNum) {
		this.id = id;
		this.year = year;
		this.month = month;
		this.articlesNum = articlesNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
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
