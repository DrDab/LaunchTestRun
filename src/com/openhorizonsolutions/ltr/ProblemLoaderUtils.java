package com.openhorizonsolutions.ltr;

public class ProblemLoaderUtils
{
	public static boolean problemExists(String cpid)
	{
		if(!cpid.matches("/222"))
		{
			return true;
		}
		return false;
	}
	
	public static Problem getProblem(String cpid)
	{
		return new Problem("811", "Sample Problem OwO", "Test Contest Set Mark IV", "(insert PDF view here + notes + html code)", null, null);
	}
}
