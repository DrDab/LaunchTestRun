package com.openhorizonsolutions.ltr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() 
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
		String requestURI = request.getRequestURI();
		String cpid = requestURI.substring(new String("/LaunchTestRun/test.html").length());
		System.out.println("CPID:" + cpid);
		Problem p = null;
		if (ProblemLoaderUtils.problemExists(cpid))
		{
			p = ProblemLoaderUtils.getProblem(cpid);
		}
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
				" <style>body,td,th {\n" + 
				"  font-family: Arial, Helvetica, sans-serif;\n" + 
				"  font-size: 14px;\n" + 
				"  color: #000;\n" + 
				"}\n" + 
				"a:link {color: #000; text-decoration: underline; }\n" + 
				"a:active {color: #009; text-decoration: underline; }\n" + 
				"a:visited {color: #009; text-decoration: underline; }\n" + 
				"a:hover {color: #33F; text-decoration: none; }\n" + 
				"body {\n" + 
				"  margin-top: 0px;\n" + 
				"  margin-bottom: 0px;\n" + 
				"  background-color: #AAA;\n" + 
				"}\n" + 
				"div.main {\n" + 
				"  background-color: #FFFFFF;\n" + 
				"  width: 914px;\n" + 
				"  text-align: left;\n" + 
				"}	\n" + 
				".navbar {\n" + 
				"  position: relative;\n" + 
				"  top: -60px;\n" + 
				"}\n" + 
				".form_error {\n" + 
				"  font-weight: bold;\n" + 
				"  color:#800;\n" + 
				"  padding-top:6px;\n" + 
				"}\n" + 
				".navbar ul {\n" + 
				"  list-style: none;\n" + 
				"  margin: 20px;\n" + 
				"  padding: 0;  \n" + 
				"}\n" + 
				".navbar li a {\n" + 
				"  display: inline;\n" + 
				"  font-variant: small-caps;  \n" + 
				"  font-size: 16px;\n" + 
				"  background-color: #139;\n" + 
				"  color: #EEE;\n" + 
				"  text-decoration: none;\n" + 
				"  margin: 5px 0; \n" + 
				"  width: 105px;\n" + 
				"  float: left;\n" + 
				"  text-align:center;\n" + 
				"  border-left:1px solid #fff;\n" + 
				"  padding-top: 3px;\n" + 
				"  padding-bottom: 3px;\n" + 
				"}\n" + 
				".navbar li a:hover {\n" + 
				"  background-color: #47F;\n" + 
				"  color:#FFF\n" + 
				"}\n" + 
				"\n" + 
				".shadow1 {\n" + 
				"	margin: 0px;\n" + 
				"	width: 900px;\n" + 
				"        background-color: rgb(68,68,68); /* Needed for IEs */\n" + 
				"        -moz-box-shadow: 5px 5px 5px rgba(68,68,68,0.6);\n" + 
				"        -webkit-box-shadow: 5px 5px 5px rgba(68,68,68,0.6);\n" + 
				"        box-shadow: 5px 5px 5px rgba(68,68,68,0.6);\n" + 
				"        filter: progid:DXImageTransform.Microsoft.Blur(PixelRadius=3,MakeShadow=true,ShadowOpacity=0.30);\n" + 
				"        -ms-filter: \"progid:DXImageTransform.Microsoft.Blur(PixelRadius=3,MakeShadow=true,ShadowOpacity=0.30)\";\n" + 
				"        zoom: 1;\n" + 
				"}\n" + 
				".shadow1 .content {\n" + 
				"        position: relative; /* This protects the inner element from being blurred */\n" + 
				"	width: 900px;\n" + 
				"        background-color: #FFF;\n" + 
				"\n" + 
				"}\n" + 
				".panel {\n" + 
				"  background-color: #FFF; \n" + 
				"  padding: 5px 15px 10px 15px;\n" + 
				" text-align: left;\n" + 
				"}\n" + 
				".panel a:link {color: #000; text-decoration: underline; }\n" + 
				".panel a:active {color: #009; text-decoration: underline; }\n" + 
				".panel a:visited {color: #009; text-decoration: underline; }\n" + 
				".panel a:hover {color: #33F; text-decoration: none; }\n" + 
				".panel h2 {\n" + 
				"  font-variant: small-caps;  \n" + 
				"  line-height: 25%;\n" + 
				"}\n" + 
				".sponsors span {\n" + 
				"  display:inline-block;\n" + 
				"}\n" + 
				".sponsors a {\n" + 
				"  vertical-align:text-bottom;\n" + 
				"  padding: 1px;\n" + 
				"  text-decoration: none;\n" + 
				"  text-align:center;\n" + 
				"  float: left;\n" + 
				"  border:1px solid #FFF;\n" + 
				"}\n" + 
				".sponsors a img {\n" + 
				"  border: 0;\n" + 
				"}\n" + 
				".sponsors a:hover {\n" + 
				"  border:1px solid #CCC;\n" + 
				"}\n" + 
				".panel li {\n" + 
				"  padding-top: 6px;\n" + 
				"}\n" + 
				".field label{\n" + 
				"  display: inline-block;\n" + 
				"  width: 80px;\n" + 
				"  font-weight: bold;\n" + 
				"}\n" + 
				".field2 {\n" + 
				"  padding-top: 5px;\n" + 
				"}\n" + 
				".field2 label{\n" + 
				"  display: inline-block;\n" + 
				"  width: 150px;\n" + 
				"  font-weight: bold;\n" + 
				"}\n" + 
				".forgotpass label {\n" + 
				"  display: inline-block;\n" + 
				"  width: 80px;\n" + 
				"}\n" + 
				".forgotpass {\n" + 
				"  padding-top: 0px;\n" + 
				"  font-size: 10px;\n" + 
				"}\n" + 
				".field {\n" + 
				"  padding-top: 5px;\n" + 
				"}\n" + 
				".historypanel {\n" + 
				" text-decoration: none; \n" + 
				" color: #000;\n" + 
				"}\n" + 
				".historypanel img {\n" + 
				" border: none;\n" + 
				"}\n" + 
				".historypanel:hover {\n" + 
				"  background-color: #EEE;\n" + 
				"}\n" + 
				".problem-text\n" + 
				"{\n" + 
				"        background: white;\n" + 
				"        border: 1px solid black;\n" + 
				"        padding-right: 20px;\n" + 
				"        padding-left: 20px;\n" + 
				"        padding-top: 5px;\n" + 
				"        padding-bottom: 10px;\n" + 
				" 	margin-bottom: 15px;\n" + 
				"	font-family: \"Raleway\", \"Helvetica Neue\", Helvetica, Arial, sans-serif;\n" + 
				"}\n" + 
				"pre, code, .mono, .mono * { font-family: monospace !important; }\n" + 
				".prewrap { white-space: pre-wrap; }\n" + 
				"\n" + 
				".status-working\n" + 
				"{\n" + 
				"	font-weight: bold;\n" + 
				"	color: black;\n" + 
				"}\n" + 
				"\n" + 
				".status-yes\n" + 
				"{\n" + 
				"	font-weight: bold;\n" + 
				"	color: green;\n" + 
				"}\n" + 
				"\n" + 
				".status-no\n" + 
				"{\n" + 
				"	font-weight: bold;\n" + 
				"	color: #900;\n" + 
				"}\n" + 
				"\n" + 
				".trial-result {\n" + 
				"    border: 1px solid pink;\n" + 
				"    display: inline-block;\n" + 
				"    height: 50px;\n" + 
				"    margin: 3px;\n" + 
				"    position: relative;\n" + 
				"    width: 60px;\n" + 
				"    vertical-align: top;\n" + 
				"}\n" + 
				".trial-status-no {\n" + 
				"    background-color: #FFE2E2;\n" + 
				"    border: 1px solid #993333;\n" + 
				"    color: #993333;\n" + 
				"}\n" + 
				".trial-status-partial {\n" + 
				"    background-color: #EEFFEE;\n" + 
				"    border: 1px solid #339933;\n" + 
				"    color: #339933;\n" + 
				"}\n" + 
				".trial-status-unknown {\n" + 
				"    background-color: #EEEEEE;\n" + 
				"    border: 1px solid black;\n" + 
				"    color: black;\n" + 
				"}\n" + 
				".trial-num {\n" + 
				"    bottom: 2px;\n" + 
				"    display: inline;\n" + 
				"    float: left;\n" + 
				"    font-size: small;\n" + 
				"    left: 3px;\n" + 
				"    position: absolute;\n" + 
				"    text-align: right;\n" + 
				"}\n" + 
				".trial-result .info span {\n" + 
				"    display: block;\n" + 
				"    text-align: right;\n" + 
				"}\n" + 
				".trial-result .info {\n" + 
				"    bottom: 2px;\n" + 
				"    font-size: x-small;\n" + 
				"    position: absolute;\n" + 
				"    right: 3px;\n" + 
				"}\n" + 
				".res-symbol {\n" + 
				"    font-size: xx-large;\n" + 
				"    text-align: center;\n" + 
				"}\n" + 
				".trial-status-partial .res-symbol {\n" + 
				"    font-size: x-large !important;\n" + 
				"}\n" + 
				".trial-status-yes {\n" + 
				"    background-color: #EEFFEE;\n" + 
				"    border: 1px solid #339933;\n" + 
				"    color: #339933;\n" + 
				"}\n" + 
				".tooltip {\n" + 
				"	display:none;\n" + 
				"	position:absolute;\n" + 
				"	border:1px solid #333;\n" + 
				"	background-color:#161616;\n" + 
				"	border-radius:5px;\n" + 
				"	padding:10px;\n" + 
				"	color:#fff;\n" + 
				"	font-size:12px Arial;\n" + 
				"}\n" + 
				".bar rect {\n" + 
				"  fill: #47f;\n" + 
				"  shape-rendering: crispEdges;\n" + 
				"}\n" + 
				".bar text {\n" + 
				"  fill: #fff;\n" + 
				"}\n" + 
				".axis path, .axis line {\n" + 
				"  fill: none;\n" + 
				"  stroke: #000;\n" + 
				"  shape-rendering: crispEdges;\n" + 
				"}</style>\n" + 
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
				"\n" + 
				"</head>\n" + 
				"";
		try
		{
			s += 	"\n" + 
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
					"<h2> " + p.getTitle() + "</h2>\n" + 
					"<h2> " + p.getSetInfo() + " </h2>\n" + 
					" \n" + 
					"</div><br><br><br>\n" + 
					"\n" + 
					"<br style=\"clear:both\" />\n" + 
					"\n" + 
					"\n" +
					"<p style=\"text-align:left;\" class=\"mono prewrap output-data\"></p>\n" + 
					"</div>\n" + 
					"\n" + 
					"<br style=\"clear:both\" />\n" + 
					"\n" + 
					"<div align=\"right\" style=\"position:relative; float:right; right:40px; top:-93px; width:350px; z-index:2; padding:0px;\">\n" +  
					"\n" + 
					"</div>\n" + 
					"\n" + 
					"<div align=\"left\" style=\"position:relative; float:left; left:30px; top:-100px; width:840px; z-index:1;\"> \n" + 
					"<div align=\"left\" class=\"problem-text\" style='width:800px; padding-top:10px;'>\n" + 
					"<span id=\"probtext-text\" class=\"mathjax\">" +
					p.getDescription() +
					"</span>\n" + 
					"</div>\n" + 
					"\n" + 
					"    \n" + 
					"		<form class=\"submission\" method=\"POST\" action=\"/LaunchTestRun/submit-solution\" enctype=\"multipart/form-data\">\n" + 
					"		<div id=\"solution\">\n" + 
					"			<input type=\"hidden\" name=\"cpid\" value=\"811\"/>\n" + 
					"		\n<br><br><br>" + 
					"			<div class=\"field2\">\n" + 
					"				<label for=\"language\">Language:</label>\n" + 
					"				<select name=\"language\">\n" + 
					"					<option value='1'>C (GCC 8.1)</option>\n" + 
					"					<option value='2'>C++ (GCC 8.1)</option>\n" + 
					"					<option value='3'>Java (Oracle JVM 8)</option>\n" + 
					"					<option value='4'>Python (2.7.6)</option>\n" + 
					"					<option value='5'>Python (3.4.0)</option>\n" + 
					"				</select></div>\n" + 
					"			\n" + 
					"			<div class=\"field2\">\n" + 
					"				<label for=\"sourcefile\">Source File:</label>\n" + 
					"				<input name=\"sourcefile\" size=\"50\" type=\"file\" class=\"required\" /></div>\n" + 
					"		\n" + 
					"			<div class=\"field2\">\n" + 
					"				    <input name=\"solution-submit\" id=\"solution-submit\" type=\"submit\" value=\"Submit Solution\" /></div>\n" + 
					"			</div>\n" + 
					"		</form>\n" + 
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
		catch (Exception e)
		{
			e.printStackTrace();
			s += 
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
					"<h2> Error </h2>\n" + 
					" \n" + 
					"</div><br><br><br>\n" + 
					"\n" + 
					"<br style=\"clear:both\" />\n" + 
					"\n" + 
					"\n" + 
					"<div id=\"last-status\" style=\"padding:10px; position:relative; float:left; left:30px; top:-45px; width:820px; border:1px solid black; background-color:#FFF; \" \n" + 
					"	data-response-code=\"-1\" data-sid=\"1006973\"> \n" + 
					"   <img src=\"current/images/ajax.gif\"/> <p style=\"display:inline;\"> </p>\n" + 
					"<div id=\"trial-information\"></div>\n" + 
					"<p style=\"text-align:left;\" class=\"mono prewrap output-data\"></p>\n" + 
					"</div>\n" + 
					"\n" + 
					"<br style=\"clear:both\" />\n" + 
					"\n" + 
					"<div align=\"right\" style=\"position:relative; float:right; right:40px; top:-93px; width:350px; z-index:2; padding:0px;\">\n" +  
					"\n" + 
					"</div>\n" + 
					"\n" + 
					"<div align=\"left\" style=\"position:relative; float:left; left:30px; top:-100px; width:840px; z-index:1;\"> \n" + 
					"<div align=\"left\" class=\"problem-text\" style='width:800px; padding-top:10px;'>\n" + 
					"<span id=\"probtext-text\">" +
					"Oops, something went wrong: " + e +
					"</span>\n" + 
					"</div>\n" + 
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
		String end = "<center>Page requested: " + request.getRequestURI() + "<br>Page generated in: " + (double)((DataStore.stw.getElapsedNanoTime() - start)/ 1000000000.0) + " seconds [ 100% Tomcat 8 ] </center>";
		response.getWriter().append(s).append(end + "</body>\n" + 
				"\n" + 
				"</html>\n" + 
				"\n" + 
				"\n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
