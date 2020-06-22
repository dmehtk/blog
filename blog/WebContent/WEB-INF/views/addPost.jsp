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
        	<h1>추가하기</h1>
        	<!-- 글쓰기 폼 -->
            <form method="post" action="${pageContext.request.contextPath}/AddPostServlet">
            	<table class="table table-striped">
            	<tr>
            		<td>member_id : </td>
            		<td><input type="text" name="memberId" value="${loginMember.memberId }" readonly="readonly"></td>
            	</tr>
            	<tr>
            		<td>subject_name : </td>
            		<td>
		            	<select name="subjectName">
		            		<option value="">선택하세요</option>
		            		<c:forEach var="s" items="${subjectList }">
		            			<option value="${s.subjectName }">${s.subjectName }</option>
		            		</c:forEach>
		            	</select>
	            	</td>
            	</tr>
            	<tr>
            		<td>post_title : </td>
            		<td><input type="text" name="postTitle"></td>
            	</tr>
            	<tr>
            		<td>post_content</td>
            		<td><input type="text" name="postContent"></td>
            	</tr>
            	</table>
            	<button type="submit">추가하기</button>
            </form>
        </div>
    </div>
</body>
</html>