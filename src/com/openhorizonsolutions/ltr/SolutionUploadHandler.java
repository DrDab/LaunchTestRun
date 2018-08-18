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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Set factory constraints
			factory.setSizeThreshold(1024 * 512);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Set overall request size constraint
			upload.setSizeMax(1024 * 512);

			// Parse the request
				 String uploadPath = getServletContext().getRealPath("")
			                + File.separator + UPLOAD_DIRECTORY;
			         
			        // creates the directory if it does not exist
			        File uploadDir = new File(uploadPath);
			        if (!uploadDir.exists()) {
			            uploadDir.mkdir();
			        }
			 
			        try
			        {
			            Part filePart = request.getPart("sourcefile");
			            Part problemIDPart = request.getPart("cpid");
			            InputStream filecontent = filePart.getInputStream();
			            InputStream pidcontent = problemIDPart.getInputStream();
			            String fileName = new File(filePart.getSubmittedFileName()).getName();
	                    String filePath = uploadPath + File.separator + fileName;
	                    File storeFile = new File(filePath);
	                    storeFile.createNewFile();
	                    System.out.println(storeFile.getAbsolutePath());
	                    byte[] buffer = new byte[filecontent.available()];
	                    byte[] pidpartbuffer = new byte[pidcontent.available()];
	                    filecontent.read(buffer, 0, filecontent.available());
	                    pidcontent.read(pidpartbuffer, 0, pidcontent.available());
	                    String problemID = new String(pidpartbuffer);
	                    System.out.println(problemID);
	                    OutputStream outStream = new FileOutputStream(storeFile);
	                    outStream.write(buffer);
	                    outStream.close();
	                     request.setAttribute("message",
	                            "Upload has been done successfully!<br>File Name: " + fileName + "<br>Size: " + buffer.length + "<br>File type: " + filePart.getContentType());
			                    
			        } 
			        catch (Exception ex) 
			        {
			            request.setAttribute("message",
			                    "There was an error: " + ex.getMessage());
			        }
			        getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
			} 
			writer.println("Data successfully received! Yiff yiff!");
			writer.flush();
			return;
		}
		// doGet(request, response);

}
