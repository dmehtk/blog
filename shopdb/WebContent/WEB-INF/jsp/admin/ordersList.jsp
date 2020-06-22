<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ordersList</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <!-- jQuery library -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <!-- Popper JS -->   
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <!-- Latest compiled JavaScript -->
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body class="bg-primary">
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<h1 class="text-center text-white display-4 font-weight-bold">주문 리스트</h1>
			<a class=" text-white font-weight-bold" href="${pageContext.request.contextPath}/admin/">메인메뉴</a>
			<table class="table table-striped table-light">
				<tr class="table-warning">
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
						<td><a href="${pageContext.request.contextPath}/admin/UpdateOrdersState?ordersId=${c.orders.ordersId}&ordersState=${c.orders.ordersState}">${c.orders.ordersState }</a></td>
						<td>${c.orders.userName }</td>
						<td>${c.orders.userPhone }</td>
						<td>${c.orders.userAddress }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col-md-3"></div>
	</div>	
</body>
</html>