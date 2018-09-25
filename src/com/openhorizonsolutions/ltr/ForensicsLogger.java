package com.openhorizonsolutions.ltr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

@SuppressWarnings("all")
public class ForensicsLogger 
{
	private Stopwatch stw;
	private File logfile = null;
	private PrintWriter pw = null;
	
	public ForensicsLogger(Stopwatch stw)
	{
		this.stw = stw;
	}
	
	public void setFile(File logfile)
	{
		this.logfile = logfile;
		if (pw == null)
		{
			try
			{
				pw = new PrintWriter(new BufferedWriter(new FileWriter(logfile)));
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void logUpload(String uuid, String md5sum, String IP, String language, String filename, int filesize)
	{
		double time = stw.getTime();
		String writeOut = "[ " + time + " ] (UPLOAD) UUID: " + uuid + " MD5: " + md5sum + " IP: " + IP + " LANG: " + language + " FILE: " + filename + " SIZE: " + filesize;
		if (!logfile.exists())
		{
			try 
			{
				logfile.createNewFile();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pw.println(writeOut);
		pw.flush();
	}
	
	public void logUploadResult(boolean judge, String uuid, String stdin, String stdout, String stderr, double millis)
	{
		double time = stw.getTime();
		String writeOut = "[ " + time + " ] (EXECUTE) UUID: " + uuid + " JUDGE: " + judge + "\nSTDIN:\n" + stdin + "<\nSTDOUT:\n" + stdout + "<\nSTDERR:\n" + stderr + "<\nTIME: " + millis + "ms";
		if (!logfile.exists())
		{
			try 
			{
				logfile.createNewFile();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pw != null)
		{
			pw.println(writeOut);
			pw.flush();
		}
	}
	
	public void logError(String exception, String IP)
	{
		double time = stw.getTime();
		String writeOut = "\n\n[ " + time + " ] [!] (ERROR) Exception:\n" + exception + " IP: " + IP + "\n";
		if (!logfile.exists())
		{
			try 
			{
				logfile.createNewFile();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pw != null)
		{
			pw.println(writeOut);
			pw.flush();
		}
	}
}
