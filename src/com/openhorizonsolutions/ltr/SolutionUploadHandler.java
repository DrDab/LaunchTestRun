package com.openhorizonsolutions.ltr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

			String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists())
			{
				uploadDir.mkdir();
			}

			try
			{
				Part filePart = request.getPart("sourcefile");
				Part problemIDPart = request.getPart("cpid");
				Part languagePart = request.getPart("languagePart");
				InputStream filecontent = filePart.getInputStream();
				InputStream pidcontent = problemIDPart.getInputStream();
				InputStream langcontent = languagePart.getInputStream();
				String fileName = new File(filePart.getSubmittedFileName()).getName();
				String filePath = uploadPath + File.separator + fileName;
				byte[] buffer = new byte[filecontent.available()];
				byte[] pidpartbuffer = new byte[pidcontent.available()];
				byte[] languagebuffer = new byte[langcontent.available()];
				filecontent.read(buffer, 0, filecontent.available());
				pidcontent.read(pidpartbuffer, 0, pidcontent.available());
				langcontent.read(languagebuffer, 0, langcontent.available());
				String problemID = new String(pidpartbuffer);
				String languageStr = new String(languagebuffer);
				File storeFile = new File(filePath);
				
				
				System.out.println("Path: " + storeFile.getAbsolutePath());
				System.out.println("Problem ID: " + problemID);
				System.out.println("Filename: " + fileName);
				System.out.println("File size: " + buffer.length);
				
				// the file size is over 1MB
				if (buffer.length >= 10000)
				{
					request.setAttribute("message", "File size too big. Please upload a smaller file.");
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
					if (languageType > 4 || languageType < 0)
					{
						ok = false;
					}
					
					if (ok)
					{
						if (!(DataStore.types[languageType].equals(filePart.getContentType())))
						{
							ok = false;
						}
					}
					
					if (ok)
					{
						storeFile.createNewFile();
						OutputStream outStream = new FileOutputStream(storeFile);
						outStream.write(buffer);
						outStream.close();
						request.setAttribute("message", "Upload has been done successfully!<br>File Name: " + fileName + "<br>Size: " + buffer.length + "<br>File type: " + filePart.getContentType());
					}
					else
					{
						request.setAttribute("message", "The selected language type is doesn't match the content type uploaded.");
					}
				}
			} 
			catch (Exception ex) 
			{
				request.setAttribute("message", "There was an error: " + ex.getMessage());
			}
			getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);

		}

		return;
	}
	// doGet(request, response);

}
