package com.eazow.blog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eazow.blog.entity.Admin;


public class AuthenticationFilter implements Filter
{

	public void destroy()
	{
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		String requestURI = ((HttpServletRequest)request).getRequestURI();
		int index = requestURI.indexOf("/admin/");
		//��ȡ�����servlet����
		String servletName = requestURI.substring(index + 7);
		
		if("preLoginAdminServlet".equals(servletName)) 
		{
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//������󲻵���loginAdminServlet������й���
		if(!"loginAdminServlet".equals(servletName))
		{
			HttpSession session = ((HttpServletRequest)request).getSession();
			Admin admin = (Admin)session.getAttribute("admin");
			if(null == admin)
			{
//				request.setAttribute("usernameErrorMessage", "���¼");
//				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException
	{
	}

}
