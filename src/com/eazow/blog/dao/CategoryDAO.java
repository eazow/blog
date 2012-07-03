package com.eazow.blog.dao;

import java.util.List;

import com.eazow.blog.entity.Category;

public interface CategoryDAO {
	public List<Category> getAllCategories();

	public Category getCategory(int id);

	public boolean addArticlesNumOfCategory(int categoryId);

	public boolean addArticlesNumOfCategory(int categoryId, int num);

	public boolean reduceArticlesNumOfCategory(int categoryId);

	public boolean updateCategory(Category category);

	public boolean deleteCategory(int id);

	public boolean addCategory(Category category);
}
