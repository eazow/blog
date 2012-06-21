<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
	<head>
		<link rel="shortcut icon" href="../favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="../css/login.css" media="screen" />
		<script type="text/javascript" src="../script/jquery.js"></script>
		<title>Eazow</title>
	</head>
	<body>
		<form id="theForm" action="loginAdminServlet" method="post">
			<div id="loginDiv">
				<h1>Eazow</h1>
				<div class="inputWrap">
					<!-- 
					<label>用户名</label>
					 -->
					<input id="username" name="username" class="inputText" autocomplete="off" onclick="clearErrorMessage()"/>
				</div>
				<div class="inputWrap">
					<input type="password" id="password" name="password" class="inputText" autocomplete="off" onclick="clearErrorMessage()" 
						onkeypress="checkEnter()"/>
				</div>
				<input type="submit" value="登 录" id="submit" onclick="checkForm()"/>
				<div id="msgDiv">${usernameErrorMessage}</div>
			</div>
		</form>
		<div id="foot">
			&copy;2010-2011 Powered by Eazow
		<div>
	</body>
</html>
<script type="text/javascript">
	function checkForm()
	{
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		
		if(null==username || ""==username)
		{
			document.getElementById("msgDiv").innerHTML = "请输入用户名";
			return false;
		}
		if(null==password || ""==password)
		{
			document.getElementById("msgDiv").innerHTML = "请输入密码";
			return false;
		}
	}
	function clearErrorMessage()
	{
		document.getElementById("msgDiv").innerHTML = "";
		document.getElementById("msgDiv").innerHTML = "";
	}
	function checkEnter()
	{
		if(event.keyCode == 13)
			checkForm();
	}
</script>