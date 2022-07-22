<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="myUtil" class="utilities.userStories" scope="session"></jsp:useBean>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HAMMR Services Creating...</title>
<link rel="stylesheet" href="ourStyle.css">
</head>
<body>

<% 
	   String username = myUtil.getUser();
	   String groupId = request.getParameter("study_group_id");
	   String course = request.getParameter("course");
	   String building = request.getParameter("building");
	   int room = Integer.parseInt(request.getParameter("room"));
	   String virtual_link = request.getParameter("virtual_link");
	   if(virtual_link.length() > 0){
		   myUtil.createStudyGroup(groupId, course, username, building, room, virtual_link);
	       myUtil.joinStudyGroup(groupId, username);
	   }
	   else{
		   myUtil.createStudyGroup(groupId, course, username, building, room);
	       myUtil.joinStudyGroup(groupId, username);
	   }
   %>   
       	<jsp:forward page="listCurrentGroups.jsp"></jsp:forward>
       

</body>
</html>