package com.eazow.blog.servlet.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.entity.Category;
import com.eazow.blog.entity.Tag;
import com.eazow.blog.service.CategoryService;
import com.eazow.blog.service.TagService;
import com.eazow.blog.util.DateUtil;

@SuppressWarnings("serial")
public class PreAddArticleAdminServlet extends HttpServlet {
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
		CategoryService categoryService = DAOFactory
				.getCategoryServiceInstance();
		List<Category> categoriesList = categoryService.getAllCategories();
		request.setAttribute("categoriesList", categoriesList);

		String presentTime = DateUtil.parseDateToString(new Date());
		request.setAttribute("presentTime", presentTime);

		TagService tagService = DAOFactory.getTagServiceInstance();
		List<Tag> tagsList = tagService.getAllTags();
		request.setAttribute("tagsList", tagsList);

		// DraftService draftService = DAOFactory.getDraftServiceInstance();
		// int draftsNum = draftService.getAllDraftsNum();
		// request.setAttribute("draftsNum", draftsNum);

		request.getRequestDispatcher("addArticle.jsp").forward(request,
				response);
	}

}
