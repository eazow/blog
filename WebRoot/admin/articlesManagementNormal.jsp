<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.eazow.blog.entity.*"%>
<%
	Admin admin = (Admin)session.getAttribute("admin");
	List<Article> articlesList = (List<Article>)request.getAttribute("articlesList");
	List<Category> categoriesList = (List<Category>)request.getAttribute("categoriesList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
	<head>
		<link rel="shortcut icon" href="../favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="../css/index.css"
			media="screen" />
		<title>Eazow's blog</title>
		<!-- syntaxhighlighter -->
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shCore.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushBash.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushCpp.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushCSharp.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushCss.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushDelphi.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushDiff.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushGroovy.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushJava.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushJScript.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushPhp.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushPlain.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushPython.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushRuby.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushScala.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushSql.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushVb.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushXml.js"></script>
		<link type="text/css" rel="stylesheet" href="../syntaxhighlighter2.1/styles/shCore.css"/>
		<link type="text/css" rel="stylesheet" href="../syntaxhighlighter2.1/styles/shThemeDefault.css"/>
		<script type="text/javascript">
			SyntaxHighlighter.config.clipboardSwf = '../syntaxhighlighter2.1/scripts/clipboard.swf';
			SyntaxHighlighter.all();
		</script>
		<!-- syntaxhighlighter -->
	</head>

	<body>
		<center>
			<div class="container">
				<div class="header">
					<div class="title">
						<h1>
							<a href="#"	style="text-decoration: none">Eazow's Blog</a>
						</h1>
					</div>
					<div class="navigation">
						<a href="manageArticlesAdminServlet">Home</a>
						<a href="#">Album</a>
						<a href="#">Profile</a>
						<a href="getAboutAdminServlet">About</a>
						<span style="margin-top: 56px; float: left">
							Welcome&nbsp;&nbsp;<%= admin.getUsername() %>
						</span>
						<div class="clearer">
							<span></span>
						</div>
					</div>
				</div>
				<div class="main">
					<div class="content">
						<%
							for (Article article : articlesList) {
						%>
	
						<h1>
							<a href="manageArticleAdminServlet?id=<%=article.getId()%>">
								<%=article.getId()%>.&nbsp;
								<%= article.getTitle() %>
							</a>
						</h1>
						<div class="descr">
							<%= article.getPostDateDisplay() %>
						</div>
						<p>
							<%= article.getContent() %>
							<%	
								Category category = article.getCategory();
							%>
						</p>
						<div
							style="text-align: right; border-bottom-style: dashed; border-bottom-width: 1px; border-bottom-color: #DDD; padding-bottom: 10px">
							category:&nbsp;
							<a href="manageArticlesOyCategoryAdminServlet?id=<%=category.getId()%>"> 
								<%=category.getName()%>
							</a> 
							&nbsp;|&nbsp;
							<a href="preEditArticleAdminServlet?id=<%=article.getId()%>">edit</a>&nbsp;|&nbsp;
							<a href="deleteArticleAdminServlet?id=<%=article.getId()%>" onclick="return confirm('确定删除')">
								delete
							</a>&nbsp;|&nbsp;
							comments(<%= article.getCommentsNum() %>)&nbsp;
						</div>
						<br />
						<%
							}
						%>
					</div>
	
					<div class="sidenav">
						<a href="manageArticlesAdminServlet" style="text-decoration: none; cursor: pointer">
							<h1>
								文章管理
							</h1>
						</a>
						<ul>
							<li>
								<a href="preAddArticleAdminServlet" style="text-decoration: none; cursor: pointer">
									<img src="../img/postnew.gif"
										style="height: 16px; width: 16px; border: 0; vertical-align: middle"/>
									写新文章
								</a>
							</li>
							<li>
								<a href="manageDraftsAdminServlet" style="text-decoration: none; cursor: pointer">
									<img src="../img/draft.jpg" 
										style="height: 15px; width: 15px; border: 0; vertical-align: middle"/>
									草稿箱(${draftsNum})
								</a>
							</li>
						</ul>
						
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
						<h1>
							Search
						</h1>
						<!-- 
						<form action="#">
							<div>
								<input type="text" name="search" class="styled" />
								<input type="button" value="search" class="button" disabled="disabled"/>
							</div>
						</form>
						 -->
					</div>
					<div class="clearer">
						<span></span>
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