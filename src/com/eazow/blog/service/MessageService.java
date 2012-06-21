package com.eazow.blog.service;

import java.util.List;

import com.eazow.blog.entity.Message;


public interface MessageService
{
	public boolean addMessage(Message message);
	public List<Message> getAllMessages();
	public Message getMessage(int id);
	public boolean deleteMessage(int id);
	public boolean updateMessage(Message message);
}
