package com.eazow.blog.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.service.VisitRecordService;

@SuppressWarnings("serial")
public class DeleteVisitRecordAdminServlet extends HttpServlet {

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
		String visitRecordIdStr = request.getParameter("visitRecordId");
		if (visitRecordIdStr == null) {
			response.sendError(400, "Input Violation");
			return;
		}
		int visitRecordId = 0;
		try {
			visitRecordId = Integer.parseInt(visitRecordIdStr);
		} catch (NumberFormatException e) {
			response.sendError(400, "Input Violation");
			return;
		}
		VisitRecordService visitRecordService = DAOFactory
				.getVisitRecordServiceInstance();
		visitRecordService.deleteVisitRecord(visitRecordId);

		response.sendRedirect("manageVisitRecordsAdminServlet");
	}

}
