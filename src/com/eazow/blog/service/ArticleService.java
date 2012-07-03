package com.eazow.blog.service;

import java.util.List;

import com.eazow.blog.entity.Article;

public interface ArticleService {
	public List<Article> getAllArticles();

	public Article getArticle(int id);

	public Article getArticle(String title);

	public List<Article> getArticlesOfCategory(int categoryId);

	public List<Article> getArticlesOfCategory(int categoryId, int pageNum,
			int pageSize);

	public boolean addArticle(Article article);

	public boolean deleteArticle(int id);

	public int getTotalPages(int pageSize);

	public int getTotalPagesOfCategory(int categoryId, int pageSize);

	public List<Article> getArticles(int pageNum, int pageSize);

	public boolean addViewCount(int articleId);

	public int getTotalPagesByArticleArchive(int articleArchiveId, int pageSize);

	public List<Article> getArticlesByArticleArchive(int articleArchiveId,
			int pageNum, int pageSize);

	public boolean updateArticle(Article article);

	public int getTotalArticlesNum();

	public int getTotalPagesOfTag(int tagId, int pageSize);

	public List<Article> getArticlesOfTag(int tagId, int pageNum, int pageSize);

	public List<Article> manageArticlesOfTag(int tagId);
}
