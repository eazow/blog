package com.eazow.blog.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Message;
import com.eazow.blog.service.MessageService;
import com.eazow.blog.service.VisitRecordService;


@SuppressWarnings("serial")
public class GetProfileServlet extends HttpServlet
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
		VisitRecordService visitRecordService = DAOFactory.getVisitRecordServiceInstance();
		int totalPageView = visitRecordService.getTotalPageView();
		int todayPageView = visitRecordService.getTodayPageView(new Date());
		
		request.setAttribute("totalPageView", totalPageView);
		request.setAttribute("todayPageView", todayPageView);
		
		//Message
		MessageService messageService = DAOFactory.getMessageServiceInstance();
		List<Message> messagesList = messageService.getAllMessages();
		request.setAttribute("messagesList", messagesList);
		
		request.getRequestDispatcher("profile.jsp").forward(request, response);
	}

}
