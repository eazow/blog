package com.eazow.blog.service;

import java.util.List;

import com.eazow.blog.entity.Category;


public interface CategoryService
{
	public List<Category> getAllCategories();
	public Category getCategory(int id);
	public boolean updateCategory(Category category);
	public boolean deleteCategory(int id);
	public boolean addCategory(Category category);
	//管理所有分类,同时获得分类下的所有文章
	public List<Category> manageAllCategories();
}
