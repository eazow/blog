package com.eazow.blog.test;

import com.eazow.blog.dao.TagDAO;
import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Tag;


public class TagDAOTest
{
	public static void main(String[] args)
	{
		Tag tag = new Tag("aaaaaaa");
		TagDAO tagDAO = DAOFactory.getTagDAOInstance();
		int newId = tagDAO.addTag(tag);
		System.out.println(newId);
	}
}
