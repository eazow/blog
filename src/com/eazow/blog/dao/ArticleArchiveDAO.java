package com.eazow.blog.dao;

import java.util.List;

import com.eazow.blog.entity.Article;
import com.eazow.blog.entity.ArticleArchive;


public interface ArticleArchiveDAO
{
	public List<ArticleArchive> getArticleArchiveList();
	public ArticleArchive getArticleArchive(int id);
	public ArticleArchive getArticleArchive(int year, int month);
	//根据文章年月更新文章存档
	public boolean updateArticleArchive(Article article);
	//增加该文章存档对应的文章数
	public boolean addArticlesNumOfArticleArchive(int articleArchiveId);
	//减少该文章存档对应的文章数,如果文章数为0,则删除该文章存档
	public boolean reduceArticlesNumOfArticleArchive(int articleArchiveId);
	public boolean addArticleArchive(ArticleArchive articleArchive);
	public boolean deleteArticleArchive(int id);
	//更新文章存档
	public boolean updateArticleArchive(ArticleArchive articleArchive);
}
