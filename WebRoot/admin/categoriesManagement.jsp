<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.eazow.blog.entity.*"%>
<%
	List<Category> categoriesList = (List<Category>) request.getAttribute("categoriesList");
	String location = "index";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
	<head>
		<script type="text/javascript" src="../script/ajax.js"></script>
		<link href="../css/admin.css" rel="stylesheet">
		<script type="text/javascript">
			//flag==0表示没有分类正在修改
			var flag = 0;
			//flag1==0表示没有文章数在修改
			var flag1 = 0;
			//保存修改前的innerHTML
			var originalCategoryName;
			var originalArticlesNum;
			var tempHTML1;
			var tempHTML2;
			var tempHTML3;
			var tempHTML4;
			function editCategoryName(id)
			{
				if(1 == flag)
				{
					document.getElementById("errorDiv").innerHTML = "请不要同时修改多个分类";
					return;
				}
				if(flag == 0)
				{
					flag = 1;
				}
				var tdName = document.getElementById("tdName"+id);
				var tdNameOperation = document.getElementById("tdNameOperation"+id);
				var a = document.getElementById("a"+id);
				originalCategoryName = a.innerHTML;
				tempHTML1 = tdName.innerHTML;
				tempHTML2 = tdNameOperation.innerHTML;
				tdName.innerHTML = "<input id='text"+id+"' type='text' onclick='clearErrorMessage()' size='15'"
					+ " value='"+originalCategoryName+"'/>";
				tdNameOperation.innerHTML = "<input type='button' value='修改'"
					+ "onclick='updateCategoryName("+id+")' style='font-size: 7px'/>"
					+ "<a href='#' onclick='cancelEditCategoryName("+id+")'>取消</a>";
			}
			function clearErrorMessage()
			{
				document.getElementById("errorDiv").innerHTML = "";
			}
			function updateCategoryName(id)
			{
				var categoryName = document.getElementById("text"+id).value;
				if(null==categoryName || ""==categoryName)
				{
					document.getElementById("errorDiv").innerHTML = "请输入分类名";
					return;
				}
				if(categoryName == originalCategoryName)
				{
					document.getElementById("errorDiv").innerHTML = "请修改分类名";
					return;
				}
				document.getElementById("categoryIdInput").value = id;
				document.getElementById("categoryNameInput").value = categoryName;
				document.getElementById("categoryNameForm").submit();
			}
			function cancelEditCategoryName(id)
			{
				if(flag == 1)
					flag = 0;
				var tdName = document.getElementById("tdName"+id);
				var tdNameOperation = document.getElementById("tdNameOperation"+id);
				tdName.innerHTML = tempHTML1;
				tdNameOperation.innerHTML = tempHTML2;
				clearErrorMessage();
			}
			function editArticlesNum(id)
			{
				if(1 == flag1)
				{
					document.getElementById("errorDiv").innerHTML = "请不要同时修改多个文章总数";
					return;
				}
				if(flag1 == 0)
				{
					flag1 = 1;
				}
				var tdNum = document.getElementById("tdNum"+id);
				var tdNumOperation = document.getElementById("tdNumOperation"+id);
				originalArticlesNum = tdNum.innerHTML;
				tempHTML3 = tdNum.innerHTML;
				tempHTML4 = tdNumOperation.innerHTML;
				tdNum.innerHTML = "<input id='input"+id+"' type='text' onclick='clearErrorMessage()' size='15'"
					+ " value='"+originalArticlesNum+"'/>";
				tdNumOperation.innerHTML = "<input type='button' value='修改'"
					+ "onclick='updateArticlesNum("+id+")' style='font-size: 7px'/>"
					+ "<a href='#' onclick='cancelEditArticlesNum("+id+")'>取消</a>";
			}
			function cancelEditArticlesNum(id)
			{
				if(flag1 == 1)
					flag1 = 0;
				var tdNum = document.getElementById("tdNum"+id);
				var tdNumOperation = document.getElementById("tdNumOperation"+id);
				tdNum.innerHTML = tempHTML3;
				tdNumOperation.innerHTML = tempHTML4;
				clearErrorMessage();
			}
			function updateArticlesNum(id)
			{
				var articlesNum = document.getElementById("input"+id).value;
				if(null==articlesNum || ""==articlesNum)
				{
					document.getElementById("errorDiv").innerHTML = "请输入文章数";
					return;
				}
				if(articlesNum == originalArticlesNum)
				{
					document.getElementById("errorDiv").innerHTML = "请修改文章数";
					return;
				}
				document.getElementById("categoryIdInput3").value = id;
				document.getElementById("articlesNumInput").value = articlesNum;
				document.getElementById("articlesNumForm").submit();
			}
			function deleteCategory(id)
			{
				if(!confirm("确定删除?\n还有文章的分类将不会被删除"))
				{
					return;
				}
				document.getElementById("categoryIdInput2").value = id;
				document.getElementById("deleteCategoryForm").submit();
			}
			function addCategory()
			{
				var categoryName = document.getElementById("addCategoryInput").value;
				if(null==categoryName || ""==categoryName)
				{
					document.getElementById("addCategorySpan").innerHTML = "请输入分类名";
					return;
				}
				document.getElementById("addCategoryForm").submit();
			}
			function clearAddCagegorySpan()
			{
				document.getElementById("addCategorySpan").innerHTML = "";
			}
		</script>
	</head>
	<body>
		<div style="margin-top: 20px; margin-left: 20px">
			<form action="addCategoryAdminServlet" id="addCategoryForm" method="post">
				<img src="../img/plus.gif"/>添加新的分类
				<input type="text" name="categoryName" id="addCategoryInput" size="20" onclick="clearAddCagegorySpan()"/>	
				<input type="button" value=" 添加 " onclick="addCategory()"/>			
				<span id="addCategorySpan" style="color: red; font-weight: bold">
					${ addCategoryErrorMessage }
				</span>			
			</form>
		</div>
		<form action="updateCategoryNameAdminServlet" id="categoryNameForm" method="post">
			<input type="hidden" name="categoryId" id="categoryIdInput"/>
			<input type="hidden" name="categoryName" id="categoryNameInput"/>
		</form>
		<form action="deleteCategoryAdminServlet" id="deleteCategoryForm" method="post">
			<input type="hidden" name="categoryId" id="categoryIdInput2"/>
		</form>
		<form action="updateArticlesNumOfCategoryAdminServlet" id="articlesNumForm" method="post">
			<input type="hidden" name="categoryId" id="categoryIdInput3"/>
			<input type="hidden" name="articlesNum" id="articlesNumInput"/>
		</form>
		<table class="content_table">
			<tr class="header_tr">
				<td style="width: 30px">id</td>
				<td style="width: 150px">名称</td>
				<td style="width: 100px"></td>
				<td style="width: 100px">文章总数</td>
				<td style="width: 60px"></td>
				<td style="text-align: left">对应文章</td>
				<td>操作</td>
			</tr>
			<%
				int i=0;
				for(Category category: categoriesList)
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
							<%= category.getId() %>
						</td>
						<td id="tdName<%=category.getId()%>">
							<a id="a<%=category.getId()%>" href="manageArticlesOfCategoryAdminServlet?categoryId=<%=category.getId()%>">
								<%=category.getName()%>
							</a>
						</td>
						<td id="tdNameOperation<%=category.getId()%>" align="left">
							<img src="../img/edit.gif" style="vertical-align: middle; cursor: pointer"
								alt="edit" onclick="editCategoryName(<%=category.getId()%>)"/>
						</td>
						<td id="tdNum<%=category.getId()%>">
							<%= category.getArticlesNum() %>
						</td>
						<td align="left" id="tdNumOperation<%=category.getId()%>">
							<img src="../img/edit.gif" style="vertical-align: middle; cursor: pointer"
								alt="edit" onclick="editArticlesNum(<%=category.getId()%>)"/>
						</td>
						<%
							List<Article> articlesList = category.getArticles();
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
							<img src="../img/delete.gif" style="vertical-align: middle; cursor: pointer" 
								alt="delete" onclick="deleteCategory(<%=category.getId()%>)"/>
						</td>
					</tr>
			<%
				}
			%>
		
		</table>
	</body>
</html>