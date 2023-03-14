<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="refresh" content="3;URL='index.jsp'" />
<link rel="stylesheet" href="error.css">
<title>Success</title>
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
			href="searchpatron.html">Search Patron</a>
	</div>
	<div class="main">
		<%
		String msg = request.getParameter("q");
		%>
		<div style="padding-top: 100px;">
			<center><%=msg%></center>
		</div>
</body>
</html>