<%@ page import="org.apache.shiro.subject.support.DefaultSubjectContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html lang="en">
<head>
  <meta charset="utf-8">
<head>
<title>Services</title>
  <link rel="stylesheet" type="text/css" href="../css/shCore.css">
  <link rel="stylesheet" type="text/css" href="../css/tablestyles.css">
  <link rel="stylesheet" href="../css/jquery-ui.css">
  
  <link rel="stylesheet" href="../css/new_menu_styles.css">
    <link rel="stylesheet" type="text/css" href="../css/jquery.notify.css">
  
   <script src="../js/new_menu_script.js"></script>
  
  <script src="../js/jquery.js"></script>
  <script src="../js/jquery-ui.js"></script>
     <script src="../js/jquery.notify.min.js"></script>
  
	<script type="text/javascript" language="javascript" src="../js/shCore.js"></script>

<script type="text/javascript" id="OFrameworkJS"
	ThemeURI="${pageContext.request.contextPath}/css/OdyAirSearch/"
	SkinID="default"
	src="${pageContext.request.contextPath}/js/OFramework.js"></script>
	
<script type="text/javascript">
	function backToSearch() {
		var backRes = document.getElementById("backRes").value;
		document.getElementById("res").src = backRes;
	}

	function init() {
		document.getElementById("res").style.display = "none";
		document.getElementById("showLink").style.display = "none";
	}
</script>
<style type="text/css">
.frame-area {
	display: block;
	overflow: auto;
	border: #000000 0px solid;
	margin: 0px;
	padding: 0px;
}
</style>

</head>
<body onload="init();">
	<%
		String currentUser = request.getSession(false)
				.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)
				.toString().toLowerCase();
	
	System.out.println("=====Curent User : "+currentUser);
		UserDAO userDao = new UserDAO();
		User userId = userDao.getUserById(currentUser);
		System.out.println("============"+userId.toString());
		String odyUserId = userId.getOdyUserId();
		String odyPassword = userId.getOdyPassword();
		long comanyId = userId.getStore().getCompany().getCompanyId();
		System.out.println("============="+odyUserId+"\t"+odyPassword+comanyId);
	%>
<div id='cssmenu'>
<ul>
   <li class='active'><a href='#'>Services</a></li>
   <li><a href='../reports/bookings.do'>Reports</a></li>
   <shiro:hasAnyRoles name="Admin,Affiliate,Store Manager,District Manager">
   		<li><a href='../admin/manage.do'>Admin</a></li>
   </shiro:hasAnyRoles>
</ul>
</div>
<%@include file="header.jsp" %>

<div id="AirSearchForm"></div>

	<script language="javascript" type="text/javascript">
		if (OFramework != null) {
			OFramework.QSParams.Add("siid",<%=comanyId%>);
			// change this with your valid siid (odysseus generated ID for each website/affiliate - unique per site)
			//OFramework.QSParams.Add("siid", "101424");
			OFramework.QSParams.Add("referrer", "testing query string add"); // be sure to remove this or set the right value here - for  example only

			oAForm = OFramework.AirSearchForm(document
					.getElementById("AirSearchForm"));

			//Un comment the fallowing three lines of code, inorder to pass Odysesues User Id and Password
			//OFramework.QSParams.Add("p1", "<%=odyUserId%>");
			//OFramework.QSParams.Add("p2", "<%=odyPassword%>");			
			//oAForm.TargetURL = "http://air.casatravel.com/admin/login.aspx";
			
			//console.debug(OFramework.res);
			//oAForm.Target = "_new"; //use this only if you want the search results to open in a different page

			//oAForm.Target = document.getElementById("res");
		}
	</script>
	<br />
	<p align="center" style="color: brown;">
		<b>This page is place holder for services layout provided by Casa</b>
	</p>
	<br />
	<iframe src="" id="res" width="100%" height="510" frameborder="0"
		marginheight="0" marginwidth="0" scrolling="auto" class="frame-area">

	</iframe>



	<input type="hidden" id="backRes" />

	<div id="showLink">
		<p align="center">
			<a href="#" id="back" onclick="backToSearch();">Back to Search
				Results</a>
		</p>
	</div>

</body>
</html>