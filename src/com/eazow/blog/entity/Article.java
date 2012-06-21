package com.eazow.blog.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Article
{
	//����Ӧ����
	private int id;
	private String title;
	private String content;
	private Date postDate;
	private int categoryId;
	private int commentsNum;
	private int viewCount;
	
	//������Ӧ����,���㴦��
	private Category category;
	private List<Comment> comments;
	private List<Tag> tags;
	
	//����Article����,�����е�����δ��ʼ��
	public Article(String title, String content, Date postDate, int categoryId, int commentsNum, int viewCount)
	{
		this.title = title;
		this.content = content;
		this.postDate = postDate;
		this.categoryId = categoryId;
		this.commentsNum = commentsNum;
		this.viewCount = viewCount;
	}
	public Article(int id, String title, String content, Date postDate, int categoryId, int commentsNum, int viewCount)
	{
		this.id = id;
		this.title = title;
		this.content = content;
		this.postDate = postDate;
		this.categoryId = categoryId;
		this.commentsNum = commentsNum;
		this.viewCount = viewCount;
	}
	
	public int getViewCount()
	{
		return viewCount;
	}
	public void setViewCount(int viewCount)
	{
		this.viewCount = viewCount;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public String getContent() 
	{
		return content;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}
	public Date getPostDate()
	{
		return postDate;
	}
	public void setPostDate(Date postDate)
	{
		this.postDate = postDate;
	}
	public int getCategoryId()
	{
		return categoryId;
	}
	public void setCategoryId(int categoryId) 
	{
		this.categoryId = categoryId;
	}
	public int getCommentsNum()
	{
		return commentsNum;
	}
	public void setCommentsNum(int commentsNum)
	{
		this.commentsNum = commentsNum;
	}

	//������Ӧ���Է���
	public Category getCategory()
	{
		return category;
	}
	public void setCategory(Category category)
	{
		this.category = category;
	}
	public String getPostDateDisplay()
	{
		//24Сʱ��
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(postDate);
	}
	public List<Comment> getComments()
	{
		return comments;
	}
	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}
	//��ҳ���ݵ�ժҪ��ʾ
	public boolean hasAbstractOfContent()
	{
		int endIndex = content.indexOf("<!--More-->");
		//����<!--More-->
		if(-1 != endIndex)
		{
			return true;
		}
		return false;
	}
	public String getAbstractOfContent()
	{
		int endIndex = content.indexOf("<!--More-->");
		if(-1 != endIndex)
		{
			String abstractOfContent = content.substring(0, endIndex);
			return abstractOfContent;
		}
		return content;
	}
	public List<Tag> getTags()
	{
		return tags;
	}
	public void setTags(List<Tag> tags)
	{
		this.tags = tags;
	}
	
}
