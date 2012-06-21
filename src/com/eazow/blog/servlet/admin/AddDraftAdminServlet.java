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
import com.eazow.blog.entity.Draft;
import com.eazow.blog.service.DraftService;
import com.eazow.blog.util.DateUtil;


@SuppressWarnings("serial")
public class AddDraftAdminServlet extends HttpServlet
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
		
		String categoryIdStr = request.getParameter("categoryId");
		String title = request.getParameter("title");
		String postDateStr = request.getParameter("postDate");
		String content = request.getParameter("content");
		//Tags
		String tags = request.getParameter("tags");
		
		if(null==title || "".equals(title))
		{
			request.setAttribute("titleErrorMessage", "请输入文章标题");
			request.getRequestDispatcher("preAddArticleAdminServlet").forward(request, response);
			return;
		}
		Date postDate = DateUtil.parseStringToDate(postDateStr);
		if(null == postDate)
		{
			request.setAttribute("postDateErrorMessage", "请输入正确的日期");
			request.getRequestDispatcher("preAddArticleAdminServlet").forward(request, response);
			return;
		}
		if(null==content || "".equals(content))
		{
			request.setAttribute("titleErrorMessage", "请输入文章标题");
			request.getRequestDispatcher("preAddArticleAdminServlet").forward(request, response);
			return;
		}
		if(null == categoryIdStr)
		{
			response.sendError(400, "Input Invalid");
			return;
		}
		int categoryId = 0;
		try
		{
			categoryId = Integer.parseInt(categoryIdStr);
		}
		catch (NumberFormatException e) 
		{
			response.sendError(400, "Input Invalid");
			return;
		}
		if(null==tags || "".equals(tags))
		{
			request.setAttribute("titleErrorMessage", "请输入标签");
			request.getRequestDispatcher("preAddArticleAdminServlet").forward(request, response);
			return;
		}
		
		Draft draft = new Draft(title, content, postDate, categoryId, tags);
		DraftService draftService = DAOFactory.getDraftServiceInstance();
		boolean flag = draftService.addDraft(draft);
		if(!flag)
		{
			response.sendError(400, "Database Access Error");
			return;
		}
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		//校验通过,但登录超时
		if(null == admin)
		{
			request.setAttribute("usernameErrorMessage", "请登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("manageArticlesAdminServlet");
	}

}
