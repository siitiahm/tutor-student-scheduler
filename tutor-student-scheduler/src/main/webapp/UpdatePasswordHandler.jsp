<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="myUtil" class="utilities.userStories" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href ="ourStyle.css">
</head>
<body>

<%@ include file = "head.jsp"%>

<%
	int updatePass = -1;
	String username = myUtil.getUser();
	String oldpassword = request.getParameter("oldpw");
	String newPassword = request.getParameter("newpw");
	String newPassword2 = request.getParameter("newpw2");
	
	if(newPassword2 != newPassword)
	{
		updatePass = myUtil.updatePassword(username, newPassword);
	}
	
	
%>

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
<% if(updatePass > 0){ %>
<h1>Password Changed</h1>
<%}
else{%>
	Please make sure both new passwords are the same
	
	<jsp:forward page="UpdatePasswordForm.jsp"></jsp:forward>
<%} %>

</body>
</html>