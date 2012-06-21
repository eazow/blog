package com.eazow.blog.service;

import java.util.List;

import com.eazow.blog.entity.Motto;


public interface MottoService
{
	public List<Motto> getAllMottos();
	public boolean addMotto(Motto motto);
	public Motto getRandomMotto();
}
