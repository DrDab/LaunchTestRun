package com.openhorizonsolutions.ltr;

public class ProblemLoaderUtils
{
	public static boolean problemExists(String cpid)
	{
		return true;
	}
	
	public static Problem getProblem(String cpid)
	{
		return new Problem("811", "Sample Problem OwO", "Test Contest Set Mark IV", "(insert PDF view here + notes + html code)", null, null);
	}
}
