<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="sidenav">
	<a href="manageArticlesAdminServlet" style="text-decoration: none; cursor: pointer">
		<h1>
			文章管理
		</h1>
	</a>
	<ul>
		<li>
			<a href="preAddArticleAdminServlet" style="text-decoration: none; cursor: pointer">
				<img src="../img/postnew.gif"
					style="height: 16px; width: 16px; border: 0; vertical-align: middle"/>
				写新文章
			</a>
		</li>
		<li>
			<a href="manageDraftsAdminServlet" style="text-decoration: none; cursor: pointer">
				<img src="../img/draft.jpg" 
					style="height: 15px; width: 15px; border: 0; vertical-align: middle"/>
				草稿箱(${draftsNum})
			</a>
		</li>
	</ul>
	
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
<div class="clearer"><span></span></div>