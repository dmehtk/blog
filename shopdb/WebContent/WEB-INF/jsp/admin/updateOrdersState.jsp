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
	<div class="container">
		<h1  class="text-center display-4 font-weight-bold">배송상태 수정</h1>
		<table class="table table-bordered">
			<form method="post" action="${pageContext.request.contextPath}/admin/UpdateOrdersState">
				<tr>
					<th>orders_id : </th>
					<td>
						<input type="text" name="ordersId" value="${o.ordersId }">
					</td>
				</tr>
				<tr>
					<th>orders_state : </th>
					<td>
						<select name="ordersState">
						<option value="${o.ordersState }">주문상태 : ${o.ordersState }</option>
						<c:if test="${o.ordersState eq '주문완료' }">
						<option value="주문취소">주문취소</option>
						<option value="배송중">배송중</option>
						<option value="배송완료">배송완료</option>
						</c:if>
						<c:if test="${o.ordersState eq '주문취소' }">
						<option value="주문완료">주문완료</option>
						<option value="배송중">배송중</option>
						<option value="배송완료">배송완료</option>
						</c:if>
						<c:if test="${o.ordersState eq '배송중' }">
						<option value="주문완료">주문완료</option>
						<option value="주문취소">주문취소</option>
						<option value="배송완료">배송완료</option>
						</c:if>
						<c:if test="${o.ordersState eq '배송완료' }">
						<option value="주문완료">주문완료</option>
						<option value="주문취소">주문취소</option>
						<option value="배송중">배송중</option>
						</c:if>
					</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button class="btn btn-outline-primary" type="submit">수정하기</button>
					</td>
				</tr>
			</form>
		</table>
	</div>
</body>
</html>