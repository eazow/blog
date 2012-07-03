package com.eazow.blog.service.impl;

import java.util.List;

import com.eazow.blog.dao.MessageDAO;
import com.eazow.blog.entity.Message;
import com.eazow.blog.service.MessageService;

public class MessageServiceImpl implements MessageService {
	private MessageDAO messageDAO;
	private static MessageService messageService;

	private MessageServiceImpl(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

	public static MessageService getMessageServiceImpl(MessageDAO messageDAO) {
		if (null == messageService)
			messageService = new MessageServiceImpl(messageDAO);
		return messageService;
	}

	// 实现方法
	public boolean addMessage(Message message) {
		return this.messageDAO.addMessage(message);
	}

	public List<Message> getAllMessages() {
		return this.messageDAO.getAllMessages();
	}

	public Message getMessage(int id) {
		return this.messageDAO.getMessage(id);
	}

	public boolean deleteMessage(int id) {
		return this.messageDAO.deleteMessage(id);
	}

	public boolean updateMessage(Message message) {
		return this.messageDAO.updateMessage(message);
	}
}
