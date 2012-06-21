<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*"%>
<%
	List<Message> messagesList = (List<Message>)request.getAttribute("messagesList");
	String location = "index";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/admin.css"
		media="screen" />
	<script type="text/javascript">
		function deleteMessage(id)
		{
			if(!confirm("确定删除?"))
			{
				return;
			}
			document.getElementById("messageIdInput").value = id;
			document.getElementById("deleteMessageForm").submit();
		}
		function editMessage(id)
		{
			document.getElementById("idInput").value = id;
			document.getElementById("editMessageForm").submit();
		}
	</script>
</head>
<body>
<form action="preEditMessageAdminServlet" id="editMessageForm" method="post">
	<input type="hidden" name="messageId" id="idInput"/>
</form>
<form action="deleteMessageAdminServlet" id="deleteMessageForm" method="post">
	<input type="hidden" name="messageId" id="messageIdInput"/>
</form>
<table class="content_table">
	<tr class="header_tr">
		<td>id</td>
		<td>昵称</td>
		<td>留言内容</td>
		<td>留言时间</td>
		<td>操作</td>
	</tr>
	<%
		int i = 0;
		for(Message message: messagesList)
		{
			String className = (++i%2==0)?"even_tr":"odd_tr";
	%>
			<tr class="<%=className%>">
				<td>
					<%= message.getId() %>
				</td>
				<td>
					<%= message.getNickname() %>
				</td>
				<td>
					<%= message.getContent() %>
				</td>
				<td>
					<%= message.getDate() %>
				</td>
				<td>
					<img src="../img/edit.gif" style="vertical-align: middle; cursor: pointer"
								alt="edit" onclick="editMessage(<%=message.getId()%>)"/>
					<img src="../img/delete.gif" style="vertical-align: middle; cursor: pointer" 
								alt="delete" onclick="deleteMessage(<%=message.getId()%>)"/>
				</td>
			</tr>
	<%
		}
	%>

</table>
</body>
</html>