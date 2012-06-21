package com.eazow.blog.dao.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
	public static String url = null;
	public Connection conn = null;
	public Statement stmt = null;
	public PreparedStatement  pstmt = null;
	public ResultSet rs = null;
	
	public DBConnector()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost/blog?user=root&password=hustWHU6";
//			url = "jdbc:mysql://localhost/hhmhywzr?user=hhmhywzr&password=hustWHU6";
//			url = "jdbc:mysql://localhost/hhmhywzr?user=root&password=hustWHU6";
//			url = "jdbc:mysql://61.152.105.38/hhmhywzr?user=hhmhywzr&password=hustWHU6";
			this.conn = DriverManager.getConnection(url);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void closeConnection() 
	{
		if (rs != null) 
		{
			try
			{
				rs.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		if (pstmt != null)
		{
			try
			{
				pstmt.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if (stmt != null)
		{
			try 
			{
				stmt.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if (conn != null)
		{
			try 
			{
				conn.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
