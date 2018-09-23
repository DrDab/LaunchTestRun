package com.openhorizonsolutions.ltr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/IndexPageServlet")
public class IndexPageServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public IndexPageServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		double start = DataStore.stw.getElapsedNanoTime();
		String contextPath = request.getContextPath();
		// String requestURI = request.getRequestURI();
		// String[] sep = requestURI.split("/");
		// String cpid = sep[sep.length - 1];
		
		String s = "";
		s += "\n" + 
				"\n" + 
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \n" + 
				"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" + 
				"\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
				"\n" + 
				"<head>\n" + 
				"\n" + 
				"\n" + 
				" <title>LaunchTestRun</title>\n" + 
				" <META HTTP-EQUIV=\"EXPIRES\" CONTENT=\"0\">\n" + 
				" <META HTTP-EQUIV=\"CACHE-CONTROL\" CONTENT=\"NO-CACHE\">\n" + 
				" <META HTTP-EQUIV=\"PRAGMA\" CONTENT=\"NO-CACHE\">\n" + 
				"\n" + 
				"<script type=\"text/x-mathjax-config\">\n" + 
				"MathJax.Hub.Config({\n" + 
				"    \"HTML-CSS\": {\n" + 
				"	scale: 85,\n" + 
				"	preferredFont: \"STIX\"\n" + 
				"    },\n" + 
				"  tex2jax: {\n" + 
				"    inlineMath: [['$','$'],['\\\\(','\\\\)']],\n" + 
				"    processEscapes: true,\n" + 
				"    skipTags: ['pre'],\n" + 
				"    processClass: \"mathjax\",\n" + 
				"    ignoreClass: \"no-mathjax\"\n" + 
				"  }\n" + 
				"});\n" + 
				"</script>\n" + 
				"<script type=\"text/javascript\" src=\"http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>\n" + 
				"<link rel=\"icon\" href=\"" + contextPath + "/assets/favicon.ico\" type=\"image/x-icon\">\n" + 
				"<link rel=\"shortcut icon\" href=\"" + contextPath + "/assets/favicon.ico\" type=\"image/x-icon\">\n" + 
				"<link rel=\"stylesheet\" href=\"" + contextPath + "/assets/fonts.css\">\n" +
				"<link rel=\"stylesheet\" href=\"" + contextPath + "/assets/page-styles.css\">\n" +
				"\n" + 
				"</head>\n" + 
				"" +
				"\n" + 
				"<body class=\"no-mathjax\">\n" + 
				" <div align=\"center\">\n" + 
				"  <div class=\"shadow1\">\n" + 
				"\n" + 
				"   <div class=\"content\">\n" + 
				"\n" + 
				"  \n" + 
				"\n" + 
				"   \n" + 
				"    \n" + 
				"\n" + 
				"\n" + 
				"<div class=\"panel\">\n" + 
				"\n" + 
				"<center><h2>Welcome to the LaunchTestRun Dynamic Grading System!</h2></center>\n" + 
				" \n" + 
				"</div><br><br><br>\n" + 
				"\n" + 
				"<br style=\"clear:both\" />\n" + 
				"\n" + 
				"\n" + 
				"<div id=\"trial-information\"></div>\n" + 
				"<p style=\"text-align:left;\" class=\"mono prewrap output-data\"></p>\n" +
				"\n" + 
				"<br style=\"clear:both\" />\n" + 
				"\n" + 
				"<div align=\"right\" style=\"position:relative; float:right; right:40px; top:-93px; width:350px; z-index:2; padding:0px;\">\n" +  
				"\n" + 
				"</div>\n" + 
				"\n" + 
				"<div align=\"left\" style=\"position:relative; float:left; left:30px; top:-100px; width:840px; z-index:1;\"> \n" + 
				"<div align=\"left\" class=\"rcorners2\" style='width:800px; padding-top:10px;'>\n" + 
				"<span id=\"probtext-text\">" +
				"<strong id=\"greeting\">Hello!</strong><br><br>You have reached the homepage for the LaunchTestRun server, a automated platform for testing competitive programming solutions. " +
				"LaunchTestRun lets you view programming problems, and test your solutions to these problems at any time you want.<br><br>" +
				"To view a list of problems, please click <a href=\"" + contextPath + "/listproblems.html\">here</a>." +
				"" +
				"</span>\n" + 
				"</div>\n" + 
				"\n" + 
				"</div>\n" + 
				"<script>\n" + 
				"	var greeting = document.getElementById(\"greeting\");\n" + 
				"	var today = new Date()\n" + 
				"	var curHr = today.getHours()\n" + 
				"\n" + 
				"	if (curHr < 12) {\n" + 
				"		greeting.innerHTML = \"Good morning!\";\n" +
				"	} else if (curHr < 18) {\n" + 
				"		greeting.innerHTML = \"Good afternoon!\";\n" +
				"	} else {\n" + 
				"		greeting.innerHTML = \"Good evening!\";\n" +
				"	}" + 
				"</script>" +
				"\n" + 
				"\n" + 
				"<br style=\"clear:both\" />\n" + 
				"\n" + 
				"\n" + 
				"   </div>\n" + 
				"  </div>\n" + 
				" </div>\n" + 
				"\n" + 
				" <br style=\"clear:both\" />\n" + 
				"\n";
		
		String end = "<center>Page requested: " + request.getRequestURI() + "<br>Page generated in: " + (double)((DataStore.stw.getElapsedNanoTime() - start)/ 1000000000.0) + " seconds [ 100% Tomcat 8 ]<br>LaunchTestRun is (C) copyright of Victor Du.</center>";
		response.getWriter().append(s).append(end + "</body>\n" + 
				"\n" + 
				"</html>\n" + 
				"\n" + 
				"\n");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
