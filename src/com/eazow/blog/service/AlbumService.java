package com.eazow.blog.service;

import java.util.List;

import com.eazow.blog.entity.Album;

public interface AlbumService {
	public List<Album> getAllAlbums();

	public Album getAlbum(int id);

	public boolean deleteAlbum(int id);

	public boolean addAlbum(Album album);

}
