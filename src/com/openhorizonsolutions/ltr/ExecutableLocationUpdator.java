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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONObject;

@SuppressWarnings("all")
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
			File configFolder = new File(directory, "config");
			if (configFolder.exists())
			{
				if (configFolder.isFile())
				{
					configFolder.delete();
					configFolder = new File(directory, "config");
					configFolder.mkdir();
				}
			}
			else
			{
				configFolder.mkdir();
			}
			jsonFile = new File(configFolder, "settings.json");
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
			String jsonData = "";
			try
			{
				jsonData = new String(Files.readAllBytes((jsonFile.toPath())));
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
