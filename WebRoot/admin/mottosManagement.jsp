<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*"%>
<%
	List<Motto> mottosList= (List<Motto>)request.getAttribute("mottosList");
	String location = "index";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/admin.css"	media="screen" />
	<script type="text/javascript">
		function deleteMotto(id)
		{
			if(!confirm("确定删除?"))
			{
				return;
			}
		}
		function addMotto()
		{
			var mottoContent = document.getElementById("addMottoInput").value;
			if(null==mottoContent || ""==mottoContent)
			{
				document.getElementById("addMottoSpan").innerHTML = "请输入分类名";
				return;
			}
			document.getElementById("addMottoForm").submit();
		}
		function clearAddMottoSpan()
		{
			document.getElementById("addMottoSpan").innerHTML = "";
		}
	</script>
</head>
<body>
<div style="margin-top: 20px; margin-left: 20px">
	<form action="addMottoAdminServlet" id="addMottoForm" method="post">
		<img src="../img/plus.gif"/>添加新的格言:<br/>
		<input type="text" name="mottoContent" id="addMottoInput" size="100" onclick="clearAddMottoSpan()"/>	
		<input type="button" value="添加" onclick="addMotto()"/>			
		<span id="addMottoSpan" style="color: red; font-weight: bold">
			${ addMottoErrorMessage }
		</span>			
	</form>
</div>
<form action="deleteMottoAdminServlet" id="deleteMottoForm" method="post">
	<input type="hidden" name="mottoId" id="mottoIdInput"/>
</form>
<table class="content_table">
	<tr class="header_tr">
		<td style="width: 50px">id</td>
		<td>内容</td>
		<td>创建时间</td>
		<td>操作</td>
	</tr>
	<%
		int i=0;
		for(Motto motto: mottosList)
		{
			String className = (++i%2==0)?"even_tr":"odd_tr";
	%>
			<tr class="<%=className%>">
				<td>
					<%= motto.getId() %>
				</td>
				<td>
					<%= motto.getContent() %>
				</td>
				<td>
					<%= motto.getCreatedDate() %>
				</td>
				<td>
					<img src="../img/edit.gif" style="vertical-align: middle; cursor: pointer"
								alt="edit"/>
					<img src="../img/delete.gif" style="vertical-align: middle; cursor: pointer" 
								alt="delete" onclick="deleteComment(<%=motto.getId()%>)"/>
				</td>
			</tr>
	<%
		}
	%>
</table>
</body>
</html>