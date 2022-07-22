<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="myUtil" class="utilities.userStories" scope="session"></jsp:useBean>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HAMMR Services </title>
</head>
<body>

<% 
		if(myUtil.getUser() != null)
		{%>
			<jsp:forward page="listCurrentGroups.jsp"></jsp:forward>
		<%}
		myUtil.openConnection();
	   	String user = request.getParameter("user");
       	String pw = request.getParameter("pw");
       	boolean correctInfo = myUtil.logIn(user, pw);
       	if(!correctInfo){ %>
    	   	<jsp:forward page="login.jsp"></jsp:forward>
       	<%}
       	else{ 
    	   myUtil.isTutor(user);
       	%>
       		<jsp:forward page="listCurrentGroups.jsp"></jsp:forward>
       	<%} 
       %>
       	
       

</body>
</html>