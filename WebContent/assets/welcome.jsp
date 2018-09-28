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
                    <h2>Welcome!</h2>
                </div>
                    <div align="left" class="card"
                        style='width: 800px; padding-top: 10px;'>
                        <div class="container">
                          <span id="probtext-text" class="mathjax">
                          <strong id="greeting">Hello!</strong><br><br>You have reached the homepage for the LaunchTestRun server, a automated platform for testing competitive programming solutions. LaunchTestRun lets you view programming problems, and test your solutions to these problems at any time you want.<br><br>To view a list of problems, please click <a href="<%= response.encodeURL(request.getContextPath() + "/listproblems.html") %>">here</a>. <br>
                          </span>
                        </div>
                        <br>
                    </div>
                    <br><br>
            </div>
        </div>
    </div>
    
    <br style="clear: both" />
    <center>LaunchTestRun is (C) copyright of Victor Du.</center>
 
<script>
    var greeting = document.getElementById("greeting");
    var today = new Date()
    var curHr = today.getHours()

    if (curHr < 12) {
        greeting.innerHTML = "Good morning!";
    } else if (curHr < 18) {
        greeting.innerHTML = "Good afternoon!";
    } else {
        greeting.innerHTML = "Good evening!";
    }
</script>

<br style="clear:both" />
<br style="clear:both" />

</body>

</html>


