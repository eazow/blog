package com.eazow.blog.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eazow.blog.dao.AlbumDAO;
import com.eazow.blog.dao.connector.DBConnector;
import com.eazow.blog.entity.Album;

public class AlbumDAOImpl implements AlbumDAO {
	private static AlbumDAO albumDAO = null;

	private AlbumDAOImpl() {
	}

	public static AlbumDAO getAlbumDAOInstance() {
		if (null == albumDAO)
			albumDAO = new AlbumDAOImpl();
		return albumDAO;
	}

	public List<Album> getAllAlbums() {
		List<Album> albumsList = new ArrayList<Album>();
		DBConnector dbConnector = null;
		String sql = "select * from album order by id desc";
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Album album = null;
			while (dbConnector.rs.next()) {
				album = new Album(dbConnector.rs.getInt("id"), dbConnector.rs
						.getString("name"),
						dbConnector.rs.getInt("images_Num"), dbConnector.rs
								.getInt("cover_image_id"));
				albumsList.add(album);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return albumsList;
	}

	public Album getAlbum(int id) {
		DBConnector dbConnector = null;
		String sql = "select * from album where id = " + id;
		Album album = null;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				album = new Album(dbConnector.rs.getInt("id"), dbConnector.rs
						.getString("name"),
						dbConnector.rs.getInt("images_Num"), dbConnector.rs
								.getInt("cover_image_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return album;
	}

	public Album getAlbum(String name) {
		DBConnector dbConnector = null;
		String sql = "select * from album where name = '" + name + "'";
		Album album = null;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			if (dbConnector.rs.next()) {
				album = new Album(dbConnector.rs.getInt("id"), dbConnector.rs
						.getString("name"),
						dbConnector.rs.getInt("images_Num"), dbConnector.rs
								.getInt("cover_image_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return album;
	}

	public boolean deleteAlbum(int id) {
		DBConnector dbConnector = null;
		int result = 0;
		boolean flag = true;
		try {
			dbConnector = new DBConnector();
			String sql = "delete from album where id = " + id;
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

	public boolean addAlbum(Album album) {
		Album albumTemp = this.getAlbum(album.getName());
		// ∏√album“—¥Ê‘⁄
		if (null != albumTemp)
			return false;
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "insert into album(name, images_num, cover_image_id) values(?, ?, ?)";
		try {
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setString(1, album.getName());
			dbConnector.pstmt.setInt(2, 0);
			dbConnector.pstmt.setInt(3, 0);
			result = dbConnector.pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		if (result == 1)
			return true;
		else
			return false;
	}

	public boolean updateAlbum(Album album) {
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "update album set name=?, images_num=?, cover_image_id=? where id = ?";
		try {
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setString(1, album.getName());
			dbConnector.pstmt.setInt(2, album.getImagesNum());
			dbConnector.pstmt.setInt(3, album.getCoverImageId());
			dbConnector.pstmt.setInt(4, album.getId());
			result = dbConnector.pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		if (result == 1)
			return true;
		else
			return false;
	}

}
