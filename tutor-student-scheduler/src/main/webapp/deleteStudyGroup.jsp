<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="myUtil" class="utilities.userStories" scope="session"> </jsp:useBean>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HAMMR Services Study Groups</title>
<link rel="stylesheet" href ="ourStyle.css">
</head>

<body>
<%@ include file="head.jsp" %>

<%if(myUtil.getUser() == null) {%>
<jsp:forward page="login.jsp"></jsp:forward>
<%}; %>

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

<%  String reformat = request.getParameter("delete");
	String groupId = reformat.substring(10); 

	ResultSet rset = myUtil.getCourse(groupId);
	String test = null;
	while(rset.next()){
		test = rset.getString(1);
	}
%>
<form action = "deleteStudyGroupHandler.jsp" method = "get">
	<article style="height:200px;">
		<h1>- HOLD ON THERE! -</h1>
	</article>
	<article style="height: 390px;">
	<hr>
		<h4> <i><span style="font-size: 33px">You are about to <span style="color: red;">DELETE</span>: <br></span></i> <%=test + " (" + groupId + ")"%> <br><br> 
		<i>This is a <span style="color: red;">permanent</span> decision, so ensure <br> that this is what you would like to do. </i> </h4>
		<p>  
			<button id="Back" type="submit" formaction="listCurrentGroups.jsp" class="leave" style="top:350px;"> BACK </button>
		</p>
		<a>
			<button name="delete" id="delete" type="submit" value="<%=groupId %>" class="findMore" style="top:350px;"> DELETE </button>
		</a>
	</article>
</form>

<hr>

<div class="image_2"></div>
</section>

<h5>
This website is for practicing HTML and JSP in tandem with a private database owned by the Campus of PLU. All images within this site are 
not owned by the creators. This site was not crafted nor is owned by <br> Pacific Lutheran University. The creators of this website have 
full creative control over this content. <br> <br>

Copyright 2022-2022 by HAMMR Incorporated. All Rights Reserved.<br>
HAMMR Services is Powered by Redbull GmbH. 1987-2022.<br><br><br>

<img src="hammr.png" alt="hehehehehehehe" style="width:40px;height:40px;opacity:70%;">
</h5>

</body>
</html>