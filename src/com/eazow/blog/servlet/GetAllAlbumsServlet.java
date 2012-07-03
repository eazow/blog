package com.eazow.blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Album;
import com.eazow.blog.service.AlbumService;

@SuppressWarnings("serial")
public class GetAllAlbumsServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AlbumService albumService = DAOFactory.getAlbumServiceInstance();
		List<Album> albumsList = albumService.getAllAlbums();

		request.setAttribute("albumsList", albumsList);
		request.getRequestDispatcher("albums.jsp").forward(request, response);
	}

}
