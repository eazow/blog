<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.eazow.blog.entity.*"%>
<%@ page import="com.eazow.blog.util.*" %>
<%
	List<VisitRecord> visitRecordsList = (List<VisitRecord>)request.getAttribute("visitRecordsList");
	VisitRecordPageUtil visitRecordPageUtil = (VisitRecordPageUtil)request.getAttribute("visitRecordPageUtil");
	String location = "index";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/admin.css" media="screen" />
	<script type="type/javascript">
		function deleteVisitRecord(id)
		{
			if(!confirm("确定删除?"))
			{
				return;
			}
		}
		//Page
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
		
		function deleteVisitRecord(id)
		{
			if(!confirm("确定删除?"))
			{
				return;
			}
			document.getElementById("visitRecordIdInput").value = id;
			document.getElementById("deleteVisitRecordForm").submit();
		}
	</script>
</head>
<body>
<table class="content_table">
	<tr class="header_tr">
		<td>id</td>
		<td>访问ip</td>
		<td>访问时间</td>
		<td>操作</td>
	</tr>
	<%
		int visitRecordCount = 0;
		for(VisitRecord visitRecord: visitRecordsList)
		{
			String className = (++visitRecordCount%2==0)?"even_tr":"odd_tr";
	%>
			<tr class="<%=className%>">
				<td>
					<%= visitRecord.getId() %>
				</td>
				<td>
					<%= visitRecord.getSourceIP() %>
				</td>
				<td>
					<%= visitRecord.getVisitDate() %>
				</td>
				<td>
					<img src="../img/delete.gif" style="vertical-align: middle; cursor: pointer" 
								alt="delete" onclick="deleteVisitRecord(<%=visitRecord.getId()%>)"/>
				</td>
			</tr>
	<%
		}
	%>

</table>
	<div class="pageDiv">
			<form action="manageVisitRecordsAdminServlet" id="pageForm" method="post">
				<input type="hidden" name="pageNum" id="pageNum"></input>
			</form>
<%
				if(visitRecordPageUtil.getTotalPages() <= 10)
				{
					if(visitRecordPageUtil.getCurrentPage() == 1)
					{
%>
						<span style="color: #999; font-weight: bold; font-weight: bold">上一页</span>&nbsp;|&nbsp;
<%
					}
					else
					{
%>
						<a onclick="getPage(${visitRecordPageUtil.currentPage-1})"  onmouseover="addUnderline(this)" 
							onmouseout="removeUnderline(this)" style="color: blue; font-weight: bold">上一页
						</a>&nbsp;|&nbsp;
<%
					}
					for(int i=1; i<=visitRecordPageUtil.getTotalPages(); i++)
					{
						if(visitRecordPageUtil.getCurrentPage()==i)
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
					if(visitRecordPageUtil.getCurrentPage() != visitRecordPageUtil.getTotalPages())
					{
%>
						<a style="text-decoration: none" onclick="getPage(${visitRecordPageUtil.currentPage+1})"
							onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)"
							style="color: blue; font-weight: bold">下一页</a>
<%
					}
					else
					{
%>
						<span style="color: #999; font-weight: bold">下一页</span>&nbsp;
<%
					}	
%>
					<span style="font-size: 13px; font-weight: bold">共${visitRecordPageUtil.totalPages}页</span>
<%
				}
				else if(visitRecordPageUtil.getCurrentPage() <= 5)  //总页数>10
				{
					if(visitRecordPageUtil.getCurrentPage() == 1)
					{
%>
						<span style="color: #999; font-weight: bold">上一页</span> |&nbsp;
<%
					}
					else
					{
%>
						<a onclick="getPage(${visitRecordPageUtil.currentPage-1})"  onmouseover="addUnderline(this)" 
							onmouseout="removeUnderline(this)" style="color: blue; font-weight: bold">
							上一页
						</a>&nbsp;|&nbsp;
<%
					}
					for(int i=1; i<=10; i++)
					{
						if(visitRecordPageUtil.getCurrentPage() == i)
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
					<a style="text-decoration: none" onclick="getPage(${visitRecordPageUtil.currentPage+1})" 
						onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)"
						style="color: blue; font-weight: bold">下一页</a>&nbsp;
					<a style="text-decoration: none" onclick="getPage(${visitRecordPageUtil.totalPages})" 
						onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)" 
						style="color: blue; font-weight: bold">末页</a>&nbsp;
					<span style="font-size: 13px; font-weight: bold">共${visitRecordPageUtil.totalPages}页</span>
<%
				}
				else      //当前页>5 总页数>10
				{
%>
					<a onclick="getPage(1)" onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)" 
						style="color: blue; font-weight: bold">首页</a>&nbsp;
					<a onclick="getPage(${visitRecordPageUtil.currentPage-1})" onmouseover="addUnderline(this)" 
						onmouseout="removeUnderline(this)" style="color: blue; font-weight: bold">上一页</a>&nbsp;|&nbsp;
<%
					if(visitRecordPageUtil.getCurrentPage()+5 < visitRecordPageUtil.getTotalPages())  //不到最后一页
					{
						for(int i=visitRecordPageUtil.getCurrentPage()-4; i<=visitRecordPageUtil.getCurrentPage()+5; i++)
						{
							if(visitRecordPageUtil.getCurrentPage() == i)
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
						<a style="text-decoration: none" onclick="getPage(${visitRecordPageUtil.currentPage+1})" 
							onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)" 
							style="color: blue; font-weight: bold">下一页</a>&nbsp;
						<a style="text-decoration: none" onclick="getPage(${visitRecordPageUtil.totalPages})" 
							onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)" 
							style="color: blue; font-weight: bold">末页</a>&nbsp;
						<span style="font-size: 13px; font-weight: bold">共${visitRecordPageUtil.totalPages}页</span>
<%
					}
					else   //当前页到了最后一页 
					{
						for(int i=visitRecordPageUtil.getTotalPages()-9; i<=visitRecordPageUtil.getTotalPages(); i++)
						{
							if(visitRecordPageUtil.getCurrentPage() == i)
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
						if(visitRecordPageUtil.getCurrentPage() != visitRecordPageUtil.getTotalPages())
						{
%>
							<a style="text-decoration: none" onclick="getPage(${visitRecordPageUtil.currentPage+1})" 
								onmouseover="addUnderline(this)" onmouseout="removeUnderline(this)"
								style="color: blue; font-weight: bold">下一页</a>
<%
						}
						else
						{
%>
							<span style="color: #999; font-weight: bold">下一页</span>
<%
						}
%>
						&nbsp;<span style="font-size: 13px; font-weight: bold">共${visitRecordPageUtil.totalPages}页</span>
<%
					}
				}
		%>
	</div>
</div>
</body>
</html>