package com.openhorizonsolutions.ltr;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
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
	
	public static StdPipePostExecOutputHandler getProgramOutput(String uuid, File file, int language) throws InterruptedException
	{
		File programOutputFile = new File(file.getParent(), uuid + "-program-output");
		File programErrFile = new File(file.getParent(), uuid + "-program-error");
		String command = "";
		if (language == 0 || language == 1)
		{
			command += "";
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
		command += file.toString();
		try 
		{
			ProcessBuilder pb = new ProcessBuilder(command.split(" "));
			pb.redirectOutput(programOutputFile);
			pb.redirectError(programErrFile);
			Process buildProcess = pb.start();
		    System.out.println("Waiting for process...");
		    buildProcess.waitFor();
		    System.out.println("Done! Yiff yiff!~");
		    byte[] stdoutarr = Files.readAllBytes(programOutputFile.toPath());
		    byte[] stderrarr = Files.readAllBytes(programErrFile.toPath());
		    String stdout = new String(stdoutarr);
		    String stderr = new String(stderrarr);
		    System.out.println("STDOUT: " + stdout + "<");
		    System.out.println("STDERR: " + stderr + "<");
		    return new StdPipePostExecOutputHandler(stdout, stderr);
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
		    System.out.println("Waiting for process...");
		    buildProcess.waitFor();
		    System.out.println("Done! Yiff yiff!~");
		    byte[] stdoutarr = Files.readAllBytes(programOutputFile.toPath());
		    byte[] stderrarr = Files.readAllBytes(programErrFile.toPath());
		    String stdout = new String(stdoutarr);
		    String stderr = new String(stderrarr);
		    System.out.println("STDOUT: " + stdout + "<");
		    System.out.println("STDERR: " + stderr + "<");
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