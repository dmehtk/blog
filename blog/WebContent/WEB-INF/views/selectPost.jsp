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
	        <h1>포스트 상세보기</h1>
	        <!-- 좋아요 or 싫어요 비로그인시 좋아요 버튼이 안눌리게 로그인시 가능 -->
	        <div>
	        	<c:if test="${ loginMember != null}">
	        		<a href="${pageContext.request.contextPath}/AddLikeyServlet?postNo=${post.postNo}"><img src="${pageContext.request.contextPath}/imgs/ht.png"  width="30"height="30"></a>
	        	</c:if>
	        	<c:if test="${ loginMember == null}">
	        		<img src="${pageContext.request.contextPath}/imgs/ht.png"  width="30"height="30">
	        	</c:if>
	        	<span class="badge badge-danger">${likeyCount }</span>
	        	
	        	<c:if test="${ loginMember != null}">
	        		<a href="${pageContext.request.contextPath}/AddHateyServlet?postNo=${post.postNo}"><img src="${pageContext.request.contextPath}/imgs/broken.png"  width="30"height="30"></a>
	        	</c:if>
	        	<c:if test="${ loginMember == null}">
	        		<img src="${pageContext.request.contextPath}/imgs/broken.png"  width="30"height="30">
	        	</c:if>
	        	<span class="badge badge-danger">${hateyCount }</span>
	        </div>
	        <!-- 로그인한 아이디가 글쓴이거나 로그인 아이디가 관리자 등급일때 수정 및 게시글 삭제 가능 -->
	        <c:if test="${loginMember.memberId eq post.memberId || loginMember.memberLevel < 10}">
		        <form method="post" action="${pageContext.request.contextPath}/SelectPostServlet">
		            <table class="table table-bordered">
						<tr>
							<th>post_no </th>
							<td>
								<input type="text" name="postNo" value="${post.postNo }" readonly>
							</td>
						</tr>
						<tr>
							<th>post_title : </th>
							<td>
								<input type="text" name="postTitle" value="${post.postTitle}">
							</td>
						</tr>
						<tr>
							<th>post_content : </th>
							<td>
								<textarea name="postContent" rows="8" cols="85">${post.postContent}</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2">				
								<button class="btn btn-outline-dark" type="submit">수정하기</button>
							</td>
						</tr>
					</table>
				</form>
				<form method="post" action="${pageContext.request.contextPath}/DeletePostServlet">
					<div class="input-group mb-3">
						<input type="hidden" name="postNo" value="${post.postNo }">
						<input type="password" name="memberPw" placeholder="Password">
						<button class="btn btn-outline-dark" type="submit">게시물 삭제</button>
					</div>
				</form>
			</c:if>
			<!-- 일반 등급 회원이거나 글쓴이가 아닐때 보기만 가능 -->
			<c:if test="${loginMember.memberId ne post.memberId && loginMember.memberLevel > 9 }">
				
				<table class="table table-bordered">
					<tr>
						<th>post_no </th>
						<td>
							<input type="text" name="postNo" value="${post.postNo }" readonly>
						</td>
					</tr>
					<tr>
						<th>post_title : </th>
						<td>
							<input type="text" name="postTitle" value="${post.postTitle}" readonly>
						</td>
					</tr>
					<tr>
						<th>post_content : </th>
						<td>
							<textarea name="postContent" rows="8" cols="85" readonly>${post.postContent}</textarea>
						</td>
					</tr>
				</table>
			</c:if>
			<!-- 댓글등록 폼 -->
			<div>
				<form method="post" action="${pageContext.request.contextPath}/AddCommentServlet">
					<div class="input-group mb-3">
						<input type="hidden" name="postNo" value="${post.postNo }">
						<textarea rows="2" cols="80" name="commentContent"></textarea>
						<button class="btn btn-outline-dark" type="submit">댓글 등록</button>
					</div>
				</form>
			</div>
			<!-- 댓글 리스트 (member 테이블을 조인하여 등급까지 같이 보여줌) -->
			<div>
				<table class="table table-striped">
					<c:forEach var="c" items="${commentList}">
						<tr>
							<th>작성자 : </th>
							<th>${c.memberId }</th>
							<td> 
								등급 : 
								<c:if test="${c.memberLevel < 10 }">
									관리자
								</c:if>
								<c:if test="${c.memberLevel > 9}">
									일반멤버
								</c:if>
							</td>
							<td>댓글 삭제</td>
						</tr>
						<tr>
							<th>댓글 : </th>
							<td colspan="2">${c.commentContent }</td>
							<!-- 댓글 삭제 기능은 관리자 or 글쓴이만 가능 -->
							<c:if test="${loginMember.memberId eq post.memberId || loginMember.memberLevel < 10}">
								<td><a href="${pageContext.request.contextPath}/DeleteCommentServlet?postNo=${post.postNo }&commentNo=${c.commentNo }&commentContent=${c.commentContent }">삭제</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			
			<c:if test="${currentPage > 1 }">	
				<a href="${pageContext.request.contextPath}/SelectPostServlet?currentPage=${currentPage - 1}&postNo=${post.postNo }">이전</a>	
			</c:if>
			<c:if test="${currentPage < lastPage }">
				<a href="${pageContext.request.contextPath}/SelectPostServlet?currentPage=${currentPage + 1}&postNo=${post.postNo }">다음</a>
			</c:if>
			</div>
		</div>
    </div>
</body>
</html>