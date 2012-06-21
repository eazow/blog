<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*"%>
<%
	List<Comment> commentsList = (List<Comment>)request.getAttribute("commentsList");
	String location = "index";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/admin.css"
		media="screen" />
	<script type="text/javascript">
		function deleteComment(id)
		{
			if(!confirm("确定删除?"))
			{
				return;
			}
			document.getElementById("commentIdInput").value = id;
			document.getElementById("deleteCommentForm").submit();
		}
		function editComment(id)
		{
			document.getElementById("idInput").value = id;
			document.getElementById("editCommentForm").submit();
		}
	</script>
</head>
<body>
<form action="preEditCommentAdminServlet" id="editCommentForm" method="post">
	<input type="hidden" name="commentId" id="idInput"/>
</form>
<form action="deleteCommentAdminServlet" id="deleteCommentForm" method="post">
	<input type="hidden" name="commentId" id="commentIdInput"/>
</form>
<table class="content_table">
	<tr class="header_tr">
		<td>id</td>
		<td>昵称</td>
		<td>评论内容</td>
		<td>评论时间</td>
		<td>评论文章</td>
		<td>操作</td>
	</tr>
	<%
		int i = 0;
		for(Comment comment: commentsList)
		{
			i++;
			String className = "";
			if(i%2==0) {
				className = "odd_tr";
			}
			else {
				className = "even_tr";
			}
	%>
			<tr class="<%=className%>">
				<td>
					<%= comment.getId() %>
				</td>
				<td>
					<%= comment.getNickname() %>
				</td>
				<td>
					<%= comment.getContent() %>
				</td>
				<td>
					<%= comment.getCommentDate() %>
				</td>
				<td>
					<a href="manageArticleAdminServlet?id=<%=comment.getArticle().getId()%>">
						<%= comment.getArticle().getTitle() %>
					</a>
				</td>
				<td>
					<img src="../img/edit.gif" style="vertical-align: middle; cursor: pointer"
								alt="edit" onclick="editComment(<%=comment.getId()%>)"/>
					<img src="../img/delete.gif" style="vertical-align: middle; cursor: pointer" 
								alt="delete" onclick="deleteComment(<%=comment.getId()%>)"/>
				</td>
			</tr>
	<%
		}
	%>

</table>
</body>
</html>