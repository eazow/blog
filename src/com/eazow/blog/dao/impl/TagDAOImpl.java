package com.eazow.blog.dao.impl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eazow.blog.dao.TagDAO;
import com.eazow.blog.dao.connector.DBConnector;
import com.eazow.blog.entity.Tag;

public class TagDAOImpl implements TagDAO {
	private static TagDAO tagDAO;

	private TagDAOImpl() {
	}

	public static TagDAO getTagDAOInstance() {
		if (null == tagDAO)
			tagDAO = new TagDAOImpl();
		return tagDAO;
	}

	public int addTag(Tag tag) {
		int newId = 0;
		DBConnector dbConnector = null;
		String sql = "insert into tag(name) values(?)";
		try {
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			dbConnector.pstmt.setString(1, tag.getName());
			if (dbConnector.pstmt.executeUpdate() == 1) {
				dbConnector.rs = dbConnector.pstmt.getGeneratedKeys();
				if (dbConnector.rs.next()) {
					newId = dbConnector.rs.getInt(1);
					return newId;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return newId;
	}

	public Tag getTag(String name) {
		DBConnector dbConnector = null;
		String sql = "select * from tag where name = '" + name + "'";
		Tag tag = null;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				tag = new Tag(dbConnector.rs.getInt("id"), dbConnector.rs
						.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return tag;
	}

	public List<Tag> getAllTags() {
		List<Tag> tagsList = new ArrayList<Tag>();
		DBConnector dbConnector = null;
		String sql = "select * from tag order by id desc";
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Tag tag = null;
			while (dbConnector.rs.next()) {
				tag = new Tag(dbConnector.rs.getInt("id"), dbConnector.rs
						.getString("name"));
				tagsList.add(tag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbConnector.closeConnection();
		return tagsList;
	}

	public Tag getTag(int id) {
		DBConnector dbConnector = null;
		String sql = "select * from tag where id = " + id;
		Tag tag = null;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				tag = new Tag(dbConnector.rs.getInt("id"), dbConnector.rs
						.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return tag;
	}

	public boolean deleteTag(int id) {
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "delete from tag where id = " + id;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			result = dbConnector.stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		if (0 == result)
			return false;
		else
			return true;
	}
}
