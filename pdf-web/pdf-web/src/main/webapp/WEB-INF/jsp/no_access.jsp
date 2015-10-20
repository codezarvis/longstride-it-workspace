<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="utf-8">
<head>
<title>Administrator</title>
  <link rel="stylesheet" type="text/css" href="../css/shCore.css">
  <link rel="stylesheet" type="text/css" href="../css/tablestyles.css">
  <link rel="stylesheet" href="../css/jquery-ui.css">
  
  <link rel="stylesheet" href="../css/new_menu_styles.css">
   <script src="../js/new_menu_script.js"></script>
  
  <script src="../js/jquery.js"></script>
  <script src="../js/jquery-ui.js"></script>
	<script type="text/javascript" language="javascript" src="../js/shCore.js"></script>
	
	<script>

  $(function() {
    $( "#ntabs" ).tabs();
  });
  </script>
  <style>
	h3   {color:red}
</style>
</head>
<body>
<div id='cssmenu'>
<ul>
   <li><a href='../home/services.do'>Home</a></li>
   <li ><a href='../reports/bookings.do'>Reports</a></li>
   <li class='active'><a href='#'>Admin</a></li>
</ul>
</div>
<%@include file="header.jsp" %>

<div id="ntabs">
  <ul>
    <li><a href="#noaccess">No Access</a></li>
  </ul>
  
  <div class="container" id="noaccess">
		<section>
			<h3>Insufficient Privileges</h3>
		</section>
	</div>
</div>

</body>
</html>