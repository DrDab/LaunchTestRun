package com.openhorizonsolutions.ltr;

public class Stopwatch
{
	private final long start;
	private final long startN;
	
	public Stopwatch()
	{
		start = System.currentTimeMillis();
		startN = System.nanoTime();
	}
	
	public double elapsedTime() 
	{
		long now = System.currentTimeMillis();
		return(now - start) / 1000.0;
	}
	
	public double getTime()
	{
		return System.currentTimeMillis()/1000.0;
	}
	
	public double getElapsedNanoTime()
	{
		return System.nanoTime() - startN;
	}
	
} 