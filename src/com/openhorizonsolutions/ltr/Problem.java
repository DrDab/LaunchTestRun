package com.openhorizonsolutions.ltr;

import java.io.File;

public class Problem implements Comparable<Problem>
{
	private String cpid;
	private String title;
	private String setInfo;
	private String description;
	private File input;
	private File output;
	
	public Problem(String cpid, String title, String setInfo, String description, File input, File output)
	{
		this.cpid = cpid;
		this.title = title;
		this.setInfo = setInfo;
		this.description = description;
		this.input = input;
		this.output = output;
	}
	
	
	public String getCPID()
	{
		return cpid;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getSetInfo()
	{
		return setInfo;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public File getInputFile()
	{
		return input;
	}
	
	public File getOutputFile()
	{
		return output;
	}
	
	@Override
	public int compareTo(Problem o)
	{
		return cpid.compareTo(o.getCPID());
	}
}
