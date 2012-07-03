package com.eazow.blog.dao;

import java.util.List;

import com.eazow.blog.entity.Message;

public interface MessageDAO {
	public boolean addMessage(Message message);

	public List<Message> getAllMessages();

	public Message getMessage(int id);

	public boolean deleteMessage(int id);

	public boolean updateMessage(Message message);
}
