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
			<h1 class="text-center text-white display-4 font-weight-bold" style="padding: 150px;">메뉴</h1>
			<c:if test="${loginId eq 'YUSUK'}">
				<label class="text-white d-flex justify-content-center font-weight-bold">통합관리자 : ${loginId}님 반갑습니다</label>
				<div class="d-flex justify-content-center">
					<ul class="list-group">
						<li class="list-group-item text-white">
							<a href="${pageContext.request.contextPath}/admin/TotalMemberList">통합관리자 관리(C,R,U,D)</a>
						</li>
						<!-- category CRU -->
						<li class="list-group-item text-white">
							<a href="${pageContext.request.contextPath}/admin/CategoryList">카테고리 관리(C,R,U)</a>
						</li>
						<!-- item CRU -->
						<li class="list-group-item text-white">
							<a href="${pageContext.request.contextPath}/admin/ItemList">상품 관리(C,R,U)</a>
						</li>
						<!-- orders RUD -->
						<li class="list-group-item text-white">
							<a href="${pageContext.request.contextPath}/admin/OrdersList">주문 관리(R,U)</a>
						</li>
					</ul>
				</div>
				<div class="d-flex justify-content-end">
					<a class="btn btn-outline-light text-white font-weight-bold" 
						href="${pageContext.request.contextPath}/admin/AdminLogout">로그아웃
					</a>
				</div>
			</c:if>
			<c:if test="${loginId ne 'YUSUK'}">
				<label class="text-white d-flex justify-content-center font-weight-bold">${loginId}님 반갑습니다</label>
				<div class="d-flex justify-content-center">
					<ul class="list-group">
						<li class="list-group-item text-white">
							<a href="${pageContext.request.contextPath}/admin/MemberList?memberId=${loginId}">관리자 관리(C,R,U,D)</a>
						</li>
						<!-- category CRU -->
						<li class="list-group-item text-white">
							<a href="${pageContext.request.contextPath}/admin/CategoryList">카테고리 관리(C,R,U)</a>
						</li>
						<!-- item CRU -->
						<li class="list-group-item text-white">
							<a href="${pageContext.request.contextPath}/admin/ItemList">상품 관리(C,R,U)</a>
						</li>
						<!-- orders RUD -->
						<li class="list-group-item text-white">
							<a href="${pageContext.request.contextPath}/admin/OrdersList">주문 관리(R,U)</a>
						</li>
					</ul>
				</div>
				<div class="d-flex justify-content-end">
					<a class="btn btn-outline-light text-white font-weight-bold" 
						href="${pageContext.request.contextPath}/admin/AdminLogout">로그아웃
					</a>
				</div>
			</c:if>
		</div>
		<div class="col-md-3"></div>
	</div>
</body>
</html>