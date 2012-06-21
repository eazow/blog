<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*"%>
<%
	List<Tag> tagsList = (List<Tag>)request.getAttribute("tagsList");
	String location = "index";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/admin.css" media="screen" />
	<script type="text/javascript">
		function deleteTag(id)
		{
			if(!confirm("确定删除?"))
			{
				return;
			}
			document.getElementById("tagIdInput").value = id;
			document.getElementById("deleteTagForm").submit();
		}
	</script>
</head>
<body>
<form action="deleteTagAdminServlet" id="deleteTagForm" method="post">
	<input type="hidden" name="tagId" id="tagIdInput"/>
</form>
<table class="content_table">
	<tr class="header_tr">
		<td style="width: 30px">id</td>
		<td>标签</td>
		<td>对应文章</td>
		<td>操作</td>
	</tr>
	<%
		int i=0;
		for(Tag tag: tagsList)
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
					<%= tag.getId() %>
				</td>
				<td>
					<%= tag.getName() %>
				</td>
				<%
					List<Article> articlesList = tag.getArticles();
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
								alt="edit"/>
					<img src="../img/delete.gif" style="vertical-align: middle; cursor: pointer" 
								alt="delete" onclick="deleteTag(<%=tag.getId()%>)"/>
				</td>
			</tr>
	<%
		}
	%>
</table>
</body>
</html>