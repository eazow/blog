<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	List<Album> albumsList = (List<Album>)request.getAttribute("albumsList");
	String location = "album";
%>
<style type="text/css">
	
</style>
<%@ include file="header.jsp"%>
<div class="container">
	<div id="albumContent">
	  	<table width="90%" cellspacing="30">
	  		<tr>
	  			<%
	  				int i = 0;
	  				for(Album album: albumsList)
	  				{
	  					i++;
	  			%>
		  				<td>
			  				<div class="albumDiv" onclick="getImagesOfAlbum(<%=album.getId()%>)">
					  			<img src="<%=album.getCoverImage().getLowResolutionUrl()%>" 
					  				style="max-width: 198px; max-height: 200px"/>
						  	</div>
						  	<div style="text-align: center; font-weight: bold; font-size: 12px; cursor: pointer" 
						  		onclick="getImagesOfAlbum(<%=album.getId()%>)">
						  		<%= album.getName() %>(<%= album.getImagesNum() %>)
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
		<form action="getImagesOfAlbumServlet" id="form1" method="post">
			<input type="hidden" name="albumId" id="albumIdInput"/>
		</form>
		<div class="clearer"></div>
	</div>
</div>
<%@ include file="footer.jsp"%>
<script type="text/javascript">
	function getImagesOfAlbum(albumId)
	{
		document.getElementById("albumIdInput").value = albumId;
		document.getElementById("form1").submit();
	}
</script>