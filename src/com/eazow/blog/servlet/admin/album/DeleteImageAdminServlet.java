package com.eazow.blog.servlet.admin.album;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.service.ImageService;


@SuppressWarnings("serial")
public class DeleteImageAdminServlet extends HttpServlet
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
		String idStr = request.getParameter("id");
		int id = 0;
		try
		{
			id = Integer.parseInt(idStr);
		}
		catch (NumberFormatException e) 
		{
			response.sendError(400, "Input Violation");
			return;
		}
		
		System.out.println("servletPath: " + request.getServletPath());
		String realRootPath = this.getServletContext().getRealPath("/");
		
		try
		{
			ImageService imageService = DAOFactory.getImageServiceInstance();
			imageService.deleteImage(id, realRootPath);
		}
		catch(Exception e)
		{
			response.sendError(400, "Database Access Error");
			return;
		}
		
		response.sendRedirect("manageAlbumsAdminServlet");
	}

}
