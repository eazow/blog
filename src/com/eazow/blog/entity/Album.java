package com.eazow.blog.entity;

public class Album {
	private int id;
	private String name;
	private int imagesNum;
	private int coverImageId;

	// ∏®÷˙ Ù–‘
	private Image coverImage;

	public Album(String name, int imagesNum, int coverImageId) {
		this.name = name;
		this.imagesNum = imagesNum;
		this.coverImageId = coverImageId;
	}

	public Album(int id, String name, int imagesNum, int coverImageId) {
		this.id = id;
		this.name = name;
		this.imagesNum = imagesNum;
		this.coverImageId = coverImageId;
	}

	public Image getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(Image coverImage) {
		this.coverImage = coverImage;
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

	public int getImagesNum() {
		return imagesNum;
	}

	public void setImagesNum(int imagesNum) {
		this.imagesNum = imagesNum;
	}

	public int getCoverImageId() {
		return coverImageId;
	}

	public void setCoverImageId(int coverImageId) {
		this.coverImageId = coverImageId;
	}
}
