package com.eazow.blog.dao;

import java.util.List;

import com.eazow.blog.entity.Comment;

public interface CommentDAO
{
	public void addComment(Comment comment);
	public List<Comment> getCommentsOfArticle(int articleId);
	//trueÉ¾³ý³É¹¦,falseÉ¾³ýÊ§°Ü
	public boolean deleteComment(int id);
	public Comment getComment(int id);
	public List<Comment> getLatest10Comments();
	public List<Comment> getAllComments();
	public int getTotalCommentsNum();
	public boolean updateComment(Comment comment);
}
