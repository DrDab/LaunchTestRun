package com.openhorizonsolutions.ltr;

import java.io.File;

public class Problem implements Comparable<Problem>
{
	private String cpid;
	private String title;
	private String setInfo;
	private String description;
	private String pdfLink;
	private File sampleInput;
	private File sampleOutput;
	private File judgeInput;
	private File judgeOutput;
	private String inputName;
	private int timeout;
	
	public Problem(String cpid, String title, String setInfo, String description, String pdfLink, File sampleInput, File sampleOutput, File judgeInput, File judgeOutput, String inputName, int timeout)
	{
		this.cpid = cpid;
		this.title = title;
		this.setInfo = setInfo;
		this.description = description;
		this.pdfLink = pdfLink;
		this.sampleInput = sampleInput;
		this.sampleOutput = sampleOutput;
		this.judgeInput = judgeInput;
		this.judgeOutput = judgeOutput;
		this.inputName = inputName;
		this.timeout = timeout;
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
	
	public String getPDFLink()
	{
		return pdfLink;
	}
	
	public File getSampleInput()
	{
		return sampleInput;
	}
	
	public File getSampleOutput()
	{
		return sampleOutput;
	}
	
	public File getJudgeInput()
	{
		return judgeInput;
	}
	
	public File getJudgeOutput()
	{
		return judgeOutput;
	}
	
	public String getInputName()
	{
		return inputName;
	}
	
	public int getTimeOutMillis()
	{
		return timeout;
	}
	
	@Override
	public int compareTo(Problem o)
	{
		return cpid.compareTo(o.getCPID());
	}
}
