package com.eazow.blog.servlet.admin.album;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.entity.Image;
import com.eazow.blog.service.ImageService;

@SuppressWarnings("serial")
public class ManageImageOfAlbumAdminServlet extends HttpServlet {
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

		String imageIdStr = request.getParameter("imageId");
		if (null == imageIdStr) {
			response.sendError(400, "Input Violation");
			return;
		}
		int imageId = 0;
		try {
			imageId = Integer.parseInt(imageIdStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendError(400, "Input Violation");
			return;
		}
		ImageService imageService = DAOFactory.getImageServiceInstance();
		Image image = imageService.getImageOfAlbum(imageId);

		request.setAttribute("image", image);
		request.getRequestDispatcher("imageOfAlbumManagement.jsp").forward(
				request, response);

	}

}
