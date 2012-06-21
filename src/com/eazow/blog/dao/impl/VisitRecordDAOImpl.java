package com.eazow.blog.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eazow.blog.dao.VisitRecordDAO;
import com.eazow.blog.dao.connector.DBConnector;
import com.eazow.blog.entity.VisitRecord;
import com.eazow.blog.util.DateUtil;



public class VisitRecordDAOImpl implements VisitRecordDAO
{
	private static VisitRecordDAO visitRecordDAO = null;
	
	private VisitRecordDAOImpl()
	{
	}
	
	public static VisitRecordDAO getVisitRecordDAOInstance()
	{
		if(null == visitRecordDAO)
			visitRecordDAO = new VisitRecordDAOImpl();
		return visitRecordDAO;
	}
	
	public synchronized boolean addRecord(VisitRecord visitRecord)
	{
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "insert into visit_record(source_ip, visit_date) values(?, now())";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setString(1, visitRecord.getSourceIP());
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
	
	public int getTotalPageView()
	{
		int totalPageView = 0;
		DBConnector dbConnector = null;
		String sql = "select count(*) from visit_record";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if(dbConnector.rs.next())
			{
				totalPageView = dbConnector.rs.getInt(1);
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
		return totalPageView;
	}
	
	public int getTodayPageView(Date today)
	{
		int totalPageView = 0;
		DBConnector dbConnector = null;
		String sql = "select count(*) from visit_record where visit_date >= '" + DateUtil.getTodayMinTime(today)
			+ "' and visit_date <= '" + DateUtil.getTodayMaxTime(today) + "'";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if(dbConnector.rs.next())
			{
				totalPageView = dbConnector.rs.getInt(1);
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
		return totalPageView;
	}
	
	public List<VisitRecord> getAllVisitRecords()
	{
		List<VisitRecord> visitRecordsList = new ArrayList<VisitRecord>();
		DBConnector dbConnector = null;
		String sql = "select * from visit_record";
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			VisitRecord visitRecord = null;
			while(dbConnector.rs.next())
			{
				visitRecord = new VisitRecord(dbConnector.rs.getInt("id"), dbConnector.rs.getString("source_ip"), 
						dbConnector.rs.getTimestamp("visit_date"));
				visitRecordsList.add(visitRecord);
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
		return visitRecordsList;
	}
	
	public List<VisitRecord> getVisitRecords(int pageNum, int pageSize)
	{
		int startLocation = (pageNum-1) * pageSize;
		List<VisitRecord> visitRecordsList = new ArrayList<VisitRecord>();
		DBConnector dbConnector = null;
		String sql = "select * from visit_record limit " + startLocation + ", " + pageSize;
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			VisitRecord visitRecord = null;
			while(dbConnector.rs.next())
			{
				visitRecord = new VisitRecord(dbConnector.rs.getInt("id"), dbConnector.rs.getString("source_ip"), 
						dbConnector.rs.getTimestamp("visit_date"));
				visitRecordsList.add(visitRecord);
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
		return visitRecordsList;
	}
	
	public int getTotalPages(int pageSize)
	{
		DBConnector dbConnector = null;
		String sql = "select count(*) from visit_record";
		int totalResultsNum = 0;
		try
		{
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if(dbConnector.rs.next())
			{
				totalResultsNum = dbConnector.rs.getInt(1);
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
		if(totalResultsNum%pageSize == 0)
			return totalResultsNum/pageSize;
		else
			return totalResultsNum/pageSize + 1;
	}
	
	public boolean deleteVisitRecord(int id)
	{
		DBConnector dbConnector = null;
		int result = 0;
		boolean flag = true;
		try 
		{
			dbConnector = new DBConnector();
			String sql = "delete from visit_record where id = " + id;
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
