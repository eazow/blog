package com.eazow.blog.service;

import com.eazow.blog.entity.Admin;

public interface AdminService {
	public Admin getAdmin(int id);

	public Admin getAdmin(String username);

	public Admin getAdmin(String username, String password);
}
