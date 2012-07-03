package com.eazow.blog.test;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.service.ArticleService;

public class ArticleServiceTest {
	public static void main(String[] args) {
		ArticleService articleService = DAOFactory.getArticleServiceInstance();
		articleService.getTotalPagesByArticleArchive(1, 1);
	}
}
