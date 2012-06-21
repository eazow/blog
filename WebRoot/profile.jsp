<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.eazow.blog.entity.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	List<Message> messagesList = (List<Message>)request.getAttribute("messagesList");
	String location = "profile";
%>
<%@ include file="header.jsp"%>
<style type="text/css">
	.imageDiv
	{
		margin-left: 10px;
		margin-top: 20px;
		width: 200px; 
		height:200px; 
		cursor: pointer;
		border-color: #ccc;
		border-width: 1px;
		padding-top: 1px;
		padding-left: 1px;
		border-style: solid;
		text-align: center;
		vertical-align: middle;
	}
	/**
	#contentCHead {
		background-image: url(img/content_head_foot.png);
	}
	#contentCFoot {
		background-image: url(img/content_head_foot.png);
	}
	*/
</style>
<div class="container">
	<div class="main">
		<div class="content">
			<div style="background-color: white; border-style: solid; border-width: 1px; border-color: #ccc">
			  	<div style="background-color: #9cf; font-size: 14px; padding-top:3px; padding-left: 10px">我的资料</div>
			  	<div style="padding: 10px; font-size: 12px">
			  		<table>
			  			<tr>
			  				<td>
			  					昵称:
			  				</td>
			  				<td>
			  					Eazow
			  				</td>
			  			</tr>
			  			<tr>
			  				<td>
			  					生日:
			  				</td>
			  				<td>
			  					1987
			  				</td>
			  			</tr>
			  			<tr>
			  				<td>
			  					QQ: 
			  				</td>
			  				<td>
			  					65019091
			  				</td>
			  			</tr>
			  			<tr>
			  				<td>
			  					Email:
			  				</td>
			  				<td>
			  					eazow@163.com
			  				</td>
			  			</tr>
			  			<tr>
			  				<td>
			  					个人主页:
			  				</td>
			  				<td>
			  					<a href="http://www.eazow.com">www.eazow.com</a>
			  				</td>
			  			</tr>
			  		</table>
				</div>
		  	</div>
		  	<br/><br/>
		  	<div style="background-color: white; border-style: solid; border-width: 1px; border-color: #ccc">
			  	<div style="background-color: #9cf; font-size: 14px; padding-top:3px; padding-left: 10px">留言板</div>
			  	<div style="padding: 10px">
			  	<%
			  		for(Message message: messagesList)
			  		{
			  	%>
					<div id="message" style="padding-left: 10px; padding-top: 10px; 
						border-bottom-style: dashed; border-bottom-width: 1px; border-bottom-color: #DDD; 
						padding-bottom: 10px; font-size: 12px">
						<div style="font-size: 10px; color: #ccc">
							<span style="color: #09f; font-weight: bold">
								<%= message.getNickname() %>
							</span>
							发表于: <%= message.getDateDisplay() %>
						</div>
						<div style="padding-left: 10px; padding-top: 10px">
							<p>
								<%= message.getContent() %>
							</p>
						</div>
					</div>
				<%
			  		}
				%>
					<form action="addMessageServlet" id="messageForm" method="post">
						<div style="width: 520px; padding: 15px">
							<div style="margin-bottom: 5px">
								我要踩踩 |&nbsp;&nbsp;
								昵称：
								<input name="nickname" id="nickname" size="12" value="${nickname}" 
									onclick="clearErrorMessage()"/>
							</div>
							<div>
								<textarea rows="5" cols="60" name="content" id="content" 
									onclick="clearErrorMessage()">${content}</textarea>
							</div>
							<div style="margin-top: 5px">
								验证码: <input type="text" name="verificationCodeInput" id="verificationCodeInput" 
									size="6" onclick="clearErrorMessage()"/>&nbsp;
								<img src="getVerificationCodeServlet" id="verificationCodeImage" style="vertical-align: middle"/>
								<a href="javascript:void(0)" onclick="refreshVerificationCode()">换一张</a>
								<input type="button" onclick="addMessage()" value=" 踩一踩 "/>
								<span id="errorSpan" style="color: red; font-weight: bold">
									${ errorMessage }
								</span>
							</div>
						</div>
					</form>
				</div>
		  	</div>
		</div>
		<div class="sidenav" style="padding-left: 10px">
			<h1>
				Eazow
			</h1>
			<!-- 
			<img src="img/head.jpg" style="max-width: 160px; max-height: 160px"/>
			 -->
			<div style="font-size: 12px; font-weight: bold">
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
				</table>
			</div>
		</div>
		<div class="clearer"><span></span></div>
	</div>
</div>
<%@ include file="footer.jsp"%>
<script type="text/javascript">
	function addMessage()
	{
		var nickname = document.getElementById("nickname").value;
		var theForm = document.getElementById("messageForm");
		var content = document.getElementById("content");
		var verificationCodeInput = document.getElementById("verificationCodeInput");
		var errorSpan = document.getElementById("errorSpan");
		if(nickname=="" || nickname==null)
		{
			errorSpan.innerHTML = "请输入昵称";
			return;
		}
		if(content.value=="" || content.value==null)
		{
			errorSpan.innerHTML = "请输入留言";
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