package com.eazow.blog.service.impl;

import java.util.List;

import com.eazow.blog.dao.ArticleDAO;
import com.eazow.blog.dao.CommentDAO;
import com.eazow.blog.entity.Article;
import com.eazow.blog.entity.Comment;
import com.eazow.blog.service.CommentService;

public class CommentServiceImpl implements CommentService {
	private CommentDAO commentDAO;
	private ArticleDAO articleDAO;

	private static CommentService commentService = null;

	private CommentServiceImpl(CommentDAO commentDAO, ArticleDAO articleDAO) {
		this.commentDAO = commentDAO;
		this.articleDAO = articleDAO;
	}

	public static CommentService getCommentServiceInstance(
			CommentDAO commentDAO, ArticleDAO articleDAO) {
		if (null == commentService)
			commentService = new CommentServiceImpl(commentDAO, articleDAO);
		return commentService;
	}

	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public ArticleDAO getArticleDAO() {
		return articleDAO;
	}

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	// 具体方法
	// 增加评论,同时增加文章对应评论数量
	public void addComment(Comment comment) {
		commentDAO.addComment(comment);
		articleDAO.addCommentsNumOfArticle(comment.getArticleId());
	}

	// 删除评论,同时减少文章对应评论数量
	public boolean deleteComment(int id) {
		Comment comment = commentDAO.getComment(id);
		boolean result = commentDAO.deleteComment(id);
		// 删除评论成功
		if (result) {
			articleDAO.reduceCommentsNumOfArticle(comment.getArticleId());
		}
		return result;
	}

	public Comment getComment(int id) {
		return commentDAO.getComment(id);
	}

	// 取得最新10条Comment,同时取得所对应的Article
	public List<Comment> getLatest10Comments() {
		List<Comment> latest10Comments = this.commentDAO.getLatest10Comments();
		Article article = null;
		for (Comment comment : latest10Comments) {
			article = articleDAO.getArticle(comment.getArticleId());
			comment.setArticle(article);
		}
		return latest10Comments;
	}

	// 获得Comment同时获得所对应的Article
	public List<Comment> getAllComments() {
		List<Comment> commentsList = this.commentDAO.getAllComments();
		Article article = null;
		for (Comment comment : commentsList) {
			article = this.articleDAO.getArticle(comment.getArticleId());
			comment.setArticle(article);
		}
		return commentsList;
	}

	public int getTotalCommentsNum() {
		return this.commentDAO.getTotalCommentsNum();
	}

	public Comment manageComment(int id) {
		Comment comment = this.commentDAO.getComment(id);
		Article article = this.articleDAO.getArticle(comment.getArticleId());
		comment.setArticle(article);
		return comment;
	}

	public boolean updateComment(Comment comment) {
		return this.commentDAO.updateComment(comment);
	}
}
