package com.openhorizonsolutions.ltr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
		// System.out.println("CPID:" + cpid);
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
	
	
	public static String escapeHTML(String s) 
	{
	    StringBuilder out = new StringBuilder(Math.max(16, s.length()));
	    for (int i = 0; i < s.length(); i++) 
	    {
	        char c = s.charAt(i);
	        if (c > 127 || c == '"' || c == '<' || c == '>' || c == '&')
	        {
	            out.append("&#");
	            out.append((int) c);
	            out.append(';');
	        } 
	        else
	        {
	            out.append(c);
	        }
	    }
	    return out.toString();
	}

	
	public static StdPipePostExecOutputHandler getProgramOutput(String uuid, File file, int language) throws InterruptedException
	{
		File programOutputFile = new File(file.getParent(), uuid + "-program-output");
		File programErrFile = new File(file.getParent(), uuid + "-program-error");
		String command = "";
		if (language == 0 || language == 1)
		{
			command += "";
			command += file.toString();
		}
		else if (language == 2)
		{
			command += "/usr/bin/java -cp ";
			command += file.getParent() + " ";
			command += file.getName().replaceAll(".class", "");
		}
		else if (language == 3)
		{
			command += "/usr/bin/python2 ";
			command += file.toString();
		}
		else if (language == 4)
		{
			command += "/usr/bin/python3 ";
			command += file.toString();
		}
		try 
		{
			ArrayList<Integer> al = new ArrayList<Integer>();
			// 1 = executed and terminated normally
			// 2 = forcekilled due to hangup on time.
			ProcessBuilder pb = new ProcessBuilder(command.split(" "));
			pb.redirectOutput(programOutputFile);
			pb.redirectError(programErrFile);
			Process buildProcess = pb.start();
		    // System.out.println("Waiting for process...");
			new Thread(new Runnable() 
			{

				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					try 
					{
						Thread.sleep(2000);
						if (buildProcess.isAlive())
						{
							buildProcess.destroy();
							al.add(1);
						}
					} 
					catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}).start();
		    buildProcess.waitFor();
		    byte[] stdoutarr = Files.readAllBytes(programOutputFile.toPath());
		    byte[] stderrarr = Files.readAllBytes(programErrFile.toPath());
		    String stdout = new String(stdoutarr);
		    String stderr = new String(stderrarr);
		    // System.out.println("STDOUT: " + stdout + "<");
		    // System.out.println("STDERR: " + stderr + "<");
		    if (al.size() == 0)
		    {
		    	return new StdPipePostExecOutputHandler(stdout, stderr);
		    }
		    else
		    {
		    	return new StdPipePostExecOutputHandler("Process Forcibly Terminated", "The process took too long to run, and was terminated.");
		    }
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new StdPipePostExecOutputHandler(e.getMessage(), e.getMessage());
		}
	}
	
	public static StdPipePostExecOutputHandler compileProgram(String uuid, File file, int language) throws InterruptedException
	{
		File programOutputFile = new File(file.getParent(), uuid + "-compiler-output");
		File programErrFile = new File(file.getParent(), uuid + "-compiler-error");
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
			return new StdPipePostExecOutputHandler("", "");
		}

		try 
		{
			ProcessBuilder pb = new ProcessBuilder(command.split(" "));
			pb.redirectOutput(programOutputFile);
			pb.redirectError(programErrFile);
			Process buildProcess = pb.start();
		    // System.out.println("Waiting for process...");
		    buildProcess.waitFor();
		    // System.out.println("Done! Yiff yiff!~");
		    byte[] stdoutarr = Files.readAllBytes(programOutputFile.toPath());
		    byte[] stderrarr = Files.readAllBytes(programErrFile.toPath());
		    String stdout = new String(stdoutarr);
		    String stderr = new String(stderrarr);
		    // System.out.println("STDOUT: " + stdout + "<");
		    // System.out.println("STDERR: " + stderr + "<");
		    return new StdPipePostExecOutputHandler(stdout, stderr);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new StdPipePostExecOutputHandler(e.getMessage(), e.getMessage());
		}
	}
	
}