package com.openhorizonsolutions.ltr;

import java.util.ArrayList;
import java.util.HashMap;

public class ProblemListHandler 
{
	private ArrayList<Problem> problemList;
	private HashMap<String, Integer> problemMap;
	
	public ProblemListHandler(ArrayList<Problem> problemList, HashMap<String, Integer> problemMap)
	{
		this.problemList = problemList;
		this.problemMap = problemMap;
	}
	
	public boolean problemExists(String cpid)
	{
		return problemMap.containsKey(cpid);
	}
	
	public Problem getProblem(String cpid)
	{
		if (problemMap.containsKey(cpid))
		{
			return problemList.get(problemMap.get(cpid));
		}
		return null;
	}
	
	public ArrayList<Problem> getProblemList()
	{
		return problemList;
	}
}
