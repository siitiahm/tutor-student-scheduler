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


			<% 
			String[] day=request.getParameterValues("day"); 
			String[] tempTime=request.getParameterValues("time");
			String tutor=myUtil.getUser(); 
			String [][] data=new String[day.length][3]; 
			for(int i=0; i < day.length; i++){ int
				time=Integer.parseInt(tempTime[i].replaceAll(":", "" )); String ntime=String.format("%04d", time);
				data[i][0]=day[i]; data[i][1]=ntime; data[i][2]=tutor; }
				int test=myUtil.updateAvailabilityTuples(data); %>

				<center>Added <%= test %> times to the database for <%= tutor %>
				</center>

	</body>

	</html>