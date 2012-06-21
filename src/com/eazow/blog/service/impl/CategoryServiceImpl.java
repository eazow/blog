package com.eazow.blog.service.impl;

import java.util.List;

import com.eazow.blog.dao.ArticleDAO;
import com.eazow.blog.dao.CategoryDAO;
import com.eazow.blog.entity.Article;
import com.eazow.blog.entity.Category;
import com.eazow.blog.service.CategoryService;


public class CategoryServiceImpl implements CategoryService
{
	private CategoryDAO categoryDAO;
	private ArticleDAO articleDAO;
	
	private static CategoryService categoryService = null;
	
	private CategoryServiceImpl(CategoryDAO categoryDAO, ArticleDAO articleDAO)
	{
		this.categoryDAO = categoryDAO;
		this.articleDAO = articleDAO;
	}
	
	public static CategoryService getCategoryServiceInstance(CategoryDAO categoryDAO, ArticleDAO articleDAO)
	{
		if(null == categoryService)
			categoryService = new CategoryServiceImpl(categoryDAO, articleDAO);
		return categoryService;
	}
	
	public ArticleDAO getArticleDAO()
	{
		return articleDAO;
	}
	public void setArticleDAO(ArticleDAO articleDAO)
	{
		this.articleDAO = articleDAO;
	}
	public CategoryDAO getCategoryDAO()
	{
		return categoryDAO;
	}
	public void setCategoryDAO(CategoryDAO categoryDAO)
	{
		this.categoryDAO = categoryDAO;
	}

	public List<Category> getAllCategories()
	{
		List<Category> categoriesList = categoryDAO.getAllCategories();
//		for(Category category: categoriesList)
//		{
//			int realArticlesNumOfCategory = articleDAO.getArticlesNumOfCategory(category.getId());
//			category.setRealArticlesNum(realArticlesNumOfCategory);
//		}
		return categoriesList;
	}
	
	public Category getCategory(int id)
	{
		return this.categoryDAO.getCategory(id);
	}
	
	public boolean updateCategory(Category category)
	{
		return categoryDAO.updateCategory(category);
	}
	//删除分类,如果分类文章数不为0,不许删除
	public boolean deleteCategory(int categoryId)
	{
		Category category = categoryDAO.getCategory(categoryId);
		if(null == category)
			return false;
		int articlesNum = category.getArticlesNum();
		//该分类没有对应文章
		if(articlesNum == 0)
		{
			return categoryDAO.deleteCategory(categoryId);
		}
		//分类有对应文章，不许删除
		else
		{
			return false;
		}
//		int result = articleDAO.moveArticlesToDefaultCategory(categoryId);
//		categoryDAO.addArticlesNumOfCategory(categoryId, result);
//		if(!categoryDAO.deleteCategory(categoryId))
//			return false;
//		if(result == articlesNum)
//			return true;
//		return false;
	}
	
	public boolean addCategory(Category category)
	{
		return categoryDAO.addCategory(category);
	}
	
	public List<Category> manageAllCategories()
	{
		List<Category> categoriesList = categoryDAO.getAllCategories();
		for(Category category: categoriesList)
		{
//			int realArticlesNumOfCategory = articleDAO.getArticlesNumOfCategory(category.getId());
//			category.setRealArticlesNum(realArticlesNumOfCategory);
			List<Article> articles = this.articleDAO.getArticlesOfCategory(category.getId());
			category.setArticles(articles);
		}
		return categoriesList;
	}
}
