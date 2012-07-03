package com.eazow.blog.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.entity.Category;
import com.eazow.blog.service.CategoryService;

@SuppressWarnings("serial")
public class AddCategoryAdminServlet extends HttpServlet {

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
			request.setAttribute("usernameErrorMessage", "请登录");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;
		}
		request.setCharacterEncoding("utf-8");
		String categoryName = request.getParameter("categoryName");
		if (null == categoryName || "".equals(categoryName)) {
			response.sendError(400, "Input Violation");
			return;
		}
		categoryName = categoryName.trim();
		Category category = new Category(categoryName, 0);
		CategoryService categoryService = DAOFactory
				.getCategoryServiceInstance();
		if (!categoryService.addCategory(category)) {
			request.setAttribute("addCategoryErrorMessage", "请添加不同分类");
			request.getRequestDispatcher("manageCategoriesAdminServlet")
					.forward(request, response);
			return;
		}

		response.sendRedirect("manageCategoriesAdminServlet");
	}

}
