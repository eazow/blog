package com.eazow.blog.dao;

import java.util.List;

import com.eazow.blog.entity.Image;

public interface ImageDAO {
	public List<Image> getLatestImages();

	public Image getImage(int id);

	public List<Image> getImagesOfAlbum(int albumId);

	public int getIndexLocationOfAlbum(int imageId);

	public Image getNextImageOfAlbum(int imageId);

	public Image getPreviousImageOfAlbum(int imageId);

	public boolean deleteImage(int id);

	/**
	 * 加入图片
	 * 
	 * @param image
	 *            图片
	 * @return 返回生成的id
	 */
	public int addImage(Image image);
}
