package com.eazow.blog.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.entity.ArticleArchive;
import com.eazow.blog.service.ArticleArchiveService;


@SuppressWarnings("serial")
public class UpdateArticleArchivesAdminServlet extends HttpServlet
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
		String articlesNumStr = request.getParameter("articlesNum");
		String idStr = request.getParameter("id");
		String yearStr = request.getParameter("year");
		String monthStr = request.getParameter("month");
		if(null==idStr || "".equals(idStr))
		{
			response.sendError(400, "Input Violation");
			return;
		}
		if(null==yearStr || "".equals(yearStr))
		{
			response.sendError(400, "Input Violation");
		}
		if(null==monthStr || "".equals(monthStr))
		{
			response.sendError(400, "Input Violation");
			return;
		}
		if(null == articlesNumStr || "".equals(articlesNumStr))
		{
			response.sendError(400, "Input Violatoin");
			return;
		}
		int id = 0;
		int articlesNum = 0;
		int year = 0;
		int month = 0;
		try
		{
			id = Integer.parseInt(idStr);
			year = Integer.parseInt(yearStr);
			month = Integer.parseInt(monthStr);
			articlesNum = Integer.parseInt(articlesNumStr);
		}
		catch(Exception e)
		{
			response.sendError(400, "Input Violation");
			return;
		}
		ArticleArchive articleArchive = new ArticleArchive(id, year, month, articlesNum);
		ArticleArchiveService articleArchiveService = DAOFactory.getArticleArchiveServiceInstance();
		articleArchiveService.updateArticleArchive(articleArchive);
		
		request.getRequestDispatcher("manageArticleArchivesAdminServlet").forward(request, response);
	}

}
