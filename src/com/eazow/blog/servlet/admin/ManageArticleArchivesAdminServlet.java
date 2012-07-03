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
import com.eazow.blog.entity.ArticleArchive;
import com.eazow.blog.service.ArticleArchiveService;

@SuppressWarnings("serial")
public class ManageArticleArchivesAdminServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if (null == admin) {
			request.setAttribute("usernameErrorMessage", "ÇëµÇÂ¼");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;
		}

		ArticleArchiveService articleArchiveService = DAOFactory
				.getArticleArchiveServiceInstance();
		List<ArticleArchive> articleArchivesList = articleArchiveService
				.manageAllArticleArchives();
		request.setAttribute("articleArchivesList", articleArchivesList);

		request.getRequestDispatcher("articleArchivesManagement.jsp").forward(
				request, response);
	}

}
