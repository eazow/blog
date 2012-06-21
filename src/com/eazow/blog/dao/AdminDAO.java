package com.eazow.blog.dao;

import com.eazow.blog.entity.Admin;

public interface AdminDAO {
	public Admin getAdmin(int id);
	public Admin getAdmin(String username);
	public Admin getAdmin(String username, String password);
}
