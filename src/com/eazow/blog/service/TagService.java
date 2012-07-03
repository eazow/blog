package com.eazow.blog.service;

import java.util.List;

import com.eazow.blog.entity.Tag;

public interface TagService {
	public int addTag(Tag tag);

	public List<Tag> getAllTags();

	public Tag getTag(int id);

	// ����Tags
	public List<Tag> manageAllTags();

	// ɾ��Tag,ͬʱɾ��Tag��Article��Ӧ��ϵ
	public boolean deleteTag(int id);
}
