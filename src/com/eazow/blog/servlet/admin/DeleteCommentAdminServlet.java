package com.eazow.blog.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.entity.Comment;
import com.eazow.blog.service.CommentService;

@SuppressWarnings("serial")
public class DeleteCommentAdminServlet extends HttpServlet {
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
		String commentIdStr = request.getParameter("commentId");
		if (null == commentIdStr) {
			response.sendError(400, "Id Is Null");
			return;
		}
		int commentId = 0;
		try {
			commentId = Integer.parseInt(commentIdStr);
		} catch (NumberFormatException e) {
			response.sendError(401, "Number Format Exception");
			return;
		}

		CommentService commentService = DAOFactory.getCommentServiceInstance();
		Comment comment = commentService.getComment(commentId);
		if (null == comment) {
			response.sendError(402, "Not Exist");
			return;
		}
		commentService.deleteComment(commentId);

		request.getRequestDispatcher("manageCommentsAdminServlet").forward(
				request, response);

	}

}
