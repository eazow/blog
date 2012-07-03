package com.eazow.blog.service.impl;

import java.util.List;

import com.eazow.blog.dao.ArticleArchiveDAO;
import com.eazow.blog.dao.ArticleDAO;
import com.eazow.blog.entity.Article;
import com.eazow.blog.entity.ArticleArchive;
import com.eazow.blog.service.ArticleArchiveService;
import com.eazow.blog.util.DateUtil;

public class ArticleArchiveServiceImpl implements ArticleArchiveService {
	private ArticleArchiveDAO articleArchiveDAO;
	private ArticleDAO articleDAO;

	private static ArticleArchiveService articleArchiveService = null;

	public static ArticleArchiveService getArticleArchiveServiceInstance(
			ArticleArchiveDAO articleArchiveDAO, ArticleDAO articleDAO) {
		if (null == articleArchiveService)
			articleArchiveService = new ArticleArchiveServiceImpl(
					articleArchiveDAO, articleDAO);
		return articleArchiveService;
	}

	private ArticleArchiveServiceImpl(ArticleArchiveDAO articleArchiveDAO,
			ArticleDAO articleDAO) {
		this.articleArchiveDAO = articleArchiveDAO;
		this.articleDAO = articleDAO;
	}

	public List<ArticleArchive> getArticleArchiveList() {
		List<ArticleArchive> articleArchivesList = this.articleArchiveDAO
				.getArticleArchiveList();
		// for(ArticleArchive articleArchive: articleArchivesList)
		// {
		// int year = articleArchive.getYear();
		// int month = articleArchive.getMonth();
		// //获取这个月最早一天和下个月最早一天
		// Calendar calendar = Calendar.getInstance();
		// calendar.set(year, month-1, 1);
		// Date beforeDate = calendar.getTime();
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		// String beforeDateStr = simpleDateFormat.format(beforeDate);
		// calendar.set(year, month, 1);
		// Date afterDate = calendar.getTime();
		// String afterDateStr = simpleDateFormat.format(afterDate);
		// int realArticlesNumByArticleArchive =
		// this.articleDAO.getArticlesNumByArticleArchive(beforeDateStr,
		// afterDateStr);
		// articleArchive.setRealArticlesNum(realArticlesNumByArticleArchive);
		// }
		return articleArchivesList;
	}

	public ArticleArchive getArticleArchive(int id) {
		return this.articleArchiveDAO.getArticleArchive(id);
	}

	// 获得ArticleArchive同时获得对应文章
	public ArticleArchive manageArticleArchive(int id) {
		ArticleArchive articleArchive = this.articleArchiveDAO
				.getArticleArchive(id);
		int year = articleArchive.getYear();
		int month = articleArchive.getMonth();
		String beforeDate = DateUtil.getFirstDayOfMonth(year, month);
		String afterDate = DateUtil.getFirstDayOfMonth(year, month + 1);
		List<Article> articlesList = this.articleDAO
				.getArticlesByArticleArchive(beforeDate, afterDate);
		articleArchive.setArticles(articlesList);
		return articleArchive;
	}

	public List<ArticleArchive> manageAllArticleArchives() {
		List<ArticleArchive> articleArchivesList = this.articleArchiveDAO
				.getArticleArchiveList();
		for (ArticleArchive articleArchive : articleArchivesList) {
			int year = articleArchive.getYear();
			int month = articleArchive.getMonth();
			// 这个月第一天
			String beforeDateStr = DateUtil.getFirstDayOfMonth(year, month);
			// 下个月第一天
			String afterDateStr = DateUtil.getFirstDayOfMonth(year, month + 1);
			List<Article> articles = this.articleDAO
					.getArticlesByArticleArchive(beforeDateStr, afterDateStr);
			articleArchive.setArticles(articles);
		}
		return articleArchivesList;
	}

	public boolean updateArticleArchive(ArticleArchive articleArchive) {
		return this.articleArchiveDAO.updateArticleArchive(articleArchive);
	}
}
