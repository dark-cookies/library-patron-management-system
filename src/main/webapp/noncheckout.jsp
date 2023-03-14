<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.work.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="table.css">
</head>
<body>

	<div class="sidenav">
		<a href="index.jsp">Home</a> <a href="checkout.jsp">Check Out</a> <a
			href="checkin.jsp">Check In</a> <a
			href="/Library/OverdueServlet?action=view">Overdue</a> <a
			href="/Library/NonCheckOut">Non Checkout</a> <a
			href="/Library/TopReaderServlet">Top Readers</a> <a
			href="searchpatron.jsp">Search Patron</a>
	</div>



	<div class="main">
		<table align="center">
			<caption>NonCheckOut List</caption>
			<tr>
				<th>Name</th>
				<th>Roll No</th>
				<th>Course</th>
				<th>Email</th>
			</tr>


			<% 
ArrayList<Student> list = (ArrayList<Student>)request.getAttribute("nonlist");
Iterator<Student> it = list.iterator();

while (it.hasNext()) {
	 Student s = it.next();
	 if(s != null){
			%>

			<tr>
				<td><%= s.getName() %></td>
				<td><%= s.getRollNumber() %></td>
				<td><%= s.getCourse() %></td>
				<td><%= s.getEmail() %></td>
			</tr>
	<%
	}
}
%>
		</table>
		<br> <a href="index.jsp">Back</a>
	</div>
</body>
</html>