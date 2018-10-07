package com.openhorizonsolutions.ltr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

/**
 * Servlet implementation class SolutionUploadHandler
 */
@WebServlet("/SolutionUploadHandler")
@MultipartConfig
public class SolutionUploadHandler extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIRECTORY = "upload";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SolutionUploadHandler()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Error: Form must has enctype=multipart/form-data in POST form.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		double start = DataStore.stw.getElapsedNanoTime();
		String ip = request.getRemoteAddr();
		String realPath = getServletContext().getRealPath("");
		
		if (((start - DataStore.lastTimeListRefreshed) / 1000000000.0) >= 60.0)
		{
			DataStore.lastTimeListRefreshed = start;
			DataStore.ipMap.clear();
		}
		
		if (DataStore.ipMap != null)
		{
			if (DataStore.ipMap.containsKey(ip))
			{
				DataStore.ipMap.put(ip, (int)(DataStore.ipMap.get(ip) + 1));
			}
			else
			{
				if (!ip.equals("127.0.0.1"))
				{
					DataStore.ipMap.put(ip, 1);
				}
			}
			
			if (DataStore.ipMap.containsKey(ip))
			{
				if (DataStore.ipMap.get(ip) > 6)
				{
					request.setAttribute("message", "<subsection>Upload Blocked</subsection><br><br><plain>Reason: You have attempted to upload too many files at a time. Please wait 60 seconds, relax and try again.</plain>");
					double totalTimeUsed = (double) ((DataStore.stw.getElapsedNanoTime() - start) / 1000000000.0);
					String end = String.format("Page requested: %s <br>Page generated in: %5.3f seconds<br>LaunchTestRun is (C) copyright of Victor Du.", request.getRequestURI(), totalTimeUsed);
					request.setAttribute("debuginfo", end);
					getServletContext().getRequestDispatcher("/assets/message.jsp").forward(request, response);
					return;
				}
			}
		}
		
		PrintWriter writer = response.getWriter();
		if (!ServletFileUpload.isMultipartContent(request)) 
		{
			// if not, we stop here
			writer.println("Error: Form must has enctype=multipart/form-data.");
			writer.flush();
			return;
		} 
		else 
		{
			DiskFileItemFactory factory = new DiskFileItemFactory();

			factory.setSizeThreshold(1024 * 512);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);

			upload.setSizeMax(1024 * 512);

			String uuid = UUID.randomUUID().toString();
			
			File uploadDirParent = new File(realPath, UPLOAD_DIRECTORY);
			File uploadDir = new File(uploadDirParent, uuid);
			File logFile = new File(realPath, "forensics.txt");
			
			if (!uploadDir.exists())
			{
				if (!uploadDirParent.exists() || !uploadDirParent.isDirectory())
				{
					uploadDirParent.mkdir();
				}
				uploadDir.mkdir();
			}
			else
			{
				uploadDir.delete();
				uploadDir = new File(uploadDirParent, uuid);
				uploadDir.mkdir();
			}

			if (!logFile.exists())
			{
				logFile.createNewFile();
			}
			
			DataStore.forensicsLogger.setFile(logFile);
			
			Part filePart = request.getPart("sourcefile");
			Part problemIDPart = request.getPart("cpid");
			Part languagePart = request.getPart("language");
			InputStream filecontent = filePart.getInputStream();
			InputStream pidcontent = problemIDPart.getInputStream();
			InputStream langcontent = languagePart.getInputStream();
			String fileName = new File(filePart.getSubmittedFileName()).getName();
			String fileExtension = ProblemLoaderUtils.getFileExtension(fileName);
			byte[] buffer = new byte[filecontent.available()];
			byte[] pidpartbuffer = new byte[pidcontent.available()];
			byte[] languagebuffer = new byte[langcontent.available()];
			filecontent.read(buffer, 0, filecontent.available());
			pidcontent.read(pidpartbuffer, 0, pidcontent.available());
			langcontent.read(languagebuffer, 0, langcontent.available());
			String problemID = new String(pidpartbuffer);
			String languageStr = new String(languagebuffer);
			File storeFile = new File(uploadDir, fileName);
			
			/*
			System.out.println("Path: " + storeFile.getAbsolutePath());
			System.out.println("Problem ID: " + problemID);
			System.out.println("Filename: " + fileName);
			System.out.println("File size: " + buffer.length);
			*/
			
			// the file size is over 1MB
			int fileSize = buffer.length;
			if (fileSize >= 10000)
			{
				request.setAttribute("message", "File size too big. Please upload a smaller file under 1MB.");
			}
			else
			{
				int languageType = -1;
				try
				{
					languageType = Integer.parseInt(languageStr);
					languageType--;
				}
				catch (NumberFormatException nfe0)
				{
					nfe0.printStackTrace();
					request.setAttribute("message", "The selected language type is invalid.");
				}
				boolean ok = true;
				if (languageType > 5 || languageType < 0)
				{
					ok = false;
				}
				
				if (ok)
				{
					if (!(fileExtension.equals(DataStore.types[languageType])) && !(fileExtension.equals(DataStore.altTypes[languageType])))
					{
						DataStore.forensicsLogger.logUpload(uuid, "", ip, "Invalid file type: " + fileExtension, uploadDir.getPath(), fileSize);
						ok = false;
					}
				}
				
				if (ok)
				{
					storeFile.createNewFile();
					OutputStream outStream = new FileOutputStream(storeFile);
					outStream.write(buffer);
					outStream.close();
					
					String md5sum = "";
					try
					{
						md5sum = getMD5Checksum(storeFile);
					} 
					catch (NoSuchAlgorithmException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					DataStore.forensicsLogger.logUpload(uuid, md5sum, ip, DataStore.typeNames[languageType], uploadDir.getPath(), fileSize);
					Date uploadDate = new Date((long) (DataStore.stw.getTime() * 1000.0));
					double ioDone = (DataStore.stw.getElapsedNanoTime() - start) / 1000000000.0;
					
					String realpath = getServletContext().getRealPath("");

					StdPipePostExecOutputHandler compilerOutput = null;
					try 
					{
						compilerOutput = ProblemLoaderUtils.compileProgram(uuid, storeFile, languageType, realpath);
					} 
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					File executableFile = null;
					if (languageType == 0 || languageType == 1)
					{
						executableFile = new File(storeFile.getParent(), "toExecute");
					}
					else if (languageType == 2)
					{
						executableFile = new File(storeFile.getParent(), storeFile.getName().replaceAll(".java", "") + ".class");
					}
					else if (languageType == 3 || languageType == 4)
					{
						executableFile = storeFile;
					}
					else if (languageType == 5)
					{
						executableFile = new File(storeFile.getParent(), "toExecute.exe");
					}
					
					ProblemListHandler tmp = ProblemLoaderUtils.refreshIO(realpath);
					Problem curProblem = tmp.getProblem(problemID);
					int timeout = curProblem.getTimeOutMillis();
					File sampleInput = curProblem.getSampleInput();
					File judgeInput = curProblem.getJudgeInput();
					File sampleInputLocal = new File(uploadDir, sampleInput.getName());
					File judgeInputLocal = new File(uploadDir, judgeInput.getName());
					
					try
					{
						ProblemLoaderUtils.copyFile(sampleInput, sampleInputLocal);
						ProblemLoaderUtils.copyFile(judgeInput, judgeInputLocal);
					}
					catch (NullPointerException npe0)
					{
					}
					
					double runningStart = DataStore.stw.getElapsedNanoTime();
					String inputname = curProblem.getInputName();
					File inputfile = new File(uploadDir, inputname);
					sampleInputLocal.renameTo(inputfile);
					StdPipePostExecOutputHandler executionOutputSample = null;
					try 
					{
						executionOutputSample = ProblemLoaderUtils.getProgramOutput(uuid, executableFile, inputfile, languageType, timeout, realpath);
					} 
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sampleInputLocal.delete();
					judgeInputLocal.renameTo(inputfile);
					StdPipePostExecOutputHandler executionOutputJudge = null;
					try 
					{
						executionOutputJudge = ProblemLoaderUtils.getProgramOutput(uuid, executableFile, inputfile, languageType, timeout, realpath);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					judgeInputLocal.delete();
					double runningDone = (DataStore.stw.getElapsedNanoTime() - runningStart) / 1000000000.0;

					String sampleStatus = "SYSTEM ERROR";
					String judgeStatus = "SYSTEM ERROR";
					byte[] expectedSampleOutputBytes = Files.readAllBytes(curProblem.getSampleOutput().toPath());
					byte[] expectedJudgeOutputBytes = Files.readAllBytes(curProblem.getJudgeOutput().toPath());
					String expectedSampleOutput = new String(expectedSampleOutputBytes).trim();
					String expectedJudgeOutput = new String(expectedJudgeOutputBytes).trim();
					
					DataStore.forensicsLogger.logUploadResult(false, uuid, executionOutputSample.getStdIn(), executionOutputSample.getStdOut(), executionOutputSample.getStdErr(), executionOutputSample.getMillis());
					DataStore.forensicsLogger.logUploadResult(true, uuid, executionOutputJudge.getStdIn(), executionOutputJudge.getStdOut(), executionOutputJudge.getStdErr(), executionOutputJudge.getMillis());
					
					boolean success = true;
					
					if (executionOutputSample.getStdErr().trim().equals("The process took too long to run, and was terminated."))
					{
						sampleStatus = "<plainbold><font color=\"#e62100\">TIMEOUT</font></plainbold>";
						success = false;
					}
					else if (!executionOutputSample.getStdErr().trim().equals(""))
					{
						sampleStatus = "<plainbold><font color=\"#e62100\">ERROR</font></plainbold>";
						success = false;
					}
					else if (executionOutputSample.getStdOut().trim().matches(expectedSampleOutput))
					{
						sampleStatus = "<plainbold><font color=\"#66e621\">CORRECT</font></plainbold>";
					}
					else if (executionOutputSample.getStdOut().trim().equals(expectedSampleOutput))
					{
						sampleStatus = "<plainbold><font color=\"#66e621\">CORRECT</font></plainbold>";
					}
					else
					{
						sampleStatus = "<plainbold><font color=\"#e62100\">WRONG</font></plainbold>";
						success = false;
					}
					
					if (executionOutputJudge.getStdErr().trim().equals("The process took too long to run, and was terminated."))
					{
						judgeStatus = "<plainbold><font color=\"#e62100\">TIMEOUT</font></plainbold>";
						success = false;
					}
					else if (!executionOutputJudge.getStdErr().trim().equals(""))
					{
						judgeStatus = "<plainbold><font color=\"#e62100\">ERROR</font></plainbold>";
						success = false;
					}
					else if (executionOutputJudge.getStdOut().trim().matches(expectedJudgeOutput))
					{
						judgeStatus = "<plainbold><font color=\"#66e621\">CORRECT</font></plainbold>";
					}
					else if (executionOutputJudge.getStdOut().trim().equals(expectedJudgeOutput))
					{
						judgeStatus = "<plainbold><font color=\"#66e621\">CORRECT</font></plainbold>";
					}
					else
					{
						judgeStatus = "<plainbold><font color=\"#e62100\">WRONG</font></plainbold>";
						success = false;
					}
					
					boolean compileSuccessful = executableFile.exists();
					
					String message = "";
					message += "<plainbold>Problem</plainbold>";
					message += "<plain>";
					message += "<br>" + curProblem.getTitle() + " (" + curProblem.getSetInfo() + ")";
					message += "<br><br>";
					message += "<plainbold>File Upload Details</plainbold>";
					message += "<br>File Name: " + fileName;
					message += "<br>File Size: " + buffer.length + " B";
					message += "<br>File MD5 Checksum: " + md5sum;
					message += "<br>Language: " + DataStore.typeNames[languageType];
					message += "<br>Date Uploaded: " + uploadDate.toString() + "<br><br>";
					message += "<plainbold>Your Results</plainbold>";
					message += "<plain><br>Results from sample data test:</plain> <plainbold>" + sampleStatus + "</plainbold> (" + executionOutputSample.getMillis() + "ms)";
					message += "<plain><br>Results from judge data test:</plain> <plainbold>" + judgeStatus + "</plainbold> (" + executionOutputJudge.getMillis() + "ms)";
					if (success)
					{
						message += ("<br><br>" + ProblemLoaderUtils.getWinnerFlavorText(realPath) + "<br>");
					}
					else
					{
						message += ("<br><br>" + ProblemLoaderUtils.getInspirationalFlavorText(realPath) + "<br>");
					}
					
					if (compileSuccessful)
					{
						if (sampleStatus.equals("<plainbold><font color=\"#e62100\">WRONG</font></plainbold>") || sampleStatus.equals("<plainbold><font color=\"#e62100\">ERROR</font></plainbold>"))
						{
							message += "<br><br><plainbold>Execution Output w/ Sample Data</plainbold>";
							message += "<br>The data inputted was:<br><pre><code>" + ProblemLoaderUtils.escapeHTML(executionOutputSample.getStdIn()) + "</code></pre>";
							message += "<br>The expected standard output was:<br><pre><code>" + ProblemLoaderUtils.escapeHTML(expectedSampleOutput) + "</code></pre>";
							message += "<br>Your program wrote this to standard output instead:<br><pre><code>" + ProblemLoaderUtils.escapeHTML(executionOutputSample.getStdOut()) + "</code></pre>";
							if (!executionOutputSample.getStdErr().equals(""))
							{
								message += "<br>Your program threw the following errors:<br><pre><code>" + ProblemLoaderUtils.escapeHTML(executionOutputSample.getStdErr()) + "</code></pre><br>";
							}
						}
						
						if (judgeStatus.equals("<plainbold><font color=\"#e62100\">WRONG</font></plainbold>") || judgeStatus.equals("<plainbold><font color=\"#e62100\">ERROR</font></plainbold>"))
						{
							message += "<br><br><plainbold>Execution Output w/ Judge Data</plainbold>";
							message += "<br>The data inputted was:<br><pre><code>" + ProblemLoaderUtils.escapeHTML(executionOutputJudge.getStdIn()) + "</code></pre>";
							message += "<br>The expected standard output was:<br><pre><code>" + ProblemLoaderUtils.escapeHTML(expectedJudgeOutput) + "</code></pre>";
							message += "<br>Your program wrote this to standard output instead:<br><pre><code>" + ProblemLoaderUtils.escapeHTML(executionOutputJudge.getStdOut()) + "</code></pre>";
							if (!executionOutputJudge.getStdErr().equals(""))
							{
								message += "<br>Your program threw the following errors:<br><pre><code>" + ProblemLoaderUtils.escapeHTML(executionOutputJudge.getStdErr()) + "</code></pre><br>";
							}
						}
					}
					else
					{
						boolean stdOutNotNull = !compilerOutput.getStdOut().equals("");
						boolean stdErrNotNull = !compilerOutput.getStdErr().equals("");
						String toFilterCompilerStdOut = ProblemLoaderUtils.escapeHTML(compilerOutput.getStdOut());
						String toFilterCompilerStdErr = ProblemLoaderUtils.escapeHTML(compilerOutput.getStdErr());
						toFilterCompilerStdOut = toFilterCompilerStdOut.replaceAll(executableFile.getParent(), "");
						toFilterCompilerStdErr = toFilterCompilerStdErr.replaceAll(executableFile.getParent(), "");
						if (stdOutNotNull || stdErrNotNull)
						{
							message += "<br><br><plainbold>Compiler Output</plainbold>";
						}
						if (stdOutNotNull)
						{
							message += "<br>The compiler wrote this to standard output:<br><pre><code>" + toFilterCompilerStdOut + "<br><br>";
						}
						if (stdErrNotNull)
						{
							message += "<br>The compiler threw the following errors:<br><pre><code>" + toFilterCompilerStdErr + "<br><br>";
						}
					}
					message += "</plain><br><br>";
					request.setAttribute("message", message);
					
					double totalTimeUsed = (double) ((DataStore.stw.getElapsedNanoTime() - start) / 1000000000.0);
					double compilerTime = (double)(compilerOutput.getMillis() / 1000.0);
					
					String end = String.format("Page requested: %s <br>Page generated in: %5.2f seconds<br>" +
							"Upload processed in: %5.3f seconds<br>" +
							"Code compiled in: %5.3f seconds<br>" +
							"Code judged in: %5.3f seconds<br><br>" +
							"LaunchTestRun is (C) copyright of Victor Du.", request.getRequestURI(), totalTimeUsed, ioDone, compilerTime, runningDone);
					request.setAttribute("debuginfo", end);
				}
				else
				{
					request.setAttribute("message", "The selected language type is doesn't match the file-type uploaded.");
					double totalTimeUsed = (double) ((DataStore.stw.getElapsedNanoTime() - start) / 1000000000.0);
					String end = String.format("Page requested: %s <br>Page generated in: %5.3f seconds<br>LaunchTestRun is (C) copyright of Victor Du.", request.getRequestURI(), totalTimeUsed);
					request.setAttribute("debuginfo", end);
				}
			}
			getServletContext().getRequestDispatcher("/assets/message.jsp").forward(request, response);

		}
		return;
	}
	
	public static byte[] createChecksum(File file) throws IOException, NoSuchAlgorithmException
	{
		InputStream fis = new FileInputStream(file);

		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;

		do
		{
			numRead = fis.read(buffer);
			if (numRead > 0) 
			{
				complete.update(buffer, 0, numRead);
			}
		} 
		while (numRead != -1);

		fis.close();
		return complete.digest();
	}

	public static String getMD5Checksum(File file) throws IOException, NoSuchAlgorithmException
	{
		byte[] b = createChecksum(file);
		String result = "";

		for (int i = 0; i < b.length; i++)
		{
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

}