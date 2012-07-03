package com.eazow.blog.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.eazow.blog.dao.ArticleDAO;
import com.eazow.blog.dao.connector.DBConnector;
import com.eazow.blog.entity.Article;

public class ArticleDAOImpl implements ArticleDAO {
	private static ArticleDAO articleDAO = new ArticleDAOImpl();

	private ArticleDAOImpl() {
	}

	public static ArticleDAO getArticleDAOInstance() {
		return articleDAO;
	}

	public synchronized int addArticle(Article article) {
		int newId = 0;
		DBConnector dbConnector = null;
		String sql = "insert into article(title, content, post_date, category_id, comments_num, view_count) values(?, ?, ?, ?, ?, ?)";
		try {
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setString(1, article.getTitle());
			dbConnector.pstmt.setString(2, article.getContent());
			dbConnector.pstmt.setTimestamp(3, new Timestamp(article
					.getPostDate().getTime()));
			dbConnector.pstmt.setInt(4, article.getCategoryId());
			dbConnector.pstmt.setInt(5, article.getCommentsNum());
			dbConnector.pstmt.setInt(6, article.getViewCount());
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

	public synchronized boolean addViewCount(int articleId) {
		DBConnector dbConnector = null;
		int result = 0;
		String sql = "select view_count from article where id = " + articleId;
		int viewCount = 0;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				viewCount = dbConnector.rs.getInt(1);
				viewCount++;
				sql = "update article set view_count = " + viewCount
						+ " where id = " + articleId;
				result = dbConnector.stmt.executeUpdate(sql);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		if (result == 1)
			return true;
		else
			return false;
	}

	public List<Article> getAllArticles() {
		List<Article> articlesList = new ArrayList<Article>();
		DBConnector dbConnector = null;
		// String sql = "select * from article order by id desc";
		String sql = "select * from article order by post_date desc";
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Article article = null;
			while (dbConnector.rs.next()) {
				article = new Article(dbConnector.rs.getInt("id"),
						dbConnector.rs.getString("title"), dbConnector.rs
								.getString("content"), dbConnector.rs
								.getTimestamp("post_date"), dbConnector.rs
								.getInt("category_id"), dbConnector.rs
								.getInt("comments_num"), dbConnector.rs
								.getInt("view_count"));
				articlesList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbConnector.closeConnection();
		return articlesList;
	}

	public Article getArticle(int id) {
		DBConnector dbConnector = null;
		String sql = "select * from article where id = " + id;
		Article article = null;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				article = new Article(dbConnector.rs.getInt("id"),
						dbConnector.rs.getString("title"), dbConnector.rs
								.getString("content"), dbConnector.rs
								.getTimestamp("post_date"), dbConnector.rs
								.getInt("category_id"), dbConnector.rs
								.getInt("comments_num"), dbConnector.rs
								.getInt("view_count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return article;
	}

	public synchronized boolean deleteArticle(int id) {
		DBConnector dbConnector = null;
		int result = 0;
		boolean flag = true;
		try {
			dbConnector = new DBConnector();
			String sql = "delete from article where id = " + id;
			dbConnector.stmt = dbConnector.conn.createStatement();
			result = dbConnector.stmt.executeUpdate(sql);
			if (result == 0) {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return flag;
	}

	public List<Article> getArticlesOfCategory(int categoryId) {
		List<Article> articlesList = new ArrayList<Article>();
		if (categoryId == 0) {
			return articlesList;
		}
		DBConnector dbConnector = null;
		String sql = "select * from article where category_id = " + categoryId;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Article article = null;
			while (dbConnector.rs.next()) {
				article = new Article(dbConnector.rs.getInt("id"),
						dbConnector.rs.getString("title"), dbConnector.rs
								.getString("content"), dbConnector.rs
								.getTimestamp("post_date"), dbConnector.rs
								.getInt("category_id"), dbConnector.rs
								.getInt("comments_num"), dbConnector.rs
								.getInt("view_count"));
				articlesList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return articlesList;
	}

	public List<Article> getArticlesOfCategory(int categoryId, int pageNum,
			int pageSize) {
		DBConnector dbConnector = null;
		List<Article> articlesList = new ArrayList<Article>();
		int startLocation = (pageNum - 1) * pageSize;
		if (categoryId == 0) {
			return articlesList;
		}
		String sql = "select * from article where category_id = " + categoryId
				+ " order by post_date desc limit " + startLocation + ", "
				+ pageSize;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Article article = null;
			while (dbConnector.rs.next()) {
				article = new Article(dbConnector.rs.getInt("id"),
						dbConnector.rs.getString("title"), dbConnector.rs
								.getString("content"), dbConnector.rs
								.getTimestamp("post_date"), dbConnector.rs
								.getInt("category_id"), dbConnector.rs
								.getInt("comments_num"), dbConnector.rs
								.getInt("view_count"));
				articlesList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return articlesList;
	}

	public List<Article> getArticlesByArticleArchive(String beforeDate,
			String afterDate) {
		DBConnector dbConnector = null;
		List<Article> articlesList = new ArrayList<Article>();
		String sql = "select * from article where post_date >= '" + beforeDate
				+ "' and post_date < '" + afterDate
				+ "' order by post_date desc";
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Article article = null;
			while (dbConnector.rs.next()) {
				article = new Article(dbConnector.rs.getInt("id"),
						dbConnector.rs.getString("title"), dbConnector.rs
								.getString("content"), dbConnector.rs
								.getTimestamp("post_date"), dbConnector.rs
								.getInt("category_id"), dbConnector.rs
								.getInt("comments_num"), dbConnector.rs
								.getInt("view_count"));
				articlesList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return articlesList;
	}

	public List<Article> getArticlesByArticleArchive(String beforeDate,
			String afterDate, int pageNum, int pageSize) {
		DBConnector dbConnector = null;
		List<Article> articlesList = new ArrayList<Article>();
		int startLocation = (pageNum - 1) * pageSize;
		String sql = "select * from article where post_date >= '" + beforeDate
				+ "' and post_date < '" + afterDate
				+ "' order by post_date desc limit " + startLocation + ", "
				+ pageSize;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Article article = null;
			while (dbConnector.rs.next()) {
				article = new Article(dbConnector.rs.getInt("id"),
						dbConnector.rs.getString("title"), dbConnector.rs
								.getString("content"), dbConnector.rs
								.getTimestamp("post_date"), dbConnector.rs
								.getInt("category_id"), dbConnector.rs
								.getInt("comments_num"), dbConnector.rs
								.getInt("view_count"));
				articlesList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return articlesList;
	}

	public synchronized void addCommentsNumOfArticle(int articleId) {
		DBConnector dbConnector = null;
		String sql = "select comments_num from article where id = " + articleId;
		int commentsNum = 0;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				commentsNum = dbConnector.rs.getInt(1);
				commentsNum++;
				sql = "update article set comments_num = " + commentsNum
						+ " where id = " + articleId;
				dbConnector.stmt.executeUpdate(sql);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
	}

	public synchronized void reduceCommentsNumOfArticle(int articleId) {
		DBConnector dbConnector = null;
		String sql = "select comments_num from article where id = " + articleId;
		int commentsNum = 0;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				commentsNum = dbConnector.rs.getInt(1);
				commentsNum--;
				sql = "update article set comments_num = " + commentsNum
						+ " where id = " + articleId;
				dbConnector.stmt.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
	}

	public int getArticlesNumOfCategory(int categoryId) {
		int articlesNum = 0;
		DBConnector dbConnector = null;
		String sql = "select count(*) from article where category_id = "
				+ categoryId;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				articlesNum = dbConnector.rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return articlesNum;
	}

	public int getArticlesNumByArticleArchive(String beforeDate,
			String afterDate) {
		DBConnector dbConnector = null;
		String sql = "select count(*) from article where post_date >= '"
				+ beforeDate + "' and post_date < '" + afterDate + "'";

		int articlesNum = 0;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				articlesNum = dbConnector.rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return articlesNum;
	}

	// 返回修改的行数
	public synchronized int moveArticlesToDefaultCategory(int originalCategoryId) {
		DBConnector dbConnector = null;
		int result = 0;
		String sql = "update article set category_id = 1 where category_id = "
				+ originalCategoryId;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			result = dbConnector.stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return result;
	}

	public int getTotalPages(int pageSize) {
		int totalArticlesNum = 0;
		int totalPages = 0;
		DBConnector dbConnector = null;
		String sql = "select count(*) from article";
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				totalArticlesNum = dbConnector.rs.getInt(1);
				if (totalArticlesNum % pageSize == 0) {
					totalPages = totalArticlesNum / pageSize;
				} else {
					totalPages = totalArticlesNum / pageSize + 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return totalPages;
	}

	public int getTotalPagesOfCategory(int categoryId, int pageSize) {
		int totalArticlesNum = 0;
		int totalPages = 0;
		DBConnector dbConnector = null;
		String sql = "select count(*) from article where category_id = "
				+ categoryId;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				totalArticlesNum = dbConnector.rs.getInt(1);
				if (totalArticlesNum % pageSize == 0) {
					totalPages = totalArticlesNum / pageSize;
				} else {
					totalPages = totalArticlesNum / pageSize + 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return totalPages;
	}

	public int getTotalPagesByArticleArchive(String beforeDate,
			String afterDate, int pageSize) {
		int totalArticlesNum = 0;
		int totalPages = 0;
		DBConnector dbConnector = null;
		String sql = "select count(*) from article where post_date >= '"
				+ beforeDate + "' and post_date < '" + afterDate + "'";
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				totalArticlesNum = dbConnector.rs.getInt(1);
				if (totalArticlesNum % pageSize == 0) {
					totalPages = totalArticlesNum / pageSize;
				} else {
					totalPages = totalArticlesNum / pageSize + 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return totalPages;
	}

	public List<Article> getArticles(int pageNum, int pageSize) {
		List<Article> articlesList = new ArrayList<Article>();
		int startLocation = (pageNum - 1) * pageSize;
		DBConnector dbConnector = null;
		// String sql = "select * from article order by id desc limit " +
		// startLocation + ", " + pageSize;
		String sql = "select * from article order by post_date desc limit "
				+ startLocation + ", " + pageSize;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Article article = null;
			while (dbConnector.rs.next()) {
				article = new Article(dbConnector.rs.getInt("id"),
						dbConnector.rs.getString("title"), dbConnector.rs
								.getString("content"), dbConnector.rs
								.getTimestamp("post_date"), dbConnector.rs
								.getInt("category_id"), dbConnector.rs
								.getInt("comments_num"), dbConnector.rs
								.getInt("view_count"));
				articlesList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return articlesList;
	}

	public boolean updateArticle(Article article) {
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "update article set title=?, content=?, post_date=?, category_Id=?, comments_num=? where id = ?";
		try {
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setString(1, article.getTitle());
			dbConnector.pstmt.setString(2, article.getContent());
			dbConnector.pstmt.setTimestamp(3, new Timestamp(article
					.getPostDate().getTime()));
			dbConnector.pstmt.setInt(4, article.getCategoryId());
			dbConnector.pstmt.setInt(5, article.getCommentsNum());
			dbConnector.pstmt.setInt(6, article.getId());
			result = dbConnector.pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		if (result == 1)
			return true;
		else
			return false;
	}

	public int getTotalArticlesNum() {
		int totalArticlesNum = 0;
		DBConnector dbConnector = null;
		String sql = "select count(*) from article";
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				totalArticlesNum = dbConnector.rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return totalArticlesNum;
	}

	public Article getArticle(String title) {
		DBConnector dbConnector = null;
		String sql = "select * from article where title = '" + title + "'"
				+ " order by id desc";
		Article article = null;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				article = new Article(dbConnector.rs.getInt("id"),
						dbConnector.rs.getString("title"), dbConnector.rs
								.getString("content"), dbConnector.rs
								.getTimestamp("post_date"), dbConnector.rs
								.getInt("category_id"), dbConnector.rs
								.getInt("comments_num"), dbConnector.rs
								.getInt("view_count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return article;
	}
}
