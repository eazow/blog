<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.eazow.blog.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	Article article = (Article)request.getAttribute("article");	
	List<Comment> commentsList = article.getComments(); 
	String location = "index";
%>
<%@ include file="header.jsp"%>
<div class="container">
	<div class="main">
		<div class="content">
			<div>
				<h1>
					${article.title}
				</h1>
				<div class="descr">
					${article.postDateDisplay}
				</div>
				<p>
					${article.content}
				</p>
			</div>
			<div style="text-align: right; border-bottom-style: dashed; border-bottom-width: 1px; border-bottom-color: #DDD; 
				padding-bottom: 10px; font-size: 12px">
				标签:
				<%
					List<Tag> tags = article.getTags();
					for(Tag tag: tags)
					{
				%>
						<a href="getArticlesOfTagServlet?tagId=<%=tag.getId()%>">
							<%= tag.getName() %>
						</a>
				<%
					}
				%>
				|&nbsp;
				分类:
				<a href="getArticlesOfCategoryServlet?categoryId=<%=article.getCategory().getId()%>">
					<%= article.getCategory().getName() %>
				</a>|&nbsp;
				阅读(<%=article.getViewCount()%>)&nbsp;|&nbsp;
				评论(<%= article.getCommentsNum() %>)
			</div>
			<%
				for(Comment comment: commentsList)
				{
			%>
					<div id="comment<%=comment.getId()%>" style="padding-left: 10px; padding-top: 10px; 
						border-bottom-style: dashed; border-bottom-width: 1px; border-bottom-color: #DDD; 
						padding-bottom: 10px; font-size: 12px">
						<div style="font-size: 10px; color: #ccc">
							<span style="color: #09f; font-weight: bold">
								<%= comment.getNickname() %>
							</span>
							发表于: <%= comment.getCommentDateDisplay() %>
						</div>
						<div style="padding-left: 10px; padding-top: 10px">
							<p>
								<%= comment.getContent() %>
							</p>
						</div>
					</div>
			<%		
				}
			%>
			<form action="addCommentServlet" id="commentForm" method="post">
				<div style="width: 520px; padding: 15px">
					<div style="margin-bottom: 5px">
						我要评论 |&nbsp;&nbsp;
						昵称：<input name="nickname" id="nickname" size="12" value="${nickname}"/>
					</div>
					<div>
						<textarea rows="5" cols="60" name="commentContent" id="commentContent" 
							onclick="clearErrorMessage()">${commentContent}</textarea>
						<input type="hidden" name="articleId" value="${article.id}"/>
					</div>
					<div style="margin-top: 5px">
						验证码: <input type="text" name="verificationCodeInput" id="verificationCodeInput" 
							size="6" onclick="clearErrorMessage()"/>&nbsp;
						<img src="getVerificationCodeServlet" id="verificationCodeImage" style="vertical-align: middle"/>
						<a href="javascript:void(0)" onclick="refreshVerificationCode()">换一张</a>
						<input type="button" onclick="addComment()" value="发表评论" />
						<span id="errorSpan" style="color: red; font-weight: bold">
							${ errorMessage }
						</span>
					</div>
				</div>
			</form>
			<div style="border-bottom-style: dashed; border-bottom-width: 1px; border-bottom-color: #DDD"></div>
		</div>
		<%@ include file="side.jsp"%>
	</div>
</div>
<%@ include file="footer.jsp"%>
<script type="text/javascript">
	function addComment()
	{
		var nickname = document.getElementById("nickname").value;
		var theForm = document.getElementById("commentForm");
		var commentContent = document.getElementById("commentContent");
		var verificationCodeInput = document.getElementById("verificationCodeInput");
		var errorSpan = document.getElementById("errorSpan");
		if(nickname=="" || nickname==null)
		{
			errorSpan.innerHTML = "请输入昵称";
			return;
		}
		if(commentContent.value=="" || commentContent.value==null)
		{
			errorSpan.innerHTML = "请输入评论";
			return;
		}
		if(nickname.length > 6)
		{
			errorSpan.innerHTML = "昵称请不要过长";
			return;
		}
		if(verificationCodeInput.value=="" || verificationCodeInput.value==null)
		{
			errorSpan.innerHTML = "请输入验证码";
			return;
		}
		theForm.submit();
	}
	function clearErrorMessage()
	{
		var errorSpan = document.getElementById("errorSpan");
		errorSpan.innerHTML = "";
	}
	function refreshVerificationCode()
	{
		document.getElementById("verificationCodeImage").src = "getVerificationCodeServlet?time=" + new Date();
	}
</script>