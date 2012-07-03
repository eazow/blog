package com.eazow.blog.test;

import java.util.ArrayList;
import java.util.List;

public class NullTest {
	public static void main(String[] args) {
		try {
			// List<String> list = null;

			List<String> list = new ArrayList<String>();
			for (String str : list) {
				System.out.println(str.toLowerCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
