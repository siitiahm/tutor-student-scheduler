<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="myUtil" class="utilities.userStories" scope="session"></jsp:useBean>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create account</title>
<link rel="stylesheet" href="ourStyle.css">
</head>
<body>

<%@ include file = "head.jsp" %>

<div class="image"></div>
<hr>

<nav>
	<div class="dropdown" >
		<a class = "dropbtn" href = "open.jsp">Welcome, <%=request.getParameter("username")%></a>
	</div>
	<div class="dropdown">
		<a class="dropbtn" href = "settings.jsp"> Settings </a>
			<div class="dropdown-content">
				<a href = "UpdatePasswordForm.jsp"> Change Password</a>
			   	<a href = "updatePronounsForm.jsp"> Change Pronouns</a>
			</div>
	</div>
	<%if(myUtil.getTFlag() == true) { %>
		<div class="dropdown">
			<a class="dropbtn" href = "UpdateAvailabilityForm.jsp"> Tutor</a>
				<div class="dropdown-content">
					<a href = "UpdateAvailabilityForm.jsp"> Update Availability</a>
				</div>
		</div>
	<%} %>
	<div class="dropdown">
		<a class="dropbtn" href = "listCurrentGroups.jsp"> Study Groups</a>
			<div class="dropdown-content">
				<a href = "joinStudyGroupForm.jsp"> Join</a>
			   	<a href = "createStudyGroup.jsp"> Create</a>
			</div>
	</div>
	<div class="dropdown">
		<a class="dropbtn" href = "close.jsp"> Logout</a>
	</div>

</nav>
<hr>

<%
	String user = request.getParameter("username");
	String pw = request.getParameter("password");
	String fname = request.getParameter("fname");
	String lname = request.getParameter("lname");
	boolean tflag = Boolean.parseBoolean(request.getParameter("tflag"));
	myUtil.openConnection();
	myUtil.createAccount(user, fname, lname, pw, tflag);
%>

<jsp:forward page="joinStudyGroupForm.jsp"></jsp:forward> 

</body>
</html>