package com.eazow.blog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Article;
import com.eazow.blog.entity.ArticleArchive;
import com.eazow.blog.entity.Category;
import com.eazow.blog.entity.Comment;
import com.eazow.blog.entity.Motto;
import com.eazow.blog.entity.Tag;
import com.eazow.blog.entity.VisitRecord;
import com.eazow.blog.service.ArticleArchiveService;
import com.eazow.blog.service.ArticleService;
import com.eazow.blog.service.CategoryService;
import com.eazow.blog.service.CommentService;
import com.eazow.blog.service.MottoService;
import com.eazow.blog.service.TagService;
import com.eazow.blog.service.VisitRecordService;
import com.eazow.blog.util.PageUtil;


public class GetIndexArticlesServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

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
		int totalPages = articleService.getTotalPages(pageUtil.getPageSize());
		CategoryService categoryService = DAOFactory.getCategoryServiceInstance();
		CommentService commentService = DAOFactory.getCommentServiceInstance();
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
			articlesList = articleService.getArticles(pageNum, pageUtil.getPageSize());
		}
		//totalPages = 0;
		else
		{
			articlesList = new ArrayList<Article>();
			pageUtil.setTotalPages(1);
			pageUtil.setCurrentPage(1);
		}
		List<Category> categoriesList = categoryService.getAllCategories();
		List<Comment> latest10Comments = commentService.getLatest10Comments();
		
		request.setAttribute("pageUtil", pageUtil);
		request.setAttribute("articlesList", articlesList);
		request.setAttribute("categoriesList", categoriesList);
		request.setAttribute("latest10Comments", latest10Comments);
		
		
		//访问量记录
		HttpSession session = request.getSession();
		VisitRecordService visitRecordService = DAOFactory.getVisitRecordServiceInstance();
		//增加一次访问量
		if(session.isNew())
		{
			VisitRecord visitRecord = new VisitRecord(request.getRemoteAddr(), new Date());
			visitRecordService.addVisitRecord(visitRecord);
		}
		int totalPageView = visitRecordService.getTotalPageView();
		int todayPageView = visitRecordService.getTodayPageView(new Date());
		request.setAttribute("totalPageView", totalPageView);
		request.setAttribute("todayPageView", todayPageView);
		
		//获得文章存档
		ArticleArchiveService articleArchiveService = DAOFactory.getArticleArchiveServiceInstance();
		List<ArticleArchive> articleArchiveList = articleArchiveService.getArticleArchiveList();
		request.setAttribute("articleArchiveList", articleArchiveList);
		
		//获得格言
		MottoService mottoService = DAOFactory.getMottoServiceInstance();
		Motto motto = mottoService.getRandomMotto();
		request.setAttribute("motto", motto);
		
		//文章数量
		int totalArticlesNum = articleService.getTotalArticlesNum();
		request.setAttribute("totalArticlesNum", totalArticlesNum);
		
		//评论数量
		int totalCommentsNum = commentService.getTotalCommentsNum();
		request.setAttribute("totalCommentsNum", totalCommentsNum);
		
		//标签云
		TagService tagService = DAOFactory.getTagServiceInstance();
		List<Tag> tagsList = tagService.getAllTags(); 
		request.setAttribute("tagsList", tagsList);
		
		//在线人数
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
