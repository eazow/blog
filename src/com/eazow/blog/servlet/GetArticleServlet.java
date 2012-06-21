package com.eazow.blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Article;
import com.eazow.blog.entity.ArticleArchive;
import com.eazow.blog.entity.Category;
import com.eazow.blog.entity.Comment;
import com.eazow.blog.entity.Tag;
import com.eazow.blog.service.ArticleArchiveService;
import com.eazow.blog.service.ArticleService;
import com.eazow.blog.service.CategoryService;
import com.eazow.blog.service.CommentService;
import com.eazow.blog.service.TagService;


public class GetArticleServlet extends HttpServlet
{
	private static final long serialVersionUID = 4130799179372736829L;

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
		try
		{
			String idStr = request.getParameter("id");
			int id = 0;
			if (idStr != null)
			{
				id = Integer.parseInt(idStr);
			}
			ArticleService articleService = DAOFactory.getArticleServiceInstance();
			Article article = articleService.getArticle(id);
			request.setAttribute("article", article);
			CategoryService categoryService = DAOFactory.getCategoryServiceInstance();
			List<Category> categoriesList = categoryService.getAllCategories();
			request.setAttribute("categoriesList", categoriesList);
			CommentService commentService = DAOFactory.getCommentServiceInstance();
			List<Comment> latest10Comments = commentService.getLatest10Comments();
			request.setAttribute("latest10Comments", latest10Comments);
			
			//增加文章的阅读量
			articleService.addViewCount(id);
			
			//获得文章存档
			ArticleArchiveService articleArchiveService = DAOFactory.getArticleArchiveServiceInstance();
			List<ArticleArchive> articleArchiveList = articleArchiveService.getArticleArchiveList();
			request.setAttribute("articleArchiveList", articleArchiveList);
			
			//标签云
			TagService tagService = DAOFactory.getTagServiceInstance();
			List<Tag> tagsList = tagService.getAllTags(); 
			request.setAttribute("tagsList", tagsList);
			
			request.getRequestDispatcher("article.jsp").forward(request, response);
		}
		catch (NumberFormatException e) 
		{
			response.sendError(400, "Input Violation");
			return;
		}
		catch(Exception e)
		{
			response.sendError(400);
			return;
		}
	}

}
