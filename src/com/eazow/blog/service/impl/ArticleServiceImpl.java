package com.eazow.blog.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.eazow.blog.dao.ArticleArchiveDAO;
import com.eazow.blog.dao.ArticleDAO;
import com.eazow.blog.dao.CategoryDAO;
import com.eazow.blog.dao.CommentDAO;
import com.eazow.blog.dao.TagArticleDAO;
import com.eazow.blog.dao.TagDAO;
import com.eazow.blog.entity.Article;
import com.eazow.blog.entity.ArticleArchive;
import com.eazow.blog.entity.Category;
import com.eazow.blog.entity.Comment;
import com.eazow.blog.entity.Tag;
import com.eazow.blog.entity.TagArticle;
import com.eazow.blog.service.ArticleService;
import com.eazow.blog.util.DateUtil;


public class ArticleServiceImpl implements ArticleService
{
	private ArticleDAO articleDAO;
	private CategoryDAO categoryDAO;
	private CommentDAO commentDAO;
	private ArticleArchiveDAO articleArchiveDAO;
	private TagDAO tagDAO;
	private TagArticleDAO tagArticleDAO;
	
	private static ArticleService articleService = null;
	
	private ArticleServiceImpl(ArticleDAO articleDAO, CategoryDAO categoryDAO, 
			CommentDAO commentDAO, ArticleArchiveDAO articleArchiveDAO, TagDAO tagDAO, TagArticleDAO tagArticleDAO)
	{
		this.articleDAO = articleDAO;
		this.categoryDAO = categoryDAO;
		this.commentDAO = commentDAO;
		this.articleArchiveDAO = articleArchiveDAO;
		this.tagDAO = tagDAO;
		this.tagArticleDAO = tagArticleDAO;
	}
	
	public static ArticleService getArticleServiceInstance(ArticleDAO articleDAO, CategoryDAO categoryDAO, 
			CommentDAO commentDAO, ArticleArchiveDAO articleArchiveDAO, TagDAO tagDAO, TagArticleDAO tagArticleDAO)
	{
		if(null == articleService)
			articleService = new ArticleServiceImpl(articleDAO, categoryDAO, commentDAO, articleArchiveDAO, tagDAO, tagArticleDAO);
		return articleService;
	}
	
	//具体方法
	public List<Article> getAllArticles()
	{
		List<Article> articlesList = articleDAO.getAllArticles();
		for(Article article: articlesList)
		{
			//Tags
			List<Integer> tagsIds = this.tagArticleDAO.getTagsIds(article.getId());
			List<Tag> tagsList = new ArrayList<Tag>();
			for(int tagId: tagsIds)
			{
				Tag tag = this.tagDAO.getTag(tagId);
				tagsList.add(tag);
			}
			article.setTags(tagsList);
			//Category
			int categoryId = article.getCategoryId();
			Category category = categoryDAO.getCategory(categoryId);
			article.setCategory(category);
		}
		return articlesList;
	}
	public Article getArticle(int id)
	{
		Article article = articleDAO.getArticle(id);
		Category category = categoryDAO.getCategory(article.getCategoryId());
		article.setCategory(category);
		List<Comment> comments = commentDAO.getCommentsOfArticle(id);
		article.setComments(comments);
		//Tags
		List<Integer> tagsIds = this.tagArticleDAO.getTagsIds(article.getId());
		List<Tag> tagsList = new ArrayList<Tag>();
		for(int tagId: tagsIds)
		{
			Tag tag = this.tagDAO.getTag(tagId);
			tagsList.add(tag);
		}
		article.setTags(tagsList);
		return article;
	}
	public List<Article> getArticlesOfCategory(int categoryId)
	{
		List<Article> articlesList = articleDAO.getArticlesOfCategory(categoryId);
		Category category = categoryDAO.getCategory(categoryId);
		for(Article article: articlesList)
		{
			article.setCategory(category);
			//Tags
			List<Integer> tagsIds = this.tagArticleDAO.getTagsIds(article.getId());
			List<Tag> tagsList = new ArrayList<Tag>();
			for(int tagId: tagsIds)
			{
				Tag tag = this.tagDAO.getTag(tagId);
				tagsList.add(tag);
			}
			article.setTags(tagsList);
		}
		return articlesList;
	}
	public List<Article> getArticlesOfCategory(int categoryId, int pageNum,
			int pageSize)
	{
		List<Article> articlesList = articleDAO.getArticlesOfCategory(categoryId, pageNum, pageSize);
		Category category = categoryDAO.getCategory(categoryId);
		for(Article article: articlesList)
		{
			article.setCategory(category);
			//Tags
			List<Integer> tagsIds = this.tagArticleDAO.getTagsIds(article.getId());
			List<Tag> tagsList = new ArrayList<Tag>();
			for(int tagId: tagsIds)
			{
				Tag tag = this.tagDAO.getTag(tagId);
				tagsList.add(tag);
			}
			article.setTags(tagsList);
		}
		return articlesList;
	}
	
	
	public boolean addArticle(Article article)
	{
		Category category = categoryDAO.getCategory(article.getCategoryId());
		//categoryId错误
		if(null == category)
			return false;
		//增加分类所含文章数量
		categoryDAO.addArticlesNumOfCategory(category.getId());
		//增加文章存档纪录
		this.articleArchiveDAO.updateArticleArchive(article);
		//增加文章
		int articleId = articleDAO.addArticle(article);
		//增加对应Tags
		List<Tag> tagsList = article.getTags();
		for(Tag tag: tagsList)
		{
			Tag tagTemp = tagDAO.getTag(tag.getName());
			int tagId = 0;
			if(null == tagTemp) 
			{
				tagId = tagDAO.addTag(tag);
			}
			//Tag已存在
			else
			{
				tagId = tagTemp.getId();
			}
			TagArticle tagArticle = new TagArticle(tagId, articleId);
			this.tagArticleDAO.addTagArticle(tagArticle);
		}
		return true;
	}
	
	public boolean deleteArticle(int id)
	{
		Article article = articleDAO.getArticle(id);
		//id在article表中不存在
		if(null == article)
			return false;
		Category category = categoryDAO.getCategory(article.getCategoryId());
		if(null == category)
			return false;
		articleDAO.deleteArticle(id);
		//修改文章存档对应文章数
		Date postDate = article.getPostDate();
		int year = DateUtil.getYear(postDate);
		int month = DateUtil.getMonth(postDate);
		ArticleArchive articleArchive = this.articleArchiveDAO.getArticleArchive(year, month);
		//减少文章存档对应的文章数
		this.articleArchiveDAO.reduceArticlesNumOfArticleArchive(articleArchive.getId());
		//减少分类所对应的文章数
		categoryDAO.reduceArticlesNumOfCategory(category.getId());
		//删除文章和标签的对应关系
		this.tagArticleDAO.deleteTagArticles(id);
		return true;
	}
	
	public int getTotalPages(int pageSize)
	{
		return articleDAO.getTotalPages(pageSize);
	}
	
	public int getTotalPagesOfCategory(int categoryId, int pageSize)
	{
		return articleDAO.getTotalPagesOfCategory(categoryId, pageSize);
	}
	
	public List<Article> getArticles(int pageNum, int pageSize)
	{
		List<Article> articlesList = articleDAO.getArticles(pageNum, pageSize);
		for(Article article: articlesList)
		{
			List<Integer> tagsIds = this.tagArticleDAO.getTagsIds(article.getId());
			List<Tag> tagsList = new ArrayList<Tag>();
			for(int tagId: tagsIds)
			{
				Tag tag = this.tagDAO.getTag(tagId);
				tagsList.add(tag);
			}
			article.setTags(tagsList);
			int categoryId = article.getCategoryId();
			Category category = categoryDAO.getCategory(categoryId);
			article.setCategory(category);
		}
		return articlesList;
	}
	
	public boolean addViewCount(int articleId)
	{
		return this.articleDAO.addViewCount(articleId);
	}
	
	public int getTotalPagesByArticleArchive(int articleArchiveId, int pageSize)
	{
		ArticleArchive articleArchive = articleArchiveDAO.getArticleArchive(articleArchiveId);
		int year = articleArchive.getYear();
		int month = articleArchive.getMonth();
		//获取这个月最早一天和下个月最早一天
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);
		Date beforeDate = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		String beforeDateStr = simpleDateFormat.format(beforeDate);
		calendar.set(year, month, 1);
		Date afterDate = calendar.getTime();
		String afterDateStr = simpleDateFormat.format(afterDate);
		return this.articleDAO.getTotalPagesByArticleArchive(beforeDateStr, afterDateStr, pageSize);
	}
	
	public List<Article> getArticlesByArticleArchive(int articleArchiveId,
			int pageNum, int pageSize)
	{
		ArticleArchive articleArchive = articleArchiveDAO.getArticleArchive(articleArchiveId);
		int year = articleArchive.getYear();
		int month = articleArchive.getMonth();
		//获取这个月最早一天和下个月最早一天
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);
		Date beforeDate = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		String beforeDateStr = simpleDateFormat.format(beforeDate);
		calendar.set(year, month, 1);
		Date afterDate = calendar.getTime();
		String afterDateStr = simpleDateFormat.format(afterDate);
		List<Article> articlesList = articleDAO.getArticlesByArticleArchive(beforeDateStr, afterDateStr, pageNum, pageSize);
		for(Article article: articlesList)
		{
			//Tags
			List<Integer> tagsIds = this.tagArticleDAO.getTagsIds(article.getId());
			List<Tag> tagsList = new ArrayList<Tag>();
			for(int tagId: tagsIds)
			{
				Tag tag = this.tagDAO.getTag(tagId);
				tagsList.add(tag);
			}
			article.setTags(tagsList);
			//Category
			int categoryId = article.getCategoryId();
			Category category = categoryDAO.getCategory(categoryId);
			article.setCategory(category);
		}
		return articlesList;
	}
	
	public boolean updateArticle(Article article)
	{
		Article articleOriginal = articleDAO.getArticle(article.getId());
		
		//文章存档是否变动
		int yearOriginal = DateUtil.getYear(articleOriginal.getPostDate());
		int monthOriginal = DateUtil.getMonth(articleOriginal.getPostDate());
		int year = DateUtil.getYear(article.getPostDate());
		int month = DateUtil.getMonth(article.getPostDate());
		//如果文章对应文章存档变动
		if(yearOriginal!=year || monthOriginal!=month)
		{
			ArticleArchive articleArchive = this.articleArchiveDAO.getArticleArchive(yearOriginal, monthOriginal);
			this.articleArchiveDAO.reduceArticlesNumOfArticleArchive(articleArchive.getId());
			this.articleArchiveDAO.updateArticleArchive(article);
		}
		
		//删掉最初Tags
		this.tagArticleDAO.deleteTagArticles(article.getId());
		
		//增加对应Tags
		List<Tag> tagsList = article.getTags();
		for(Tag tag: tagsList)
		{
			Tag tagTemp = tagDAO.getTag(tag.getName());
			int tagId = 0;
			if(null == tagTemp) 
			{
				tagId = tagDAO.addTag(tag);
			}
			//Tag已存在
			else
			{
				tagId = tagTemp.getId();
			}
			TagArticle tagArticle = new TagArticle(tagId, article.getId());
			this.tagArticleDAO.addTagArticle(tagArticle);
		}
		
		//categoryId没有改变
		if(articleOriginal.getCategoryId() == article.getCategoryId())
		{
			return this.articleDAO.updateArticle(article);
		}
		//categoryId发生改变
		else
		{
			this.categoryDAO.reduceArticlesNumOfCategory(articleOriginal.getCategoryId());
			this.categoryDAO.addArticlesNumOfCategory(article.getCategoryId());
			return this.articleDAO.updateArticle(article);
		}
		
	}
	
	public int getTotalArticlesNum()
	{
		return this.articleDAO.getTotalArticlesNum();
	}
	
	public Article getArticle(String title)
	{
		return this.articleDAO.getArticle(title);
	}
	
	public List<Article> getArticlesOfTag(int tagId, int pageNum, int pageSize)
	{
		List<Integer> articlesIds = this.tagArticleDAO.getArticlesIds(tagId, pageNum, pageSize);
		List<Article> articlesList = new ArrayList<Article>();
		Article article = null;
		for(int articleId: articlesIds)
		{
			article = this.articleDAO.getArticle(articleId);
			//Tags
			List<Integer> tagsIds = this.tagArticleDAO.getTagsIds(article.getId());
			List<Tag> tagsList = new ArrayList<Tag>();
			for(int tagIdTemp: tagsIds)
			{
				Tag tag = this.tagDAO.getTag(tagIdTemp);
				tagsList.add(tag);
			}
			article.setTags(tagsList);
			articlesList.add(article);
			//Category
			int categoryId = article.getCategoryId();
			Category category = categoryDAO.getCategory(categoryId);
			article.setCategory(category);
		}
		return articlesList;
	}
	
	public int getTotalPagesOfTag(int tagId, int pageSize)
	{
		return this.tagArticleDAO.getTotalPagesOfTag(tagId, pageSize);
	}
	
	public List<Article> manageArticlesOfTag(int tagId)
	{
		List<Integer> articlesIds = this.tagArticleDAO.getAllArticlesIds(tagId);
		List<Article> articlesList = new ArrayList<Article>();
		Article article = null;
		for(int articleId: articlesIds)
		{
			article = this.articleDAO.getArticle(articleId);
			//Tags
			List<Integer> tagsIds = this.tagArticleDAO.getTagsIds(article.getId());
			List<Tag> tagsList = new ArrayList<Tag>();
			for(int tagIdTemp: tagsIds)
			{
				Tag tag = this.tagDAO.getTag(tagIdTemp);
				tagsList.add(tag);
			}
			article.setTags(tagsList);
			articlesList.add(article);
			//Category
			int categoryId = article.getCategoryId();
			Category category = categoryDAO.getCategory(categoryId);
			article.setCategory(category);
		}
		return articlesList;
	}
}
