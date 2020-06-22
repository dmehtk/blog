<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>start</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <!-- jQuery lib -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <!-- Popper JS -->   
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <!-- Latest compiled JavaScript -->
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
   <style>
   		body {
			  height: 100vh;
			  margin: 0;
			  background: linear-gradient(90deg, #2E2EFE 50%, #04B4AE 50%);
			}
   </style>
</head>
<body>
	<div class="row">
		<div class=" col-md-6">
			<div class="d-flex flex-wrap align-content-center" style="height:600px">
				<h2 class="text-center text-white display-4 font-weight-bold">관리자 모드 </h2>
				<a class="btn btn-outline-light btn-lg" role="button" href="${pageContext.request.contextPath}/admin/Index">이동하기</a>
			</div>
		</div>
		<div class=" col-md-6">
			<div class="d-flex flex-wrap align-content-center text-center" style="height:600px">
				<h2 class="text-center text-white display-4 font-weight-bold">고객 모드 </h2>
				<a class="btn btn-outline-light btn-lg" role="button" href="${pageContext.request.contextPath}/mall/MallMenu">이동하기</a>
			</div>
		</div>
	</div>
</body>
</html>