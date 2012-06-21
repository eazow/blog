package com.eazow.blog.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.entity.Message;
import com.eazow.blog.service.MessageService;


@SuppressWarnings("serial")
public class DeleteMessageAdminServlet extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		if(null == admin)
		{
			request.setAttribute("usernameErrorMessage", "ÇëµÇÂ¼");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		String messageIdStr = request.getParameter("messageId");
		if(null==messageIdStr)
		{
			response.sendError(400, "Id Is Null");
			return;
		}
		int messageId = 0;
		try
		{
			messageId = Integer.parseInt(messageIdStr);
		}
		catch(NumberFormatException e)
		{
			response.sendError(401, "Number Format Exception");
			return;
		}
		
		MessageService messageService = DAOFactory.getMessageServiceInstance();
		Message message = messageService.getMessage(messageId);
		if(null == message)
		{
			response.sendError(402, "Not Exist");
			return;
		}
		messageService.deleteMessage(messageId);
		
		request.getRequestDispatcher("manageMessagesAdminServlet").forward(request, response);
	}

}
