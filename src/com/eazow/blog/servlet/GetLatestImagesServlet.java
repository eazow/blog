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
public class GetLatestImagesServlet extends HttpServlet
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
		ImageService imageService = DAOFactory.getImageServiceInstance();
		List<Image> latestImagesList = imageService.getLatestImages();
		
		request.setAttribute("latestImagesList", latestImagesList);
		
		request.getRequestDispatcher("latestImages.jsp").forward(request, response);
	}

}
