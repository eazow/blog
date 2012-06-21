package com.eazow.blog.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.eazow.blog.dao.DraftDAO;
import com.eazow.blog.dao.connector.DBConnector;
import com.eazow.blog.entity.Draft;


public class DraftDAOImpl implements DraftDAO
{
	private static DraftDAO draftDAO = null;
	
	private DraftDAOImpl()
	{
	}
	
	public static DraftDAO getDraftDAOInstance()
	{
		if(null == draftDAO)
			draftDAO = new DraftDAOImpl();
		return draftDAO;
	}
	
	public boolean addDraft(Draft draft)
	{
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "insert into draft(title, content, post_date, category_id, tags) values(?, ?, ?, ?, ?)";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setString(1, draft.getTitle());
			dbConnector.pstmt.setString(2, draft.getContent());
			dbConnector.pstmt.setTimestamp(3, new Timestamp(draft.getPostDate().getTime()));
			dbConnector.pstmt.setInt(4, draft.getCategoryId());
			dbConnector.pstmt.setString(5, draft.getTags());
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
	
	public List<Draft> getAllDrafts()
	{
		List<Draft> draftsList = new ArrayList<Draft>();
		DBConnector dbConnector = null;
		String sql = "select * from draft order by id desc";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Draft draft = null;
			while(dbConnector.rs.next())
			{
				draft = new Draft(dbConnector.rs.getInt("id"), dbConnector.rs.getString("title"), 
						dbConnector.rs.getString("content"), dbConnector.rs.getTimestamp("post_date"), 
						dbConnector.rs.getInt("category_id"), dbConnector.rs.getString("tags"));
				draftsList.add(draft);
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
		return draftsList;
	}
	
	public int getAllDraftsNum()
	{
		int draftsNum = 0;
		DBConnector dbConnector = null;
		String sql = "select count(*) from draft order by id desc";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if(dbConnector.rs.next())
			{
				draftsNum = dbConnector.rs.getInt(1);
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
		return draftsNum;
	}
	
	public Draft getDraft(int id)
	{
		DBConnector dbConnector = null;
		Draft draft = null;
		String sql = "select * from draft where id = " + id;
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			
			if(dbConnector.rs.next())
			{
				draft = new Draft(dbConnector.rs.getInt("id"), dbConnector.rs.getString("title"), 
						dbConnector.rs.getString("content"), dbConnector.rs.getTimestamp("post_date"), 
						dbConnector.rs.getInt("category_id"), dbConnector.rs.getString("tags"));
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
		return draft;
	}
	
	public synchronized boolean deleteDraft(int id)
	{
		DBConnector dbConnector = null;
		int result = 0;
		boolean flag = true;
		try 
		{
			dbConnector = new DBConnector();
			String sql = "delete from draft where id = " + id;
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
}
