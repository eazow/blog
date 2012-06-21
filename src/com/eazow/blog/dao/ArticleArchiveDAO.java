package com.eazow.blog.dao;

import java.util.List;

import com.eazow.blog.entity.Article;
import com.eazow.blog.entity.ArticleArchive;


public interface ArticleArchiveDAO
{
	public List<ArticleArchive> getArticleArchiveList();
	public ArticleArchive getArticleArchive(int id);
	public ArticleArchive getArticleArchive(int year, int month);
	//�����������¸������´浵
	public boolean updateArticleArchive(Article article);
	//���Ӹ����´浵��Ӧ��������
	public boolean addArticlesNumOfArticleArchive(int articleArchiveId);
	//���ٸ����´浵��Ӧ��������,���������Ϊ0,��ɾ�������´浵
	public boolean reduceArticlesNumOfArticleArchive(int articleArchiveId);
	public boolean addArticleArchive(ArticleArchive articleArchive);
	public boolean deleteArticleArchive(int id);
	//�������´浵
	public boolean updateArticleArchive(ArticleArchive articleArchive);
}
