<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js"></script>
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<link rel="stylesheet" href="css/main.css" />
<title>Task Phrase Extraction</title>
</head>
<body>
<div class="jumbotron text-center" id="header">
<h2>Extract Task Phrases</h2>
<p>from natural language sentences</p>
</div>
<nav class="navbar navbar-inverse" id="header">
<ul class="nav navbar-nav">
<li><a href="index.jsp" id="Maintab">Home</a></li>
<li><a href="setting.jsp" id="Settingtab">Settings</a></li>
<li><a href="login.jsp" target="view_window">Admin</a></li>
<li><a href="About.jsp" id="Abouttab">About</a></li>
</ul>
</nav>
<div>
<h3>About</h3>
<p>This Task Phrase Extraction web application extracts task phrases from natural language sentences.
Task phrases are conceptualized as verbs associated with a direct object and/or a prepositional phrase, 
such as "get iterator", "get iterator for collection", or "add to collection". To extract task phrases, the application makes use of 
grammatical dependencies between words, as detected by the 
<a href="https://stanfordnlp.github.io/CoreNLP/" target="_blank">Stanford NLP parser</a>. 
While the algorithm was originally developed for software documentation and includes 
features specific to software-related texts 
(e.g., detection of code elements and a built-in list of programming actions), 
it is applicable outside of the software domain as well.<br><br></p>
<p>This project is a collaboration between the <a href="http://ctreude.ca/" target="_blank">
Software Engineering Group</a> at the <a href="http://www.adelaide.edu.au/" target="_blank">
University of Adelaide</a> and the <a href="http://cs.mcgill.ca/~martin/" target="_blank">
Software Technology Group</a> at <a href="http://www.mcgill.ca/" target="_blank">
McGill University</a>. The underlying "algorithms for automatically extracting descriptions of programming tasks from software documentations" 
have been registered as invention #16068 at McGill University on October 16, 2015.<br><br></p>
<p>If you are using the application as part of your research, please cite our <a href="http://ieeexplore.ieee.org/document/7000568/" target="_blank">TSE 2015 paper</a>.<br><br></p>
<h3>References</h3>
<p>C. Treude. TaskNav: Extracting Development Tasks to Navigate Software Documentation. Blog, 2015. [<a href="http://ctreude.ca/2015/01/17/tse-tasknav/" target="_blank">html</a>]</p>
<p>C. Treude, M. P. Robillard, and B. Dagenais. Extracting Development Tasks to Navigate Software Documentation. In IEEE Transactions on Software Engineering, 41, 6, p. 565-581, 2015. [<a href="http://ieeexplore.ieee.org/document/7000568/" target="_blank">doi</a>] [<a href="http://cs.adelaide.edu.au/~christoph/tse15.pdf" target="_blank">pdf</a>]
</p>
<p>C. Treude, M. Sicard, M. Klocke, and M. P. Robillard. TaskNav: Task-based Navigation of Software Documentation. In Proceedings of the 37th International Conference on Software Engineering, p. 649-652, 2015. [<a href="http://ieeexplore.ieee.org/document/7203034/" target="_blank">doi</a>] [<a href="http://cs.adelaide.edu.au/~christoph/icse15.pdf" target="_blank">pdf</a>] [<a href="https://www.youtube.com/watch?v=opnGYmMGnqY" target="_blank">video</a>]
</p>
<h3>Present and Past Contributors</h3>
<p>Liang Yuan, University of Adelaide<br>
Siwen Ou, University of Adelaide<br>
<a href="http://ctreude.ca/" target="_blank">Christoph Treude</a>, <a href="http://www.adelaide.edu.au/" target="_blank">University of Adelaide</a><br>
<a href="http://www.cs.mcgill.ca/~martin/" target="_blank">Martin Robillard</a>, <a href="http://www.mcgill.ca/" target="_blank">McGill University</a>
</p>
</div>

</body>
</html>
