package com.eazow.blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Image;
import com.eazow.blog.service.ImageService;


@SuppressWarnings("serial")
public class BrowseImageOfAlbumServlet extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String imageIdStr = request.getParameter("imageId");
		if(null == imageIdStr)
		{
			response.sendError(400, "Input Violation");
			return;
		}
		int imageId = 0;
		try
		{
			imageId = Integer.parseInt(imageIdStr);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			response.sendError(400, "Input Violation");
			return;
		}
		ImageService imageService = DAOFactory.getImageServiceInstance();
		Image image = imageService.getImageOfAlbum(imageId);
		request.setAttribute("image", image);
		
		List<Image> imagesList = imageService.getImagesOfAlbum(image.getAlbumId());
		request.setAttribute("imagesList", imagesList);
		
		request.getRequestDispatcher("imageBrowseOfAlbum.jsp").forward(request, response);
	}

}
