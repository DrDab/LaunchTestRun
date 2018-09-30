package com.openhorizonsolutions.ltr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/ProblemServlet")
public class ProblemServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProblemServlet() 
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
		double start = DataStore.stw.getElapsedNanoTime();
		String contextPath = request.getContextPath();
		String requestURI = request.getRequestURI();
		String[] sep = requestURI.split("/");
		String cpid = sep[sep.length - 1];
		Problem p = null;

		String s = "";
		String header = "\n" + 
				"\n" + "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \n" + 
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
				" <META HTTP-EQUIV=\"PRAGMA\" CONTENT=\"NO-CACHE\">\n" + "\n" + 
				"<script type=\"text/x-mathjax-config\">\n" + "MathJax.Hub.Config({\n" + 
				"    \"HTML-CSS\": {\n" + 
				"	scale: 85,\n" + "	preferredFont: \"STIX\"\n" + "    },\n" + "  tex2jax: {\n" + 
				"    inlineMath: [['$','$'],['\\\\(','\\\\)']],\n" + "    processEscapes: true,\n" + 
				"    skipTags: ['pre'],\n" + "    processClass: \"mathjax\",\n" + 
				"    ignoreClass: \"no-mathjax\"\n" + 
				"  }\n" + "});\n" + "</script>\n" + 
				"<script type=\"text/javascript\" src=\"http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>\n" + 
				"<link rel=\"icon\" href=\"" + contextPath + "/assets/favicon.ico\" type=\"image/x-icon\">\n" + 
				"<link rel=\"shortcut icon\" href=\"" + contextPath + "/assets/favicon.ico\" type=\"image/x-icon\">\n" + 
				"<link rel=\"stylesheet\" href=\"" + contextPath + "/assets/fonts.css\">\n" + 
				"<link rel=\"stylesheet\" href=\"" + contextPath + "/assets/page-styles.css\">\n" + 
				"\n" + 
				"</head>\n" +
				"";
		s += header;

		ProblemListHandler tmp = ProblemLoaderUtils.refreshIO(getServletContext().getRealPath(""));
		if (cpid.equals("listproblems.html"))
		{
			String generatedLinkPart = "";
			HashMap<String, ArrayList<Problem>> categoryMap = new HashMap<String, ArrayList<Problem>>();
			ArrayList<Problem> linkGenList = tmp.getProblemList();
			for (Problem problem : linkGenList)
			{
				String setdetails = problem.getSetInfo();
				if (categoryMap.containsKey(setdetails)) 
				{
					categoryMap.get(setdetails).add(problem);
				}
				else 
				{
					categoryMap.put(setdetails, new ArrayList<Problem>());
					categoryMap.get(setdetails).add(problem);
				}
			}
			
			Object[] toSort = getSortedCategoryArray(categoryMap.keySet().toArray());

			for (Object setdetails : toSort) 
			{
				generatedLinkPart += "<div align=\"left\" class=\"card\" style='width:800px; padding-top:10px;'>\n";
				generatedLinkPart += "<div class=\"container\">\n";
				generatedLinkPart += "<p>\n";
				generatedLinkPart += "<subsection style=\"font-size: 1.9em;\">" + setdetails + "</subsection><br><br>";
				for (Problem problem : categoryMap.get(setdetails))
				{
					String pCPID = problem.getCPID();
					String title = problem.getTitle();
					generatedLinkPart += "<plain><a href=\"" + contextPath + "/problem.html/" + pCPID + "\">" + title + "</a></plain><br>\n";
				}
				generatedLinkPart += "<br><br>";
				generatedLinkPart += "</p></div></div><br><br>";
			}

			s += "\n" + 
				"<body class=\"no-mathjax\">\n" + 
				"<div id=\"lulz\">\n" +
				"	<ul>\n" + 
				"  		<li><plain><a href=\"" + contextPath + "/index.html\"><img src=\"" + contextPath + "/assets/images/baseline-home-24px.svg\" height=\"24\" width=\"24\"></a></plain></li>\n" + 
				"  		<li><plain><a href=\"" + contextPath + "/listproblems.html\"><img src=\"" + contextPath + "/assets/images/baseline-list-24px.svg\" height=\"24\" width=\"24\"></a></plain></li>\n" + 
				"	</ul>\n" +
				"</div>\n" +
				"<br>\n" +
				" <div align=\"center\">\n" + 
				"  <div class=\"shadow1\">\n" + 
				"\n" +
				"   <div class=\"content\">\n" +
				"\n" +
				"<div class=\"panel\">\n" + 
				"\n" + 
				"<h2> Problems </h2>\n" + 
				" \n" + 
				"</div><br><br>\n" + 
				"\n" + 
				"<br style=\"clear:both\" />\n" + 
				"\n" + 
				"\n" + 
				"<div id=\"trial-information\"></div>\n" + 
				"<p style=\"text-align:left;\" class=\"mono prewrap output-data\"></p>\n" + 
				"\n" + 
				"<br style=\"clear:both\" />\n" +
				"\n" + 
				""	+
				"<div align=\"right\" style=\"position:relative; float:right; right:40px; top:-93px; width:350px; z-index:2; padding:0px;\">\n" + 
				"\n" + 
				"</div>\n" + 
				"\n" + 
				"<div align=\"center\" style=\"position:relative; float:center; left:30px; top:-100px; width:840px; z-index:1;\"> \n" + 
				"<br><p>" + 
				generatedLinkPart + 
				"</p><br>" + 
				"\n" + 
				"</div>\n" + 
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
		} 
		else if (tmp.problemExists(cpid))
		{
			p = tmp.getProblem(cpid);
			String choices = "";
			for (int i = 0; i < DataStore.typeNames.length; i++)
			{
				int t = (i + 1);
				choices += "					<option value='" + t + "'>" + DataStore.typeNames[i] + "</option>\n";
			}
			String accumDescription = "";
			String pdfLink = p.getPDFLink();
			if (!(pdfLink.equals(""))) 
			{
				accumDescription += "<embed src=\"" + pdfLink
						+ "\" type=\"application/pdf\" width=\"100%\" height=\"600px\" />\n<br>\n";
			}
			accumDescription += p.getDescription();
			s += 	"\n" +
					"<body class=\"no-mathjax\">\n" +
					"<div id=\"lulz\">\n" +
					"	<ul>\n" + 
					"  		<li><plain><a href=\"" + contextPath + "/index.html\"><img src=\"" + contextPath + "/assets/images/baseline-home-24px.svg\" height=\"24\" width=\"24\"></a></plain></li>\n" + 
					"  		<li><plain><a href=\"" + contextPath + "/listproblems.html\"><img src=\"" + contextPath + "/assets/images/baseline-list-24px.svg\" height=\"24\" width=\"24\"></a></plain></li>\n" + 
					"	</ul>\n" +
					"</div>\n" +
					"<br>\n" +
					" <div align=\"center\">\n" + 
					"  <div class=\"shadow1\">\n" + "\n" +
					"   <div class=\"content\">\n" + "\n" + 
					"  \n" +
					"\n" +
					"   \n" +
					"    \n" +
					"\n" +
					"\n" + 
					"<div class=\"panel\">\n" +
					"\n" + 
					"<h2> " +
					p.getTitle() + 
					"</h2>\n" + 
					"<h2> " +
					p.getSetInfo() +
					" </h2>\n" + 
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
					"<div align=\"center\" style=\"position:relative; float:center; left:30px; top:-100px; width:840px; z-index:1;\"> \n" +
					"<div align=\"left\" class=\"card\" style='width:800px; padding-top:10px;'>\n" +
					"<div class=\"container\">\n" + "<subsection>Problem Description</subsection><br><br>\n" +
					"<span id=\"probtext-text\" class=\"mathjax\">" + accumDescription + "</span>\n" + 
					"</div>\n" +
					"</div>\n" + 
					"\n" +
					"    \n" + 
					"	<br>" +
					"	 \n" +
					"	<div align=\"left\" class=\"card\" style='width:800px; padding-top:10px;'>\n" +
					"		<div class=\"container\">\n" +
					"		<form class=\"submission\" method=\"POST\" action=\"" +
					contextPath +
					"/submit-solution\" enctype=\"multipart/form-data\">\n" +
					"		<div id=\"solution\">\n" +
					"			<input type=\"hidden\" name=\"cpid\" value=\"" + cpid + "\"/>\n" +
					"		\n" +
					"			<subsection>Test Your Solution Here!</subsection><br>\n" +
					"			<div class=\"field2\">\n" +
					"				<label for=\"language\"><plainbold>Language:</plainbold></label>\n" +
					"				<select name=\"language\">\n" + choices + "				</select></div>\n" +
					"			\n" +
					"			<div class=\"field2\">\n" +
					"				<label for=\"sourcefile\"><plainbold>Source File:</plainbold></label>\n" +
					"				<input name=\"sourcefile\" size=\"50\" type=\"file\" class=\"required\" /></div>\n" +
					"		\n" + 
					"			<div class=\"field2\">\n" +
					"				    <input name=\"solution-submit\" id=\"solution-submit\" type=\"submit\" value=\"Submit Solution\" /></div>\n" +
					"			</div>\n" +
					"			<br>" +
					"		</form>\n" + 
					"	</div>" + 
					"\n" +
					"	</div>" +
					"    \n" +
					"</div>\n" +
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
		} 
		else 
		{
			String generatedLinkPart = "";
			HashMap<String, ArrayList<Problem>> categoryMap = new HashMap<String, ArrayList<Problem>>();
			ArrayList<Problem> linkGenList = tmp.getProblemList();
			for (Problem problem : linkGenList)
			{
				String setdetails = problem.getSetInfo();
				if (categoryMap.containsKey(setdetails))
				{
					categoryMap.get(setdetails).add(problem);
				}
				else 
				{
					categoryMap.put(setdetails, new ArrayList<Problem>());
					categoryMap.get(setdetails).add(problem);
				}
			}

			Object[] toSort = getSortedCategoryArray(categoryMap.keySet().toArray());

			for (Object setdetails : toSort) 
			{
				generatedLinkPart += "<div align=\"left\" class=\"card\" style='width:800px; padding-top:10px;'>\n";
				generatedLinkPart += "<div class=\"container\">\n";
				generatedLinkPart += "<p>\n";
				generatedLinkPart += "<subsection style=\"font-size: 1.9em;\">" + setdetails + "</subsection><br><br>";
				for (Problem problem : categoryMap.get(setdetails)) 
				{
					String pCPID = problem.getCPID();
					String title = problem.getTitle();
					generatedLinkPart += "<plain><a href=\"" + contextPath + "/problem.html/" + pCPID + "\">" + title
							+ "</a></plain><br>\n";
				}
				generatedLinkPart += "<br><br>";
				generatedLinkPart += "</p></div></div><br><br>";
			}

			s += "\n" +
					"<body class=\"no-mathjax\">\n" + 
					"<div id=\"lulz\">\n" +
					"	<ul>\n" + 
					"  		<li><plain><a href=\"" + contextPath + "/index.html\"><img src=\"" + contextPath + "/assets/images/baseline-home-24px.svg\" height=\"24\" width=\"24\"></a></plain></li>\n" + 
					"  		<li><plain><a href=\"" + contextPath + "/listproblems.html\"><img src=\"" + contextPath + "/assets/images/baseline-list-24px.svg\" height=\"24\" width=\"24\"></a></plain></li>\n" + 
					"	</ul>\n" +
					"</div>\n" +
					"<br>\n" +
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
					"<h2> Error </h2>\n" + 
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
					"" +
					"<div align=\"right\" style=\"position:relative; float:right; right:40px; top:-93px; width:350px; z-index:2; padding:0px;\">\n" +
					"\n" +
					"</div>\n" +
					"\n" +
					"<div align=\"center\" style=\"position:relative; float:center; left:30px; top:-100px; width:840px; z-index:1;\"> \n" +
					"<br>" +
					"<div class=\"card\" style=\"Background-color: #ea4335; width:800px; padding-top:10px; padding-bottom:10px;\">" +
					"<div class=\"container\">" +
					"<center><plain style=\"color:#ffffff; font-size=0.8em;\">The problem that you requested does not exist: " + cpid + "</plain></center>" +
					"</div>" +
					"</div>" +
					"<br><p>" +
					generatedLinkPart +
					"</p><br>" +
					"\n" +
					"<br style=\"clear:both\" />\n" + 
					"\n" + "\n" +
					"   </div>\n" + 
					"  </div>\n" + 
					" </div>\n" +
					"\n" +
					" <br style=\"clear:both\" />\n" + "\n";
		}

		String end = "<center>Page requested: " + request.getRequestURI() + "<br>Page generated in: "
				+ (double) ((DataStore.stw.getElapsedNanoTime() - start) / 1000000000.0)
				+ " seconds [ 100% Tomcat 8 ]<br>LaunchTestRun is (C) copyright of Victor Du.</center>";
		response.getWriter().append(s).append(end + "</body>\n" + "\n" + "</html>\n" + "\n" + "\n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private Object[] getSortedCategoryArray(Object[] tmp)
	{
		Object[] toSort = tmp;
		int ec = -1;
		int mc = -1;
		int fc = -1;
		for(int i = 0; i < toSort.length; i++)
		{
			String dif = toSort[i].toString();
			if (dif.equals("Easy") && ec == -1)
			{
				ec = i;
			}
			else if (dif.equals("Medium") && mc == -1)
			{
				mc = i;
			}
			else if (dif.equals("Hard") && fc == -1)
			{
				fc = i;
			}
		}
		
		if (ec != -1 && mc != -1 && fc != -1)
		{
			toSort[0] = "Easy";
			toSort[1] = "Medium";
			toSort[2] = "Hard";
		}
		else
		{
			Arrays.sort(toSort);
		}
		return toSort;
	}

}
