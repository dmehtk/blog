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
<body>
	<div class="container">
		<h1  class="text-center display-4 font-weight-bold">멤버 추가하기</h1>
		<a href="${pageContext.request.contextPath}/admin/TotalMemberList">통합관리자 리스트</a>
		<table class="table table-bordered">
			<form method="post" action="${pageContext.request.contextPath}/admin/InsertMember">
				<tr>
					 <th>member_id :</th>
					<td><input type="text" name="memberId"><td>
				</tr>
				<tr>
					 <th>member_pw :</th>
					<td><input type="text" name="memberPw"></td>
				</tr>
				<tr>
					<td colspan="2">
						<button class="btn btn-outline-primary" type="submit">멤버 추가</button>
					</td>
				</tr>
			</form>
		</table>
	</div>
</body>
</html>