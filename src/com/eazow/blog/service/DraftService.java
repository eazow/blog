package com.eazow.blog.service;

import java.util.List;

import com.eazow.blog.entity.Draft;


public interface DraftService
{
	public boolean addDraft(Draft draft);
	public List<Draft> getAllDrafts();
	public int getAllDraftsNum();
	public Draft getDraft(int id);
	public boolean deleteDraft(int id);
}
