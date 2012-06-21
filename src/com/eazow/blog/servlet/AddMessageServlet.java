package com.eazow.blog.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Message;
import com.eazow.blog.service.MessageService;
import com.eazow.blog.util.HtmlUtil;


@SuppressWarnings("serial")
public class AddMessageServlet extends HttpServlet
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
		request.setCharacterEncoding("utf8");
		
		String nickname = request.getParameter("nickname");
		String content = request.getParameter("content");
		String verificationCodeInput = request.getParameter("verificationCodeInput");
		
		if(null==verificationCodeInput || "".equals(verificationCodeInput))
		{
			request.setAttribute("errorMessage", "请输入验证码");
			request.getRequestDispatcher("getProfileServlet").forward(request, response);
			return;
		}
		if(null == nickname || "".equals(nickname))
		{
			request.setAttribute("errorMessage", "请输入昵称");
			request.getRequestDispatcher("getProfileServlet").forward(request, response);
			return;
		}
		if(null==content || "".equals(content))
		{
			request.setAttribute("errorMessage", "请输入评论");
			request.getRequestDispatcher("getProfileServlet").forward(request, response);
			return;
		}
		if(nickname.length() > 6)
		{
			request.setAttribute("errorMessage", "请不要输入过长昵称");
			request.setAttribute("content", content);
			request.getRequestDispatcher("getProfileServlet").forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		String verificationCode = (String)session.getAttribute("verificationCode"); 
		if(verificationCodeInput.equalsIgnoreCase(verificationCode))
		{
			nickname = HtmlUtil.filtrateHtmlCode(nickname);
			content = HtmlUtil.filtrateHtmlCode(content);
			Message message = new Message(nickname, content, new Date());
			MessageService messageService = DAOFactory.getMessageServiceInstance();
			messageService.addMessage(message);
			response.sendRedirect("getProfileServlet");
		}
		else
		{
			request.setAttribute("errorMessage", "请输入正确的验证码");
			request.setAttribute("content", content);
			request.setAttribute("nickname", nickname);
			request.getRequestDispatcher("getProfileServlet").forward(request, response);
			return;
		}
	}

}
