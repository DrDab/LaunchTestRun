package com.openhorizonsolutions.ltr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class ProblemLoaderUtils
{
	// the folowing methods are sample methods that return dummy output.
	// these will return dynamic data based on reading config files from a designated directory later.
	private static ArrayList<Problem> tmpLst = new ArrayList<Problem>();
	private static final String UPLOAD_DIRECTORY = "problems";
	
	public static void refreshIO(String realPath)
	{
		ArrayList<Problem> tmp = new ArrayList<Problem>();
		String problemsPath = realPath + File.separator + UPLOAD_DIRECTORY;
		System.out.println(problemsPath);
		File problemsFolder = new File(problemsPath);
		if (!problemsFolder.exists())
		{
			problemsFolder.mkdir();
		}
		
		File[] subdirectories = problemsFolder.listFiles();
		for (File subFolder : subdirectories)
		{
			File configJson = new File(subFolder, "config.json");
			if (configJson.exists() && configJson.isFile())
			{
				// parse this json
				// format:
				/**
				 * {
				 * 	"cpid":"12345",
				 * 	"title":"sampletitle",
				 * 	"description":"sampledescription",
				 * 	"setinfo":"samplesetinfo",
				 * 	"samplein":"sample.in",
				 * 	"sampleout":"sample.out",
				 * 	"judgein":"judge.in",
				 * 	"judgeout":"judge.out"
				 * }
				 */
				try
				{
					String jsonData = getJSONDataFromFile(configJson);
					JSONObject mainObj = new JSONObject(jsonData);
					String cpid = mainObj.getString("cpid");
					String title = mainObj.getString("title");
					String description = mainObj.getString("description");
					String setInfo = mainObj.getString("setinfo");
					String sampleIn = mainObj.getString("samplein");
					String sampleOut = mainObj.getString("sampleout");
					String judgeIn = mainObj.getString("judgein");
					String judgeOut = mainObj.getString("judgeout");
					File sampleInFile = new File(subFolder, sampleIn);
					File sampleOutFile = new File(subFolder, sampleOut);
					File judgeInFile = new File(subFolder, judgeIn);
					File judgeOutFile = new File(subFolder, judgeOut);
					tmp.add(new Problem(cpid, title, setInfo, description, sampleInFile, sampleOutFile, judgeInFile, judgeOutFile));
				}
				catch (JSONException je0)
				{
					je0.printStackTrace();
				}
			}
		}
		tmpLst = tmp;
	}
	
	public static boolean problemExists(String realPath, String cpid)
	{
		refreshIO(realPath);
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
	
	 public static String getJSONDataFromFile(File location)
	 {
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(location));
			String s = "";
			String tmp = "";
			try 
			{
				while ((tmp = br.readLine()) != null)
				{
					s += tmp;
				}
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			return s;
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
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