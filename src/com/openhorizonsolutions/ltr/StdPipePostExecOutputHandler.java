package com.openhorizonsolutions.ltr;

public class StdPipePostExecOutputHandler 
{
	private String stdout;
	private String stderr;
	
	public StdPipePostExecOutputHandler(String stdout, String stderr)
	{
		this.stdout = stdout;
		this.stderr = stderr;
	}
	
	public String getStdOut()
	{
		return stdout;
	}
	
	public String getStdErr()
	{
		return stderr;
	}
}
