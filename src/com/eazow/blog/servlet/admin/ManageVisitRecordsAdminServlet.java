package com.eazow.blog.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Admin;
import com.eazow.blog.entity.VisitRecord;
import com.eazow.blog.service.VisitRecordService;
import com.eazow.blog.util.VisitRecordPageUtil;


@SuppressWarnings("serial")
public class ManageVisitRecordsAdminServlet extends HttpServlet
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
			request.setAttribute("usernameErrorMessage", "«Îµ«¬º");
			request.getRequestDispatcher("login.jsp").forward(request, response);
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
		VisitRecordPageUtil visitRecordPageUtil = new VisitRecordPageUtil();
		VisitRecordService visitRecordService = DAOFactory.getVisitRecordServiceInstance();
		int totalPages = visitRecordService.getTotalPages(visitRecordPageUtil.getPageSize());
		// ‰»ÎpageNum∑«∑®
		if(totalPages < pageNum)
		{
			response.sendError(400, "Input Violation");
			return;
		}
		visitRecordPageUtil.setCurrentPage(pageNum);
		visitRecordPageUtil.setTotalPages(totalPages);
		List<VisitRecord> visitRecordsList = visitRecordService.getVisitRecords(pageNum, visitRecordPageUtil.getPageSize());
		
		request.setAttribute("visitRecordPageUtil", visitRecordPageUtil);
		request.setAttribute("visitRecordsList", visitRecordsList);
		request.getRequestDispatcher("visitRecordsManagement.jsp").forward(request, response);
	}

}
