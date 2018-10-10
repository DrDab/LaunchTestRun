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
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
                
                    <div class="container">
                        <center>
                            <forte style="color:#8bc34a;">Launch</forte>
                            <forte style="color:#ffc107;">Test</forte>
                            <forte style="color:#0ca9f4;">Run</forte>
                        </center>
                    </div>
                <br>
                <br>
                    <div align="left" class="card"
                        style='width: 800px; padding-top: 10px;'>
                        <div class="container">
                          <span id="probtext-text" class="mathjax">
                            <plain>
                                You have reached the homepage for LaunchTestRun, an automated platform for testing programming solutions. LaunchTestRun lets you view programming problems, and test your solutions to these problems at any time you want.<br><br>To view a list of problems, please click <a href="<%= response.encodeURL(request.getContextPath() + "/listproblems.html") %>"><strong style="color:#0ca9f4;">here</strong></a>. <br>
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
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <center><font size="1">
    <%
    double totalTimeUsed = (double) ((DataStore.stw.getElapsedNanoTime() - start) / 1000000000.0); 
    out.print(String.format("Page requested: %s <br>%d Users online<br>Page generated in: %5.3f seconds<br>LaunchTestRun is open-source and freely redistributable under the MIT License.", request.getRequestURI(), DataStore.onlineMap.size(), totalTimeUsed)); 
    %>
    </font></center>

<br style="clear:both" />
<br style="clear:both" />

</body>

</html>


