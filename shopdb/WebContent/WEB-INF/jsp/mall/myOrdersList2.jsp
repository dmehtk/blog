<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <!-- jQuery library -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <!-- Popper JS -->   
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <!-- Latest compiled JavaScript -->
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<h1 class="text-center">ORDERSLIST</h1>
		<table class="table table-striped">
			<tr>
				<th>orders_id</th>
				<th>item_name</th>
				<th>item_count</th>
				<th>orders_date</th>
				<th>orders_price</th>
				<th>orders_state</th>
				<th>user_name</th>
				<th>user_phone</th>
				<th>user_address</th>
			</tr>
			<c:forEach var="c" items="${ list}">
			<tr>
					<td>${c.orders.ordersId }</td>
					<td>${c.item.itemName }</td>
					<td>${c.orders.itemCount }</td>
					<td>${c.orders.ordersDate }</td>
					<td>${c.orders.ordersPrice }</td>
					<td>${c.orders.ordersState }</td>
					<td>${c.orders.userName }</td>
					<td>${c.orders.userPhone }</td>
					<td>${c.orders.userAddress }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>