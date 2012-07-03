package com.eazow.blog.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.service.DraftService;

@SuppressWarnings("serial")
public class DeleteDraftAdminServlet extends HttpServlet {
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
		String idStr = request.getParameter("id");
		if (idStr == null) {
			response.sendError(400, "Input Violation");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			response.sendError(400, "Input Violation");
			return;
		}
		DraftService draftService = DAOFactory.getDraftServiceInstance();
		boolean result = draftService.deleteDraft(id);
		// Èç¹ûÉ¾³ýÊ§°Ü
		if (!result) {
			response.sendError(400, "Database Access Error");
			return;
		}

		response.sendRedirect("manageDraftsAdminServlet");
	}

}
