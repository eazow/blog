package com.eazow.blog.servlet.admin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.entity.Comment;
import com.eazow.blog.service.CommentService;
import com.eazow.blog.util.DateUtil;


@SuppressWarnings("serial")
public class UpdateCommentAdminServlet extends HttpServlet
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
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		if(null == admin)
		{
			request.setAttribute("usernameErrorMessage", "ÇëµÇÂ¼");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		String idStr = request.getParameter("id");
		String articleIdStr = request.getParameter("articleId");
		String nickname = request.getParameter("nickname");
		String content = request.getParameter("content");
		String commentDateStr = request.getParameter("commentDate");
		if(null==idStr || "".equals(idStr))
		{
			response.sendError(400, "Input Violation");
			return;
		}
		if(null==articleIdStr || "".equals(articleIdStr))
		{
			response.sendError(400, "Input Violation");
			return;
		}
		if(null==nickname || "".equals(nickname))
		{
			response.sendError(400, "Input Violation");
			return;
		}
		if(null==content || "".equals(content))
		{
			response.sendError(400, "Input Violation");
			return;
		}
		if(null==commentDateStr || "".equals(commentDateStr))
		{
			response.sendError(400, "Input Violation");
			return;
		}
		int id = 0;
		int articleId = 0;
		try
		{
			id = Integer.parseInt(idStr);
			articleId = Integer.parseInt(articleIdStr);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			response.sendError(400, "Input Violation");
			return;
		}
		Date commentDate = DateUtil.parseStringToDate(commentDateStr);
		Comment comment = new Comment(id, content, articleId, commentDate, nickname);
		CommentService commentService = DAOFactory.getCommentServiceInstance();
		commentService.updateComment(comment);
		
		response.sendRedirect("manageCommentsAdminServlet");
	}

}
