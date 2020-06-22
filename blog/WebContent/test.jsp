<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>login.jsp</title>
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
    	<ul id="navi">
	        <li class="group">
	            <div class="title">MENU</div>
	            <ul class="sub">
	                <li><a href="${pageContext.request.contextPath}/HomeServlet">home</a></li>
	        		<c:forEach var="subject" items="${subjectList }">
		        	<li>
		        		<a class="" href="">${subject.subjectName }</a>
		        	</li>
	        	</c:forEach>
	            </ul>
	        </li>
        </ul>
    </div>  
    <div id="section">
    	<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div style="padding: 200px;">
					<h4 class="text-center text display-4 ">
						LOGIN
						<img src="${pageContext.request.contextPath}/imgs/camera.png" width="150"height="150">
					</h4>
					<fieldset>
						<div class="d-flex justify-content-center">
							<form method="post" action="${pageContext.request.contextPath}/LoginServlet">
								<div class="form-group">
									<label >ID :</label>
									<input type="text" class="form-control-lg d-flex justify-content-center" name="memberId">
								</div>
								<div class="form-group">
									<label >Password :</label>
									<input type="password" class="form-control-lg d-flex justify-content-center" name="memberPw">
								</div>
								<button type="submit" class="btn btn-outline-dark btn-block">로그인</button>
							</form>
						</div>
					</fieldset>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
    </div>
</body>
</html>