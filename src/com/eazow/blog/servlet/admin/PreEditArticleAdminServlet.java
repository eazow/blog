package com.eazow.blog.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.entity.Article;
import com.eazow.blog.entity.Category;
import com.eazow.blog.entity.Tag;
import com.eazow.blog.service.ArticleService;
import com.eazow.blog.service.CategoryService;
import com.eazow.blog.service.TagService;


@SuppressWarnings("serial")
public class PreEditArticleAdminServlet extends HttpServlet
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
		CategoryService categoryService = DAOFactory.getCategoryServiceInstance();
		List<Category> categoriesList = categoryService.getAllCategories();
		request.setAttribute("categoriesList", categoriesList);
		
		String idStr = request.getParameter("id");
		if(idStr == null)
		{
			response.sendError(400, "Input Violation");
			return;
		}
		int id = 0;
		try
		{
			id = Integer.parseInt(idStr);
		}
		catch(NumberFormatException e)
		{
			response.sendError(400, "Input Violation");
			return;
		}
		ArticleService articleService = DAOFactory.getArticleServiceInstance();
		Article article = articleService.getArticle(id);
		request.setAttribute("article", article);
		
		//Tags
		TagService tagService = DAOFactory.getTagServiceInstance();
		List<Tag> tagsList = tagService.getAllTags();
		request.setAttribute("tagsList", tagsList);
		
		request.getRequestDispatcher("editArticle.jsp").forward(request, response);
	}

}
