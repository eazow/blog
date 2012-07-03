package com.eazow.blog.service;

import java.util.List;

import com.eazow.blog.entity.ArticleArchive;

public interface ArticleArchiveService {
	public List<ArticleArchive> getArticleArchiveList();

	public ArticleArchive getArticleArchive(int id);

	public List<ArticleArchive> manageAllArticleArchives();

	public ArticleArchive manageArticleArchive(int id);

	public boolean updateArticleArchive(ArticleArchive articleArchive);
}
