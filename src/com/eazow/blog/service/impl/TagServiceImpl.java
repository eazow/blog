package com.eazow.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.eazow.blog.dao.ArticleDAO;
import com.eazow.blog.dao.TagArticleDAO;
import com.eazow.blog.dao.TagDAO;
import com.eazow.blog.entity.Article;
import com.eazow.blog.entity.Tag;
import com.eazow.blog.service.TagService;

public class TagServiceImpl implements TagService {
	private TagDAO tagDAO;
	private TagArticleDAO tagArticleDAO;
	private ArticleDAO articleDAO;

	private static TagService tagService;

	private TagServiceImpl(TagDAO tagDAO, TagArticleDAO tagArticleDAO,
			ArticleDAO articleDAO) {
		this.tagDAO = tagDAO;
		this.tagArticleDAO = tagArticleDAO;
		this.articleDAO = articleDAO;
	}

	public static TagService getTagServiceInstance(TagDAO tagDAO,
			TagArticleDAO tagArticleDAO, ArticleDAO articleDAO) {
		if (null == tagService)
			tagService = new TagServiceImpl(tagDAO, tagArticleDAO, articleDAO);
		return tagService;
	}

	// 如果tag存在,直接返回tagId,否则插入Tag表
	public int addTag(Tag tag) {
		Tag tempTag = this.tagDAO.getTag(tag.getName());
		if (null != tempTag) {
			return tempTag.getId();
		} else {
			return this.tagDAO.addTag(tag);
		}
	}

	public List<Tag> getAllTags() {
		return this.tagDAO.getAllTags();
	}

	public Tag getTag(int id) {
		return this.tagDAO.getTag(id);
	}

	// 管理Tags
	public List<Tag> manageAllTags() {
		List<Tag> tagsList = this.tagDAO.getAllTags();
		for (Tag tag : tagsList) {
			List<Integer> articlesIds = this.tagArticleDAO
					.getAllArticlesIds(tag.getId());
			List<Article> articlesList = new ArrayList<Article>();
			Article article = null;
			for (int articleId : articlesIds) {
				// 防止article为空
				article = this.articleDAO.getArticle(articleId);
				if (null != article)
					articlesList.add(article);
			}
			tag.setArticles(articlesList);
		}
		return tagsList;
	}

	public boolean deleteTag(int id) {
		this.tagDAO.deleteTag(id);
		this.tagArticleDAO.deleteTagArticlesByTagId(id);
		return true;
	}
}
