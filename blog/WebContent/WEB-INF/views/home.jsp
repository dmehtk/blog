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
    	<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div style="padding: 200px;">
        		<h1 class="text display-4">Blog Home</h1>
        		<br>
	        	<!-- 비로그인 상태시 로그인 , 회원가입 메뉴만 나옴 -->
	            <c:if test="${loginMember == null }">
	            	<a class="btn btn-success" href="${pageContext.request.contextPath}/LoginServlet">로그인</a><br><br>
	            	<a class="btn btn-primary" href="${pageContext.request.contextPath}/AddMemberServlet">회원가입</a>
	            </c:if>
	            <!-- 로그인 상테일때 등급이 10보다 작으면 관리자 ~ 의 회원정보 라고 뜸 -->
	            <c:if test="${loginMember != null }">
	            	<c:if test="${loginMember.memberLevel < 10 }">
		            	<a class="btn btn-success" href="${pageContext.request.contextPath}/SelectMemberServlet">관리자 ${loginMember.memberId }님의 회원정보</a><br><br>
		            	<a class="btn btn-primary" href="${pageContext.request.contextPath}/LogoutServlet">로그아웃</a><br><br>
	            	</c:if>
	            	<!-- 등급이 9보다 크면 일반 회원~으로 뜸  -->
	            	<c:if test="${loginMember.memberLevel > 9 }">
		            	<a class="btn btn-success" href="${pageContext.request.contextPath}/SelectMemberServlet">일반 회원 ${loginMember.memberId }님의 회원정보</a><br><br>
		            	<a class="btn btn-primary" href="${pageContext.request.contextPath}/LogoutServlet">로그아웃</a><br><br>
	            	</c:if>
	            </c:if>
	            </div>
	        </div>
	        <div class="col-md-3"></div>
		</div>
        <!-- 메인내용 -->
    </div>
</body>
</html>