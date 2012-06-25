<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
	String location = "index";
%>
<%@ include file="header.jsp"%>
<div class="container">
	<div class="main">
		<div style="background-image: url('img/error.gif'); background-repeat: no-repeat; margin: 100px auto;
			height: 390px; width: 500px">
			<div style="font-size: 40px; padding-top: 20px; margin-bottom: 20px; padding-left: 200px">Sorry</div>
			<div style="font-size: 40px; margin-bottom: 20px; padding-left: 185px">ERROR</div>
			<div style="font-size: 15px; padding-left: 200px; color: red; font-weight: bold">
				页面将在<span id="timeSpan" style="color: black">5</span>秒后返回主页
				<img src="img/loading.gif"/>
			</div>
		</div>
	</div>
</div>
<%@ include file="footer.jsp"%>
<script type="text/javascript">
	countDown();
	var remainingTime = 5;
	function countDown()
	{
		if(remainingTime > 0)
		{
			document.getElementById("timeSpan").innerHTML = remainingTime;
		}
		else if(remainingTime<=0)
		{
			window.location.href = "index";
		}
		remainingTime--;
		setTimeout("countDown()", 1000);
	}
</script>