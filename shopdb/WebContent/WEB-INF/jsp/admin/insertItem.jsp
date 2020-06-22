<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertItem.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <!-- jQuery library -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <!-- Popper JS -->   
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <!-- Latest compiled JavaScript -->
</head>
<body>
	<div class="container">
		<h1  class="text-center display-4 font-weight-bold">아이템 추가</h1>
		<table class="table table-bordered">
	   		<tbody>
				<form method="post" action="${pageContext.request.contextPath}/admin/InsertItem">
					<tr>
						<th>category_name : </th>
						<td>
							<select name="categoryId">
								<option value="">카테고리 선택</option>
								<c:forEach var="c" items="${list }">
									<option value="${c.categoryId }">${c.categoryName }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>item_contents : </th>
						<td><input type="text" name="itemContents"></td>
					</tr>
					<tr>
						<th>item_price :</th> 
						<td><input type="text" name="itemPrice"></td>
					</tr>
					<tr>
						<th>item_name :</th> 
						<td><input type="text" name="itemName"></td>
					</tr>
					<tr>
						<td colspan="2">
							<a class="btn btn-outline-primary" role="button" href="${pageContext.request.contextPath}/mall/MallIndex">돌아가기</a>
							<button class="btn btn-outline-primary" type="submit">추가하기</button>
						</td>
					</tr>
				</form>
			</tbody>
		</table>
	</div>
</body>
</html>