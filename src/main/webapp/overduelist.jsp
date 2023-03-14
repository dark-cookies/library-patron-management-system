<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@page import="com.work.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Overdue List</title>
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
		<br> <br>
		<table align="center">
			<caption>Overdue List</caption>
			<tr>
				<th>Student Name</th>
				<th>Roll No</th>
				<th>Book Name</th>
				<th>ISBN</th>
				<th>CheckOut Date</th>
				<th>Due Date</th>
				<th>Overdue Days</th>
			</tr>
			<tr>
				<%

ArrayList<Book> list =  (ArrayList<Book>)request.getAttribute("overduelist");

Iterator<Book> it = list.iterator();

while (it.hasNext()) {
	 Book b = it.next();
	 if(b != null){
%>

				<td><%= b.student.getName() %></td>
				<td><%= b.student.getRollNumber() %></td>
				<td><%= b.getBookName() %></td>
				<td><%= b.getISBN() %></td>
				<td><%= b.getCheckOutDate() %></td>
				<td><%= b.getDueDate() %></td>
				<td><%= b.student.getOverdueDays() %></td>
			</tr>
			<%
}
}
%>

		</table>
		<br>
		<center>
			<form action="/Library/OverdueServlet">
				<input type="hidden" name="action" value="print"> <input
					type="submit" value="Download">
			</form>
		</center>
	</div>

</body>
</html>