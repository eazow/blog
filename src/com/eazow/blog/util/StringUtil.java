package com.eazow.blog.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class StringUtil {
	/*
	 * public static List<String> splitString(String sourceString, String
	 * delimiter) { List<String> list = new ArrayList<String>();
	 * 
	 * int beforeIndex = sourceString.indexOf(delimiter); int afterIndex =
	 * sourceString.indexOf(delimiter, beforeIndex + delimiter.length());
	 * 
	 * while(-1 != afterIndex) { String str = sourceString.substring(beforeIndex
	 * + delimiter.length(), afterIndex);
	 * 
	 * list.add(str);
	 * 
	 * beforeIndex = afterIndex; afterIndex = sourceString.indexOf(delimiter,
	 * beforeIndex + delimiter.length()); }
	 * 
	 * return list; }
	 */
	public static String getRandomString(int length) {
		Random random = new Random();

		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(str.length());

			builder.append(str.charAt(index));
		}

		return builder.toString();
	}

	public static List<String> splitString(String sourceString, String delimiter) {
		List<String> list = new ArrayList<String>();
		StringTokenizer stringTokenizer = new StringTokenizer(sourceString,
				delimiter);
		while (stringTokenizer.hasMoreTokens()) {
			String str = stringTokenizer.nextToken();
			list.add(str);
		}
		return list;
	}
}
