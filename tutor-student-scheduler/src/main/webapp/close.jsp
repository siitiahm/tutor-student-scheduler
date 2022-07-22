<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="myUtil" class="utilities.userStories" scope="session"></jsp:useBean>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HAMMR Services Logout</title>
<link rel="stylesheet" href="ourStyle.css">
</head>

<body>
<% 		
	if(myUtil.getConn() == null){
		myUtil.setUser(null); %>
		<jsp:forward page="login.jsp"></jsp:forward>
	<%}
	else{
		myUtil.closeConnection();
		myUtil.setUser(null);
	}
%>

<jsp:forward page="login.jsp"></jsp:forward>

</body>
</html>