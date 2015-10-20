<%@ page import="org.apache.shiro.subject.support.DefaultSubjectContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
		<meta charset="utf-8">
		<title>Casa Travels</title>
		<link rel="stylesheet" type="text/css" href="css/style.css " />
</head>
<body>
<%
if(request.getSession(false).getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) != null){
	response.sendRedirect("reports/bookings.do");
}
%>
<div id="maincontainer">
						<div id="imageheader">
						
									<img src="assets/casa.png" alt="casa travel"  	>
						</div>
				<div class="container">
					<section id="content">
						<form name="loginform" action="" method="post">
							<h1>Enter Credentials</h1>
							<div>
								<input type="text" placeholder="Username" name="username" id="username" value='<c:out value="${username}" />'>
							</div>
							<div>
								<input name="password" id="password" placeholder="Password" type="password">
							</div>
							<!-- div>
									<label style="margin-left:25px ;float:right"><input type="checkbox" name="rememberMe" value="true" > Remember me  </label>
							</div  -->
							<div>
								<input type="submit" value="Log in" />
								 
							</div>
						</form><!-- form -->
						 <c:if test="${not empty shiroLoginFailure}">
    						<font color="red">Bad credentials</font>
						 </c:if>
					</section><!-- content -->
			</div><!-- container -->
			</div>
<!-- 			
<div id="container" style="white-space:nowrap">
    <div id="image" style="display:inline;">
        <img src="images/header.png" height="90px" width="90px"/>
    </div>
    <div id="texts" style="display:inline; white-space:nowrap; color: brown;font-size: 40; font-weight: bold;"> 
       Casa Travel 
    </div>
</div>
<h2>Log In</h2>

<form name="loginform" action="" method="post">
    <div><label for="username">Username:</label><input name="username" id="username" value="<c:out value="${username}" />"></div>
    <div><label for="password">Password:</label><input name="password" id="password" type="password"></div>
    <div><input type="checkbox" name="rememberMe" value="true"/>Remember Me?</div>
    <input type="submit" value="Login"/>
</form>
<c:if test="${not empty shiroLoginFailure}">
    <font color="red">Bad credentials</font>
</c:if>

 -->
</body>
</html>
