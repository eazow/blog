package com.eazow.blog.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import com.eazow.blog.util.DateUtil;
import com.eazow.blog.util.StringUtil;

@SuppressWarnings("serial")
public class UpdateArticleAdminServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");

		String idStr = request.getParameter("id");
		String categoryIdStr = request.getParameter("categoryId");
		String title = request.getParameter("title");
		String postDateStr = request.getParameter("postDate");
		String content = request.getParameter("content");
		String tags = request.getParameter("tags");
		String commentsNumStr = request.getParameter("commentsNum");

		if (null == idStr || "".equals(idStr)) {
			response.sendError(400, "Input Invalid1");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			response.sendError(400, "Input Invalid2");
			return;
		}
		if (null == title || "".equals(title)) {
			request.setAttribute("titleErrorMessage", "请输入文章标题");
			request.getRequestDispatcher("preAddArticleAdminServlet").forward(
					request, response);
			return;
		}
		// postDate
		if (null == postDateStr || "".equals(postDateStr)) {
			request.setAttribute("postDateErrorMessage", "请输入日期");
			request.getRequestDispatcher("preAddArticleAdminServlet").forward(
					request, response);
			return;
		}
		Date postDate = DateUtil.parseStringToDate(postDateStr);
		if (null == postDate) {
			request.setAttribute("postDateErrorMessage", "请输入正确的日期");
			request.getRequestDispatcher("preAddArticleAdminServlet").forward(
					request, response);
			return;
		}
		// content
		if (null == content || "".equals(content)) {
			request.setAttribute("titleErrorMessage", "请输入文章标题");
			request.getRequestDispatcher("preAddArticleAdminServlet").forward(
					request, response);
			return;
		}
		if (null == categoryIdStr) {
			response.sendError(400, "Input Invalid3");
			return;
		}
		int categoryId = 0;
		try {
			categoryId = Integer.parseInt(categoryIdStr);
		} catch (NumberFormatException e) {
			response.sendError(400, "Input Invalid4");
			return;
		}
		// commentsNum
		if (null == commentsNumStr || "".equals(commentsNumStr)) {
			response.sendError(400, "Input Violation");
			return;
		}
		int commentsNum = 0;
		try {
			commentsNum = Integer.parseInt(commentsNumStr);
		} catch (NumberFormatException e) {
			response.sendError(400, "Input Violation");
			return;
		}

		// Tags处理
		if (null == tags || "".equals(tags)) {
			request.setAttribute("titleErrorMessage", "请输入标签");
			request.getRequestDispatcher("preAddArticleAdminServlet").forward(
					request, response);
			return;
		}
		List<String> tagNames = StringUtil.splitString(tags, " ");
		List<Tag> tagsList = new ArrayList<Tag>();
		for (String tagName : tagNames) {
			Tag tag = new Tag(tagName);
			tagsList.add(tag);
		}

		// 校验通过,但登录超时,保存至草稿箱
		if (null == admin) {
			Draft draft = new Draft(title, content, new Date(), categoryId,
					tags);
			DraftService draftService = DAOFactory.getDraftServiceInstance();
			boolean flag = draftService.addDraft(draft);
			if (!flag) {
				response.sendError(400, "Database Access Error1");
				return;
			}

			request.setAttribute("usernameErrorMessage", "请登录");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;
		}

		// 校验全部通过
		Article article = new Article(id, title, content, postDate, categoryId,
				commentsNum, 0);
		article.setTags(tagsList);
		ArticleService articleService = DAOFactory.getArticleServiceInstance();
		boolean result = articleService.updateArticle(article);
		if (!result) {
			response.sendError(400, "Database Access Error2");
			return;
		}
		response.sendRedirect("manageArticlesAdminServlet");
	}

}
