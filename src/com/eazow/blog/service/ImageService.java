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
	 *            ��Ŀ��ʵ��·��
	 */
	public void deleteImage(int id, String realRootPath);

	/**
	 * ����ͼƬ
	 * 
	 * @param image
	 *            ͼƬ
	 */
	public void addImage(Image image);
}
