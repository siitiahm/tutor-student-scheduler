<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="myUtil" class="utilities.userStories" scope="session"></jsp:useBean>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HAMMR Services Deleting...</title>
<link rel="stylesheet" href="ourStyle.css">
</head>
<body>

<% 
	   String username = myUtil.getUser();
       int groupId = Integer.parseInt(request.getParameter("delete"));
       myUtil.deleteStudyGroup(groupId);
   %>   
       	<jsp:forward page="listCurrentGroups.jsp"></jsp:forward>
       

</body>
</html>