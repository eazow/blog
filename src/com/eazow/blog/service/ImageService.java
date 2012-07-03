package com.eazow.blog.service;

import java.util.List;

import com.eazow.blog.entity.Image;

public interface ImageService {
	public List<Image> getLatestImages();

	public List<Image> getImagesOfAlbum(int albumId);

	public Image getImageOfAlbum(int id);

	public Image getNextImageOfAlbum(int imageId);

	public Image getPreviousImageOfAlbum(int imageId);

	/**
	 * @param realRootPath
	 *            项目真实根路径
	 */
	public void deleteImage(int id, String realRootPath);

	/**
	 * 加入图片
	 * 
	 * @param image
	 *            图片
	 */
	public void addImage(Image image);
}
