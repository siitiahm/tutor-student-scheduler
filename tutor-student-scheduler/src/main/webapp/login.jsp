<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HAMMR Services Login</title>
<link rel="stylesheet" href ="ourStyle.css">
</head>

<body>
<%@ include file="head.jsp" %>


<section>
<div class="image"></div>

<hr>
<nav>
	<div class="dropdown" >
		<a class="dropbtn" href = "login.jsp"> Login</a>
	</div>
	<div class="dropdown">
		<a class="dropbtn" href = "createAccForm.jsp"> Create Account</a>
	</div>
	<div class="dropdown">
		<a class="dropbtn" href = "settings.jsp"> Settings </a>
			<div class="dropdown-content">
				<a href = "UpdatePasswordForm.jsp"> Change Password</a>
			   	<a href = "updatePronounsForm.jsp"> Change Pronouns</a>
			</div>
	</div>
	<div class="dropdown">
		<a class="dropbtn" href = "UpdateAvailabilityForm.jsp"> Tutor</a>
			<div class="dropdown-content">
				<a href = "UpdateAvailabilityForm.jsp"> Update Availability</a>
			</div>
	</div>
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

<article>
	<h1>Welcome! <br> <i><span style="font-size: 27px">Please enter your username and password:</span></i></h1> 
</article>

<article>
<form action="open.jsp" method="get">
	<table class="tabledata">
		<tr>
			<td class="td"> 
				<label for="user" >Username: </label>
	   			<input type="text" id="user" name="user" value="" style= "font-size:18px; font-family:Aleo;" size="30" maxlength="15" required><br>
	   		</td>
		</tr>
		<tr>
			<td class="td">
				<label for="pw"> Password: </label>
	   			<input type="password" id="pw" name="pw" value="" style= "font-size:18px; font-family:Aleo;" size="30" maxlength="15" required><br>
			</td>
		</tr>
		<tr>
			<td align="center" class="td">
				<input type=submit value="Submit">
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