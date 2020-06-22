<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div>
	<ul id="navi">
		<li class="group">
	        <div class="title">MENU</div>
	        <ul class="sub">
				<li><a href="${pageContext.request.contextPath}/HomeServlet">home</a></li>
				<c:if test="${loginMember != null && loginMember.memberLevel < 10 }">
					<li><a href="${pageContext.request.contextPath}/AdminServlet">관리자 메뉴</a></li>
				</c:if>
				<c:forEach var="subject" items="${subjectList }">
					<li>
						<a class="" href="${pageContext.request.contextPath}/SelectPostBySubject?subjectName=${subject.subjectName}">${subject.subjectName }</a>
					</li>
				</c:forEach>
			</ul>
		</li>
	</ul>
</div> 