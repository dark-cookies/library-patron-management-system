<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="java.util.*" %>
    <%@page import="com.work.model.*" %>

    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="checkout.css">
</head>
<body>
<div class="sidenav">
    <a href="index.jsp">Home</a>
    <a href="checkout.jsp">Check Out</a>
    <a href="checkin.jsp">Check In</a>
    <a href="/Library/OverdueServlet?action=view">Overdue</a>
    <a href="/Library/NonCheckOut">Non Checkout</a>
    <a href="/Library/TopReaderServlet">Top Readers</a>
    <a href="searchpatron.jsp">Search Patron</a>
</div>
    
    
    
<div class="main">
<h1>Library Patron Management</h1>
<br>

<br>
<table>
<tr><th>Book ID</th><th>Name</th><th>ISBN</th><th>Checkout Date</th>
<th>Action</th></tr>
<tr>
<%

ArrayList<Book> list =  (ArrayList<Book>)request.getAttribute("checklist");
//ArrayList<Book> list = new ArrayList<Book>();
//list.add((Book)request.getAttribute("checklist")); 

Iterator<Book> it = list.iterator();

while (it.hasNext()) {
	 Book b = it.next();
	 if(b != null){
%>

<td><%= b.getBookId() %></td><td><%= b.getBookName() %></td>
<td><%= b.getISBN() %></td><td><%= b.getCheckOutDate() %></td>
<td><form action="/Library/CheckinServlet">
<input type="hidden" name="data" value="checkin">
<input type="hidden" name="date" value="<%= b.getCheckOutDate() %>">
<input type="hidden" name="id" value="<%= b.getBookId() %>">
<input type="submit" value="Check In">
</form></td>
</tr>
<%
}
}
%>

</table>



</div>
</body>
</html>