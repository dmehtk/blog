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
<body class="bg-primary">
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<h1 class="text-center text-white display-4 font-weight-bold">통합관리자 리스트</h1>
			<a class="text-white font-weight-bold" href="${pageContext.request.contextPath}/admin/InsertMember">추가하기</a>
			<a class=" text-white font-weight-bold" href="${pageContext.request.contextPath}/admin/">메인메뉴</a>
			<table class="table table-striped table-light">
				<tr class="table-info">
					<th>member_id</th>
					<th>member_pw</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
				<c:forEach var="m" items="${list }">
					<tr>
						<td>${m.memberId }</td>
						<td>${m.memberPw }</td>
						<td><a href="${pageContext.request.contextPath}/admin/UpdateMember?memberId=${m.memberId}">수정</a></td>
						<td><a href="${pageContext.request.contextPath}/admin/DeleteMember?memberId=${m.memberId}">삭제</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col-md-3"></div>
	</div>
</body>
</html>