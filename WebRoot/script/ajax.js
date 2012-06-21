function getXmlHttpRequest()
{
	var xmlHttpRequest;
 
	try
	{
		// Firefox, Opera 8.0+, Safari
		xmlHttpRequest = new XMLHttpRequest();
	}
	catch (e)
	{
		// Internet Explorer
		try
		{
			xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			alert("ie");
		}
		catch (e)
		{
			try
			{
				xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e)
			{
				alert("您的浏览器不支持AJAX！");
			}
		}
	}
	
	return xmlHttpRequest;
}