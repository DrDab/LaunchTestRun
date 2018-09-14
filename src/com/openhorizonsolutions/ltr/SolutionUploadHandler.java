package com.openhorizonsolutions.ltr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.Date;
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

			String realPath = getServletContext().getRealPath("");
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
			
			try
			{
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
							DataStore.forensicsLogger.logUpload(uuid, "", request.getRemoteAddr(), "Invalid file type: " + fileExtension, uploadDir.getPath(), fileSize);
							ok = false;
						}
					}
					
					if (ok)
					{
						storeFile.createNewFile();
						OutputStream outStream = new FileOutputStream(storeFile);
						outStream.write(buffer);
						outStream.close();
						
						String md5sum = getMD5Checksum(storeFile);
						
						DataStore.forensicsLogger.logUpload(uuid, md5sum, request.getRemoteAddr(), DataStore.typeNames[languageType], uploadDir.getPath(), fileSize);
						Date uploadDate = new Date((long) (DataStore.stw.getTime() * 1000.0));
						
						String realpath = getServletContext().getRealPath("");
						
						ProblemLoaderUtils.refreshIO(realpath);
						StdPipePostExecOutputHandler compilerOutput = ProblemLoaderUtils.compileProgram(uuid, storeFile, languageType, realpath);
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
						
						String inputname = curProblem.getInputName();
						File inputfile = new File(uploadDir, inputname);
						sampleInputLocal.renameTo(inputfile);
						StdPipePostExecOutputHandler executionOutputSample = ProblemLoaderUtils.getProgramOutput(uuid, executableFile, inputfile, languageType, timeout, realpath);
						sampleInputLocal.delete();
						judgeInputLocal.renameTo(inputfile);
						StdPipePostExecOutputHandler executionOutputJudge = ProblemLoaderUtils.getProgramOutput(uuid, executableFile, inputfile, languageType, timeout, realpath);
						judgeInputLocal.delete();

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
							sampleStatus = "<font color=\"#e62100\">TIMEOUT</font>";
							success = false;
						}
						else if (!executionOutputSample.getStdErr().trim().equals(""))
						{
							sampleStatus = "<font color=\"#e62100\">ERROR</font>";
							success = false;
						}
						else if (executionOutputSample.getStdOut().trim().matches(expectedSampleOutput))
						{
							sampleStatus = "<font color=\"#66e621\">CORRECT</font>";
						}
						else
						{
							sampleStatus = "<font color=\"#e62100\">WRONG</font>";
							success = false;
						}
						
						if (executionOutputJudge.getStdErr().trim().equals("The process took too long to run, and was terminated."))
						{
							judgeStatus = "<font color=\"#e62100\">TIMEOUT</font>";
							success = false;
						}
						else if (!executionOutputJudge.getStdErr().trim().equals(""))
						{
							judgeStatus = "<font color=\"#e62100\">ERROR</font>";
							success = false;
						}
						else if (executionOutputJudge.getStdOut().trim().matches(expectedJudgeOutput))
						{
							judgeStatus = "<font color=\"#66e621\">CORRECT</font>";
						}
						else
						{
							judgeStatus = "<font color=\"#e62100\">WRONG</font>";
							success = false;
						}
						
						boolean compileSuccessful = executableFile.exists();
						
						String message = "";
						message += "<strong>File Upload Details</strong>";
						message += "<br>File Name: " + fileName;
						message += "<br>File Size: " + buffer.length + " B";
						message += "<br>File MD5 Checksum: " + md5sum;
						message += "<br>Language: " + DataStore.typeNames[languageType];
						message += "<br>Date Uploaded: " + uploadDate.toString() + "<br><br>";
						message += "<strong>Your Results</strong>";
						message += "<br>Results from sample data test: <strong>" + sampleStatus + "</strong> (" + executionOutputSample.getMillis() + "ms)";
						message += "<br>Results from judge data test: <strong>" + judgeStatus + "</strong> (" + executionOutputJudge.getMillis() + "ms)";
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
							if (sampleStatus.equals("<font color=\"#e62100\">WRONG</font>") || sampleStatus.equals("<font color=\"#e62100\">ERROR</font>"))
							{
								message += "<br><br><strong>Execution Output w/ Sample Data</strong>";
								message += "<br>The data inputted was:<br><pre><code>" + ProblemLoaderUtils.escapeHTML(executionOutputSample.getStdIn()) + "</code></pre>";
								message += "<br>The expected standard output was:<br><pre><code>" + ProblemLoaderUtils.escapeHTML(expectedSampleOutput) + "</code></pre>";
								message += "<br>Your program wrote this to standard output instead:<br><pre><code>" + ProblemLoaderUtils.escapeHTML(executionOutputSample.getStdOut()) + "</code></pre>";
								if (!executionOutputSample.getStdErr().equals(""))
								{
									message += "<br>Your program threw the following errors:<br><pre><code>" + ProblemLoaderUtils.escapeHTML(executionOutputSample.getStdErr()) + "</code></pre><br>";
								}
							}
							
							if (judgeStatus.equals("<font color=\"#e62100\">WRONG</font>") || judgeStatus.equals("<font color=\"#e62100\">ERROR</font>"))
							{
								message += "<br><br><strong>Execution Output w/ Judge Data</strong>";
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
								message += "<br><br><strong>Compiler Output</strong>";
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
						message += "<br><br>";
						request.setAttribute("message", message);
					}
					else
					{
						request.setAttribute("message", "The selected language type is doesn't match the file-type uploaded.");
					}
				}
			} 
			catch (Exception ex) 
			{
				request.setAttribute("message", "There was an error: " + ex.getMessage());
				ex.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/assets/message.jsp").forward(request, response);

		}

		return;
	}
	
	public static byte[] createChecksum(File file) throws Exception 
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

	public static String getMD5Checksum(File file) throws Exception
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