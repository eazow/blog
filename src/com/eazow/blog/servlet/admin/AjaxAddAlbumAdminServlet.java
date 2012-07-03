package com.eazow.blog.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.entity.Album;
import com.eazow.blog.service.AlbumService;

@SuppressWarnings("serial")
public class AjaxAddAlbumAdminServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if (null == admin) {
			request.setAttribute("usernameErrorMessage", "请登录");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;
		}
		String albumName = request.getParameter("albumName");
		albumName = URLDecoder.decode(albumName, "utf-8");
		if (null == albumName || "".equals(albumName)) {
			response.sendError(400, "Input Violation");
			return;
		}
		albumName = albumName.trim();
		Album album = new Album(albumName, 0, 0);

		AlbumService albumService = DAOFactory.getAlbumServiceInstance();
		// 分类名重复
		if (!albumService.addAlbum(album)) {
			PrintWriter out = response.getWriter();
			out.println("ERROR");
			out.flush();
			out.close();
			return;
		}
		// 分类加入成功
		List<Album> albumsList = albumService.getAllAlbums();
		PrintWriter out = response.getWriter();
		out.print("<select name='albumId'>");
		for (Album albumTemp : albumsList) {
			out.print("<option value='" + albumTemp.getId() + "'>"
					+ albumTemp.getName() + "</option>");
		}
		out.print("</select>");
		out.flush();
		out.close();
	}

}
