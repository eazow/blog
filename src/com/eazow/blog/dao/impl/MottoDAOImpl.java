package com.eazow.blog.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.eazow.blog.dao.MottoDAO;
import com.eazow.blog.dao.connector.DBConnector;
import com.eazow.blog.entity.Motto;


public class MottoDAOImpl implements MottoDAO
{
	private MottoDAOImpl()
	{
	}
	
	private static MottoDAO mottoDAO = null;
	
	public static MottoDAO getMottoDAOInstance()
	{
		if(null == mottoDAO)
			mottoDAO = new MottoDAOImpl();
		return mottoDAO;
	}
	
	public List<Motto> getAllMottos()
	{
		List<Motto> mottosList = new ArrayList<Motto>();
		DBConnector dbConnector = null;
		String sql = "select * from motto order by id desc";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Motto motto = null;
			while(dbConnector.rs.next())
			{
				motto = new Motto(dbConnector.rs.getInt("id"), dbConnector.rs.getString("content"),
					dbConnector.rs.getTimestamp("created_date"));
				mottosList.add(motto);
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
		return mottosList;
	}
	
	public boolean addMotto(Motto motto)
	{
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "insert into motto(content, created_date) values(?, ?)";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setString(1, motto.getContent());
			dbConnector.pstmt.setTimestamp(2, new Timestamp(motto.getCreatedDate().getTime()));
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
	
	public Motto getMotto(int id)
	{
		Motto motto = null;
		DBConnector dbConnector = null;
		String sql = "select * from motto where id = " + id;
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			while(dbConnector.rs.next())
			{
				motto = new Motto(dbConnector.rs.getInt("id"), dbConnector.rs.getString("content"),
					dbConnector.rs.getTimestamp("created_date"));
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
		return motto;
	}
	
	public Motto getMotto(String content)
	{
		if(null == content)
			return null;
		else
			content = content.trim();
		Motto motto = null;
		DBConnector dbConnector = null;
		String sql = "select * from motto where content = '" + content + "'";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			while(dbConnector.rs.next())
			{
				motto = new Motto(dbConnector.rs.getInt("id"), dbConnector.rs.getString("content"),
					dbConnector.rs.getTimestamp("created_date"));
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
		return motto;
	}
	
	public Motto getRandomMotto()
	{
		Motto motto = null;
		DBConnector dbConnector = null;
		String sql = "select * from motto order by rand() limit 1";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if(dbConnector.rs.next())
			{
				motto = new Motto(dbConnector.rs.getInt("id"), dbConnector.rs.getString("content"),
					dbConnector.rs.getTimestamp("created_date"));
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
		return motto;
	}
}
