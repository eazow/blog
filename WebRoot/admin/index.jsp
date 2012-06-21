<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link rel="shortcut icon" href="../favicon.ico"/>
	<link href="../css/admin.css" rel="stylesheet">
	<script type="text/javascript" src="../script/jquery.js"></script>
	<script type="text/javascript" src="../script/admin.js"></script>
</head>
<body>
	<table>
		<tr>
			<td id="topTd">
				<div class="top">
					<div class="wrap">
						<h1>Eazow</h1>
						<div class="menu" id="menu">
							<a href="#" class="selected"><span>首页</span></a>
							<a href="#"><span>文章管理</span></a>
							<a href="#"><span>图片管理</span></a>
						</div>
						<div id="adminDiv">
							<span><img src="../img/admin/top_account.gif"/></span>
							<a href="#">admin</a>
							<b class="line"></b>
							<span><img src="../img/admin/top_about.gif"/></span>
							<a href="#">关于</a>
							<b class="line"></b>
							<a href="#" id="logoutA">安全退出</a>
						</div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td id="mainTd">
				<table>
					<tr>
						<td id="sideMenuTd">
							<a class="bar" href="#"><img src="../img/admin/blank.gif"/></a>
							<div id="sideMenuDiv">
								<div class="menu_item_selected" onclick="clickSubMenu(this)">
									<a href="#" class="arrow_empty"></a>
									<a href="manageArticlesAdminServlet" class="menu_item_text" target="mainFrame"">
										<span>文章管理<span>
									</a>
								</div>
								<div class="menu_item" onclick="clickSubMenu(this)">
									<a href="#" class="arrow_empty"></a>
									<a href="manageCategoriesAdminServlet" class="menu_item_text" target="mainFrame">
										<span>分类管理<span>
									</a>
								</div>
								<div class="menu_item" onclick="clickSubMenu(this)">
									<a href="#" class="arrow_empty"></a>
									<a href="manageArticleArchivesAdminServlet" class="menu_item_text" target="mainFrame">
										<span>文章存档管理<span>
									</a>
								</div>
								<div class="menu_item" onclick="clickSubMenu(this)">
									<a href="#" class="arrow_empty"></a>
									<a href="manageCommentsAdminServlet" class="menu_item_text" target="mainFrame">
										<span>文章评论管理<span>
									</a>
								</div>
								<div class="menu_item" onclick="clickSubMenu(this)">
									<a href="#" class="arrow_empty"></a>
									<a href="manageTagsAdminServlet" class="menu_item_text" target="mainFrame">
										<span>标签管理<span>
									</a>
								</div>
								<div class="menu_item" onclick="clickSubMenu(this)">
									<a href="#" class="arrow_empty"></a>
									<a href="manageMessagesAdminServlet" class="menu_item_text" target="mainFrame">
										<span>留言管理<span>
									</a>
								</div>
								<div class="menu_item" onclick="clickSubMenu(this)">
									<a href="#" class="arrow_empty"></a>
									<a href="manageMottosAdminServlet" class="menu_item_text" target="mainFrame">
										<span>格言管理<span>
									</a>
								</div>
								<div class="menu_item" onclick="clickSubMenu(this)">
									<a href="#" class="arrow_empty"></a>
									<a href="manageVisitRecordsAdminServlet" class="menu_item_text" target="mainFrame">
										<span>访问量管理<span>
									</a>
								</div>
							</div>
						</td>
						<td id="contentTd">
							<table>
								<tr>
									<td id="locationTd">
										<div id="locationDiv">
											首页<b class="dot"></b>文章管理
										</div>
										<div id="locationLine"></div>
									</td>
								</tr>
								<tr>
									<td id="contentMainTd">
										<iframe id="mainFrame" src="manageArticlesAdminServlet" name="mainFrame" width="100%" height="100%" frameborder="0"></iframe>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td id="footerTd">
				<table>
					<tr>
						<td width="20">
							<img src="../img/admin/connected.gif"/>
						</td>
						<td width="100">
							<img src="../img/admin/update.gif"/>
							系统版本 0.1
						</td>
						<td>
						</td>
						<td width="120">
							<img src="../img/admin/time.gif"/>
							系统时间：
							<span id="systemTimeSpan">20:10</span>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
<script type="text/javascript">
	refreshSystemTime();
	setInterval("refreshSystemTime()", 5000);
</script>