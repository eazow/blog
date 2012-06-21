<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.eazow.blog.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	List<Category> categoriesList = (List<Category>)request.getAttribute("categoriesList");
	Article article = (Article)request.getAttribute("article");	
	List<Comment> commentsList = article.getComments();
	String location = "index";
%>
<html>
	<head>
		<link rel="shortcut icon" href="../favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="../css/index.css" media="screen" />
		<title>Eazow's Blog</title>
		<!-- syntaxhighlighter -->
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shCore.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushJava.js"></script>
		<link type="text/css" rel="stylesheet" href="../syntaxhighlighter2.1/styles/shCore.css"/>
		<link type="text/css" rel="stylesheet" href="../syntaxhighlighter2.1/styles/shThemeDefault.css"/>
		<script type="text/javascript">
			SyntaxHighlighter.config.clipboardSwf = '../syntaxhighlighter2.1/scripts/clipboard.swf';
			SyntaxHighlighter.all();
		</script>
		<!-- syntaxhighlighter -->
		<script type="text/javascript">
			function addComment()
			{
				var theForm = document.getElementById("commentForm");
				var commentContent = document.getElementById("commentContent");
				var verificationCodeInput = document.getElementById("verificationCodeInput");
				var errorSpan = document.getElementById("errorSpan");
				if(commentContent.value=="" || commentContent.value==null)
				{
					errorSpan.innerHTML = "请输入评论";
					return;
				}
				if(verificationCodeInput.value=="" || verificationCodeInput.value==null)
				{
					errorSpan.innerHTML = "请输入验证码";
					return;
				}
				theForm.submit();
			}
			function clearErrorMessage()
			{
				var errorSpan = document.getElementById("errorSpan");
				errorSpan.innerHTML = "";
			}
			function refreshVerificationCode()
			{
				document.getElementById("verificationCodeImage").src = "../getVerificationCodeServlet?time=" + new Date();
			}
			function confirmDeleteComment()
			{
				if(confirm("删除这条评论"))
				{
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body>
		<%@ include file="header.jsp"%>
		<div class="container">
			<div class="main">
				<div class="content" style="width: 700px">
					<div>
						<h1>
							${article.title}
						</h1>
						<div class="descr">
							${article.postDateDisplay}
						</div>
						<p>
							${article.content}
						</p>
					</div>
					<div style="text-align: right; border-bottom-style: dashed; border-bottom-width: 1px; border-bottom-color: #DDD; 
						padding-bottom: 10px; font-size: 12px">
						标签:
						<%
							List<Tag> tags = article.getTags();
							for(Tag tag: tags)
							{
						%>
								<a href="getArticlesOfTagServlet?tagId=<%=tag.getId()%>">
									<%= tag.getName() %>
								</a>
						<%
							}
						%>
						|&nbsp;
						分类:
						<a href="getArticlesOfCategoryServlet?categoryId=<%=article.getCategoryId()%>">
							<%= article.getCategory().getName() %>
						</a>|&nbsp;
						阅读(<%=article.getViewCount()%>)&nbsp;|&nbsp;
						评论(<%= article.getCommentsNum() %>)
						|&nbsp;<a href="#">编辑</a>
						|&nbsp;<a href=#">删除</a>
					</div>
					<%
						int i=0;
						for(Comment comment: commentsList)
						{
							i++;
					%>
							<div style="padding-left: 10px; padding-top: 10px; border-bottom-style: dashed; 
								border-bottom-width: 1px; border-bottom-color: #DDD; padding-bottom: 10px; font-size: 12px">
								<div style="font-size: 10px; color: #ccc">
									<span style="color: #09f; font-weight: bold">
										<%= comment.getNickname() %>
									</span>
									发表于: <%= comment.getCommentDateDisplay() %>
									&nbsp;
									<a href="deleteCommentAdminServlet?commentId=<%=comment.getId()%>"
										onclick="return confirmDeleteComment()" hidefocus>
										<img src="../img/delete.gif" alt="删除评论" 
											style="vertical-align: middle; cursor: pointer; border: 0"/>
									</a>
								</div>
								<div style="padding-left: 10px; padding-top: 10px">
									<p>
										<%= comment.getContent() %>
									</p>
								</div>
							</div>
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
	</body>
</html>