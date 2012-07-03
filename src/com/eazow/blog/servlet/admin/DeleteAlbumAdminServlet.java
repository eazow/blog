package com.eazow.blog.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.service.AlbumService;

@SuppressWarnings("serial")
public class DeleteAlbumAdminServlet extends HttpServlet {

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
			request.setAttribute("userErrorMessage", "ÇëµÇÂ¼");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;
		}

		String albumIdStr = request.getParameter("albumId");

		if (null == albumIdStr) {
			response.sendError(400, "Input Violation");
			return;
		}
		int albumId = 0;
		try {
			albumId = Integer.parseInt(albumIdStr);
		} catch (NumberFormatException e) {
			response.sendError(400, "Input Violation");
			return;
		}

		AlbumService albumService = DAOFactory.getAlbumServiceInstance();
		boolean isSuccess = albumService.deleteAlbum(albumId);

		if (!isSuccess) {
			response.sendError(400, "Database Access Error");
			return;
		}

		response.sendRedirect("manageAlbumsAdminServlet");
	}

}
