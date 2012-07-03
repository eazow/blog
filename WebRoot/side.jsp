<%@ page language="java" pageEncoding="utf-8"%>
<%
	List<Category> categoriesList = (List<Category>)request.getAttribute("categoriesList");
	List<Comment> latest10Comments = (List<Comment>)request.getAttribute("latest10Comments");
	List<ArticleArchive> articleArchiveList = (List<ArticleArchive>)request.getAttribute("articleArchiveList");
	List<Tag> tagsList = (List<Tag>)request.getAttribute("tagsList");
%>
<div class="sidenav">
	<!-- 
	<h1>
		Search
	</h1>
	<form action="searchArticlesServlet" id="searchForm" method="post">
		<div>
			<input type="text" name="keyword" id="keyword" class="styled" value="请输入搜索内容" 
				onclick="focusInput()" onblur="blurInput()"/>
			<img src="img/search.jpg" style="cursor: pointer" alt="搜索" onmousedown="clickImage()"/>
		</div>
	</form>
	 -->
	
	<!-- 
	<h1>
		日历
	</h1>
	<div style="padding-left: 16px">
		<table border="0" cellpadding="0" cellspacing="1" class="Calendar" id="caltable">
			<thead>
			    <tr align="center" valign="middle">
					<td colspan="7" class="Title">
						<span onclick="subYear()" title="上一年" class="DayButton"
							style="cursor: pointer">7</span><span 
							id="yearSpan" style="font-size: 13px"></span><span style="font-size: 13px">年</span><span 
							onclick="addYear()" title="下一年" class="DayButton" style="cursor: pointer">8</span>
						&nbsp;
						<span onclick="subMonth();" title="上一月" class="DayButton"
							style="cursor: pointer">3</span><span
							id="monthSpan" style="font-size: 13px"></span><span 
							style="font-size: 13px">月</span><span 
							onclick="addMonth();" title="下一月" Class="DayButton"
							style="cursor: pointer">4</span>
				 	</td>
				</tr>
				<tr align="center" valign="middle"> 
					<script type="text/javascript">  
				    	document.write("<TD class=DaySunTitle id=diary >" + days[0] + "</TD>"); 
					   	for (var intLoop = 1; intLoop < days.length-1;intLoop++) 
					    document.write("<TD class=DayTitle id=diary>" + days[intLoop] + "</TD>"); 
					    document.write("<TD class=DaySatTitle id=diary>" + days[intLoop] + "</TD>"); 
			  		</script>
				</tr> 
			</thead>
			<tbody border=1 cellspacing="0" cellpadding="0" id="calendar" align="center">
				<script type="text/javascript">
					for (var intWeeks = 0; intWeeks < 6; intWeeks++)
					{
						document.write("<TR style='cursor:hand'>");
						for (var intDays = 0; intDays < days.length;intDays++) 
							document.write("<TD class=CalendarTD onMouseover='buttonOver();' onMouseOut='buttonOut();'></TD>");
						document.write("</TR>");
					} 
				</script>
			</tbody>
		</table>
		<script  type="text/javascript">
			Calendar();
		</script>
	</div>
	 -->

	<!-- 标签云 -->
	<h1>
		标签云
	</h1>
	<div>
		<object type="application/x-shockwave-flash" data="flash/3dtags.swf"
			width="200" height="200">
			<param name="movie" value="flash/3dtags.swf" />
			<param name="bgcolor" value="#D3E9F8" />
			<param name="AllowScriptAccess" value="always" />
			<param name="flashvars"
				value='jsonData=
				{
					"type":"tagBall",
					"delicacy":2,
					"renderCycleTime":20,
					"radius":60,
					"focalLength":300,
					"color":"#000000",
					"rotationModeX":15,
					"rotationModeY":15,
					"elements":
					[
				<%
					int i = 1;
					for(Tag tagTemp: tagsList)
					{
						String url = "getArticlesOfTagServlet?tagId="+tagTemp.getId();
				%>
						{
							"type":"text",
							"data":"<%= tagTemp.getName() %>",
							"hcolor":"0x008000",
							"size":12,
							"bold":0,
							"herf":"<%=url%>",
							"window":"_self",
							"fontFamily":"\u5b8b\u4f53"
						}
				<%
						if(i != tagsList.size())
						{
				%>			
							,
				<%
						}
						i++;
					}
				%>
					]
				}'/>
		</object>
	</div>
	<!-- 标签云end -->

	<h1>
		文章分类
	</h1>
	<ul>
		<%
			for (Category category : categoriesList) 
			{
		%>
				<li>
					<a href="getArticlesOfCategoryServlet?categoryId=<%= category.getId() %>"> 
						<%= category.getName() %>(<%= category.getArticlesNum() %>)
					</a>
				</li>
		<%
			}
		%>
	</ul>

	<h1>
		文章存档
	</h1>
	<% 
		if(0 == articleArchiveList.size())
		{
	%>
			暂时还没文章存档
	<%
		}
		else
		{
	%>
			<ul>
				<%
					for(ArticleArchive articleArchiveTemp: articleArchiveList)
					{
				%>
						<li>
							<a href="getArticlesByArticleArchiveServlet?articleArchiveId=<%=articleArchiveTemp.getId()%>">
								<%=articleArchiveTemp.toString()%>(<%=articleArchiveTemp.getArticlesNum()%>)
							</a>
						</li>
				<%
					}
				%>
			</ul>
	<%
		}
	%>
	
	<h1>
		最新评论
	</h1>
	<% 
		if(0 == latest10Comments.size())
		{
	%>
			暂时还没最新评论
	<%
		}
		else
		{
	%>
			<ul>
				<%
					for(Comment comment: latest10Comments)
					{
				%>
						<li>
							<a href="getArticleServlet?id=<%=comment.getArticle().getId()%>#comment<%=comment.getId()%>"
								title="<%=comment.getNickname()%>:<%=comment.getContent()%>">
									<%= comment.getContentDisplayInLatest10Comments() %>
							</a>
						</li>
				<%
					}
				%>
			</ul>
	<%
		}
	%>
	<h1>
		统计信息
	</h1>
	<div style="padding-left: 15px; font-size: 12px; color: #777">
		<table>
			<tr>
				<td>
					今日访问:
				</td>
				<td>
					${ todayPageView }
				</td>
			</tr>
			<tr>
				<td>
					总访问量: 
				</td>
				<td>
					${ totalPageView }
				</td>
			</tr>
			<tr>
				<td>
					文章数量: 
				</td>
				<td>
					${ totalArticlesNum }
				</td>
			</tr>
			<tr>
				<td>
					评论数量: 
				</td>
				<td>
					${ totalCommentsNum }
				</td>
			</tr>
		</table>
	</div>
</div>
<div class="clearer"></div>