package com.eazow.blog.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.eazow.blog.dao.MessageDAO;
import com.eazow.blog.dao.connector.DBConnector;
import com.eazow.blog.entity.Message;

public class MessageDAOImpl implements MessageDAO {
	private static MessageDAO messageDAO;

	private MessageDAOImpl() {
	}

	public static MessageDAO getMessageDAOInstance() {
		if (null == messageDAO)
			messageDAO = new MessageDAOImpl();
		return messageDAO;
	}

	// 实现方法
	public boolean addMessage(Message message) {
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "insert into message(nickname, content, date) values(?, ?, ?)";
		try {
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setString(1, message.getNickname());
			dbConnector.pstmt.setString(2, message.getContent());
			dbConnector.pstmt.setTimestamp(3, new Timestamp(message.getDate()
					.getTime()));
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

	public List<Message> getAllMessages() {
		List<Message> messagesList = new ArrayList<Message>();
		DBConnector dbConnector = null;
		String sql = "select * from message order by id desc";
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			Message message = null;
			while (dbConnector.rs.next()) {
				message = new Message(dbConnector.rs.getInt("id"),
						dbConnector.rs.getString("nickname"), dbConnector.rs
								.getString("content"), dbConnector.rs
								.getTimestamp("date"));
				messagesList.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return messagesList;
	}

	public Message getMessage(int id) {
		Message message = null;
		DBConnector dbConnector = null;
		String sql = "select * from message where id = " + id;
		try {
			dbConnector = new DBConnector();
			dbConnector.stmt = dbConnector.conn.createStatement();
			dbConnector.rs = dbConnector.stmt.executeQuery(sql);
			while (dbConnector.rs.next()) {
				message = new Message(dbConnector.rs.getInt("id"),
						dbConnector.rs.getString("nickname"), dbConnector.rs
								.getString("content"), dbConnector.rs
								.getTimestamp("date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		return message;
	}

	public boolean deleteMessage(int id) {
		DBConnector dbConnector = null;
		int result = 0;
		String sql = "delete from message where id = " + id;
		try {
			dbConnector = new DBConnector();
			result = dbConnector.conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnector.closeConnection();
		}
		if (0 == result)
			return false;
		else
			return true;
	}

	public boolean updateMessage(Message message) {
		int result = 0;
		DBConnector dbConnector = null;
		String sql = "update message set nickname=?, content=?, date=? where id = ?";
		try {
			dbConnector = new DBConnector();
			dbConnector.pstmt = dbConnector.conn.prepareStatement(sql);
			dbConnector.pstmt.setString(1, message.getNickname());
			dbConnector.pstmt.setString(2, message.getContent());
			dbConnector.pstmt.setTimestamp(3, new Timestamp(message.getDate()
					.getTime()));
			dbConnector.pstmt.setInt(4, message.getId());
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
