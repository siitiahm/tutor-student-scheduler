<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="myUtil" class="utilities.userStories" scope="session"></jsp:useBean>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HAMMR</title>
<link rel="stylesheet" href ="ourStyle.css">
</head>
<body>
<%@ include file = "head.jsp" %>

<div class="image"></div>
<hr>

<nav>
	<div class="dropdown" >
		<a class = "dropbtn" href = "open.jsp">Welcome, <%=myUtil.getUser() %></a>
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
TESTING

<% if(myUtil.getConn() == null){ %>
	<jsp:forward page="open.jsp"></jsp:forward>
<%} %>

<%
	String currentPro = myUtil.getPronouns(myUtil.getUser());
%>
Testing
<form action = "updatePronounsHandler.jsp" method = "get">

<center>
Current Pronouns: <%= currentPro %>
<br>
New Pronouns: <br>

<select name = "pronouns"  id = "pronouns" required >
	<option value = "she/her">she/her</option>
	<option value = "he/him">he/him</option>
	<option value = "they/them">they/them</option>
</select> 
<br>
<input type = "submit">
</center>

</form>
</body>
</html>