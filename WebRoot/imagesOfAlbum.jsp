<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	List<Image> imagesList = (List<Image>)request.getAttribute("imagesList");
	Album album = (Album)request.getAttribute("album");
	String location = "album";
%>
<style type="text/css">
	.imageDiv
	{
		margin-left: 10px;
		margin-top: 20px;
		width: 200px; 
		height:200px; 
		border-color: #ccc;
		border-width: 1px;
		padding-top: 1px;
		padding-left: 1px;
		border-style: solid;
		text-align: center;
		vertical-align: middle;
	}
</style>
<script type="text/javascript">
	function browseImage(id)
	{
		document.getElementById("imageIdInput").value = id;
		document.getElementById("form1").submit();
	}
</script>
<%@ include file="header.jsp"%>
<div class="container">
	<div class="main">
		<div class="content" style="width: 900px">
			<div style="background-color: white; border-style: solid; border-width: 1px; border-color: #ccc">
			  	<div style="background-color: #9cf; font-size: 14px; padding-top:3px; padding-left: 10px">
			  		<a href="getAllAlbumsServlet" 
			  			style="font-weight: bold; color: black; text-decoration: underline">
			  			相册</a>
			  		--
			  		<span style="font-weight: bold">
			  			${album.name}
			  		</span>
			  	</div>
			  	<div style="padding: 10px">
			  		<form action="browseImageOfAlbumServlet" method="post" id="form1">
			  			<input type="hidden" name="imageId" id="imageIdInput"/>
			  		</form>
				  	<table>
				  		<tr>
				  			<%
				  				int i = 0;
				  				for(Image image: imagesList)
				  				{
				  					i++;
				  			%>
					  				<td>
						  				<div class="imageDiv">
						  					<p>
									  			<img src="<%=image.getLowResolutionUrl()%>" style="max-height: 198px; max-width: 199px; cursor: pointer"
									  				onclick="browseImage(<%=image.getId()%>)"/>
									  		</p>
									  	</div>
									  	<div style="text-align: center">
									  		<a href="#" style="cursor: pointer; font-weight: bold; font-size: 12px; 
									  			text-decoration: none" onclick="browseImage(<%=image.getId()%>)">
									  			<%= image.getName() %>
									  		</a>
									  	</div>
						  			</td>
						  	<%
						  			if(i%4 == 0)
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
		<div class="clearer"><span></span></div>
	</div>
</div>
<%@ include file="footer.jsp"%>