<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.eazow.blog.entity.*"%>
<%
	List<Draft> draftsList = (List<Draft>)request.getAttribute("draftsList");
	String location = "index";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
	<head>
		<link rel="shortcut icon" href="../favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="../css/index.css"
			media="screen" />
		<title>Eazow's blog</title>
	</head>
	<body>
		<%@ include file="header.jsp"%>
		<div class="container">
			<div class="main">
				<div class="content" style="width: 700px">
					<div style="font-weight: bold; margin-bottom: 10px">
						文章管理 > 草稿箱
					</div>
					<%
						if(0 == draftsList.size())
						{
					%>
							暂时没有草稿
					<%
						}
						else
						{
							for (Draft draft: draftsList)
							{
					%>
								<h1>
									<a href="#">
										<%= draft.getTitle() %>
									</a>
									<a href="postDraftAdminServlet?id=<%=draft.getId()%>" 
										style="color: black; font-size: 12px">
										[发表]
									</a>
								</h1>
								<div class="descr">
									<%= draft.getPostDateDisplay() %>
								</div>
								<p>
									<%= draft.getContent() %>
									<%	
										Category category = draft.getCategory();
									%>
								</p>
								<div style="text-align: right; border-bottom-style: dashed; border-bottom-width: 1px; 
									border-bottom-color: #DDD; padding-bottom: 10px">
									标签:&nbsp;<%= draft.getTags() %>
									&nbsp;|&nbsp;
									分类:&nbsp;<%=category.getName()%>
									&nbsp;|&nbsp;
									<a href="#">edit</a>&nbsp;|&nbsp;
									<a href="deleteDraftAdminServlet?id=<%=draft.getId()%>" onclick="return confirm('确定删除?')">
										delete
									</a>
								</div>
								<br />
					<%
							}
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
									style="height: 15px; width: 13px; border: 0; vertical-align: middle"/>
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
	</body>
</html>