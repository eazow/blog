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
import com.eazow.blog.entity.Album;
import com.eazow.blog.entity.Image;
import com.eazow.blog.service.AlbumService;
import com.eazow.blog.service.ImageService;

@SuppressWarnings("serial")
public class ManageImagesOfAlbumAdminServlet extends HttpServlet {
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

		String albumIdStr = request.getParameter("albumId");
		if (null == albumIdStr) {
			response.sendError(400, "Input Violation");
			return;
		}
		int albumId = 0;
		try {
			albumId = Integer.parseInt(albumIdStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendError(400, "Input Violation");
			return;
		}
		ImageService imageService = DAOFactory.getImageServiceInstance();
		AlbumService albumService = DAOFactory.getAlbumServiceInstance();
		List<Image> imagesList = imageService.getImagesOfAlbum(albumId);
		Album album = albumService.getAlbum(albumId);

		request.setAttribute("imagesList", imagesList);
		request.setAttribute("album", album);

		request.getRequestDispatcher("imagesOfAlbumManagement.jsp").forward(
				request, response);
	}

}
