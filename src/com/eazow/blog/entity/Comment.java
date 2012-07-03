package com.eazow.blog.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.eazow.blog.util.CommentUtil;

public class Comment {
	private int id;
	private String content;
	private int articleId;
	private Date commentDate;
	private String nickname;

	// ��������
	private Article article;

	public Comment(int id, String content, int articleId, Date commentDate,
			String nickname) {
		this.id = id;
		this.content = content;
		this.articleId = articleId;
		this.commentDate = commentDate;
		this.nickname = nickname;
	}

	public Comment(String content, int articleId, Date commentDate,
			String nickname) {
		this.content = content;
		this.articleId = articleId;
		this.commentDate = commentDate;
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCommentDateDisplay() {
		// 24Сʱ��yyyy-MM-dd HH:mm:ss 12Сʱ��yyyy-MM-dd hh:mm:ss
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(commentDate);
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getContentDisplay() {
		// ����commentContentһ�й���
		return CommentUtil.formatComment(content);
	}

	public String getContentDisplayInLatest10Comments() {
		// �����������б�����ʾ�����۸�ʽ
		return CommentUtil.formatCommentInLatest10Comment(content);
	}
}
