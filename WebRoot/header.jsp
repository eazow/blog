<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*" %>
<html>
	<head>
		<script type="text/javascript" src="script/jquery.js"></script>
		<script type="text/javascript" src="syntaxhighlighter2.1/scripts/shCore.js"></script>
		<script type="text/javascript" src="syntaxhighlighter2.1/scripts/shBrushJava.js"></script>
		<script type="text/javascript" src="syntaxhighlighter2.1/scripts/shBrushJScript.js"></script>
		<script type="text/javascript" src="syntaxhighlighter2.1/scripts/shBrushCss.js"></script>
		<link type="text/css" rel="stylesheet" href="syntaxhighlighter2.1/styles/shCore.css"/>
		<link type="text/css" rel="stylesheet" href="syntaxhighlighter2.1/styles/shThemeDefault.css"/>
		<script type="text/javascript">
			SyntaxHighlighter.config.clipboardSwf = 'syntaxhighlighter2.1/scripts/clipboard.swf';
			SyntaxHighlighter.all();
		</script>
		<!-- 添加favicon.ico -->
		<link rel="shortcut icon" href="favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="css/index.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="css/calendar.css"/>
		<script type="text/javascript" src="script/calendar.js"></script>
		<title>Eazow <%Motto motto = (Motto)request.getAttribute("motto");if(null!=motto)out.print("-");%> ${motto.content}</title>
	</head>
	<body>
		<div class="header">
			<div class="header_content">
				<div class="title">
					<h1>
						<a href="http://www.eazow.com" style="text-decoration: none">
							Eazow
						</a>
					</h1>
				</div>
				<ul class="navigation">
					<li><a href="index" <%if("index".equals(location)){%>class="active"<%}%> hidefocus>Home</a></li>
					<li><a href="albums" <%if("album".equals(location)){%>class="active"<%}%> hidefocus>Album</a></li>
					<li><a href="profile" <%if("profile".equals(location)){%>class="active"<%}%> hidefocus>Profile</a></li>
	 			</ul>
			</div>
		</div>
		<%@ include file="slides.html"%>
	<!--
		<div style="background-image: url('img/bg.png'); background-repeat: repeat-x; background-color: #D3E9F8"> 
	-->
		<div style="background-color: #fffffe">
			<div style="/*background-image: url('img/clouds.png'); */background-repeat: no-repeat; padding-top: 20px; background-position: -28px 300px">
			
			<!--	<div id="mainWrapper">-->
				<!--
				<div id="contentCHead"></div>
				-->
