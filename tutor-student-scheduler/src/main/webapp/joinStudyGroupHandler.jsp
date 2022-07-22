<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="myUtil" class="utilities.userStories" scope="session"></jsp:useBean>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HAMMR Services Joining... </title>
<link rel="stylesheet" href="ourStyle.css">
</head>
<body>

<% 
	   String student = myUtil.getUser();
       String reformat = request.getParameter("join");
       String groupId = reformat.substring(10); 
       
	   myUtil.joinStudyGroup(groupId, student);
%>

<jsp:forward page="listCurrentGroups.jsp"></jsp:forward>

</body>
</html>