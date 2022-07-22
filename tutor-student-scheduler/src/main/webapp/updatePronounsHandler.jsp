<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="myUtil" class="utilities.userStories" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Handler for Update Pronouns</title>
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

<%
	String fname = request.getParameter("fname");
	String pronouns = request.getParameter("pronouns");
	String lname = request.getParameter("lname");
	String username = myUtil.getUser();
	
	myUtil.updatePronouns(username,pronouns);
	String newPro = myUtil.getPronouns(username);
%>

<h1>Pronouns Updated!</h1>
<h1>Your new Pronouns are <%= newPro %></h1>
</body>
</html>