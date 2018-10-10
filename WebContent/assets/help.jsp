<%@ page import="java.io.*, com.openhorizonsolutions.ltr.*" contentType="text/html"%>

<%
    double start = DataStore.stw.getElapsedNanoTime();
    String ip = request.getRemoteAddr();
    response.addHeader("X-Clacks-Overhead", "GNU Terry Pratchett");
    if (((start - DataStore.lastTimeOnlineRefreshed) / 1000000000.0) >= 900.0) 
    {
        DataStore.lastTimeOnlineRefreshed = start;
        DataStore.onlineMap.clear();
    }
    
    if (DataStore.onlineMap != null) 
    {
        if (!DataStore.onlineMap.containsKey(ip))
        {
            DataStore.onlineMap.put(ip, 1);
        }
    }
%>

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

<!--

                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      .hhhhhhhhhhhhhhhhhhhhhhho                                                     
                      `hhhhhhhhhhhhhhhhhhhhhhho                                                     
```````````````````````hhhhhhhhhhhhhhhhhhhhhhhs      ```````````````````````````````````````````````
yyyyyyyyyyyyyyyyyyyyyyyhhhhhhhhhhhhhhhhhhhhhhhy      +yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy
hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy      ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy      ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy      ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy      ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy      ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy      ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy      ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
yyyyyyyyyyyyyyyyyyyyyyyhhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhhyyyyyyyyyyyyyyyyyyyyyyy
```````````````````````hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhh-``````````````````````
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhh.                      
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhh.                      
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhh.                      
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhh.                      
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhh.                      
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhh.                      
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhh.                      
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhh.``````````````````````
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhhyyyyyyyyyyyyyyyyyyyyyyy
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
                       hhhhhhhhhhhhhhhhhhhhhhhy      /hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
                       hhhhhhhhhhhhhhhhhhhhhhhy      -hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
                       hhhhhhhhhhhhhhhhhhhhhhhy      -hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
                       hhhhhhhhhhhhhhhhhhhhhhhy      -hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
                       yhhhhhhhhhhhhhhhhhhhhhhy      -hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
                       yhhhhhhhhhhhhhhhhhhhhhhh      -hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
                       
                       
-->


</head>

<body class="no-mathjax">
<div id="lulz">
    <ul>
        <li><plain><a href="<%= response.encodeURL(request.getContextPath() + "/index.html") %>"><img src="<%= response.encodeURL(request.getContextPath() + "/assets/images/baseline-home-24px.svg") %>" height="24" width="24"></a></plain></li>
        <li><plain><a href="<%= response.encodeURL(request.getContextPath() + "/listproblems.html") %>"><img src="<%= response.encodeURL(request.getContextPath() + "/assets/images/baseline-list-24px.svg") %>" height="24" width="24"></a></plain></li>
        <li><plain><a href="<%= response.encodeURL(request.getContextPath() + "/help.html") %>"><img src="<%= response.encodeURL(request.getContextPath() + "/assets/images/baseline-info-24px.svg") %>" height="24" width="24"></a></plain></li>
    </ul>
</div>
<br>
    <div align="center">
        <div class="shadow1">
            <div class="content">
                <div class="panel">
                    <h2>Information</h2>
                </div>
					<div align="left" class="card"
						style='width: 800px; padding-top: 10px;'>
						<div class="container">
							<span id="probtext-text" class="mathjax"> <plain>
								<subsection>General Technical Details</subsection>
								<ul>
									<li>Your program must be less than 2MB in size. Your
										program may take up to 30 seconds to compile, but keep it
										reasonable. Unless otherwise stated, your program will be
										given 2 seconds to execute.</li>
									<li>Do not submit programs that open data files that
										aren't related to the problem at hand. Read only the specified
										input files and write only the specified output files. Do not
										use `temporary' data files.</li>
									<li>Progams must not pause and wait for keypresses. For
										example, if you call system("pause") from your code, the
										grading environment might time out waiting for a non-existant
										keypress.</li>
									<li>Virtually every program's output is in the form of
										"lines". Since this is a UNIX environment, lines in all
										input/output files are terminated with a single newline "\n",
										rather than a carriage return plus newline "\r\n" (although
										properly-designed programs generally should not care which
										convention is being used). If your output does not contain a
										newline at the end of every line, it may be graded as
										incorrect.</li>
								</ul>
								</plain>
							</span>
						</div>
						<br>
					</div>
					<br>
					<br>
					<div align="left" class="card"
                        style='width: 800px; padding-top: 10px;'>
                        <div class="container">
                            <span id="probtext-text" class="mathjax"> <plain>
                                <subsection>Language-Specific Technical Details</subsection>
                                <ul>
                                    <li>For <b>C/C++</b> programmers: Programs are compiled
                                        with gcc/g++ using the "-O2" optimization flag and "-lm"
                                        to access the math library, and also "-std=c++11" to enable
                                        support for C++11. Ints are 32 bits in size; use a "long long"
                                        if you need a 64-bit integer. To read or write a long long
                                        variable with C-style I/O (e.g., scanf, printf), use the
                                        "%lld" format string.
                                    </li>

									<li>For <b>C#</b> programmers: Programs are compiled with
										the mono free implementation of Microsoft .NET. You must
										submit your entire program in one file, and this file must
										have exactly one public class named the same as the file (for
										example, if your file is called "MyFile.cs", then it should
										contain "public class MyFile"). This class needs to have your
										main method. All other clases in the file should be defined
										without the "public" tag (e.g., as "class MyOtherClass"). Do
										not include a "package" line in your source code.
									</li>

									<li>For <b>Java</b> programmers: Programs are compiled
										with Java version 1.8 or above, and the Java standard library
										is provided.. You must submit your entire program in one file,
										and this file must have exactly one public class named the
										same as the file (for example, if your file is called
										"MyFile.java", then it should contain "public class MyFile").
										This class needs to have your public static void main
										function. All other clases in the file should be defined
										without the "public" tag (e.g., as "class MyOtherClass"). Do
										not include a "package" line in your source code.
									</li>

									<li>For <b>Python</b> programmers: We offer both Python
                                        2 and Python 3. Please be sure to select the correct
                                        version when you submit, since it is often the case that
                                        programs developed for one version will not work properly in
                                        the other (use "python --version" to check the version of your
                                        local Python interpreter).
                                    </li>
                                </ul>
                                </plain>
                            </span>
                        </div>
                        <br>
                    </div>
					<br><br>
            </div>
        </div>
    </div>
    
    <br style="clear: both" />
    <center><font size="1">
    <%
    double totalTimeUsed = (double) ((DataStore.stw.getElapsedNanoTime() - start) / 1000000000.0); 
    out.print(String.format("Page requested: %s <br>%d Users online<br>Page generated in: %5.3f seconds<br>This page was adapted from the USACO contest rules.", request.getRequestURI(), DataStore.onlineMap.size(), totalTimeUsed)); 
    %>
    </font></center>
    
<br style="clear:both" />
<br style="clear:both" />

</body>

</html>


