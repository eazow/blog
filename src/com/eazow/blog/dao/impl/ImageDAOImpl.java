package com.eazow.blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eazow.blog.dao.ImageDAO;
import com.eazow.blog.dao.connector.DBConnector;
import com.eazow.blog.entity.Image;

public class ImageDAOImpl implements ImageDAO {
	private static ImageDAO imageDAO = null;

	private ImageDAOImpl() {
	}

	public static ImageDAO getImageDAOInstance() {
		if (null == imageDAO)
			imageDAO = new ImageDAOImpl();
		return imageDAO;
	}

	public List<Image> getLatestImages() {
		List<Image> imagesList = new ArrayList<Image>();
		DBConnector dbConnector = null;
		String sql = "select * from image order by id desc";
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Image image = null;
			while (dbConnector.rs.next()) {
				image = new Image(dbConnector.rs.getInt("id"), dbConnector.rs
						.getString("name"), dbConnector.rs.getString("url"),
						dbConnector.rs.getString("low_resolution_url"),
						dbConnector.rs.getTimestamp("upload_date"),
						dbConnector.rs.getInt("album_id"));
				imagesList.add(image);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbConnector.closeConnection();
		return imagesList;
	}

	public Image getImage(int id) {
		Image image = null;
		DBConnector dbConnector = null;
		String sql = "select * from image where id = " + id;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				image = new Image(dbConnector.rs.getInt("id"), dbConnector.rs
						.getString("name"), dbConnector.rs.getString("url"),
						dbConnector.rs.getString("low_resolution_url"),
						dbConnector.rs.getTimestamp("upload_date"),
						dbConnector.rs.getInt("album_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbConnector.closeConnection();
		return image;
	}

	public List<Image> getImagesOfAlbum(int albumId) {
		List<Image> imagesList = new ArrayList<Image>();
		DBConnector dbConnector = null;
		String sql = "select * from image where album_id = " + albumId
				+ " order by id asc";
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Image image = null;
			while (dbConnector.rs.next()) {
				image = new Image(dbConnector.rs.getInt("id"), dbConnector.rs
						.getString("name"), dbConnector.rs.getString("url"),
						dbConnector.rs.getString("low_resolution_url"),
						dbConnector.rs.getTimestamp("upload_date"),
						dbConnector.rs.getInt("album_id"));
				imagesList.add(image);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbConnector.closeConnection();
		return imagesList;
	}

	public int getIndexLocationOfAlbum(int imageId) {
		Image image = getImage(imageId);
		String sql = "select count(*) from image where id >= " + imageId
				+ " and album_Id = " + image.getAlbumId() + " order by id desc";
		DBConnector dbConnector = null;
		int index = 0;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				index = dbConnector.rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return index;
	}

	public Image getNextImageOfAlbum(int imageId) {
		Image image = null;
		Image imageTemp = getImage(imageId);
		DBConnector dbConnector = null;
		String sql = "select * from image where id < " + imageId
				+ " and album_id = " + imageTemp.getAlbumId()
				+ " order by id desc";
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				image = new Image(dbConnector.rs.getInt("id"), dbConnector.rs
						.getString("name"), dbConnector.rs.getString("url"),
						dbConnector.rs.getString("low_resolution_url"),
						dbConnector.rs.getTimestamp("upload_date"),
						dbConnector.rs.getInt("album_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return image;
	}

	public Image getPreviousImageOfAlbum(int imageId) {
		Image image = null;
		Image imageTemp = getImage(imageId);
		DBConnector dbConnector = null;
		String sql = "select * from image where id > " + imageId
				+ " and album_id = " + imageTemp.getAlbumId()
				+ " order by id asc";
		System.out.println(sql);
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				image = new Image(dbConnector.rs.getInt("id"), dbConnector.rs
						.getString("name"), dbConnector.rs.getString("url"),
						dbConnector.rs.getString("low_resolution_url"),
						dbConnector.rs.getTimestamp("upload_date"),
						dbConnector.rs.getInt("album_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return image;
	}

	public boolean deleteImage(int id) {
		Image image = this.getImage(id);

		// 删除硬盘上的image文件

		DBConnector dbConnector = null;
		int result = 0;
		boolean flag = true;
		try {
			dbConnector = new DBConnector();
			String sql = "delete from image where id = " + id;
			dbConnector.stmt = dbConnector.conn.createStatement();
			result = dbConnector.stmt.executeUpdate(sql);
			if (result == 0) {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return flag;
	}

	/**
	 * 加入图片
	 * 
	 * @param image
	 *            图片
	 */
	public int addImage(Image image) {
		int generatedKey = 0;
		DBConnector dbConnector = null;
		String sql = "insert into image(name, url, low_resolution_url, upload_date, album_id) values(?, ?, ?, now(), ?)";
		try {
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setString(1, image.getName());
			dbConnector.pstmt.setString(2, image.getUrl());
			dbConnector.pstmt.setString(3, image.getLowResolutionUrl());
			dbConnector.pstmt.setInt(4, image.getAlbumId());
			dbConnector.pstmt.executeUpdate();
			ResultSet rs = dbConnector.pstmt.getGeneratedKeys();
			rs.next();
			generatedKey = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return generatedKey;
	}
}
