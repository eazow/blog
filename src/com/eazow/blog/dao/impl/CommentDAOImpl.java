package com.eazow.blog.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.eazow.blog.dao.CommentDAO;
import com.eazow.blog.dao.connector.DBConnector;
import com.eazow.blog.entity.Comment;


public class CommentDAOImpl implements CommentDAO
{
	private static CommentDAO commentDAO = new CommentDAOImpl();
	
	private CommentDAOImpl(){}
	
	public static CommentDAO getCommentDAOInstance()
	{
		return commentDAO;
	}
	
	public synchronized void addComment(Comment comment)
	{
		DBConnector dbConnector = null;
		String sql = "insert into comment(content, article_id, comment_date, nickname) values(?, ?, now(), ?)";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setString(1, comment.getContent());
			dbConnector.pstmt.setInt(2, comment.getArticleId());
			dbConnector.pstmt.setString(3, comment.getNickname());
			dbConnector.pstmt.executeUpdate();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			dbConnector.closeConnection();
		}
	}
	
	public List<Comment> getCommentsOfArticle(int articleId)
	{
		DBConnector dbConnector = null;
		List<Comment> commentsList = new ArrayList<Comment>();
		String sql = "select * from comment where article_id = " + articleId + " order by id desc";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Comment comment = null;
			while(dbConnector.rs.next())
			{
				comment = new Comment(dbConnector.rs.getInt("id"), dbConnector.rs.getString("content"),
						dbConnector.rs.getInt("article_id"), dbConnector.rs.getTimestamp("comment_date"), 
						dbConnector.rs.getString("nickname"));
				commentsList.add(comment);
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
		return commentsList;
	}
	
	public synchronized boolean deleteComment(int id)
	{
		DBConnector dbConnector = null;
		int result = 0;
		String sql = "delete from comment where id = " + id;
		try
		{
			dbConnector = new DBConnector();
			result = dbConnector.conn.createStatement().executeUpdate(sql);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			dbConnector.closeConnection();
		}
		if(0==result)
			return false;
		else
			return true;
	}
	
	public Comment getComment(int id)
	{
		DBConnector dbConnector = null;
		Comment comment = null;
		String sql = "select * from comment where id = " + id;
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if(dbConnector.rs.next())
			{
				comment = new Comment(dbConnector.rs.getInt("id"), dbConnector.rs.getString("content"),
						dbConnector.rs.getInt("article_id"), dbConnector.rs.getTimestamp("comment_date"), 
						dbConnector.rs.getString("nickname"));
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
		return comment;
	}
	
	public List<Comment> getLatest10Comments()
	{
		DBConnector dbConnector = null;
		List<Comment> commentsList = new ArrayList<Comment>();
		Comment comment = null;
		String sql = "select * from comment order by id desc limit 10";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			while(dbConnector.rs.next())
			{
				comment = new Comment(dbConnector.rs.getInt("id"), dbConnector.rs.getString("content"),
						dbConnector.rs.getInt("article_id"), dbConnector.rs.getTimestamp("comment_date"),
						dbConnector.rs.getString("nickname"));
				commentsList.add(comment);
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
		return commentsList;
	}
	
	public List<Comment> getAllComments()
	{
		DBConnector dbConnector = null;
		List<Comment> commentsList = new ArrayList<Comment>();
		Comment comment = null;
		String sql = "select * from comment order by id desc";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			while(dbConnector.rs.next())
			{
				comment = new Comment(dbConnector.rs.getInt("id"), dbConnector.rs.getString("content"),
						dbConnector.rs.getInt("article_id"), dbConnector.rs.getTimestamp("comment_date"),
						dbConnector.rs.getString("nickname"));
				commentsList.add(comment);
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
		return commentsList;
	}
	
	public int getTotalCommentsNum()
	{
		int totalCommentsNum = 0;
		DBConnector dbConnector = null;
		String sql = "select count(*) from comment";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if(dbConnector.rs.next())
			{
				totalCommentsNum = dbConnector.rs.getInt(1);
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
		return totalCommentsNum;
	}
	
	public synchronized boolean updateComment(Comment comment)
	{
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "update comment set content=?, article_id=?, comment_date=?, nickname=? where id = ?";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setString(1, comment.getContent());
			dbConnector.pstmt.setInt(2, comment.getArticleId());
			dbConnector.pstmt.setTimestamp(3, new Timestamp(comment.getCommentDate().getTime()));
			dbConnector.pstmt.setString(4, comment.getNickname());
			dbConnector.pstmt.setInt(5, comment.getId());
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
