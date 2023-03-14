<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@page import="com.work.model.*"%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="checkout.css">
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

		<br>


		<h1>Check Out</h1>

		Name :
		<%= session.getAttribute("name")%>
		<br>
		<br> ISBN :
		<form action="/Library/CheckOutServlet">


			<br>
			<br>
			<div class="wrap">
				<div class="search">
					<input type="text" name="ISBN" class="searchTerm" required>
					<br>
					<input type="hidden" name="data" value="searchBook"> <input
						type="submit" value="Search">
				</div>
			</div>
		</form>
		<br> <br>
		<% 	
Book BO = (Book)session.getAttribute("booklist");
%>

		<table>
			<tr>
				<th>Book ID</th>
				<th>Name</th>
				<th>ISBN</th>
				<th>Author</th>
				<th>Publisher</th>
				<th></th>
			</tr>
			<tr>
				<td><%=BO.getBookId() %></td>
				<td><%=BO.getBookName() %></td>
				<td><%=BO.getISBN() %></td>
				<td><%=BO.getAuthor() %></td>
				<td><%=BO.getPublisher() %></td>
				<td><form action="/Library/CheckOutServlet">
						<input type="hidden" name="data" value="addBook"> <input
							type="submit" value="add">
					</form></td>
			</tr>
		</table>


		<%

ArrayList<Book> list = 
				(ArrayList<Book>)request.getSession().getAttribute("addedList");

if(list==null){}
else{


request.getSession().setAttribute("checkoutlist",list);

%>
		<br>
		<table>
			<caption>Cart</caption>
			<tr>
				<th>Book ID</th>
				<th>Name</th>
				<th>ISBN</th>
				<th>Author</th>
				<th>Publisher</th>
			</tr>
			<tr>
				<%

Iterator<Book> it = list.iterator();

while (it.hasNext()) {
	 Book s = it.next();
	 if(s != null){
%>
				<td><%= s.getBookId() %></td>
				<td><%= s.getBookName() %></td>
				<td><%= s.getISBN() %></td>
				<td><%= s.getAuthor() %></td>
				<td><%= s.getPublisher() %></td>
			</tr>
			<%
}
}
%>
			<tr>
				<td></td>
				<td></td>
				<td><form action="/Library/CheckOutServlet">
						<input type="hidden" name="data" value="checkout"> 
						<input type="submit" value="Check out">
					</form>
					<a href="/Library/CheckOutServlet?data=cancel">Cancel</a>
				</td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br>
	
		<%
}
%>





	</div>
</body>
</html>