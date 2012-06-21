<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	List<Album> albumsList = (List<Album>)request.getAttribute("albumsList");
	String location = "album";
%>
<html>
	<head>
		<link rel="shortcut icon" href="../favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="../css/index.css" media="screen" />
		<title>Eazow's Blog</title>
		<style type="text/css">
			.albumDiv
			{
				margin-left: 10px;
				margin-top: 20px;
				background-repeat: no-repeat;
				width: 210px; 
				height:210px; 
				cursor: pointer;
				padding-top: 1px;
				background-image: url('../img/album_bg.gif');
				padding: 2px;
			}
		</style>
		
		<script type="text/javascript">
			function getImagesOfAlbum(albumId)
			{
				document.getElementById("albumIdInput").value = albumId;
				document.getElementById("form1").submit();
			}
		</script>
	</head>

	<body>
		<%@ include file="header.jsp"%>
		<div class="container">
			<div class="main">
				<div class="content">
					<div style="background-color: white; border-style: solid; border-width: 1px; border-color: #ccc">
					  	<div style="background-color: #9cf; font-size: 14px; padding-top:3px; padding-left: 10px">相册</div>
					  	<div style="padding-top: 10px; padding-left: 3px">
						  	<table>
						  		<tr>
						  			<%
						  				int i = 0;
						  				for(Album album: albumsList)
						  				{
						  					i++;
						  			%>
							  				<td>
								  				<div class="albumDiv" onclick="getImagesOfAlbum(<%=album.getId()%>)">
										  			<img src="../<%=album.getCoverImage().getLowResolutionUrl()%>" 
										  				style="max-width: 198px; max-height: 200px"
										  				onclick="getImagesOfAlbum(<%=album.getId()%>)"/>
											  	</div>
											  	<div style="text-align: center; font-weight: bold; font-size: 12px">
											  		<%= album.getName() %>(<%= album.getImagesNum() %>)
											  	</div>
											  	<div style="text-align: center; font-weight: bold; font-size: 12px">
											  		<a href="#">编辑</a>|
											  		<a href="deleteAlbumAdminServlet?albumId=<%=album.getId()%>" onclick="return confirm('确认删除')">删除</a>
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
						 <form action="manageImagesOfAlbumAdminServlet" id="form1" method="post">
						 	<input type="hidden" name="albumId" id="albumIdInput"/>
						 </form>
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
							<a href="#">相册</a>
						</li>
					</ul>
					<h1>
						<a href="preUploadImageAdminServlet" style="text-decoration: none">
							上传图片
						</a>
					</h1>
				</div>
				<div class="clearer">
					<span></span>
				</div>
			</div>
			<div class="footer" style="margin-top: 100px">
				&copy;2010&nbsp;
				<a href="http://www.eazow.com">Eazow</a>
				&nbsp;
				<a href="http://www.miitbeian.gov.cn">鄂ICP备10202535号</a>&nbsp;
				<script src="http://s16.cnzz.com/stat.php?id=2381155&web_id=2381155&show=pic" language="JavaScript"></script>
			</div>
		</div>
	</body>
</html>