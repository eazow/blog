<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	List<Image> latestImagesList = (List<Image>)request.getAttribute("latestImagesList");
%>
<html>
	<head>
		<link rel="shortcut icon" href="favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="css/index.css" media="screen" />
		<title>Eazow's Blog</title>
		<style type="text/css">
			.imageDiv
			{
				margin-left: 10px;
				margin-top: 20px;
				width: 200px; 
				height:200px; 
				cursor: pointer;
				border-color: #ccc;
				border-width: 1px;
				padding-top: 1px;
				padding-left: 1px;
				border-style: solid;
				text-align: center;
				vertical-align: middle;
			}
		</style>
	</head>

	<body>
		<center>
		<div class="container">
			<%@ include file="header.jsp"%>
			<div class="main">
				<div class="content">
					<div style="background-color: white; border-style: solid; border-width: 1px; border-color: #ccc">
					  	<div style="background-color: #9cf; font-size: 14px; padding-top:3px; padding-left: 10px">最新图片</div>
					  	<div style="padding: 10px">
						  	<table>
						  		<tr>
						  			<%
						  				int i = 0;
						  				for(Image image: latestImagesList)
						  				{
						  					i++;
						  			%>
							  				<td>
								  				<div class="imageDiv">
								  					<p>
											  			<img src="<%=image.getLowResolutionUrl()%>" style="max-height: 198px; max-width: 199px"/>
											  		</p>
											  	</div>
											  	<div style="text-align: center; font-weight: bold; font-size: 12px; cursor: pointer">
											  		<%= image.getName() %>
											  	</div>
								  			</td>
								  	<%
										  	if(i%3 == 0)
							  				{
								  	%>
								  				</tr><tr>
								  	<%
							  				}
						  				}
								  	%>
						  		</tr>
						  		
						  	</table>
						  </div>
				  	</div>
				</div>
				
				<div class="sidenav">
					<h1>
						Album
					</h1>
					<ul>
						<li>
							<a href="#">最新图片</a>
						</li>
						<li>
							<a href="getAllAlbumsServlet">相册</a>
						</li>
					</ul>
					<!-- 
					<h1>
						<a href="preUploadImage.action" style="text-decoration: none">上传新图片</a>
					</h1>
					 -->
				</div>
			
				<div class="clearer">
					<span></span>
				</div>
			</div>
			<%@ include file="footer.jsp"%>
		</div>
		

	</body>
</html>