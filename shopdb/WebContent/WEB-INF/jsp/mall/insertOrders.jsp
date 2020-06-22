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
	<!-- item 상세화면 -->
	<div class="container">
		<h1  class="text-center display-4 font-weight-bold">item 상세화면</h1>
		<table class="table table-bordered">
			<tr>
				<td>item_id</td>
				<td>${i.itemId }</td>
			</tr>
			<tr>
				<td>category_id</td>
				<td>${i.categoryId }</td>
			</tr>
			<tr>
				<td>item_contents</td>
				<td>${i.itemContents }</td>
			</tr>
			<tr>
				<td>item_price</td>
				<td>${i.itemPrice }</td>
			</tr>
			<tr>
				<td>item_name</td>
				<td>${i.itemName }</td>
			</tr>
			<tr>
				<td>item_img</td>
				<td><img src="${pageContext.request.contextPath}/imgs/${i.itemImg }"></td>
			</tr>
		
			<!-- 주문 폼 -->
			<form method="post" action="${pageContext.request.contextPath}/mall/InsertOrders">
				<input type="hidden" name="itemId"value="${i.itemId }">
				<input type="hidden" name="itemPrice"value="${i.itemPrice }">
			<tr>
				<td>item_count</td>
				<td>
					<input type="text" name="itemCount">
				</td>
			</tr>
			<tr>
				<td>user_name</td>
				<td><input type="text" name="userName"></td>
			</tr>
			<tr>
				<td>user_phone</td>
				<td>
					<input type="text" name="userPhone">
				</td>
			</tr>
			<tr>
				<td>user_address</td>
				<td>
					<input type="text" name="userAddress">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button class="btn btn-outline-primary" type="submit">주문하기</button>
				</td>
			</tr>	
			</form>
		</table>
	</div>
</body>
</html>