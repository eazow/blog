package com.eazow.blog.util;

public class HtmlUtil
{
	public static String filtrateHtmlCode(String str)
	{
		if(null == str)
			return null;
		StringBuilder sb = new StringBuilder();
		int length = str.length();
		for(int i=0; i<length; i++)
		{
			char c = str.charAt(i);
			switch(c)
			{
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '"':
				sb.append("&#34;");
				break;
			case '\'':
				sb.append("&#39;");
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}
}
