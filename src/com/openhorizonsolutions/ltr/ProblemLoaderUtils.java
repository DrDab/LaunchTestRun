package com.openhorizonsolutions.ltr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class ProblemLoaderUtils
{
	// the folowing methods are sample methods that return dummy output.
	// these will return dynamic data based on reading config files from a designated directory later.
	private static ArrayList<Problem> tmpLst = new ArrayList<Problem>();
	private static HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
	private static final String UPLOAD_DIRECTORY = "problems";
	
	public static void refreshIO(String realPath)
	{
		ArrayList<Problem> tmp = new ArrayList<Problem>();
		String problemsPath = realPath + File.separator + UPLOAD_DIRECTORY;
		File problemsFolder = new File(problemsPath);
		if (!problemsFolder.exists())
		{
			problemsFolder.mkdir();
		}
		
		File[] subdirectories = problemsFolder.listFiles();
		for (File subFolder : subdirectories)
		{
			if (subFolder.isDirectory())
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
					 * 	"pdf":"(insert pdf link)"
					 * 	"setinfo":"samplesetinfo",
					 * 	"samplein":"sample.in",
					 * 	"sampleout":"sample.out",
					 * 	"judgein":"judge.in",
					 * 	"judgeout":"judge.out"
					 * 	"inputname":"input.txt"
					 * 	"timeout":"2000"
					 * }
					 */
					try
					{
						String jsonData = getJSONDataFromFile(configJson);
						JSONObject mainObj = new JSONObject(jsonData);
						String cpid = mainObj.getString("cpid");
						String title = mainObj.getString("title");
						String description = "";
						if (mainObj.has("description"))
						{
							description = mainObj.getString("description");
						}
						String pdf = "";
						if (mainObj.has("pdf"))
						{
							pdf = mainObj.getString("pdf");
						}
						String setInfo = mainObj.getString("setinfo");
						String sampleIn = mainObj.getString("samplein");
						String sampleOut = mainObj.getString("sampleout");
						String judgeIn = mainObj.getString("judgein");
						String judgeOut = mainObj.getString("judgeout");
						String inputName = mainObj.getString("inputname");
						int timeout = Integer.parseInt(mainObj.getString("timeout"));
						File sampleInFile = new File(subFolder, sampleIn);
						File sampleOutFile = new File(subFolder, sampleOut);
						File judgeInFile = new File(subFolder, judgeIn);
						File judgeOutFile = new File(subFolder, judgeOut);
						tmpMap.put(cpid, tmp.size());
						tmp.add(new Problem(cpid, title, setInfo, description, pdf, sampleInFile, sampleOutFile, judgeInFile, judgeOutFile, inputName, timeout));
					}
					catch (JSONException je0)
					{
						je0.printStackTrace();
					}
					catch (NumberFormatException nfe0)
					{
						nfe0.printStackTrace();
					}
				}
			}
		}
		Collections.sort(tmpLst);
		tmpLst = tmp;
	}
	
	public static boolean problemExists(String cpid)
	{
		return tmpMap.containsKey(cpid);
	}
	
	public static Problem getProblem(String cpid)
	{
		if (tmpMap.containsKey(cpid))
		{
			return tmpLst.get(tmpMap.get(cpid));
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
	
	public static StdPipePostExecOutputHandler getProgramOutput(String uuid, File file, int language, int timeout, String serverpath) throws InterruptedException
	{
		if (!file.getParentFile().exists())
		{
			file.getParentFile().mkdir();
		}
		ExecutableLocationUpdator eld = new ExecutableLocationUpdator(serverpath);
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
			command += eld.getJava() + " -cp ";
			command += file.getParent() + " ";
			command += file.getName().replaceAll(".class", "");
		}
		else if (language == 3)
		{
			command += eld.getPython27() + " ";
			command += file.toString();
		}
		else if (language == 4)
		{
			command += eld.getPython36() + " ";
			command += file.toString();
		}
		try 
		{
			ArrayList<Integer> al = new ArrayList<Integer>();
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
						Thread.sleep((long) timeout);
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
	
	public static StdPipePostExecOutputHandler compileProgram(String uuid, File file, int language, String serverpath) throws InterruptedException
	{
		if (!file.getParentFile().exists())
		{
			file.getParentFile().mkdir();
		}
		ExecutableLocationUpdator eld = new ExecutableLocationUpdator(serverpath);
		File programOutputFile = new File(file.getParent(), uuid + "-compiler-output");
		File programErrFile = new File(file.getParent(), uuid + "-compiler-error");
		String command = "";
		if (language == 0)
		{
			command += eld.getGCC() + " ";
			command += file.getAbsolutePath() + " ";
			command += "-o " + file.getParent() + "/toExecute";
		}
		else if (language == 1)
		{
			command += eld.getGPP() + " ";
			command += file.getAbsolutePath() + " ";
			command += "--std=c++11 ";
			command += "-o " + file.getParent() + "/toExecute";
		}
		else if (language == 2)
		{
			command += eld.getJavac() + " -source 1.8 -target 1.8 ";
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
	
	public static void copyFile(File source, File dest) throws IOException 
	{
	    InputStream is = null;
	    OutputStream os = null;
	    try 
	    {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[is.available()];
	        int length;
	        while ((length = is.read(buffer)) > 0) 
	        {
	            os.write(buffer, 0, length);
	        }
	    } 
	    finally
	    {
	        is.close();
	        os.close();
	    }
	}
	
	public static String getFileExtension(String fileName) 
	{
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) 
		{
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		else 
		{
			return "";
		}
	}
	
}