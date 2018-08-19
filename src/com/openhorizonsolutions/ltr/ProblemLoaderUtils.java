package com.openhorizonsolutions.ltr;

import java.util.ArrayList;

public class ProblemLoaderUtils
{
	// the folowing methods are sample methods that return dummy output.
	// these will return dynamic data based on reading config files from a designated directory later.
	private static ArrayList<Problem> tmpLst = new ArrayList<Problem>();
		
	public static void refreshIO()
	{
		tmpLst = new ArrayList<Problem>();
		tmpLst.add(new Problem("1", "Sample Problem OwO", "Test Contest Set Mark IV", "(insert PDF view here + notes + html code)", null, null));
		tmpLst.add(new Problem("2", "Sample Problem OwO II", "Test Contest Set Mark IV", "(insert PDF view here + notes + html code)", null, null));
		tmpLst.add(new Problem("3", "Sample Problem OwO III", "Test Contest Set Mark IV", "Sample Text<br><br>Sample question text here. There are $N$ pies on the side of the road. $0.5N$ of these pies are green and blue.<br><br>(insert PDF view here + notes + html code)", null, null));
	}
	
	public static boolean problemExists(String cpid)
	{
		refreshIO();
		System.out.println("CPID:" + cpid);
		for (Problem p : tmpLst)
		{
			if (p.getCPID().matches(cpid))
			{
				return true;
			}
		}
		return false;
	}
	
	public static Problem getProblem(String cpid)
	{
		for (Problem p : tmpLst)
		{
			if (p.getCPID().matches(cpid))
			{
				return p;
			}
		}
		return null;
	}
	
	public static ArrayList<Problem> getProblemList()
	{
		return tmpLst;
	}
}
