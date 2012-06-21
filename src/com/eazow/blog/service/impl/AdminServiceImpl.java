package com.eazow.blog.service.impl;

import com.eazow.blog.dao.AdminDAO;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.service.AdminService;


public class AdminServiceImpl implements AdminService
{
	private static AdminService adminService = null;
	private AdminDAO adminDAO;
	private AdminServiceImpl(AdminDAO adminDAO)
	{
		this.adminDAO = adminDAO;
	}
	public static AdminService getAdminServiceInstance(AdminDAO adminDAO)
	{
		if(null == adminService)
			adminService = new AdminServiceImpl(adminDAO);
		return adminService;
	}
	public AdminDAO getAdminDAO()
	{
		return adminDAO;
	}
	public void setAdminDAO(AdminDAO adminDAO)
	{
		this.adminDAO = adminDAO;
	}
	
	//具体方法
	public Admin getAdmin(int id)
	{
		return this.adminDAO.getAdmin(id);
	}

	public Admin getAdmin(String username)
	{
		return this.adminDAO.getAdmin(username);
	}
	
	public Admin getAdmin(String username, String password)
	{
		return this.adminDAO.getAdmin(username, password);
	}

}
