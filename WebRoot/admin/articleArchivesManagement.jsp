<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.eazow.blog.entity.*"%>
<%
	List<ArticleArchive> articleArchivesList = (List<ArticleArchive>)request.getAttribute("articleArchivesList");
	String location = "index";
%>
<html>
<head>
	<link href="../css/admin.css" rel="stylesheet">
</head>
<body>
<form action="preEditArticleArchiveAdminServlet" id="editArticleArchiveForm" method="post">
	<input type="hidden" name="articleArchiveId" id="idInput"/>
</form>
<form action="deleteArticleArchiveAdminServlet" id="deleteArticleArchiveForm" method="post">
	<input type="hidden" name="articleArchiveId" id="articleArchiveIdInput"/>
</form>
<table class="content_table">
	<tr class="header_tr">
		<td style="width: 30px">id</td>
		<td style="width: 100px">年</td>
		<td style="width: 100px">月</td>
		<td style="width: 100px">文章总数</td>
		<td style="text-align: left">对应文章</td>
		<td>操作</td>
	</tr>
	<%
	int i = 0;
	for(ArticleArchive articleArchive: articleArchivesList)
	{
		i++;
		String className = "";
		if(i%2==0) {
			className = "even_tr";
		}
		else {
			className = "odd_tr";
		}
%>
		<tr class="<%=className%>">
			<td>
				<%= articleArchive.getId() %>
			</td>
			<td>
				<%= articleArchive.getYear() %>
			</td>
			<td>
				<%= articleArchive.getMonth() %>
			</td>
			<td>
				<%= articleArchive.getArticlesNum() %>
			</td>
			<%
				List<Article> articlesList = articleArchive.getArticles();
			%>											
			<td>
				<%
					int j = 0;
					for(Article article: articlesList)
					{
						j++;
				%>
						<span style="font-weight: bold">
							<%= j %>.
						</span>
						<a href="manageArticleAdminServlet?id=<%=article.getId()%>">
							<%= article.getTitle() %>
						</a>
						
				<%
						if(j != articlesList.size())
						{
				%>
							<br/>
				<%		
						}
					}
				%>
			</td>
			<td>
				<img src="../img/edit.gif" style="vertical-align: middle; cursor: pointer"
							alt="edit" onclick="editArticleArchive(<%=articleArchive.getId()%>)"/>
				<img src="../img/delete.gif" style="vertical-align: middle; cursor: pointer" 
							alt="delete" onclick="deleteArticleArchive(<%=articleArchive.getId()%>)"/>
			</td>
		</tr>
<%
	}
%>
</table>
</body>
</html>	
<script type="text/javascript">
	function deleteArticleArchive(id)
	{
		if(!confirm("确定删除?"))
		{
			return;
		}
	}
	function editArticleArchive(id)
	{
		document.getElementById("idInput").value = id;
		document.getElementById("editArticleArchiveForm").submit();
	}
</script>