package com.eazow.blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eazow.blog.dao.ArticleDAO;
import com.eazow.blog.dao.factory.DAOFactory;


public class DeleteArticleServlet extends HttpServlet 
{
	private static final long serialVersionUID = -5672062982492337932L;

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
		String idStr = request.getParameter("id");
		Integer id = Integer.parseInt(idStr);
		
		ArticleDAO articleDAO = DAOFactory.getArticleDAOInstance();
		articleDAO.deleteArticle(id);
		
		request.getRequestDispatcher("/getIndexArticlesServlet").forward(request, response);
	}

}
