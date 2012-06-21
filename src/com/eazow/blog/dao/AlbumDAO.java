package com.eazow.blog.dao;

import java.util.List;

import com.eazow.blog.entity.Album;


public interface AlbumDAO
{
	public List<Album> getAllAlbums();
	public Album getAlbum(int id);
	public Album getAlbum(String name);
	public boolean deleteAlbum(int id);
	public boolean addAlbum(Album album);
	public boolean updateAlbum(Album album);
	
}
