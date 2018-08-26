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
