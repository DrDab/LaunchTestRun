<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>LaunchTestRun</title>

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
<script type="text/javascript"
    src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
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
				<div class="panel">
					<h2>Error</h2>
				</div>
				<div class="card"
					style="Background-color: #ea4335; width: 800px; padding-top: 10px; padding-bottom: 10px;">
					<div class="container">
						<center>
							<plain style="color:#ffffff; font-size=0.8em;"> <subsection>Error 404: Page Not Found</subsection> </plain>
						</center>
					</div>
				</div>
				<br>
				<div align="left" class="card"
					style='width: 800px; padding-top: 10px;'>
					<div class="container">
						<span id="probtext-text" class="mathjax">
						  <plain>
						  The page that you have requested has been moved, or does not exist.
						  <br>
						  <br>
						  To return to the homepage, please click <a href="<%= response.encodeURL(request.getContextPath() + "/index.html") %>">here</a>.
						  <br>
						  To view a list of problems, please click <a href="<%= response.encodeURL(request.getContextPath() + "/listproblems.html") %>">here</a>.
						  </plain>
						  <br>
					   </span>
					</div>
					<br>
				</div>
				<br><br>
			</div>
		</div>
	</div>

	<br style="clear: both" />
	<center><font size="1">LaunchTestRun is (C) copyright of Victor Du.</font></center>
</body>

</html>


