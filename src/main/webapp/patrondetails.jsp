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
		<%
		Student s = (Student) request.getAttribute("user");
		%>
		<h1 style="font-weight: bolder; padding-left: 330px;">Patron
			Details</h1>
		<div class="detail" style="padding-left: 330px; font-size: 20px;">
			Name :
			<%=s.getName()%><br> Roll Number :
			<%=s.getRollNumber()%><br> No of Books :
			<%=s.getBookCount()%><br>
		</div>
		<br>
		<%
		ArrayList<Book> list=(ArrayList<Book>)request.getAttribute("checklist");
		if (list.isEmpty()) {
		%>
		<center>No Books checked out</center>
		<%
		} else {
		%>
		<table align="center">
			<tr>
				<th>Book Name</th>
				<th>ISBN</th>
				<th>Book ID</th>
				<th>Checkout Date</th>
				<th>CheckIn Date</th>
			</tr>


			<%
			Iterator<Book> it = list.iterator();

			while (it.hasNext()) {
			    Book b = it.next();
			    if (b != null) {
			%>

			<tr>
				<td style="text-align: left;"><%=b.getBookName()%></td>
				<td><%=b.getISBN()%></td>
				<td><%=b.getBookId()%></td>
				<td><%=b.getCheckOutDate()%></td>
				<%
				if (b.getCheckInDate() != null) {
				%>
				<td><%=b.getCheckInDate()%></td>
				<%
				} else {
				%>
				<td>NA</td>
				<%
				}
				%>

			</tr>
			<%
			}
			}
			%>
		</table>
		<%
		}
		%>
		<br> <a href="searchpatron.jsp" style="color: white;">Back</a>
	</div>
</body>
</html>