CKEDITOR.plugins.add(
	"code", 
	{
		requires:["dialog"], 
		lang:["en"], 
		init:function (a) 
		{
			if (CKEDITOR.env.ie6Compat) 
			{
				return;
			}
			a.addCommand("code", new CKEDITOR.dialogCommand("code"));
			a.ui.addButton(
				"Code", 
				{
					label:"插入代码", 
					command:"code", 
					icon:this.path + "code.gif"});
			CKEDITOR.dialog.add("code", this.path + "dialogs/code.js");
		}
	}
);

