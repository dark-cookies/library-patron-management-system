<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="search.css">
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
		<h1>Library Patron Management</h1>
		<h2 style="padding-left: 63px;">Check In</h2>

		<form action="/Library/CheckinServlet">
		   <div class="wrap">
			  <div class="search">
				  <input type="number" placeholder="Enter roll no" name="rollNo"
					  required> <input type="hidden" name="data"
					  value="checkStudent"> <input type="submit" value="Search">
				</div>
				<br> <br> <a class="button" href="index.jsp">Cancel</a>
			</div>
		</form>
	</div>
</body>
</html>