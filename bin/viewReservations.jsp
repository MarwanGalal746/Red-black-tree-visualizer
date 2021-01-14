<%@page import="java.sql.*"%>
<%@page import="validateInfo.DBConnection"%>
<%@page import="Reservation.ReservationDao"%>
<%@page import="Reservation.Reservation"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reservations</title>
</head>
<body>

<%
Connection con = DBConnection.initializeDatabase();
ReservationDao reservationDao = new ReservationDao();
ResultSet rset= reservationDao.getReservations(con);
if (rset.next())
{
%>
<table border="1">
		<tr>
			<th>Reservation ID</th>
			<th>From Date</th>
			<th>To Date</th>
			<th>Hotel</th>
			<th>Client Username</th>
			<th>Payment</th>
		</tr>
		<%
					      
					                do {%>
		<tr>
			<td><%=rset.getString("reservationID")%></td>
			<td><%=rset.getDate("fromDate")%></td>
			<td><%=rset.getDate("toDate")%></td>
			<td><%=rset.getString("Hotel")%></td>
			<td><%=rset.getString("client")%></td>
			<td><%=rset.getString("payment")%></td>
		</tr>
		<%}while (rset.next());%>
	</table>
	<br />
	<form class="formcss2" method="POST" align="center"
		action="cancelReservation.jsp" onsubmit="return validateForm()">
		<br> <b>If you want to cancel a reservation, enter its ID and
			press Cancel:</b><br> <input type="text" name="cancel"
			placeholder="ID for reservation to cancel" id="cancel"> <input
			type="submit" name="submit" value="Cancel Reservation" class="d12"><br>
	</form>
	<% 
            }		     	
            else{%>

	<h1 class="headcss">No Reservations!</h1>
	<% }%>
	
<script>
	  function validateForm() {
	  var cancel = document.getElementById("cancel").value;
	  if (cancel == "") {
	    alert("Data field cannot be empty!!");
	    return false;
	  }
	}
</script>
	
		

</body>
</html>