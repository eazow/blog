<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*, java.util.*,com.eazow.blog.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	List<Article> articlesList = (List<Article>) request.getAttribute("articlesList");
	Tag tag = (Tag)request.getAttribute("tag");
	String location = "index";
%>
<%@ include file="header.jsp"%>
<div class="container">
	<div class="main">
		<div class="content">
			<div style="font-weight: bold; color: #ccc; margin-bottom: 10px">
				您正在查看包含标签"<%= tag.getName() %>"的文章
			</div>
			<%
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
						border-bottom-color: #DDD; padding-bottom: 10px">
						标签:
						<%
							List<Tag> tags = article.getTags();
							for(Tag tagTemp: tags)
							{
						%>
								<a href="getArticlesOfTagServlet?tagId=<%=tagTemp.getId()%>">
									<%= tagTemp.getName() %>
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