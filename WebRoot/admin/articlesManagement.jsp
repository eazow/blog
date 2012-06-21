<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.eazow.blog.entity.*"%>
<%
	List<Article> articlesList = (List<Article>)request.getAttribute("articlesList");
	String location = "index";
%>
<html>
<head>
	<link href="../css/admin.css" rel="stylesheet">
</head>
<body>
<form action="preEditArticleAdminServlet" id="editArticleForm" method="post">
	<input type="hidden" name="id" id="idInput2"/>
</form>
<form action="deleteArticleAdminServlet" id="deleteArticleForm" method="post">
	<input type="hidden" name="id" id="idInput"/>
</form>
<table class="content_table">
	<tr class="header_tr">
		<td style="width: 50px">id</td>
		<td>文章标题</td>
		<td>发表日期</td>
		<td>文章分类</td>
		<td>文章标签</td>
		<td>评论数量</td>
		<td>操作</td>
	</tr>
<%
	int articleCount =0;
	for (Article article : articlesList) 
	{
		articleCount++;
		String className = "";
		if(articleCount%2==0) {
			className = "even_tr";
		}
		else {
			className = "odd_tr";
		}
%>
		<tr class="<%=className%>">
			<td>
				<%= article.getId() %>
			</td>
			<td>
				<a href="manageArticleAdminServlet?id=<%=article.getId()%>">
					<%= article.getTitle() %>
				</a>
			</td>
			<td>
				<%= article.getPostDate() %>
			</td>
			<td>
				<a href="manageArticlesOfCategoryAdminServlet?categoryId=<%=article.getCategory().getId()%>">
					<%= article.getCategory().getName()%>
				</a>
			</td>
			<%
				List<Tag> tagsList = article.getTags();
			%>
			<td>
			<%
				int i = 0;
				for(Tag tag: tagsList)
				{
					i++;
			%>
					<span style="font-weight: bold"><%=i%>.</span>&nbsp;
					<a href="manageArticlesOfTagAdminServlet?tagId=<%=tag.getId()%>">
						<%= tag.getName() %>
					</a>
					<br/>
			<%
				}
			%>
			</td>
			<td>
				<%= article.getCommentsNum() %>
			</td>
			<td>
				<img src="../img/edit.gif" style="vertical-align: middle; cursor: pointer"
					alt="edit" onclick="editArticle(<%=article.getId()%>)"/>
				<img src="../img/delete.gif" style="vertical-align: middle; cursor: pointer" 
					alt="delete" onclick="deleteArticle(<%=article.getId()%>)"/>
			</td>
		</tr>							
<%
	}
%>
</table>
</body>
</html>
<script type="text/javascript">
	function editArticle(id)
	{
		document.getElementById("idInput2").value = id;
		document.getElementById("editArticleForm").submit();
	}
	function deleteArticle(id)
	{
		if(!confirm("确定删除?"))
		{
			return;
		}
		document.getElementById("idInput").value = id;
		document.getElementById("deleteArticleForm").submit();
	}
</script>