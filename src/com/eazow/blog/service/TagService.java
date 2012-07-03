package com.eazow.blog.service;

import java.util.List;

import com.eazow.blog.entity.Tag;

public interface TagService {
	public int addTag(Tag tag);

	public List<Tag> getAllTags();

	public Tag getTag(int id);

	// 管理Tags
	public List<Tag> manageAllTags();

	// 删除Tag,同时删除Tag、Article对应关系
	public boolean deleteTag(int id);
}
