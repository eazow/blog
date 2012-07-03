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
import com.eazow.blog.entity.Draft;
import com.eazow.blog.service.DraftService;

@SuppressWarnings("serial")
public class ManageDraftsAdminServlet extends HttpServlet {
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
		DraftService draftService = DAOFactory.getDraftServiceInstance();
		List<Draft> draftsList = draftService.getAllDrafts();
		int draftsNum = draftService.getAllDraftsNum();

		request.setAttribute("draftsList", draftsList);
		request.setAttribute("draftsNum", draftsNum);

		request.getRequestDispatcher("draftsManagement.jsp").forward(request,
				response);
	}

}
