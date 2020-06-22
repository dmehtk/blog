<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<body class="bg-info">
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div style="padding: 200px;">
				<h1 class="text-center text-white font-weight-bold">고객 메뉴</h1>
				<br><br>
				<div class="d-flex justify-content-center">
					<ul class="list-group">
						<li class="list-group-item text-white">
							<a href="${pageContext.request.contextPath}/mall/MyOrdersList">주문 확인</a>
						</li>
						<!-- category CRU -->
						<li class="list-group-item text-white">
							<a href="${pageContext.request.contextPath}/mall/MallIndex">상품 리스트</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-md-3"></div>
	</div>
</body>
</html>