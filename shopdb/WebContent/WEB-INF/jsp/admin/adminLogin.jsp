<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminLogin</title>
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
			<div style="padding: 200px;">
				<h1 class="text-center text-white display-4 font-weight-bold">
					관리자 로그인
					<img src="${pageContext.request.contextPath}/imgs/company.png" width="150"height="150">
				</h1>
				<div class="d-flex justify-content-center">
					<form method="post" action="${pageContext.request.contextPath}/admin/AdminLogin">
						<div class="form-group">
							<label class="text-white font-weight-bold">ID :</label>
							<input type="text" class="form-control-lg d-flex justify-content-center" name="memberId">
						</div>
						<div class="form-group">
							<label class="text-white font-weight-bold">Password :</label>
							<input type="password" class="form-control-lg d-flex justify-content-center" name="memberPw">
						</div>
						<button type="submit" class="btn btn-outline-light text-white font-weight-bold">로그인</button>
					</form>
				</div>
			</div>
		</div>
		<div class="col-md-3"></div>
	</div>
</body>
</html>