package com.eazow.blog.dao;

import java.util.List;

import com.eazow.blog.entity.Motto;


public interface MottoDAO
{
	public List<Motto> getAllMottos();
	public boolean addMotto(Motto motto);
	public Motto getMotto(int id);
	public Motto getMotto(String content);
	public Motto getRandomMotto();
}
