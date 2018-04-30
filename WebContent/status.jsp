<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' href='style.css' type='text/css' />
<title>Insert title here</title>
</head>
<body>
<h1 align="center">Restaurant Reservation System</h1><br>
<%boolean status=(Boolean)request.getAttribute("status"); %>
<%if(status){ %>
<h2>Reservation done successfully</h2>
<%} else{ %>
<h2>Sorry. Reservation failed</h2>
<%} %>
<a href="index.jsp">Go Back</a>
</body>
</html>