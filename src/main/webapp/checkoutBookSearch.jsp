<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.work.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.work.model.Book"%>
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
		<h1>Search</h1>
		<br> Name :
		<%=session.getAttribute("name")%>
		<br> <br> ISBN :
		<form action="/Library/CheckOutServlet">
			<div class="wrap">
				<div class="search">
					<input type="text" name="ISBN" required><br> <input
						type="hidden" name="data" value="searchBook"> <input
						type="submit" value="Search">
				</div>
				<br> <br> <a class="button" href="checkout.jsp">Cancel</a>
			</div>
		</form>
		<br> <br>
		
		
		<%--<table>
			<caption>Available Books</caption>
			<tr>
				<th>Book Name</th>
				<th>ISBN</th>
				<th>Author</th>
				<th>Publisher</th>
			</tr>
			<tr>
				<%
				ArrayList<Book> List = (ArrayList<Book>) request.getAttribute("bklist");
				if(List.isEmpty()){
					%>
						<center>No Books checked out</center>
						<%
				}
			else{
				
				Iterator<Book> it = List.iterator();
				while (it.hasNext()) {
				    Book s = it.next();
				    if (s != null) {
				%>
				<td><%=s.getBookName()%></td>
				<td><%=s.getISBN()%></td>
				<td><%=s.getAuthor()%></td>
				<td><%=s.getPublisher()%></td>
			</tr>
			<%
			}
			}
			}
			%>
		</table> --%>
	</div>
</body>
</html>