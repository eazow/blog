package com.eazow.blog.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eazow.blog.dao.ArticleArchiveDAO;
import com.eazow.blog.dao.connector.DBConnector;
import com.eazow.blog.entity.Article;
import com.eazow.blog.entity.ArticleArchive;
import com.eazow.blog.util.DateUtil;


public class ArticleArchiveDAOImpl implements ArticleArchiveDAO
{
	private static ArticleArchiveDAO articleArchiveDAO = null;
	
	private ArticleArchiveDAOImpl()
	{
	}
	
	public static ArticleArchiveDAO getArticleArchiveDAOInstance()
	{
		if(null == articleArchiveDAO)
			articleArchiveDAO = new ArticleArchiveDAOImpl();
		return articleArchiveDAO;
	}
	
	
	public List<ArticleArchive> getArticleArchiveList()
	{
		List<ArticleArchive> articleArchiveList = new ArrayList<ArticleArchive>();
		DBConnector dbConnector = null;
		String sql = "select * from article_archive order by id desc";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			ArticleArchive articleArchive = null;
			while(dbConnector.rs.next())
			{
				articleArchive = new ArticleArchive(dbConnector.rs.getInt("id"), dbConnector.rs.getInt("year"), 
						dbConnector.rs.getInt("month"), dbConnector.rs.getInt("articles_num"));
				articleArchiveList.add(articleArchive);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		dbConnector.closeConnection();
		return articleArchiveList;
	}
	
	public ArticleArchive getArticleArchive(int id)
	{
		ArticleArchive articleArchive = null;
		DBConnector dbConnector = null;
		String sql = "select * from article_archive where id = " + id;
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			
			if(dbConnector.rs.next())
			{
				articleArchive = new ArticleArchive(dbConnector.rs.getInt("id"), dbConnector.rs.getInt("year"), 
						dbConnector.rs.getInt("month"), dbConnector.rs.getInt("articles_num"));
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
		return articleArchive;
	}
	
	public ArticleArchive getArticleArchive(int year, int month)
	{
		ArticleArchive articleArchive = null;
		DBConnector dbConnector = null;
		String sql = "select * from article_archive where year = " + year + " and month = " + month;
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			
			if(dbConnector.rs.next())
			{
				articleArchive = new ArticleArchive(dbConnector.rs.getInt("id"), dbConnector.rs.getInt("year"), 
						dbConnector.rs.getInt("month"), dbConnector.rs.getInt("articles_num"));
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
		return articleArchive;
	}
	
	public synchronized boolean addArticlesNumOfArticleArchive(int articleArchiveId)
	{
		DBConnector dbConnector = null;
		int result = 0;
		String sql = "select articles_num from article_archive where id = " + articleArchiveId;
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
				sql = "update article_archive set articles_num = " + articlesNum + " where id = " + articleArchiveId;
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
	
	public synchronized boolean reduceArticlesNumOfArticleArchive(int articleArchiveId)
	{
		DBConnector dbConnector = null;
		int result = 0;
		String sql = "select articles_num from article_archive where id = " + articleArchiveId;
		int articlesNum=0;
		try 
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if(dbConnector.rs.next())
			{
				articlesNum = dbConnector.rs.getInt(1);
				articlesNum--;
				//如果文章数为0,则删除该条存档
				if(0 == articlesNum)
				{
					return deleteArticleArchive(articleArchiveId);
				}
				else
				{
					sql = "update article_archive set articles_num = " + articlesNum + " where id = " + articleArchiveId;
					result = dbConnector.stmt.executeUpdate(sql);
				}
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
	
	public synchronized boolean deleteArticleArchive(int id)
	{
		DBConnector dbConnector = null;
		int result = 0;
		boolean flag = true;
		try 
		{
			dbConnector = new DBConnector();
			String sql = "delete from article_archive where id = " + id;
			dbConnector.stmt = dbConnector.conn.createStatement();
			result = dbConnector.stmt.executeUpdate(sql);
			if (result == 0) {
				flag = false;
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
		return flag;
	}
	
	public boolean addArticleArchive(ArticleArchive articleArchive)
	{
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "insert into article_archive(year, month, articles_num) values(?, ?, ?)";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setInt(1, articleArchive.getYear());
			dbConnector.pstmt.setInt(2, articleArchive.getMonth());
			dbConnector.pstmt.setInt(3, articleArchive.getArticlesNum());
			result = dbConnector.pstmt.executeUpdate();
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
		else
			return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean updateArticleArchive(Article article)
	{
		List<ArticleArchive> articleArchiveList = getArticleArchiveList();
		Date postDate = article.getPostDate();
		int year = DateUtil.getYear(postDate);
		int month = DateUtil.getMonth(postDate);
		
		for(ArticleArchive articleArchive: articleArchiveList)
		{
			//有对应的文章存档
			if(year==articleArchive.getYear() && month==articleArchive.getMonth())
			{
				return this.addArticlesNumOfArticleArchive(articleArchive.getId());
			}

		}
		//否则创建新的文章存档
		ArticleArchive articleArchiveNew = new ArticleArchive(year, month, 1);
		
		return this.addArticleArchive(articleArchiveNew);
	}
	
	public boolean updateArticleArchive(ArticleArchive articleArchive)
	{
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "update article_archive set year=?, month=?, articles_num=? where id = ?";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setInt(1, articleArchive.getYear());
			dbConnector.pstmt.setInt(2, articleArchive.getMonth());
			dbConnector.pstmt.setInt(3, articleArchive.getArticlesNum());
			dbConnector.pstmt.setInt(4, articleArchive.getId());
			result = dbConnector.pstmt.executeUpdate();
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
		else
			return false;
	}
}
