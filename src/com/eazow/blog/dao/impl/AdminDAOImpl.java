package com.eazow.blog.dao.impl;

import java.sql.SQLException;

import com.eazow.blog.dao.AdminDAO;
import com.eazow.blog.dao.connector.DBConnector;
import com.eazow.blog.entity.Admin;

public class AdminDAOImpl implements AdminDAO {
	private static AdminDAO adminDAO = new AdminDAOImpl();

	private AdminDAOImpl() {
	}

	public static AdminDAO getAdminDAOInstance() {
		return adminDAO;
	}

	public Admin getAdmin(int id) {
		DBConnector dbConnector = null;
		String sql = "select * from admin where id = " + id;
		Admin admin = null;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				admin = new Admin();
				admin.setId(dbConnector.rs.getInt("id"));
				admin.setUsername(dbConnector.rs.getString("username"));
				admin.setPassword(dbConnector.rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return admin;
	}

	public Admin getAdmin(String username) {
		DBConnector dbConnector = null;
		String sql = "select * from admin where username = '" + username + "'";
		Admin admin = null;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				admin = new Admin();
				admin.setId(dbConnector.rs.getInt("id"));
				admin.setUsername(dbConnector.rs.getString("username"));
				admin.setPassword(dbConnector.rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return admin;
	}

	public Admin getAdmin(String username, String password) {
		DBConnector dbConnector = null;
		String sql = "select * from admin where username = '" + username
				+ "' and password = '" + password + "'";
		Admin admin = null;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				admin = new Admin();
				admin.setId(dbConnector.rs.getInt("id"));
				admin.setUsername(dbConnector.rs.getString("username"));
				admin.setPassword(dbConnector.rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return admin;
	}
}
