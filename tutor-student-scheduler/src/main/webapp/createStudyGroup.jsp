<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="myUtil" class="utilities.userStories" scope="session"></jsp:useBean>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HAMMR Services Study Group Creation</title>
<link rel="stylesheet" href ="ourStyle.css">
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

<article>
	<h1>Let's Get Creative! <br> <i><span style="font-size: 28px">Please fill out the following information:</span></i></h1> 
</article>

<article>
<form action="createStudyGroupHandler.jsp" method="get">
	<table class="tabledata">
	<tr>
		<td class="td">  
			<label for="course" >Course: </label>
   			<input type="text" id="course" name="course" value="" size="30" maxlength="7" required><br>
   		</td>
	</tr>
	<tr>
		<td class="td">
			<label for="study_group_id">GroupID: </label>
   			<input type="text" id="study_group_id" name="study_group_id" value="" size="30" maxlength="8" required><br>
		</td>
	</tr>
	<tr>
		<td class="td">
			<label for="building" >Building: </label>
   			<input type="text" id="building" name="building" value="" size="30" maxlength="15" required><br>
		</td>
	</tr>
	<tr>
		<td class="td">
			<label for="room" >Room #: </label>
   			<input type="text" id="room" name="room" value="" size="30" maxlength="3" required><br>
		</td>
	</tr>
	<tr>
		<td class="td">
			<label for="virtual_link" >Zoom Link: </label>
  	 		<input type="text" id="virtual_link" name="virtual_link" size="30"><br>
		</td>
	</tr>
	<tr>
		<td class="td" align="center">
			<input type=submit value="Create">
		</td>
	</tr>
</table>
</form>
</article>
<hr>

<div class="image_2"></div>
</section>

<hr>
<article>
	<h5> 
	This website is for practicing HTML and JSP in tandem with a private database owned by the Campus of PLU. All images within this site are 
	not owned by the creators. This site was not crafted nor is owned by <br> Pacific Lutheran University. The creators of this website have 
	full creative control over this content. <br> <br>
	
	Copyright 2022-2022 by HAMMR Incorporated. All Rights Reserved.<br>
	HAMMR Services is Powered by Redbull GmbH. 1987-2022.<br><br><br>
	
	<img src="hammr.png" alt="hehehehehehehe" style="width:40px;height:40px;opacity:70%;">
	</h5>
</article>

</body>
</html>