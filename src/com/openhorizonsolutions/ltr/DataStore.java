package com.openhorizonsolutions.ltr;

import java.util.HashMap;

public class DataStore
{
	public static Stopwatch stw = new Stopwatch();
	
	public static ForensicsLogger forensicsLogger = new ForensicsLogger(stw);
	
	public static String[] types = {"c", "cpp", "java", "py", "py", "cs"};
	public static String[] altTypes = {"c", "cc", "java", "py", "py", "cs"};
	public static String[] typeNames = {"C", "C++11", "Java", "Python 2", "Python 3", "C#"};
	//public static String[] types = {"text/x-csrc", "text/x-c++src", "text/x-java", "text/x-python", "text/x-python"};
	
	public static HashMap<String, Integer> ipMap = new HashMap<String, Integer>();
	public static double lastTimeListRefreshed = 0.0; // in nanotime
	
	public static HashMap<String, Integer> onlineMap = new HashMap<String, Integer>();
	public static double lastTimeOnlineRefreshed = 0.0; // in nanotime
}
