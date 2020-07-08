/*
 * Copyright (c) 2018 TeamsCode, Victor Du
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
	public boolean matchRegex;
	
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
		this.matchRegex = false;
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
		return getTitle().compareTo(o.getTitle());
	}
}
