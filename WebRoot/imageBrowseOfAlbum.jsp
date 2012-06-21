<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	Image image = (Image)request.getAttribute("image");
	int indexLocationOfAlbum = image.getIndexLocationOfAlbum();
	Album album = image.getAlbum();
	String location = "album";
%>
<style type="text/css">
	.imageDiv
	{
		margin-left: 10px;
		margin-top: 20px;
	}
	.jcarousel-skin-tango .jcarousel-container-horizontal {
  			width: 85%;
	}
	.jcarousel-skin-tango .jcarousel-clip-horizontal {
  			width: 100%;
	}
</style>
<%@ include file="header.jsp"%>
<div class="container">
	<div class="main">
		<div class="content" style="width: 900px">
			<div style="background-color: white; border-style: solid; border-width: 1px; border-color: #ccc">
			  	<div style="background-color: #9cf; font-size: 14px; padding-top:3px; padding-left: 10px">
			  		<a href="getAllAlbumsServlet" 
			  			style="color: black; font-weight: bold; text-decoration: underline">相册</a> -- 
			  		<a href="getImagesOfAlbumServlet?albumId=<%=album.getId()%>" 
			  			style="color: black; font-weight: bold; text-decoration: underline">
			  			<%=album.getName()%>
			  		</a>
			  	</div>
			  	<div style="padding: 10px; text-align: center">
	  				<div class="imageDiv">
				  		<img src="${image.url}" id="currentImg" style="max-height: 860px; max-width: 860px"/>
				  	</div>
				  	<br/>
				  	<ul id="mycarousel" class="jcarousel-skin-tango">
				  	<%
				  		List<Image> imagesList = (List<Image>)request.getAttribute("imagesList");
				  	 	for(Image imageTemp: imagesList)
				  	 	{
				  	%>
						<li>
							<img src="<%=imageTemp.getLowResolutionUrl()%>"
								style="max-width: 180px; max-height: 180px"
								onclick="viewImage('<%=imageTemp.getUrl()%>')"/>
						</li>
					<%
				  	 	}
					%>
				  	</ul>
				</div>
		  	</div>
		</div>
		<div class="clearer"><span></span></div>
	</div>
</div>
<%@ include file="footer.jsp"%>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery('#mycarousel').jcarousel({
			visible: 3
		});
	});
	
	function viewImage(url)
	{
		document.getElementById("currentImg").src = url;
	}
</script>