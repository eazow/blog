package com.eazow.blog.servlet.admin.album;

import java.io.IOException;
import java.util.List;

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
public class ManageLatestImagesAdminServlet extends HttpServlet {
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

		ImageService imageService = DAOFactory.getImageServiceInstance();
		List<Image> latestImagesList = imageService.getLatestImages();

		request.setAttribute("latestImagesList", latestImagesList);

		request.getRequestDispatcher("latestImagesManagement.jsp").forward(
				request, response);
	}

}
