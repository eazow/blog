<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	List<Category> categoriesList = (List<Category>)request.getAttribute("categoriesList");
	ArticleArchive articleArchive = (ArticleArchive)request.getAttribute("articleArchive");
%>
<html>
	<head>
		<link rel="shortcut icon" href="../favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="../css/index.css" media="screen" />
		<title>Eazow's Blog</title>
		<script type="text/javascript">
			function checkForm()
			{
				document.getElementById("form1").submit();
			}
		</script>
	</head>
	<body>
		<center>
			<div class="container">
				<div class="header">
					<div class="title">
						<h1>
							<a href="#"
								style="text-decoration: none">Eazow's Blog</a>
						</h1>
					</div>
					<div class="navigation">
						<a href="manageArticlesAdminServlet">Home</a>
						<a href="#">Album</a>
						<a href="#">Profile</a>
						<a href="getAboutAdminServlet">About</a>
						<span style="margin-top: 56px; float: left">
						</span>
						<div class="clearer">
							<span></span>
						</div>
					</div>
				</div>
				<div class="main">
					<div class="content">
						<div style="font-weight: bold; margin-bottom: 10px">
							文章存档管理 > 修改文章存档
						</div>
						<form action="updateArticleArchivesAdminServlet" id="form1" method="post">
							<table width="95%" cellspacing="0">
								<tr style="background-color: #CCFFFF; height: 30px">
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										id
									</td>
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										<%= articleArchive.getId() %>
										<input type="hidden" name="id" value="<%= articleArchive.getId() %>"/>
									</td>
								</tr>
								<tr style="height: 36px">
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										年
									</td>
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										<input name="year" value="<%= articleArchive.getYear() %>"/>
									</td>
								</tr>
								<tr style="height: 36px">
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										月
									</td>
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										<input name="month" value="<%= articleArchive.getMonth() %>"/>
									</td>
								</tr>
								<tr style="height: 36px">
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										文章总数
									</td>
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										<input type="text" name="articlesNum" value="<%= articleArchive.getArticlesNum() %>"/>
									</td>
								</tr>
								<tr style="height: 36px">
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										对应文章
									</td>
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px;
										text-align: left">
										<%
											List<Article> articlesList = articleArchive.getArticles();
											int i = 0;
											for(Article article: articlesList)
											{
												i++;
										%>
												<%= i %>.&nbsp;
												<%= article.getTitle() %><br/>
										<%
											}
										%>
									</td>
								</tr>
								<tr style="height: 36px">
									<td>
									</td>
									<td>
										<input type="button" value=" 修 改 " onclick="checkForm()"/>
									</td>
								</tr>
							</table>
						</form>
					</div>
				
					<div class="sidenav">
						<a href="manageArticlesAdminServlet" style="text-decoration: none; cursor: pointer">
							<h1>
								文章管理
							</h1>
						</a>
						
						<a href="manageCategoriesAdminServlet" style="text-decoration: none; cursor: pointer">
							<h1>
								分类管理
							</h1>
						</a>
						
						<a href="manageArticleArchivesAdminServlet" style="text-decoration: none; cursor: pointer">
							<h1>
								文章存档管理
							</h1>
						</a>
						
						<a href="manageCommentsAdminServlet" style="text-decoration: none; cursor: pointer">
							<h1>
								文章评论管理
							</h1>
						</a>
						
						<a href="manageTagsAdminServlet" style="text-decoration: none; cursor: pointer">
							<h1>
								标签管理
							</h1>
						</a>
						
						<a href="manageMessagesAdminServlet" style="text-decoration: none; cursor: pointer">
							<h1>
								留言管理
							</h1>
						</a>
						
						<a href="manageMottosAdminServlet" style="text-decoration: none; cursor: pointer">
							<h1>
								格言管理
							</h1>
						</a>
						
						<a href="manageVisitRecordsAdminServlet" style="text-decoration: none; cursor: pointer">
							<h1>
								访问量管理
							</h1>
						</a>
					</div>
					<div class="clearer">
					</div>
				</div>
				<div class="footer">
					&copy;2010&nbsp;
					<a href="http://www.eazow.com">Eazow</a>&nbsp;
					<a href="http://www.miitbeian.gov.cn">鄂ICP备10202535号</a>&nbsp;
					<script src="http://s16.cnzz.com/stat.php?id=2381155&web_id=2381155&show=pic" language="javascript"></script>
				</div>
			</div>
		</center>
	</body>
</html>