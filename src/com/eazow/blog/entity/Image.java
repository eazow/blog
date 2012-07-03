package com.eazow.blog.entity;

import java.util.Date;

public class Image {
	private int id;
	private String name;
	private String url;
	private String lowResolutionUrl;
	private Date uploadDate;
	private int albumId;

	// 辅助属性
	private Album album;
	private int indexLocationOfAlbum;

	public Image(int id, String name, String url, String lowResolutionUrl,
			Date uploadDate, int albumId) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.lowResolutionUrl = lowResolutionUrl;
		this.uploadDate = uploadDate;
		this.albumId = albumId;
	}

	/**
	 * 新加图片时的构造函数
	 * 
	 * @param name
	 * @param url
	 * @param lowResolutionUrl
	 * @param albumId
	 */
	public Image(String name, String url, String lowResolutionUrl, int albumId) {
		this.name = name;
		this.url = url;
		this.lowResolutionUrl = lowResolutionUrl;
		this.albumId = albumId;
	}

	public int getIndexLocationOfAlbum() {
		return indexLocationOfAlbum;
	}

	public void setIndexLocationOfAlbum(int indexLocationOfAlbum) {
		this.indexLocationOfAlbum = indexLocationOfAlbum;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getLowResolutionUrl() {
		return lowResolutionUrl;
	}
}
