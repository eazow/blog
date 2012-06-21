package com.eazow.blog.servlet.admin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.entity.Motto;
import com.eazow.blog.service.MottoService;


@SuppressWarnings("serial")
public class AddMottoAdminServlet extends HttpServlet
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
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		if(null == admin)
		{
			request.setAttribute("usernameErrorMessage", "请登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		request.setCharacterEncoding("utf-8");
		String mottoContent = request.getParameter("mottoContent");
		if(null==mottoContent || "".equals(mottoContent))
		{
			response.sendError(400, "Input Violation");
			return;
		}
		mottoContent = mottoContent.trim();
		Motto motto = new Motto(mottoContent, new Date());
		MottoService mottoService = DAOFactory.getMottoServiceInstance();
		if(!mottoService.addMotto(motto))
		{
			request.setAttribute("addMottoErrorMessage", "请添加不同格言");
			request.getRequestDispatcher("manageMottosAdminServlet").forward(request, response);
			return;
		}
		
		response.sendRedirect("manageMottosAdminServlet");
	}

}
