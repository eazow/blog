package com.eazow.blog.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Comment;
import com.eazow.blog.service.CommentService;
import com.eazow.blog.util.HtmlUtil;


@SuppressWarnings("serial")
public class AddCommentServlet extends HttpServlet
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
		request.setCharacterEncoding("utf8");
		
		String articleIdStr = request.getParameter("articleId");
		int articleId = Integer.parseInt(articleIdStr);
		
		String verificationCodeInput = request.getParameter("verificationCodeInput");
		String commentContent = request.getParameter("commentContent");
		String nickname = request.getParameter("nickname");
		if(null==verificationCodeInput || "".equals(verificationCodeInput))
		{
			request.setAttribute("errorMessage", "请输入验证码");
			request.getRequestDispatcher("getArticleServlet?id="+articleId).forward(request, response);
			return;
		}
		if(null==commentContent || "".equals(commentContent))
		{
			request.setAttribute("errorMessage", "请输入评论");
			request.getRequestDispatcher("getArticleServlet?id="+articleId).forward(request, response);
			return;
		}
		if(null == nickname || "".equals(nickname))
		{
			request.setAttribute("errorMessage", "请输入昵称");
			request.getRequestDispatcher("getArticleServlet?id="+articleId).forward(request, response);
			return;
		}
		if(nickname.length() > 6)
		{
			request.setAttribute("errorMessage", "请不要输入过长昵称");
			request.setAttribute("commentContent", commentContent);
			request.getRequestDispatcher("getArticleServlet?id="+articleId).forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		String verificationCode = (String)session.getAttribute("verificationCode"); 
		if(verificationCodeInput.equalsIgnoreCase(verificationCode))
		{
			nickname = HtmlUtil.filtrateHtmlCode(nickname);
			commentContent = HtmlUtil.filtrateHtmlCode(commentContent);
			Comment comment = new Comment(commentContent, articleId, new Date(), nickname);
			CommentService commentService = DAOFactory.getCommentServiceInstance();
			commentService.addComment(comment);
			
			response.sendRedirect("getArticleServlet?id="+articleId);
		}
		else
		{
			request.setAttribute("errorMessage", "请输入正确的验证码");
			request.setAttribute("commentContent", commentContent);
			request.setAttribute("nickname", nickname);
			request.getRequestDispatcher("getArticleServlet?id="+articleId).forward(request, response);
			return;
		}
	}
}
