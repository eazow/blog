<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*,com.eazow.blog.entity.Motto,java.util.*,com.eazow.blog.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	List<Article> articlesList = (List<Article>) request.getAttribute("articlesList");
	String location = "index";
%>
<%@ include file="header.jsp"%>
<div class="container">
	<%@ include file="slides.html"%>
	<div class="main">
		<div class="content">
			<%
				if(articlesList.size() == 0)
				{
			%>
					<div>
						暂时还没有文章
					</div>
			<%
				}
				for (Article article : articlesList)
				{
			%>
					<h1>
						<a href="getArticleServlet?id=<%=article.getId()%>">
							<%= article.getTitle() %>
						</a>
					</h1>
					<div class="descr">
						<%= article.getPostDateDisplay() %>
					</div>
					<%
						if(article.hasAbstractOfContent())
						{
					%>
							<p>
								<%= article.getAbstractOfContent() %>
							</p>
							<br/>
							<div>
								<a href="getArticleServlet?id=<%=article.getId()%>"
									style="color: red; font-weight: bold"
									onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)">
									查看全文>>
								</a>
							</div>
					<%
						}
						else
						{
					%>
							<p>
								<%= article.getAbstractOfContent() %>
							</p>
					<%
						}
					%>
					<%
						Category category = article.getCategory();
					%>
					<div style="text-align: right; border-bottom-style: dashed; border-bottom-width: 1px; 
						border-bottom-color: #999; padding-bottom: 10px">
						标签:
						<%
							List<Tag> tags = article.getTags();
							for(Tag tag: tags)
							{
						%>
								<a href="getArticlesOfTagServlet?tagId=<%=tag.getId()%>">
									<%= tag.getName() %>
								</a>
						<%
							}
						%>
						|&nbsp;
						分类:
						<a href="getArticlesOfCategoryServlet?categoryId=<%=category.getId()%>">
							<%= category.getName() %>
						</a>|&nbsp;
						阅读(<%=article.getViewCount()%>)&nbsp;|&nbsp;
						评论(<%= article.getCommentsNum() %>)
					</div>
					<br/>
			<%
				}
			%>
			<%@ include file="page.jsp"%>
		</div>
		<%@ include file="side.jsp"%>		
	</div>
</div>
<%@ include file="footer.jsp"%>