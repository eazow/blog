package com.eazow.blog.dao;

import java.util.List;

import com.eazow.blog.entity.Tag;


public interface TagDAO
{
	//0表示插入失败,返回插入后记录id
	public int addTag(Tag tag);
	public Tag getTag(String name);
	public List<Tag> getAllTags();
	public Tag getTag(int id);
	public boolean deleteTag(int id);
}
