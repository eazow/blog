<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	Message message = (Message)request.getAttribute("message");
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
					<div class="content" style="width: 700px">
						<div style="font-weight: bold; margin-bottom: 10px">
							留言管理 > 修改留言
						</div>
						<form action="updateMessageAdminServlet" id="form1" method="post">
							<table width="95%" cellspacing="0">
								<tr style="background-color: #CCFFFF; height: 30px">
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										id
									</td>
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										<%= message.getId() %>
										<input type="hidden" name="id" value="<%= message.getId() %>"/>
									</td>
								</tr>
								<tr style="height: 36px">
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										昵称
									</td>
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										<input name="nickname" value="<%= message.getNickname() %>"/>
									</td>
								</tr>
								<tr style="height: 36px">
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										内容
									</td>
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										<textarea name="content" rows="6" cols="50"><%= message.getContent() %>
										</textarea>
									</td>
								</tr>
								<tr style="height: 36px">
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										留言日期
									</td>
									<td style="border-bottom-style: dotted; border-color: #CCFFFF; border-width: 1px">
										<input type="text" name="date" value="<%= message.getDateDisplay() %>"/>
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