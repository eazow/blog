package com.eazow.blog.service.impl;

import java.util.List;

import com.eazow.blog.dao.CategoryDAO;
import com.eazow.blog.dao.DraftDAO;
import com.eazow.blog.entity.Category;
import com.eazow.blog.entity.Draft;
import com.eazow.blog.service.DraftService;


public class DraftServiceImpl implements DraftService
{
	private DraftDAO draftDAO;
	private CategoryDAO categoryDAO;
	
	private static DraftService draftService = null;
	
	private DraftServiceImpl(DraftDAO draftDAO, CategoryDAO categoryDAO)
	{
		this.draftDAO = draftDAO;
		this.categoryDAO = categoryDAO;
	}
	
	public static DraftService getDraftServiceInstance(DraftDAO draftDAO, CategoryDAO categoryDAO)
	{
		if(null == draftService)
			draftService = new DraftServiceImpl(draftDAO, categoryDAO);
		return draftService;
	}
	
	public boolean addDraft(Draft draft)
	{
		return this.draftDAO.addDraft(draft);
	}
	
	public List<Draft> getAllDrafts()
	{
		List<Draft> draftsList = this.draftDAO.getAllDrafts();
		Category category = null;
		for(Draft draft: draftsList)
		{
			int categoryId = draft.getCategoryId();
			category = categoryDAO.getCategory(categoryId);
			draft.setCategory(category);
		}
		return draftsList;
	}
	
	public int getAllDraftsNum()
	{
		return this.draftDAO.getAllDraftsNum();
	}
	
	public Draft getDraft(int id)
	{
		return this.draftDAO.getDraft(id);
	}
	
	public boolean deleteDraft(int id)
	{
		return this.draftDAO.deleteDraft(id);
	}

}
