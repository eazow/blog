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

public class GetArticlesOfCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 2836812398268471931L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryIdStr = request.getParameter("categoryId");
		int categoryId = 0;
		try {
			categoryId = Integer.parseInt(categoryIdStr);
		} catch (NumberFormatException e) {
			response.sendError(400, "Input Violation");
			return;
		}
		String pageNumStr = request.getParameter("pageNum");
		int pageNum = 1;
		if (null == pageNumStr || "".equals(pageNumStr)) {
			pageNum = 1;
		} else {
			try {
				pageNum = Integer.parseInt(pageNumStr);
			} catch (NumberFormatException e) {
				response.sendError(400, "Input Violation");
				return;
			}
		}
		PageUtil pageUtil = new PageUtil();
		ArticleService articleService = DAOFactory.getArticleServiceInstance();
		int totalPages = articleService.getTotalPagesOfCategory(categoryId,
				pageUtil.getPageSize());
		List<Article> articlesList = null;
		if (totalPages != 0) {
			// 要获得的页码大于总页数
			if (pageNum > totalPages) {
				response.sendError(400, "Input Violation");
				return;
			}
			pageUtil.setTotalPages(totalPages);
			pageUtil.setCurrentPage(pageNum);
			articlesList = articleService.getArticlesOfCategory(categoryId,
					pageNum, pageUtil.getPageSize());
		}
		// totalPages = 0;
		else {
			articlesList = new ArrayList<Article>();
			pageUtil.setTotalPages(1);
			pageUtil.setCurrentPage(1);
		}
		request.setAttribute("pageUtil", pageUtil);
		request.setAttribute("articlesList", articlesList);

		CategoryService categoryService = DAOFactory
				.getCategoryServiceInstance();
		List<Category> categoriesList = categoryService.getAllCategories();
		request.setAttribute("categoriesList", categoriesList);

		CommentService commentService = DAOFactory.getCommentServiceInstance();
		List<Comment> latest10Comments = commentService.getLatest10Comments();
		request.setAttribute("latest10Comments", latest10Comments);

		// 获得文章存档
		ArticleArchiveService articleArchiveService = DAOFactory
				.getArticleArchiveServiceInstance();
		List<ArticleArchive> articleArchiveList = articleArchiveService
				.getArticleArchiveList();
		request.setAttribute("articleArchiveList", articleArchiveList);

		// 标签云
		TagService tagService = DAOFactory.getTagServiceInstance();
		List<Tag> tagsList = tagService.getAllTags();
		request.setAttribute("tagsList", tagsList);

		request.getRequestDispatcher("articlesOfCategory.jsp").forward(request,
				response);
	}

}
