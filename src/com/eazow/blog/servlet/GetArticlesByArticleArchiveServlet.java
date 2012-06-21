package com.eazow.blog.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import com.eazow.blog.util.PageUtil;


@SuppressWarnings("serial")
public class GetArticlesByArticleArchiveServlet extends HttpServlet
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
		String articleArchiveIdStr = request.getParameter("articleArchiveId");
		int articleArchiveId = 0;
		try
		{
			articleArchiveId = Integer.parseInt(articleArchiveIdStr);
		}
		catch (NumberFormatException e)
		{
			response.sendError(400, "Input Violation");
			return;
		}
		String pageNumStr = request.getParameter("pageNum");
		int pageNum = 1;
		if(null==pageNumStr || "".equals(pageNumStr))
		{
			pageNum = 1;
		}
		else
		{
			try
			{
				pageNum = Integer.parseInt(pageNumStr);
			}
			catch(NumberFormatException e)
			{
				response.sendError(400, "Input Violation");
				return;
			}
		}
		PageUtil pageUtil = new PageUtil();
		ArticleService articleService = DAOFactory.getArticleServiceInstance();
		int totalPages = articleService.getTotalPagesByArticleArchive(articleArchiveId, pageUtil.getPageSize());
		List<Article> articlesList = null;
		if(totalPages != 0)
		{
			//要获得的页码大于总页数
			if(pageNum > totalPages)
			{
				response.sendError(400, "Input Violation");
				return;
			}
			pageUtil.setTotalPages(totalPages);
			pageUtil.setCurrentPage(pageNum);
			articlesList =  articleService.getArticlesByArticleArchive(articleArchiveId, pageNum, pageUtil.getPageSize());
		}
		else
		{
			articlesList = new ArrayList<Article>();
			pageUtil.setTotalPages(1);
			pageUtil.setCurrentPage(1);
		}
		request.setAttribute("pageUtil", pageUtil);
		request.setAttribute("articlesList", articlesList);
		
		CategoryService categoryService = DAOFactory.getCategoryServiceInstance();
		List<Category> categoriesList = categoryService.getAllCategories();
		request.setAttribute("categoriesList", categoriesList);
		
		CommentService commentService = DAOFactory.getCommentServiceInstance();
		List<Comment> latest10Comments = commentService.getLatest10Comments();
		request.setAttribute("latest10Comments", latest10Comments);
		
		//获得文章存档
		ArticleArchiveService articleArchiveService = DAOFactory.getArticleArchiveServiceInstance();
		List<ArticleArchive> articleArchiveList = articleArchiveService.getArticleArchiveList();
		ArticleArchive articleArchive = articleArchiveService.getArticleArchive(articleArchiveId);
		request.setAttribute("articleArchiveList", articleArchiveList);
		request.setAttribute("articleArchive", articleArchive);
		
		//标签云
		TagService tagService = DAOFactory.getTagServiceInstance();
		List<Tag> tagsList = tagService.getAllTags(); 
		request.setAttribute("tagsList", tagsList);
		
		request.getRequestDispatcher("articlesByArticleArchive.jsp").forward(request, response);
	}
}
