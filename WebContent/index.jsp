<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.restaurant.model.Reservation"%>
<%@ page import="org.restaurant.dao.RestaurantDao"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' href='style.css' type='text/css' />
<title>Insert title here</title>
</head>
<h1 align="center">Restaurant Reservation System</h1>
<h3 align ="right">Hi <%=session.getAttribute("name") %></h3>
<h3 align ="right"><a href="register.jsp">Logout</a></h3>
<h2>Current Reservations</h2>

<% ArrayList<Reservation> resvList = RestaurantDao.getReservations(); %>

<table border="1">
<tr>
<th>Customer Name</th>
<th>No of tables</th>
<th>Day</th>
<th>Time</th>
</tr>

<%for(Reservation resv : resvList){ %>
<tr>
<td><%=resv.getCustomer().getName()%></td>
<td><%=resv.getTables_reqd() %></td>
<td><%=resv.getReservation_day() %></td>
<td><%=resv.getTime() %></td>
<tr>
<%} %>

</table>
<br>
<h2 >Make Reservation</h2>
<form action="RestaurantController" name="reservationform" method="POST">
No of seats:&nbsp <input type="text" name="seats" id="seats"/><br><br>
Day: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<select name="day">
		<option value="Sunday">Sunday</option>
		<option value="Monday">Monday</option>
		<option value="Tuesday">Tuesday</option>
		<option value="Wednesday">Wednesday</option>
		<option value="Thursday">Thursday</option>
		<option value="Friday">Friday</option>
		<option value="Saturday">Saturday</option>
	 </select><br><br>
Time:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input type="time" name="time" id="time"/><br><br>
<input type="hidden" name="name" value=<%=session.getAttribute("name")%>/>
<input type="submit" value="Reserve"/>
</form>

</html>