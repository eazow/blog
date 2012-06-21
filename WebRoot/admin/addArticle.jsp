<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	List<Category> categoriesList = (List<Category>)request.getAttribute("categoriesList");
	List<Tag> tagsList = (List<Tag>)request.getAttribute("tagsList");
	String location = "index";
%>
<html>
	<head>
		<link rel="shortcut icon" href="../favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="../css/index.css" media="screen" />
		<title>Eazow's Blog</title>
		<!-- syntaxhighlighter -->
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shCore.js"></script>
		<script type="text/javascript" src="../syntaxhighlighter2.1/scripts/shBrushJava.js"></script>
		<link type="text/css" rel="stylesheet" href="../syntaxhighlighter2.1/styles/shCore.css"/>
		<link type="text/css" rel="stylesheet" href="../syntaxhighlighter2.1/styles/shThemeDefault.css"/>
		<script type="text/javascript">
			SyntaxHighlighter.config.clipboardSwf = '../syntaxhighlighter2.1/scripts/clipboard.swf';
			SyntaxHighlighter.all();
		</script>
		<!-- syntaxhighlighter -->
		<style type="text/css">
			#articleCategoriesTable td
			{
				border-bottom-style: solid;
				border-bottom-width: 1px;
			}
			img
			{
				cursor: pointer;
			}
		</style>
		<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="../script/ajax.js"></script>
		<script type="text/javascript">
			var categorySpanInnerHTML;
			function checkForm()
			{
				var title = document.getElementById("titleInput").value;
				var postDate = document.getElementById("postDateInput").value;
				if(null==title || ""==title)
				{
					document.getElementById("titleSpan").innerHTML = "请输入标题";
					return;
				}
				if(null==postDate || ""==postDate)
				{
					document.getElementById("postDateSpan").innerHTML = "请输入时间";
				}
				if(CKEDITOR.instances.editor1.getData() == "")
				{
					alert("请输入文章内容");
					return;
				}
				var tags = document.getElementById("tagsInput").value;
				if(null == tags || ""==tags)
				{
					alert("请输入标签");
					return;
				}
				document.getElementById("content").value = CKEDITOR.instances.editor1.getData();
				document.getElementById("form1").submit();
			}
			function saveAsDraft()
			{
				var title = document.getElementById("titleInput").value;
				if(null==title || ""==title)
				{
					document.getElementById("titleSpan").innerHTML = "请输入标题";
					return;
				}
				if(CKEDITOR.instances.editor1.getData() == "")
				{
					alert("请输入文章内容");
					return;
				}
				document.getElementById("content").value = CKEDITOR.instances.editor1.getData();
				document.getElementById("form1").action="addDraftAdminServlet";
				document.getElementById("form1").submit();
			}
			function clearTitleMessage()
			{
				document.getElementById("titleSpan").innerHTML = "";
			}
			function clearPostDateMessage()
			{
				document.getElementById("postDateSpan").innerHTML = "";
			}
			function preAddCategory()
			{
				var categorySpan = document.getElementById("categorySpan");
				categorySpanInnerHTML = categorySpan.innerHTML;
				categorySpan.innerHTML = "分类名称<input type='text' name='categoryName' id='categoryNameInput' size='20'/>" 
					+ "<input type='button' style='font-size: 8px' onclick='addCategory()' value='新增分类'/>&nbsp;"
					+ "<a href='#' onclick='cancelAddCategory()'>取消</a>"
			}
			function cancelAddCategory()
			{
				document.getElementById("categorySpan").innerHTML = categorySpanInnerHTML;
				document.getElementById("categoryErrorSpan").innerHTML = "";
			}
			function addCategory()
			{
				var categoryName = document.getElementById("categoryNameInput").value;
				if(null==categoryName || ""==categoryName)
				{
					document.getElementById("categoryErrorSpan").innerHTML = "请输入分类名";
					return;
				}
				//ajax
				var xmlHttpRequest = getXmlHttpRequest();
				xmlHttpRequest.onreadystatechange = function()
				{
					if(xmlHttpRequest.readyState == 4)
					{
						var responseText = xmlHttpRequest.responseText;
						//出错,分类名称重复
						if(responseText.indexOf("ERROR")!=-1)
						{
							alert("请输入不同分类名");
						}
						else
						{
							document.getElementById("selectCategorySpan").innerHTML = responseText;
							cancelAddCategory();
						}
					}
				}
				var url = "ajaxAddCategoryAdminServlet?categoryName=" + categoryName;
				url = encodeURI(encodeURI(url));
				xmlHttpRequest.open("post", url, "true");
				xmlHttpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded;charset=utf-8");
				xmlHttpRequest.send(null);
			}
			//Tag
			function addTag(theOption)
			{
				var value = theOption.value;
				if("请选择标签" != value)
				{
					var tagsInput = document.getElementById("tagsInput");
					if(tagsInput.value == "")
						tagsInput.value += theOption.value;
					else
						tagsInput.value += " " + theOption.value;
				}
			}
		</script>
	</head>
	<body>
		<%@ include file="header.jsp"%>
		<div class="container">
			<div class="main">
				<div class="content">
					<div style="font-weight: bold; margin-bottom: 10px">
						文章管理 > 创建新的文章
					</div>
					<form action="addArticleAdminServlet" id="form1" method="post">
						<div style="border-bottom-style: dashed; border-bottom-width: 1px; margin-top:10px;
								border-bottom-color: #DDD; height: 30px">
							<input type="hidden" name="content" id="content"/>
							文章分类&nbsp;&nbsp;
							<span id="selectCategorySpan">
								<select name="categoryId">
									<%
										int i=1;
										for(Category category: categoriesList)
										{
									%>
											<option value="<%= category.getId() %>" >
												<%= category.getName() %>
											</option>
									<%
										}
									%>
								</select>
							</span>
							&nbsp;
							<span id="categorySpan">
								<a href="#" onclick="preAddCategory()">
									<img src="../img/plus.gif" style="border: 0"/>增加分类
								</a>
							</span>
							<span id="categoryErrorSpan" style="color: red; font-weight: bold">
							</span>
						</div>
						<br/>
						文章标题
						<input type="text" size="80" id="titleInput" name="title" onclick="clearTitleMessage()"/>
						<span id="titleSpan" style="font-weight: bold; color: red">
							${ titleErrorMessage }
						</span>
						<br/><br/>
						发表日期&nbsp;<input id="postDateInput" name="postDate" size="25" value="${presentTime}"
							onclick="clearPostDateMessage()"/>&nbsp;(格式:yyyy-MM-dd HH:mm:ss)
						<span id="postDateSpan" style="font-weight: bold; color: red">
							${ postDateErrorMessage }
						</span>
						<br/><br/>
						<div>
							<textarea cols="60" id="editor1" name="editor1" rows="10"></textarea>
							<script type="text/javascript">
								CKEDITOR.replace('editor1',	{});
							</script>
						</div>
						<br/>
						<div>
							标签&nbsp;<input type="text" size="50" name="tags" id="tagsInput"/>
							<select onchange="addTag(this)">
								<option>请选择标签</option>
						<%
							for(Tag tag: tagsList)
							{
						%>
								<option><%= tag.getName() %></option>
						<%
							}
						%>
							</select>
						</div>
						<br/>
						<input type="button" value="发表文章" onclick="checkForm()"/>&nbsp;
						<input type="button" value="保存至草稿箱" onclick="saveAsDraft()"/>
						<span id="contentSpan" style="font-weight: bold; color: red"></span>
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
					<span></span>
				</div>
			</div>
			<div class="footer">
				&copy;2010&nbsp;
				<a href="http://www.eazow.com">Eazow</a>&nbsp;
				<a href="http://www.miitbeian.gov.cn">鄂ICP备10202535号</a>&nbsp;
				<script src="http://s16.cnzz.com/stat.php?id=2381155&web_id=2381155&show=pic" language="javascript"></script>
			</div>
		</div>
	</body>
</html>