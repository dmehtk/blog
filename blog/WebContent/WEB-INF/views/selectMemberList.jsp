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
    	<div class="container">
    		<!-- 총 회원 목록폼 회원 목록폼은 0번인 통합관리자만 이용가능 회원 등급 조정이 가능함 -->
        	<h1>회원목록${lastPage }</h1>
        	<label class="text-end">현재페이지 : <mark>${currentPage }</mark></label>
            <table class="table ">
            	<tr>
            		<th>ID</th>
            		<th>LEVEL</th>
            		<th>회원 레벨 수정</th>
            	</tr>
            	<c:forEach var="m" items="${list }">
	            	<tr>
	            		<td>${m.memberId }</td>
	            		<td>
	            			${m.memberLevel }
	            			<c:if test="${m.memberLevel < 10 }">
	            				관리자
	            			</c:if>
	            			<c:if test="${m.memberLevel > 9 }">
	            				일반멤버
	            			</c:if>
	            		</td>
	            		<td><a href="${pageContext.request.contextPath}/UpdateAdminServlet?memberId=${m.memberId}">레벨 수정</a></td>
	            	</tr>
            	</c:forEach>
            </table>
            <c:if test="${currentPage > 1 }">	
            	<a href="${pageContext.request.contextPath}/SelectMemberListServlet?currentPage=${currentPage - 1}">이전</a>	
            </c:if>
            <c:if test="${currentPage < lastPage }">
            	<a href="${pageContext.request.contextPath}/SelectMemberListServlet?currentPage=${currentPage + 1}">다음</a>
            </c:if>
        </div>
    </div>
</body>
</html>