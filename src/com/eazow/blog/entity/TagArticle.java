package com.eazow.blog.entity;

public class TagArticle {
	private int tagId;
	private int articleId;

	public TagArticle(int tagId, int articleId) {
		this.tagId = tagId;
		this.articleId = articleId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
}
