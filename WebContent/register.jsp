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
<h3>Create Account</h3>
<form  action="RegisterCustomer" method="POST">
Username
<input type="text" name="name" id="name"/><br><br>
Password
<input type="password" name="pswd" id="pswd"/><br><br>
<input type="submit" value="Register"/><br><br>
</form>
<h5><a href="login.jsp">Already have an account? Login here</a></h5>
</body>
</html>