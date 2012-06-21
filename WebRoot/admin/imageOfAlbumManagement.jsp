<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	Image image = (Image)request.getAttribute("image");
	int indexLocationOfAlbum = image.getIndexLocationOfAlbum();
	Album album = image.getAlbum();
%>
<html>
	<head>
		<link rel="shortcut icon" href="../favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="../css/index.css" media="screen" />
		<title>My Space</title>
		
		<script type="text/javascript">
			function addUnderline(theHiperlinks)
			{
				theHiperlinks.style.textDecoration = "underline";
			}
			function removeUnderline(theHiperlinks)
			{
				theHiperlinks.style.textDecoration = "none";
			}
			
			function manageNextImage(imageId)
			{
				document.getElementById("imageIdInput1").value = imageId;
				document.getElementById("nextImageForm").submit();
			}
			function managePreviousImage(imageId)
			{
				document.getElementById("imageIdInput2").value = imageId;
				document.getElementById("previousImageForm").submit();
			}
		</script>
		
		<style type="text/css">
			.imageDiv
			{
				margin-left: 10px;
				margin-top: 20px;
				width: 650px; 
				height: 650px; 
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
				<div class="header">
					<div class="title">
						<h1>
							<a href="/blog/servlet/ListBlogsServlet"
								style="text-decoration: none">Eazow's Blog</a>
						</h1>
					</div>
					<div class="navigation">
						<a href="manageArticlesAdminServlet">Home</a>
						<a href="manageAlbumsAdminServlet">Album</a>
						<a href="getProfileServlet">Profile</a>
					</div>
				</div>
				<div class="main">
					<div class="content">
						<div style="background-color: white; border-style: solid; border-width: 1px; border-color: #ccc">
						  	<div style="background-color: #9cf; font-size: 14px; padding-top:3px; padding-left: 10px">图片</div>
						  	<div style="padding: 10px; text-align: center">
				  				<div class="imageDiv">
							  		<img src="../${image.url}" style="max-height: 650px; max-width: 650px"/>
							  	</div>
							  	<br/>
							  	<div>
							  		<input type="button" value="上一张" onclick="managePreviousImage(${image.id})"
							  			<%
							  				if(indexLocationOfAlbum == 1)
							  				{
							  			%>
							  					disabled="true"
							  			<%
							  				}
							  			%>
							  			/>
							  		&nbsp;<span style="font-size: 13px"><%=indexLocationOfAlbum%>/<%=album.getImagesNum()%></span>&nbsp;
							  		<input type="button" value="下一张" onclick="manageNextImage(${image.id})"
							  			<%
							  				if(indexLocationOfAlbum == album.getImagesNum())
							  				{
							  			%>
							  					disabled="true"
							  			<%
							  				}
							  			%>
							  			/>
							  	</div>
							  	<div style="margin-top: 5px; font-weight: bold">
							  		<a href="#">编辑</a>|<a href="#">删除</a>
							  	</div>
							  	<form action="manageNextImageOfAlbumAdminServlet" method="post" id="nextImageForm">
							  		<input type="hidden" name="imageId" id="imageIdInput1"/>
							  	</form>
							  	<form action="managePreviousImageOfAlbumAdminServlet" method="post" id="previousImageForm">
							  		<input type="hidden" name="imageId" id="imageIdInput2"/>
							  	</form>
							</div>
					  	</div>
					</div>
					
					<div class="sidenav">
						<h1>
							Album
						</h1>
						<ul>
							<li>
								<a href="manageLatestImagesAdminServlet">最新图片</a>
							</li>
							<li>
								<a href="manageAlbumsAdminServlet">相册</a>
							</li>
						</ul>
					</div>
				
					<div class="clearer">
						<span></span>
					</div>
				</div>
				<div class="footer">
					&copy;2010&nbsp;
					<a href="http://www.eazow.com">Eazow</a>&nbsp;
					<a href="http://www.miitbeian.gov.cn">鄂ICP备10202535号</a>&nbsp;
					<script src="http://s16.cnzz.com/stat.php?id=2381155&web_id=2381155&show=pic" language="JavaScript"></script>
				</div>
			</div>
		</center>
	</body>
</html>