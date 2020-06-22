<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertCategoryForm</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <!-- jQuery library -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <!-- Popper JS -->   
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <!-- Latest compiled JavaScript -->
</head>
<body>
	<div class="row" style="margin:0;">
		<div class="col-*-*"> 	
			<h1 class="text-center display-4 font-weight-bold">카테고리 입력</h1>
			<br><br>
			<a class="text font-weight-bold" href="${pageContext.request.contextPath}/admin/CategoryList">카테고리 목록</a>
			<form method="post" action="${pageContext.request.contextPath}/admin/InsertCategory">
				<div class="input-group mb-3 d-flex justify-content-center">
				  	<div class="input-group-prepend">
				    	<span class="input-group-text text bg-light">카테고리 이름</span>
				    </div>
				  	<input type="text" class="form-control-md" name="categoryName">
				  	<div class="input-group-append">
				    	<button class="btn btn-info" type="submit">추가하기</button>
				  	</div>
				</div>
			</form>
		</div>
		<div class="col-*-*"></div>
	</div>
</body>
</html>