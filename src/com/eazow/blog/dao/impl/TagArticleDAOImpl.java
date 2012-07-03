package com.eazow.blog.dao.impl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eazow.blog.dao.TagArticleDAO;
import com.eazow.blog.dao.connector.DBConnector;
import com.eazow.blog.entity.TagArticle;

public class TagArticleDAOImpl implements TagArticleDAO {
	private static TagArticleDAO tagArticleDAO;

	private TagArticleDAOImpl() {
	}

	public static TagArticleDAO getTagArticleDAOInstance() {
		if (null == tagArticleDAO)
			tagArticleDAO = new TagArticleDAOImpl();
		return tagArticleDAO;
	}

	public int addTagArticle(TagArticle tagArticle) {
		int newId = 0;
		DBConnector dbConnector = null;
		String sql = "insert into tag_article(tag_id, article_id) values(?, ?)";
		try {
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			dbConnector.pstmt.setInt(1, tagArticle.getTagId());
			dbConnector.pstmt.setInt(2, tagArticle.getArticleId());
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

	public int deleteTagArticles(int articleId) {
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "delete from tag_article where article_id = " + articleId;
		try {
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			result = dbConnector.pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return result;
	}

	public int deleteTagArticlesByTagId(int tagId) {
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "delete from tag_article where tag_id = " + tagId;
		try {
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			result = dbConnector.pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return result;
	}

	public List<Integer> getTagsIds(int articleId) {
		List<Integer> tagsIdsList = new ArrayList<Integer>();
		DBConnector dbConnector = null;
		String sql = "select tag_id from tag_article where article_id = "
				+ articleId;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			while (dbConnector.rs.next()) {
				int tagId = dbConnector.rs.getInt("tag_id");
				tagsIdsList.add(tagId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return tagsIdsList;
	}

	public List<Integer> getArticlesIds(int tagId, int pageNum, int pageSize) {
		List<Integer> articlesIdsList = new ArrayList<Integer>();
		DBConnector dbConnector = null;
		int startLocation = (pageNum - 1) * pageSize;
		String sql = "select article_id from tag_article where tag_id = "
				+ tagId + " order by article_id desc limit " + startLocation
				+ ", " + pageSize;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			while (dbConnector.rs.next()) {
				int articleId = dbConnector.rs.getInt("article_id");
				articlesIdsList.add(articleId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return articlesIdsList;
	}

	public int getTotalPagesOfTag(int tagId, int pageSize) {
		int totalArticlesNum = 0;
		DBConnector dbConnector = null;
		String sql = "select count(*) from tag_article where tag_id = " + tagId;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			while (dbConnector.rs.next()) {
				totalArticlesNum = dbConnector.rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		if (totalArticlesNum % pageSize == 0)
			return totalArticlesNum / pageSize;
		else
			return totalArticlesNum / pageSize + 1;
	}

	public List<Integer> getAllArticlesIds(int tagId) {
		List<Integer> articlesIdsList = new ArrayList<Integer>();
		DBConnector dbConnector = null;
		String sql = "select article_id from tag_article where tag_id = "
				+ tagId + " order by article_id desc";
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			while (dbConnector.rs.next()) {
				int articleId = dbConnector.rs.getInt("article_id");
				articlesIdsList.add(articleId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return articlesIdsList;
	}
}
