<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>blog</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <!-- jQuery library -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <!-- Popper JS -->   
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <!-- Latest compiled JavaScript -->
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<style>
    body {
        padding: 0;
        margin: 0;
        width: 100%;
        height: 100%;
        overflow: hidden;
        background-position: 0 0;
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: cover;
        position: relative;
        overflow-y: auto;
        font-family:"맑은 고딕";
    } 
    #section {
        margin-left: 350px;
        background: white;
    }
     ul#navi {
        width: 200px;
        height: 3000px;
        position: fixed;
        overflow: hidden;
        float: left;
        text-indent: 10px;
        background: #006F84;
        color : white; 
	}
    ul#navi, ul#navi ul {
        margin:0;
        padding:0;
        list-style:none;
	}
    li.group {
        margin-bottom: 3px;
	}
    li.group div.title {
    	border-bottom : 2px solid white;
    	border-top : 2px solid white;
    	margin-top : 200px;
        height: 35px;
        line-height: 35px;
        background:#006F84;
        cursor:pointer;
	}
    ul.sub li {
    	border-bottom : 2px solid white;
        margin-bottom: 2px;
        height:35px;
        line-height:35px;
        background:#006F84;
        cursor:pointer;
       
	}
    ul.sub li a {
        display: block;
        width: 100%;
        height:100%;
        text-decoration:none;
        color:white;
        
	}
    ul.sub li:hover {
        background:#C4FDFF;
	}
</style>
</head>
<body>
   <div>
	  <jsp:include page="/WEB-INF/views/inc/side.jsp"></jsp:include>
   </div> 
    <div id="section">
            <div id="section">
    	<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div style="padding: 200px;">
					<!-- 회원 탈퇴전에 비밀번호 입력하는 폼 -->
					<h4 class="text-center">
						비밀번호를 입력해주세요.
					</h4>
					<div class="d-flex justify-content-center">
						<form method="post" action="${pageContext.request.contextPath}/DeleteMemberServlet">
							<fieldset>
								<div class="form-group">
									<label >Password :</label>
									<input type="password" class="form-control-lg d-flex justify-content-center" name="memberPw">
								</div>
								<button type="submit" class="btn btn-outline-dark btn-block">확인</button>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
    </div>
    </div>
</body>
</html>