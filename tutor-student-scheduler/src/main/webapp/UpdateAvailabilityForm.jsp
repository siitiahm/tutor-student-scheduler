<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<jsp:useBean id="myUtil" class="utilities.userStories" scope="session"></jsp:useBean>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>HAMMR Services Update Availability</title>
		<link rel="stylesheet" href="ourStyle.css">
	</head>

	<body>
		<%@ include file="head.jsp" %>

			<div class="image"></div>
			<hr>

			<nav>
				<div class="dropdown">
					<a class="dropbtn" href="open.jsp">Welcome, <%=myUtil.getUser() %></a>
				</div>
				<div class="dropdown">
					<a class="dropbtn" href="settings.jsp"> Settings </a>
					<div class="dropdown-content">
						<a href="UpdatePasswordForm.jsp"> Change Password</a>
						<a href="updatePronounsForm.jsp"> Change Pronouns</a>
					</div>
				</div>
				<%if(myUtil.getTFlag()==true) { %>
					<div class="dropdown">
						<a class="dropbtn" href="UpdateAvailabilityForm.jsp"> Tutor</a>
						<div class="dropdown-content">
							<a href="UpdateAvailabilityForm.jsp"> Update Availability</a>
						</div>
					</div>
					<%} %>
						<div class="dropdown">
							<a class="dropbtn" href="listCurrentGroups.jsp"> Study Groups</a>
							<div class="dropdown-content">
								<a href="joinStudyGroupForm.jsp"> Join</a>
								<a href="createStudyGroup.jsp"> Create</a>
							</div>
						</div>
						<div class="dropdown">
							<a class="dropbtn" href="close.jsp"> Logout</a>
						</div>

			</nav>
			<hr>

			<% int n; if(request.getParameter("num")==null) n=0; else n=Integer.parseInt(request.getParameter("num"));
				%>
				<center>
				<form action="UpdateAvailabilityForm.jsp">
					How many availabilities would you like to insert:
					<input type="input" name="num" value="<%=n %>">
					<input type="submit" value="GET ROWS">
				</form>

				<% if(n> 0){ %>
					<form action="UpdateAvailabilityHandler.jsp" method="get">
						<table>
							<tr>
								<th>What day would you like to add availability for?</th>
								<th> What time are you avalibile</th>
							</tr> <br>

							<% for (int i=0; i < n; i++){ %>
								<tr>
									<td><select name="day" id="day" required>
											<option value="Monday">Monday</option>
											<option value="Tuesday">Tuesday</option>
											<option value="Wednesday">Wednesday</option>
											<option value="Thursday">Thursday</option>
											<option value="Friday">Friday</option>
											<option value="Saturday">Saturday</option>
											<option value="Sunday">Sunday</option>
										</select> </td>

									<td><input type="time" name="time" id="time"></input></td>
								</tr>
								<%} %>
						</table>
						<% } %>
							<input type=submit>
					</form>
</center>
	</body>

	</html>