package com.eazow.blog.service.impl;

import java.io.File;
import java.util.List;

import com.eazow.blog.dao.AlbumDAO;
import com.eazow.blog.dao.ImageDAO;
import com.eazow.blog.entity.Album;
import com.eazow.blog.entity.Image;
import com.eazow.blog.service.ImageService;


public class ImageServiceImpl implements ImageService
{
	private ImageDAO imageDAO;
	private AlbumDAO albumDAO;
	private static ImageService imageService = null;
	private ImageServiceImpl(ImageDAO imageDAO, AlbumDAO albumDAO)
	{
		this.imageDAO = imageDAO;
		this.albumDAO = albumDAO;
	}
	public static ImageService getImageServiceInstance(ImageDAO imageDAO, AlbumDAO albumDAO)
	{
		if(null == imageService)
			imageService = new ImageServiceImpl(imageDAO, albumDAO);
		return imageService;
	}
	public AlbumDAO getAlbumDAO()
	{
		return albumDAO;
	}
	public void setAlbumDAO(AlbumDAO albumDAO)
	{
		this.albumDAO = albumDAO;
	}
	public ImageDAO getImageDAO()
	{
		return imageDAO;
	}
	public void setImageDAO(ImageDAO imageDAO)
	{
		this.imageDAO = imageDAO;
	}
	public List<Image> getLatestImages()
	{
		return this.imageDAO.getLatestImages();
	}
	
	public List<Image> getImagesOfAlbum(int albumId)
	{
		return this.imageDAO.getImagesOfAlbum(albumId);
	}
	public Image getImageOfAlbum(int id)
	{
		Image image = this.imageDAO.getImage(id);
		if(null != image)
		{
			Album album = this.albumDAO.getAlbum(image.getAlbumId());
			image.setAlbum(album);
		}
		int indexLocationOfAlbum = this.imageDAO.getIndexLocationOfAlbum(id);
		image.setIndexLocationOfAlbum(indexLocationOfAlbum);
		return image;
	}
	
	public Image getNextImageOfAlbum(int imageId)
	{
		Image image = this.imageDAO.getNextImageOfAlbum(imageId);
		if(null != image)
		{
			Album album = this.albumDAO.getAlbum(image.getAlbumId());
			image.setAlbum(album);
		}
		int indexLocationOfAlbum = this.imageDAO.getIndexLocationOfAlbum(image.getId());
		image.setIndexLocationOfAlbum(indexLocationOfAlbum);
		return image;
	}
	
	public Image getPreviousImageOfAlbum(int imageId)
	{
		Image image = this.imageDAO.getPreviousImageOfAlbum(imageId);
		if(null != image)
		{
			Album album = this.albumDAO.getAlbum(image.getAlbumId());
			image.setAlbum(album);
		}
		int indexLocationOfAlbum = this.imageDAO.getIndexLocationOfAlbum(image.getId());
		image.setIndexLocationOfAlbum(indexLocationOfAlbum);
		return image;
	}
	
	public void deleteImage(int id, String realRootPath)
	{
		Image image = imageDAO.getImage(id);
		Album album = albumDAO.getAlbum(image.getAlbumId());
		//减少相册包含图片数量
		album.setImagesNum(album.getImagesNum()-1);
		this.albumDAO.updateAlbum(album);
		
		this.imageDAO.deleteImage(id);
		
		File imageFile = new File(realRootPath + image.getUrl());
		imageFile.delete();
		File thumbnailFile = new File(realRootPath + image.getLowResolutionUrl());
		thumbnailFile.delete();
		
	}
	
	/**
	 * 加入图片
	 * @param image 图片
	 */
	public void addImage(Image image)
	{
		int imageId = this.imageDAO.addImage(image);
		Album album = this.albumDAO.getAlbum(image.getAlbumId());
		album.setImagesNum(album.getImagesNum()+1);
		//若还没设置相册封面图片
		if(album.getCoverImageId() <= 0)
		{
			album.setCoverImageId(imageId);
		}
		this.albumDAO.updateAlbum(album);
	}
}
