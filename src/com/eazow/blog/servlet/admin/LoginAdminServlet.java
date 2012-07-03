package com.eazow.blog.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.security.SecurityUtil;
import com.eazow.blog.service.AdminService;

public class LoginAdminServlet extends HttpServlet {

	private static final long serialVersionUID = -1602670428650742415L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (null == username || "".equals(username)) {
			request.setAttribute("usernameErrorMessage", "请输入用户名");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;
		}
		if (null == password || "".equals(password)) {
			request.setAttribute("usernameErrorMessage", "请输入密码");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;
		}

		// 将password加密
		password = SecurityUtil.generatePassword(password);

		AdminService adminService = DAOFactory.getAdminServiceInstance();
		Admin admin = adminService.getAdmin(username, password);
		// 用户名、密码错误
		if (admin == null) {
			request.setAttribute("usernameErrorMessage", "用户名或密码错误");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("admin", admin);
		// response.sendRedirect("manageArticlesAdminServlet");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
