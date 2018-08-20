package com.openhorizonsolutions.ltr;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	public static String getProgramOutput(File file, int language)
	{
		String command = "";
		if (language == 0 || language == 1)
		{
			command += "exec ";
		}
		else if (language == 2)
		{
			command += "java ";
		}
		else if (language == 3)
		{
			command += "python2 ";
		}
		else if (language == 4)
		{
			command += "python3 ";
		}
		
		return "";
	}
	
	public static String compileProgram(File file, int language) throws InterruptedException
	{
		String command = "";
		if (language == 0)
		{
			command += "/usr/bin/gcc ";
			command += file.getAbsolutePath() + " ";
			command += "-o " + file.getParent() + "/toExecute";
		}
		else if (language == 1)
		{
			command += "/usr/bin/g++ ";
			command += file.getAbsolutePath() + " ";
			command += "--std=c++11 ";
			command += "-o " + file.getParent() + "/toExecute";
		}
		else if (language == 2)
		{
			command += "/usr/bin/javac -source 1.8 -target 1.8 ";
			command += file.getAbsolutePath() + " ";
			command += "-d " + file.getParent();
		}
		else
		{
			return "";
		}

		try 
		{
			ByteArrayOutputStream stdoutStream = new ByteArrayOutputStream();
			PrintStream stdoutPs = new PrintStream(stdoutStream);
			ByteArrayOutputStream stderrStream = new ByteArrayOutputStream();
			PrintStream stderrPs = new PrintStream(stderrStream);
			Process buildProcess = Runtime.getRuntime().exec(command.split(" "));
			inheritIO(buildProcess.getInputStream(), stdoutPs);
		    inheritIO(buildProcess.getErrorStream(), stderrPs);
		    System.out.println("Waiting for process...");
		    buildProcess.waitFor();
		    System.out.println("Done! Yiff yiff!~");
		    String stdout = stdoutStream.toString("UTF8");
		    String stderr = stderrStream.toString("UTF8");
		    System.out.println("STDOUT: " + stdout + "<");
		    System.out.println("STDERR: " + stderr + "<");
		    return stderr;
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public static void inheritIO(final InputStream src, final PrintStream dest)
	{
	    new Thread(new Runnable() 
	    {
	        public void run() 
	        {
	            @SuppressWarnings("resource")
				Scanner sc = new Scanner(src);
	            while (sc.hasNextLine()) 
	            {
	                dest.println(sc.nextLine());
	            }
	        }
	    }
	    ).start();
	}
}
