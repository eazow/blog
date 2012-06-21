package com.eazow.blog.util;

public class CommentUtil
{
	//��������ÿ����ʾ���ַ���
	public static final int LINE_LENGTH = 70;
	public static final int LINE_LENGTH_OF_LATEST_10_COMMENTS = 10;
	//�����۽��и�ʽ��,һ�й����Զ�����
	public static String formatComment(String commentContent)
	{
		StringBuilder formattedComment = new StringBuilder();
		String[] commentLines = commentContent.split("\n");
		String line = null;
		StringBuilder lineBuilder = new StringBuilder(); 
		for(int i=0; i<commentLines.length; i++)
		{
			line = commentLines[i];
			while(line.length() > LINE_LENGTH)
			{
				lineBuilder.append(line.substring(0, LINE_LENGTH) + "<br/>");
				line = line.substring(LINE_LENGTH);
			}
			lineBuilder.append(line);
			if(i!=commentLines.length-1)
				formattedComment.append(lineBuilder.toString() + "<br/>");
			else
				formattedComment.append(lineBuilder.toString());
		}
		return formattedComment.toString();
	}
	
	public static String formatCommentInLatest10Comment(String commentContent)
	{
		if(commentContent.length() > LINE_LENGTH_OF_LATEST_10_COMMENTS)
			return commentContent.substring(0, LINE_LENGTH_OF_LATEST_10_COMMENTS) + "...";
		return commentContent;
	}
}
