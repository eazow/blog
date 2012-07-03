package com.eazow.blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Album;
import com.eazow.blog.entity.Image;
import com.eazow.blog.service.AlbumService;
import com.eazow.blog.service.ImageService;

@SuppressWarnings("serial")
public class GetImagesOfAlbumServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		request.getRequestDispatcher("imagesOfAlbum.jsp").forward(request,
				response);
	}

}
