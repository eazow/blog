CKEDITOR.dialog.add(
	"code", 
	function (a) 
	{
		return {
			title:"插入代码",
			minWidth:590, 
			minHeight:300, 
			contents:
			[
				{
					id:"tab1", 
					label:"", 
					title:"", 
					expand:true, 
					padding:0, 
					elements:
					[
						{
							type:"html", 
							html:"<div>"
								+	"<div>"
								+		"选择语言:&nbsp;"
								+		"<select id='select1'>"
								+			"<option value='java'>java</option>"
								+			"<option value='javascript'>javascript</option>"	
								+			"<option value='css'>css</option>"
								+			"<option value='xml'>xml</option>"
								+			"<option value='sql'>sql</option>"		
								+		"</select>"
								+	"</div>"
								+	"<br/>"
								+	"<div>"
								+		"代码:<br/>"
								+		"<textarea id='textarea1' cols='120' rows='20' style='background-color: white'>"
								+		"</textarea>"
								+	"</div>"
								+"</div><br/>"
						}
					]
				}
			], 
			onOk: function()
			{
				var language = document.getElementById("select1").value;
				var code = document.getElementById("textarea1").value;
				
				var html = "<div style='font-size: 12px'>"
						+		"<pre class='brush: " + language + ";'>" 
						+ 			code
						+  		"</pre>"
						+  "</div>";
				CKEDITOR.instances.editor1.insertHtml(html);
			}
		};
	}
);

