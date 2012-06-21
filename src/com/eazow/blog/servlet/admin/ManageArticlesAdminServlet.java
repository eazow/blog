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
import com.eazow.blog.service.ArticleService;
import com.eazow.blog.service.CategoryService;
import com.eazow.blog.service.DraftService;


@SuppressWarnings("serial")
public class ManageArticlesAdminServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doPost(request, response);
	}

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
		ArticleService articleService = DAOFactory.getArticleServiceInstance();
		List<Article> articlesList = articleService.getAllArticles();
		CategoryService categoryService = DAOFactory.getCategoryServiceInstance();
		List<Category> categoriesList = categoryService.getAllCategories();
		request.setAttribute("articlesList", articlesList);
		request.setAttribute("categoriesList", categoriesList);
		
		DraftService draftService = DAOFactory.getDraftServiceInstance();
		int draftsNum = draftService.getAllDraftsNum();
		request.setAttribute("draftsNum", draftsNum);
		request.getRequestDispatcher("articlesManagement.jsp").forward(request,	response);
	}

}
