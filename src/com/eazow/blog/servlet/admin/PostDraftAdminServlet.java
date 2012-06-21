package com.eazow.blog.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.entity.Article;
import com.eazow.blog.entity.Draft;
import com.eazow.blog.entity.Tag;
import com.eazow.blog.service.ArticleService;
import com.eazow.blog.service.DraftService;
import com.eazow.blog.util.StringUtil;


@SuppressWarnings("serial")
public class PostDraftAdminServlet extends HttpServlet
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
		
		DraftService draftService = DAOFactory.getDraftServiceInstance();
		Draft draft = draftService.getDraft(id);
		
		Article article = new Article(draft.getTitle(), draft.getContent(), draft.getPostDate(), draft.getCategoryId(), 0, 0);
		String tags = draft.getTags();
		List<String> tagNames = StringUtil.splitString(tags, " ");
		List<Tag> tagsList = new ArrayList<Tag>();
		for(String tagName: tagNames)
		{
			Tag tag = new Tag(tagName);
			tagsList.add(tag);
		}
		article.setTags(tagsList);
		ArticleService articleService = DAOFactory.getArticleServiceInstance();
		articleService.addArticle(article);
		
		response.sendRedirect("manageArticlesAdminServlet");
	}

}
