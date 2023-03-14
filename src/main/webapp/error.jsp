<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="error.css">
<title>Error</title>
<style>
body {
	font-size: 30px;
}
</style>
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
String err = request.getParameter("q");
String error =null;
String back = null;
switch(err){
	case "b":
		error = "Book Not Available";
		back = "checkoutBookSearch.jsp";
		break;
	case "c":
		error = "Error while checkin";
		back = "checkinbook.jsp";
		break;
	case "e":
		error = "Error while checking out";
		back = "index.jsp";
		break;
	case "f":
		error = "No Current checkout entry found";
		back = "checkin.jsp";
		break;
	case "n":
		error = "Every student has checked out";
		back = "index.jsp";
		break;
	case "s":
		error = "Student not found";
		back = "checkout.jsp";
		break;
	case "sp":
		error = "Student not found";
		back = "searchpatron.jsp";
		break;
	case "o":
		error = "There are no Overdue's";
		back = "index.jsp";
		break;
	case "r":
		error = "There are no readers";
		back = "index.jsp";
		break;
		
		
}
%>
		<div style="padding-top: 100px;">
			<center><%= error %></center>
		</div>
		<br> <br> <br> <a
		href="javascript:window.history.back();" style="padding-left: 200px;">
		Back</a>

	</div>
</body>
</html>