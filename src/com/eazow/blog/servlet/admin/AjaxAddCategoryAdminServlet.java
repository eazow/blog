package com.eazow.blog.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

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
public class AjaxAddCategoryAdminServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if (null == admin) {
			request.setAttribute("usernameErrorMessage", "请登录");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;
		}
		String categoryName = request.getParameter("categoryName");
		categoryName = URLDecoder.decode(categoryName, "utf-8");
		if (null == categoryName || "".equals(categoryName)) {
			response.sendError(400, "Input Violation");
			return;
		}
		categoryName = categoryName.trim();
		Category category = new Category(categoryName, 0);
		CategoryService categoryService = DAOFactory
				.getCategoryServiceInstance();
		// 分类名重复
		if (!categoryService.addCategory(category)) {
			PrintWriter out = response.getWriter();
			out.println("ERROR");
			out.flush();
			out.close();
			return;
		}
		// 分类加入成功
		List<Category> categories = categoryService.getAllCategories();
		PrintWriter out = response.getWriter();
		out.print("<select name='categoryId'>");
		for (Category categoryTemp : categories) {
			out.print("<option value='" + categoryTemp.getId() + "'>"
					+ categoryTemp.getName() + "</option>");
		}
		out.print("</select>");
		out.flush();
		out.close();
	}

}
