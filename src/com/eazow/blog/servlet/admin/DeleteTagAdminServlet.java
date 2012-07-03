package com.eazow.blog.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.service.TagService;

@SuppressWarnings("serial")
public class DeleteTagAdminServlet extends HttpServlet {
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
		String tagIdStr = request.getParameter("tagId");
		if (null == tagIdStr) {
			response.sendError(400, "Id Is Null");
			return;
		}
		int tagId = 0;
		try {
			tagId = Integer.parseInt(tagIdStr);
		} catch (NumberFormatException e) {
			response.sendError(401, "Number Format Exception");
			return;
		}
		TagService tagService = DAOFactory.getTagServiceInstance();
		tagService.deleteTag(tagId);

		response.sendRedirect("manageTagsAdminServlet");
	}
}
