<%--------------------------------------------------------
说明: 图片上传页面
---------------------------------------------------------%>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	List<Album> albumsList = (List<Album>)request.getAttribute("albumsList");
%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../css/index.css" media="screen" />
		<title>My Space</title>
		<script type="text/javascript" src="../script/ajax.js"></script>
	</head>
	<body>
		<center>
		<div class="container">
			<div class="header">
				<div class="title">
					<h1>
						<a href="/blog/servlet/ListBlogsServlet"
							style="text-decoration: none">My Blog</a>
					</h1>
				</div>
				<div class="navigation">
					<a href="">Home</a>
					<a href="manageAlbumsAdminServlet">Album</a>
					<a href="#">Profile</a>
					<a href="#">About</a>
				</div>
			</div>
			<div class="main">
				<div class="content" style="width: 700px; padding-top: 20px">
					<div style="background-color: white; border-style: solid; border-width: 1px; border-color: #ccc">
					  	<div style="background-color: #9cf; font-size: 14px; padding-top:3px; padding-left: 10px">上传图片</div>
					  	<div style="padding-left: 10px; padding-top:30px; padding-bottom: 30px; padding-right: 10px">
							<form id="imagesForm" action="uploadImagesAdminServlet" method="post" enctype="multipart/form-data">
								<span id="albumSelectSpan">
								<select id="albumsSelect" name="albumId" label="选择相册">
									<%
										for(Album album: albumsList)
										{
									%>
											<option value="<%=album.getId()%>"><%= album.getName() %></option>
									<%
										}
									%>
								</select>
								</span>
								&nbsp;&nbsp;<span id="albumSpan" style="cursor: pointer">
								<span onclick="preAddAlbum()"><img src="../img/plus.gif"/>创建新相册</span></span>
								<span id="errorMessage" style="color: red; padding-left: 30px"></span>
 								<hr style="height: 1px; border-style: dotted; color: #9cf"/>
 								<div id="imageDiv">
 									<div>
										图片: 
									</div>
									<div>
										<input type="file" id="myImage" name="myImages" size="80" style="font-size: 12px"/>
									</div>
								</div>
								<br/>
 								<input type="button" onclick="addImage()" value=" 添加图片 " style="font-size: 8px" style="cursor: pointer"></input>
						  		<input type="button" id="submitButton" value=" 上传图片 " style="font-size: 8px" style="cursor: pointer" 
						  			onclick="validateForm()"/>
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
					<h1>
						上传图片
					</h1>
				</div>
			
				<div class="clearer">
					<span></span>
				</div>
			</div>
			<div class="footer">
				&copy; 2010 &nbsp;
				<a href="http://www.eazow.cn">Eazow</a>
			</div>
		</div>
		</center>
	</body>
</html>
<script type="text/javascript">
function preAddAlbum()
{
	var albumSpan = document.getElementById("albumSpan");
	albumSpan.innerHTML = "<input id='albumName' type='text' onclick='clearErrorMessage()'/>"
		+"<input type='button' style='font-size: 8px' value='创建' onclick='addAlbum()'/>&nbsp;"
		+"<a href='#' onclick='cancelAddAlbum()'>取消</a>";
}
function addAlbum()
{
	var albumNameInput = document.getElementById("albumName");
	var albumName = albumNameInput.value;
	if(null == albumName || ""==albumName)
	{
		var theSpan = document.getElementById("errorMessage");
		theSpan.innerHTML = "请输入相册名称";
		return;
	}
	//ajax
	var xmlHttpRequest = getXmlHttpRequest();
	xmlHttpRequest.onreadystatechange = function()
	{
		if(xmlHttpRequest.readyState == 4)
		{
			var responseText = xmlHttpRequest.responseText;
			//出错,相册名称重复
			if(responseText.indexOf("ERROR")!=-1)
			{
				alert("请输入不同相册名");
			}
			else
			{
				document.getElementById("albumSelectSpan").innerHTML = responseText;
				cancelAddAlbum();
			}
		}
	}
	url = "ajaxAddAlbumAdminServlet?albumName=" + albumName;
	url = encodeURI(encodeURI(url));
	xmlHttpRequest.open("POST", url, "true");
	xmlHttpRequest.send(null);
}
function createSelect(albumsList)
{
	var albumsSelect = document.getElementById("albumsSelect");
	var selectLength = albumsSelect.length;
	for(var i=0; i<selectLength; i++)
	{
		albumsSelect.remove(0);
	}
	for(var index in albumsList)
	{
		var selectOption;
		for(var key in albumsList[index])
		{
			if("id" == key)
			{
				selectOption = document.createElement("option");
				selectOption.value = albumsList[index][key];
			}
			if("name" == key)
			{
				selectOption.text = albumsList[index][key];
				albumsSelect.add(selectOption);
			}
		}
	}
}
function cancelAddAlbum()
{
	var albumSpan = document.getElementById("albumSpan");
	albumSpan.innerHTML = "<span onclick='preAddAlbum()'><img src='../img/plus.gif'/>创建新相册</span>";
	clearErrorMessage();
}
function clearErrorMessage()
{
	var theSpan = document.getElementById("errorMessage");
	theSpan.innerHTML = "";
}

function validateForm()
{
	var myImages = document.getElementsByName("myImages");
	for(var i=0; i<myImages.length; i++)
	{
		var myImage = myImages[i];
		if(null==myImage.value || ""==myImage.value)
		{
			alert("请选择图片");
			myImage.focus();
			return;
		}
	}
	document.getElementById("imagesForm").submit();			
}

function addImage()
{
	var imageDiv = document.getElementById("imageDiv");
	imageDiv.innerHTML += 
		"<div><input type='file' id='myImage' name='myImages' size='80' style='font-size: 12px'/>"
		+"<img src='../img/delete.gif' align='center' onclick='deleteImage(this)'/></div>";
}

function deleteImage(theImage)
{
	var theDiv = theImage.parentNode;
	var imageDiv = document.getElementById("imageDiv");
	imageDiv.removeChild(theDiv);
}
</script>