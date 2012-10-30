<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*,com.eazow.blog.entity.Motto,java.util.*,com.eazow.blog.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	List<Article> articlesList = (List<Article>) request.getAttribute("articlesList");
	String location = "index";
%>
<%@ include file="header.jsp"%>
<%/*@ include file="scroll.html"*/%>
<div class="container">
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
						<a href="article?id=<%=article.getId()%>">
							<%= article.getTitle() %>
						</a>
					</h1>
					<!-- 
					<div class="descr" style="position: absolute; margin-top: -29px; width: 160px; margin-left: -60px; background-image: url(img/date_bg.png); height: 70px; background-position: 0, 100%; background-repeat: no-repeat">
						<div style="width: 86px; height: 56px; background-color: #EB374B; color: white">
					-->
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
								<a href="article?id=<%=article.getId()%>"
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
					<div style="text-align: right; border-bottom-style: solid; border-bottom-width: 1px;; 
						border-bottom-color: #eee; padding-bottom: 10px">
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