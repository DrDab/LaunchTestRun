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

public class StdPipePostExecOutputHandler 
{
	private String stdin;
	private String stdout;
	private String stderr;
	private double millis;
	
	public StdPipePostExecOutputHandler(String stdin, String stdout, String stderr, double millis)
	{
		this.stdin = stdin;
		this.stdout = stdout;
		this.stderr = stderr;
		this.millis = millis;
	}
	
	public String getStdIn()
	{
		return stdin;
	}
	
	public String getStdOut()
	{
		return stdout;
	}
	
	public String getStdErr()
	{
		return stderr;
	}
	
	public double getMillis()
	{
		return millis;
	}
}
