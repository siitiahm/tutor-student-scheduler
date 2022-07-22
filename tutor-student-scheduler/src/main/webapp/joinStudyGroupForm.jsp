<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="myUtil" class="utilities.userStories" scope="session"></jsp:useBean>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HAMMR Services Study Groups</title>
<link rel="stylesheet" href ="ourStyle.css">
</head>

<script>
	function change(value){
		// fetching the table that may have been displayed previously, if any.
		var oldTableDisplay = document.getElementById("join").value;
		
		// feed the buttons the value that was selected
		document.getElementById("join").value = value;
		
		// the check that rehides the previous table.
		if(document.getElementById(oldTableDisplay).style.display == 'block'){
			document.getElementById(oldTableDisplay).style.display = 'none';
		}
		
		// unhide the table that is coorelated with the element pressed
		document.getElementById(value).style.display = 'block';
	}
</script>

<body>
<%@ include file="head.jsp" %>

<%if(myUtil.getUser() == null) {%>
<jsp:forward page="login.jsp"></jsp:forward>
<%}; %>


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

<%ResultSet groups = myUtil.listAllStudyGroups(); 
  int rowCount = 0;
  if (groups.last()) {
	  rowCount = groups.getRow();
	  groups.beforeFirst();
  }
%>

<% 
	ArrayList<String> stud_groups = new ArrayList<String>();
	String[][] stud_info = new String[rowCount][6]; 
	int index = 0;
	int row = 0;
	
	while(groups.next()){
		stud_groups.add(groups.getString(2) + " (" + groups.getString(1) + ")");
		stud_info[index][0] = "GroupID:  " + groups.getString(1);
		stud_info[index][1] = "Course:   " + groups.getString(2); 
		stud_info[index][2] = "Creator:    " + groups.getString(3);
		stud_info[index][3] = "Building:   " + groups.getString(4);
		stud_info[index][4] = "Room #:   " + groups.getString(5);
		stud_info[index][5] = "Zoom Link: " + groups.getString(6);
		index += 1;
	}
%>

<article style="height: 600px;">
	
	<table class="formatTable1">
		<tr>
			<th> All Study Groups: </th>
		</tr>
		<%for(String groupsIn: stud_groups) {%>
		<% row = stud_groups.indexOf(groupsIn); %>
			<tr>
				<td> <button class="unhidebtn" onclick="change('<%=stud_info[row][0] %>')"><%=groupsIn%></button> </td>
			</tr> 
		<%}; %>
	</table>
	
	<%for(String groupsIn: stud_groups) {%>
	<% row = stud_groups.indexOf(groupsIn); %>
		<table id="<%=stud_info[row][0]%>" class="formatTable2">
			<tr>
				<th>General Information: |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||</th>
			</tr>
				 <%for(int c = 0; c < 6; c++){ %>
					<tr>
						<td class="td"> <%=stud_info[row][c] %></td>
					</tr>
				 <%}; %>
		</table>
	<%}; %>

	<form action="joinStudyGroupHandler.jsp" method="get"> 
		<p> 
			<button name="join" id="join" type="submit" class="join" value="<%=stud_info[0][0] %>"> JOIN GROUP </button>
		</p>
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