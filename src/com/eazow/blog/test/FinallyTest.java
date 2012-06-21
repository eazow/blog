package com.eazow.blog.test;

public class FinallyTest
{
	public static void main(String[] args)
	{
		try
		{
			System.out.println("before return");
			return;
		}
		catch(Exception e)
		{
			System.out.println("Exception");
		}
		finally
		{
			System.out.println("finally");
		}
	}
}
