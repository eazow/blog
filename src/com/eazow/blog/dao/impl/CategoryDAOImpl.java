package com.eazow.blog.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eazow.blog.dao.CategoryDAO;
import com.eazow.blog.dao.connector.DBConnector;
import com.eazow.blog.entity.Category;


public class CategoryDAOImpl implements CategoryDAO
{
	private static CategoryDAO categoryDAO = new CategoryDAOImpl();
	
	private CategoryDAOImpl(){}
	
	public static CategoryDAO getCategoryDAOInstance()
	{
		return categoryDAO;
	}
	
	public List<Category> getAllCategories() 
	{
		DBConnector dbConnector = null;
		List<Category> categoriesList = new ArrayList<Category>();
		try 
		{
			dbConnector = new DBConnector();
			String sql = "select * from category";
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Category category = null;
			while (dbConnector.rs.next()) 
			{
				category = new Category(dbConnector.rs.getInt("id"), 
						dbConnector.rs.getString("name"), dbConnector.rs.getInt("articles_num"));
				categoriesList.add(category);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			dbConnector.closeConnection();
		}
		return categoriesList;
	}

	public Category getCategory(int id)
	{
		DBConnector dbConnector = null;
		Category category = null;
		try
		{
			dbConnector = new DBConnector();
			String sql = "select * from category where id = " + id;
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next())
			{
				category = new Category(dbConnector.rs.getInt("id"), 
						dbConnector.rs.getString("name"), dbConnector.rs.getInt("articles_num"));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			dbConnector.closeConnection();
		}
		return category;
	}
	
	public synchronized boolean addArticlesNumOfCategory(int categoryId)
	{
		DBConnector dbConnector = null;
		int result = 0;
		String sql = "select articles_num from category where id = " + categoryId;
		int articlesNum=0;
		try 
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if(dbConnector.rs.next())
			{
				articlesNum = dbConnector.rs.getInt(1);
				articlesNum++;
				sql = "update category set articles_num = " + articlesNum + " where id = " + categoryId;
				result = dbConnector.stmt.executeUpdate(sql);
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			dbConnector.closeConnection();
		}
		if(result==1)
			return true;
		return false;
	}
	
	public synchronized boolean addArticlesNumOfCategory(int categoryId, int num)
	{
		DBConnector dbConnector = null;
		int result = 0;
		String sql = "select articles_num from category where id = " + categoryId;
		int articlesNum=0;
		try 
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if(dbConnector.rs.next())
			{
				articlesNum = dbConnector.rs.getInt(1);
				articlesNum += num;
				sql = "update category set articles_num = " + articlesNum + " where id = " + categoryId;
				result = dbConnector.stmt.executeUpdate(sql);
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			dbConnector.closeConnection();
		}
		if(result==1)
			return true;
		return false;
	}
	
	public synchronized boolean reduceArticlesNumOfCategory(int categoryId)
	{
		DBConnector dbConnector = null;
		int result = 0;
		String sql = "select articles_num from category where id = " + categoryId;
		int articlesNum=0;
		try 
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if(dbConnector.rs.next())
			{
				articlesNum = dbConnector.rs.getInt(1);
				if(articlesNum==0)
					return false;
				articlesNum--;
				sql = "update category set articles_num = " + articlesNum + " where id = " + categoryId;
				result = dbConnector.stmt.executeUpdate(sql);
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			dbConnector.closeConnection();
		}
		if(result==1)
			return true;
		return false;
	}
	
	public synchronized boolean updateCategory(Category category)
	{
		DBConnector dbConnector = null;
		int result = 0;
		int categoryId = category.getId();
		String sql = "update category set name = '"+category.getName()+"', articles_num = "+category.getArticlesNum()
			+" where id = " + categoryId;
		try 
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			result = dbConnector.stmt.executeUpdate(sql);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			dbConnector.closeConnection();
		}
		if(result==1)
			return true;
		return false;
	}
	
	public synchronized boolean deleteCategory(int id)
	{
		DBConnector dbConnector = null;
		int result = 0;
		String sql = "delete from category where id = " + id;
		try 
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			result = dbConnector.stmt.executeUpdate(sql);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			dbConnector.closeConnection();
		}
		if(result==1)
			return true;
		return false;
	}
	
	public synchronized boolean addCategory(Category category)
	{
		DBConnector dbConnector = null;
		int result = 0;
		String sql = "select * from category where name = '" + category.getName() + "'";
		try 
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			//有相同的分类
			if(dbConnector.rs.next())
				return false;
			sql = "insert into category(name, articles_num) values('"
				+ category.getName()+ "', " + category.getArticlesNum() + ")";
			result = dbConnector.stmt.executeUpdate(sql);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			dbConnector.closeConnection();
		}
		if(result==1)
			return true;
		return false;
	}
}
