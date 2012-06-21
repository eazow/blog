package com.eazow.blog.service;

import java.util.List;

import com.eazow.blog.entity.Comment;


public interface CommentService
{
	public void addComment(Comment comment);
	public boolean deleteComment(int id);
	public Comment getComment(int id);
	public List<Comment> getLatest10Comments();
	public List<Comment> getAllComments();
	public int getTotalCommentsNum();
	
	//��������,ͬʱ��ö�Ӧ������
	public Comment manageComment(int id);
	public boolean updateComment(Comment comment);
}
