package com.eazow.blog.dao;

import java.util.List;

import com.eazow.blog.entity.TagArticle;


public interface TagArticleDAO
{
	public int addTagArticle(TagArticle tagArticle);
	public int deleteTagArticles(int articleId);
	public int deleteTagArticlesByTagId(int tagId);
	public List<Integer> getTagsIds(int articleId);
	public List<Integer> getArticlesIds(int tagId, int pageNum, int pageSize);
	public int getTotalPagesOfTag(int tagId, int pageSize);
	public List<Integer> getAllArticlesIds(int tagId);
}
