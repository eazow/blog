<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.eazow.blog.entity.Admin"%>
<%
	Admin admin = (Admin)session.getAttribute("admin");
	if(null == admin)
	{
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
	<head>
		<link rel="shortcut icon" href="../favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="../css/admin.css"
			media="screen" />
		<title>Eazow's blog</title>
		<!-- syntaxhighlighter -->
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shCore.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushJava.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushJScript.js"></script>
		<link type="text/css" rel="stylesheet" href="../syntaxhighlighter2.1/styles/shCore.css"/>
		<link type="text/css" rel="stylesheet" href="../syntaxhighlighter2.1/styles/shThemeDefault.css"/>
		<script type="text/javascript">
			SyntaxHighlighter.config.clipboardSwf = '../syntaxhighlighter2.1/scripts/clipboard.swf';
			SyntaxHighlighter.all();
		</script>
		<!-- syntaxhighlighter -->
	</head>
	<body>
	<div class="header">
		<div class="header_content">
			<div class="title">
				<h1>
					<a href="#" style="text-decoration: none">
						Eazow<br/>
					</a>
				</h1>
			</div>
			<ul class="navigation">
			<%-- 
				<li><a href="manageArticlesAdminServlet" <%if("index".equals(location)){%>class="active"<%}%>>Home</a></li>
				<li><a href="manageAlbumsAdminServlet" <%if("album".equals(location)){%>class="active"<%}%>>Album</a></li>
				<li><a href="#" <%if("profile".equals(location)){%>class="active"<%}%>>Profile</a></li>
				<li><a href="getAboutAdminServlet" <%if("about".equals(location)){%>class="active"<%}%>>About</a></li> --%>
			</ul>
			<div style="float: right; color: white">
				Welcome&nbsp;&nbsp;<%= admin.getUsername() %>
			</div>
		</div>
	</div>
	<div class="clearer"></div>
