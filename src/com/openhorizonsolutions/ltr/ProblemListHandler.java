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
