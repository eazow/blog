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
	//�������з���,ͬʱ��÷����µ���������
	public List<Category> manageAllCategories();
}
