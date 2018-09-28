<%@ page isErrorPage="true" import="java.io.*, com.openhorizonsolutions.ltr.*" contentType="text/html"%>

<% double start = DataStore.stw.getElapsedNanoTime(); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>


 <title>LaunchTestRun</title>
 <META HTTP-EQUIV="EXPIRES" CONTENT="0">
 <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
 <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">

<script type="text/x-mathjax-config">
MathJax.Hub.Config({
    "HTML-CSS": {
    scale: 85,
    preferredFont: "STIX"
    },
  tex2jax: {
    inlineMath: [['$','$'],['\\(','\\)']],
    processEscapes: true,
    skipTags: ['pre'],
    processClass: "mathjax",
    ignoreClass: "no-mathjax"
  }
});
</script>
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<link rel="stylesheet" href="<%= response.encodeURL(request.getContextPath() + "/assets/fonts.css") %>">
<link rel="stylesheet" href="<%= response.encodeURL(request.getContextPath() + "/assets/page-styles.css") %>">
<link rel="icon"
    href="<%= response.encodeURL(request.getContextPath() + "/assets/favicon.ico") %>">
<link rel="shortcut icon " 
    href="<%= response.encodeURL(request.getContextPath() + "/assets/favicon.ico") %>">
</head>

<body class="no-mathjax">
 <div align="center">
  <div class="shadow1">

   <div class="content">

  

   
    


<div class="panel">

<h2> Error </h2>
 
</div><br>

<br style="clear:both" />


<div id="trial-information"></div>
<p style="text-align:left;" class="mono prewrap output-data"></p>

<br style="clear:both" />

<div align="right" style="position:relative; float:right; right:40px; top:-93px; width:350px; z-index:2; padding:0px;">

</div>

<div align="center" style="position:relative; float:center; left:30px; top:-100px; width:840px; z-index:1;"> 
<div align="left" class="card" style='width:800px; padding-top:10px;'>
<div class="container">
<span id="probtext-text"><div class="card" style="Background-color: #ea4335; width:800px; padding-top:10px; padding-bottom:10px;"><div class="container"><center><plain style="color:#ffffff; font-size=0.8em;">
Oops, something went wrong. Pwease repowt the fowwowing error to the webmaster. *blushes* X///3
<br>
<br>
Error message:
<br>
<%=out.println(exception.getMessage());%>
<br>
<br>
Stack trace:
<br>
<%
	String realPath = getServletContext().getRealPath("");
	File logFile = new File(realPath, "forensics.txt");
	DataStore.forensicsLogger.setFile(logFile);
	StringWriter stringWriter = new StringWriter();
	PrintWriter printWriter = new PrintWriter(stringWriter);
	exception.printStackTrace(printWriter);
	out.println(stringWriter);
	DataStore.forensicsLogger.logError(stringWriter.toString(), request.getRemoteAddr());
	printWriter.close();
	stringWriter.close();
%>
</plain></center></div></div></span>
</div>
</div>

</div>


<br style="clear:both" />


   </div>
  </div>
 </div>

 <br style="clear:both" />

<center>Page requested: <% out.print(request.getRequestURI()); %><br>Page generated in: <% out.print((double) ((DataStore.stw.getElapsedNanoTime() - start) / 1000000000.0)); %> seconds [ 100% Tomcat 8 ]<br>LaunchTestRun is (C) copyright of Victor Du.</center></body>

</html>


