<%@ page language="java" pageEncoding="utf-8"%>
<%
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	int totalPageView = (Integer)request.getAttribute("totalPageView");
	int todayPageView = (Integer)request.getAttribute("todayPageView");
	if(articlesList.size() != 0)
	{
%>
		<div class="pageDiv">
			<form action="getIndexArticlesServlet" id="pageForm" method="post">
				<input type="hidden" name="pageNum" id="pageNum"></input>
			</form>
<%
				if(pageUtil.getTotalPages() <= 10)
				{
					if(pageUtil.getCurrentPage() == 1)
					{
%>
						<span style="color: #999; font-weight: bold">上一页</span>&nbsp;|&nbsp;
<%
					}
					else
					{
%>
						<a onclick="getPage(${pageUtil.currentPage-1})"  onmouseover="addUnderline(this)" 
							onmouseout="removeUnderline(this)" style="font-weight: bold">上一页
						</a>&nbsp;|&nbsp;
<%
					}
					for(int i=1; i<=pageUtil.getTotalPages(); i++)
					{
						if(pageUtil.getCurrentPage()==i)
						{
%>
							<span class="currentPageSpan" id="currentPageSpanId"><%= i %></span>
<%
						}
						else
						{
%>
							<span class="pageSpan" onmouseover="changeBgColor(this)" 
								onmouseout="changeBgColorBack(this)" onclick="getPage(<%=i%>)">
								&nbsp;<%= i %>
							</span>
<%
						}
					}
%>
					&nbsp;|&nbsp;
<%
					if(pageUtil.getCurrentPage() != pageUtil.getTotalPages())
					{
%>
						<a style="text-decoration: none; font-weight: bold" onclick="getPage(${pageUtil.currentPage+1})"
							onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)">下一页</a>
<%
					}
					else
					{
%>
						<span style="color: #999; font-weight: bold">下一页</span>&nbsp;
<%
					}	
%>
					<span style="font-size: 13px; font-weight: bold">共${pageUtil.totalPages}页</span>
<%
				}
				else if(pageUtil.getCurrentPage() <= 5)  //总页数>10
				{
					if(pageUtil.getCurrentPage() == 1)
					{
%>
						<span style="color: #999; font-weight: bold">上一页</span> |&nbsp;
<%
					}
					else
					{
%>
						<a onclick="getPage(${pageUtil.currentPage-1})"  onmouseover="addUnderline(this)" 
							onmouseout="removeUnderline(this)" style="font-weight: bold">
							上一页
						</a>&nbsp;|&nbsp;
<%
					}
					for(int i=1; i<=10; i++)
					{
						if(pageUtil.getCurrentPage() == i)
						{
%>
							<span class="currentPageSpan" id="currentPageSpanId"><%= i %></span>
<%
						}
						else
						{
%>
							<span class="pageSpan" onmouseover="changeBgColor(this)" 
								onmouseout="changeBgColorBack(this)" onclick="getPage(<%=i%>)">
								&nbsp;<%= i %>
							</span>
<%
						}
					}
%>
					&nbsp;|&nbsp;
					<a style="text-decoration: none; font-weight: bold" onclick="getPage(${pageUtil.currentPage+1})" 
						onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)">下一页</a>&nbsp;
					<a style="text-decoration: none; font-weight: bold" onclick="getPage(${pageUtil.totalPages})" 
						onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)">末页</a>&nbsp;
					<span style="font-size: 13px; font-weight: bold">共${pageUtil.totalPages}页</span>
<%
				}
				else      //当前页>5 总页数>10
				{
%>
					<a onclick="getPage(1)" onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)" 
						style="font-weight: bold">首页</a>&nbsp;
					<a onclick="getPage(${pageUtil.currentPage-1})" onmouseover="addUnderline(this)" 
						onmouseout="removeUnderline(this)" style="font-weight: bold">上一页</a>&nbsp;|&nbsp;
<%
					if(pageUtil.getCurrentPage()+5 < pageUtil.getTotalPages())  //不到最后一页
					{
						for(int i=pageUtil.getCurrentPage()-4; i<=pageUtil.getCurrentPage()+5; i++)
						{
							if(pageUtil.getCurrentPage() == i)
							{
%>
								<span class="currentPageSpan" id="currentPageSpanId"><%= i %></span>
<%
							}
							else
							{
%>
								<span class="pageSpan" onmouseover="changeBgColor(this)" 
									onmouseout="changeBgColorBack(this)" onclick="getPage(<%=i%>)">
									&nbsp;<%= i %>
								</span>
<%
							}
						}
%>
						&nbsp;|&nbsp;
						<a style="text-decoration: none; font-weight: bold" onclick="getPage(${pageUtil.currentPage+1})" 
							onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)">下一页</a>&nbsp;
						<a style="text-decoration: none; font-weight: bold" onclick="getPage(${pageUtil.totalPages})" 
							onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)">末页</a>&nbsp;
						<span style="font-size: 13px; font-weight: bold">共${pageUtil.totalPages}页</span>
<%
					}
					else   //当前页到了最后一页 
					{
						for(int i=pageUtil.getTotalPages()-9; i<=pageUtil.getTotalPages(); i++)
						{
							if(pageUtil.getCurrentPage() == i)
							{
%>
								<span class="currentPageSpan" id="currentPageSpanId"><%=i%></span>
<%
							}
							else
							{
%>
								<span class="pageSpan" onmouseover="changeBgColor(this)" 
									onmouseout="changeBgColorBack(this)" onclick="getPage(<%=i%>)">
									&nbsp;<%= i %>
								</span>
<%
							}
						}
%>
						&nbsp;|&nbsp;
<%
						if(pageUtil.getCurrentPage() != pageUtil.getTotalPages())
						{
%>
							<a style="text-decoration: none; font-weight: bold" onclick="getPage(${pageUtil.currentPage+1})" 
								onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)">下一页</a>
<%
						}
						else
						{
%>
							<span style="color: #999; font-weight: bold">下一页</span>
<%
						}
%>
						&nbsp;<span style="font-size: 13px; font-weight: bold">共${pageUtil.totalPages}页</span>
<%
					}
				}
%>
		</div>
<%
	}
%>
<script type="text/javascript">
	function changeBgColor(theSpan)
	{
		theSpan.style.backgroundColor = "#9CF";
	}
	function changeBgColorBack(theSpan)
	{
		theSpan.style.backgroundColor = "white";
	}
	function addUnderline(theA)
	{
		theA.style.textDecoration = "underline";
		theA.style.cursor = "pointer";
	}
	function removeUnderline(theA)
	{
		theA.style.textDecoration = "none";
	}
	function getPage(pageNum)
	{
		document.getElementById("pageNum").value = pageNum;
		document.getElementById("pageForm").submit();
	}
	
	var hasInput = false;
	function focusInput()
	{
		var theInput = document.getElementById("keyword");
		if(!hasInput)
		{
			theInput.value = "";
			theInput.style.color = "black";
		}
	}
	
	function blurInput()
	{
		var theInput = document.getElementById("keyword");
		if(theInput.value == "")
		{
			theInput.value = "请输入搜索内容";
			theInput.style.color = "#ccc";
			hasInput = false;
		}
		else
		{
			hasInput = true;
		}
	}
	
	function clickImage()
	{
		if(!hasInput)
			return;
		var searchForm = document.getElementById("searchForm");
		searchForm.submit();
	}
</script>