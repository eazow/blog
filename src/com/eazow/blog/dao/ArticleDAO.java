package com.eazow.blog.dao;

import java.util.List;

import com.eazow.blog.entity.Article;


public interface ArticleDAO
{
	public int addArticle(Article article);
	public List<Article> getAllArticles();
	public Article getArticle(int id);
	public boolean deleteArticle(int id);
	public List<Article> getArticlesOfCategory(int categoryId);
	public List<Article> getArticlesOfCategory(int categoryId, int pageNum, int pageSize);
	public void addCommentsNumOfArticle(int articleId);
	public void reduceCommentsNumOfArticle(int articleId);
	public int getArticlesNumOfCategory(int categoryId);
	public int moveArticlesToDefaultCategory(int originalCategoryId);
	public int getTotalPages(int pageSize);
	//获取某个分类的文章页数
	public int getTotalPagesOfCategory(int categoryId, int pageSize);
	public List<Article> getArticles(int pageNum, int pageSize);
	public boolean addViewCount(int articleId);
	//时间在beforeDate和afterDate之间的纪录
	public int getTotalPagesByArticleArchive(String beforeDate, String afterDate, int pageSize);
	public List<Article> getArticlesByArticleArchive(String beforeDate, String afterDate, int pageNum, int pageSize);
	public int getArticlesNumByArticleArchive(String beforeDate, String afterDate);
	public List<Article> getArticlesByArticleArchive(String beforeDate, String afterDate);
	public boolean updateArticle(Article article);
	public int getTotalArticlesNum();
	public Article getArticle(String title);
}
