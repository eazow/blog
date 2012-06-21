package com.eazow.blog.service.impl;

import java.util.Date;
import java.util.List;

import com.eazow.blog.dao.AlbumDAO;
import com.eazow.blog.dao.ImageDAO;
import com.eazow.blog.entity.Album;
import com.eazow.blog.entity.Image;
import com.eazow.blog.service.AlbumService;


public class AlbumServiceImpl implements AlbumService
{
	private static AlbumService albumService = null;
	
	private AlbumDAO albumDAO;
	private ImageDAO imageDAO;
	
	private AlbumServiceImpl(AlbumDAO albumDAO, ImageDAO imageDAO)
	{
		this.albumDAO = albumDAO;
		this.imageDAO = imageDAO;
	}
	
	public static AlbumService getAlbumServiceInstance(AlbumDAO albumDAO, ImageDAO imageDAO)
	{
		if(null == albumService)
			albumService = new AlbumServiceImpl(albumDAO, imageDAO);
		return albumService;
	}
	public ImageDAO getImageDAO()
	{
		return imageDAO;
	}
	public void setImageDAO(ImageDAO imageDAO)
	{
		this.imageDAO = imageDAO;
	}
	public AlbumDAO getAlbumDAO()
	{
		return albumDAO;
	}
	public void setAlbumDAO(AlbumDAO albumDAO)
	{
		this.albumDAO = albumDAO;
	}
	public List<Album> getAllAlbums()
	{
		List<Album> albumsList = this.albumDAO.getAllAlbums();
		Image image = null;
		for(Album album: albumsList)
		{
			image = this.imageDAO.getImage(album.getCoverImageId());
			//如果没有封面图片，则采用默认图片
			if(null == image)
			{
				image = new Image(0, "cover.gif", "img/cover.gif", "img/cover.gif", new Date(), 0);
			}
			album.setCoverImage(image);
		}
		
		return albumsList;
	}
	
	public Album getAlbum(int id)
	{
		return this.albumDAO.getAlbum(id);
	}
	
	public boolean deleteAlbum(int id)
	{
		//删除相册
		boolean flag = this.albumDAO.deleteAlbum(id);
		
		//删除相册包含的图片
		List<Image> imagesList = this.imageDAO.getImagesOfAlbum(id);
		if(null == imagesList)
			return flag;
		for(Image image: imagesList)
		{
			imageDAO.deleteImage(image.getId());
		}
		return flag;
	}

	public boolean addAlbum(Album album)
	{
		return this.albumDAO.addAlbum(album);
	}
	
}
