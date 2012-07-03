package com.eazow.blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SearchArticlesServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		if (null == keyword || "".equals(keyword)) {
			response.sendRedirect("getIndexArticlesServlet");
			return;
		}

		// keyword = URLEncoder.encode(keyword, "utf-8");
		// URL url = new URL("http://www.baidu.com.cn/s?wd=" + keyword);

		response.sendRedirect("http://www.baidu.com.cn/s?wd=" + keyword
				+ "&utf-8");
	}

}
