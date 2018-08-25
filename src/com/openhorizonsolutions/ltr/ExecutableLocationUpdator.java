package com.openhorizonsolutions.ltr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.json.JSONObject;

public class ExecutableLocationUpdator
{
	private String directory;
	private File jsonFile;
	private String jsonData;
	private JSONObject mainObject;
	
	public ExecutableLocationUpdator(String directory)
	{
		try
		{
			this.directory = directory;
			jsonFile = new File(directory, "settings.json");
			if (!jsonFile.exists())
			{
				jsonFile.createNewFile();
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(jsonFile)));
				pw.println("{");
				pw.println("	\"java\":\"/usr/bin/java\",");
				pw.println("	\"javac\":\"/usr/bin/javac\",");
				pw.println("	\"gcc\":\"/usr/bin/gcc\",");
				pw.println("	\"g++\":\"/usr/bin/g++\",");
				pw.println("	\"python2\":\"/usr/bin/python2\",");
				pw.println("	\"python3\":\"/usr/bin/python3\"");
				pw.println("	\"csharp-build\":\"/usr/bin/mcs\"");
				pw.println("	\"csharp-run\":\"/usr/bin/mono\"");
				pw.println("}");
				pw.close();
			}
			jsonData = ProblemLoaderUtils.getJSONDataFromFile(jsonFile);
			mainObject = new JSONObject(jsonData);
		}
		catch (Exception e)
		{
			System.err.println("Couldn't load settings JSON: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String getJava()
	{
		if (mainObject.has("java"))
		{
			return mainObject.getString("java");
		}
		return "";
	}
	
	public String getJavac()
	{
		if (mainObject.has("javac"))
		{
			return mainObject.getString("javac");
		}
		return "";
	}
	
	public String getGCC()
	{
		if (mainObject.has("gcc"))
		{
			return mainObject.getString("gcc");
		}
		return "";
	}
	
	public String getGPP()
	{
		if (mainObject.has("g++"))
		{
			return mainObject.getString("g++");
		}
		return "";
	}
	
	public String getPython27()
	{
		if (mainObject.has("python2"))
		{
			return mainObject.getString("python2");
		}
		return "";
	}
	
	public String getPython36()
	{
		if (mainObject.has("python3"))
		{
			return mainObject.getString("python3");
		}
		return "";
	}
	
	public String getMCS()
	{
		if (mainObject.has("csharp-build"))
		{
			return mainObject.getString("csharp-build");
		}
		return "";
	}
	
	public String getCSharpRunner()
	{
		if (mainObject.has("csharp-run"))
		{
			return mainObject.getString("csharp-run");
		}
		return "";
	}
}
