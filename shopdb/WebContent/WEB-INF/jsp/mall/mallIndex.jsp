<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mallIndex</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <!-- jQuery library -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <!-- Popper JS -->   
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <!-- Latest compiled JavaScript -->
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
      <div class="container-fluid">
         <!--
          상품 리스트 - 상품상세+주문폼 - 주문액션 - 주문정보폼(이름+전화번호) - 나의 주문목록 
          -->
          <div class="jumbotron jumbotron-fluid">
          <center><h1>SHOP</h1>
          </div>      
          <h2>상품목록</h2>
            <table class="table table-borderless">
             <tr>
                <c:forEach var="ic" items="${list}" varStatus="stats">
                      
                      <c:if test="${stats.index != 0 && stats.index % 5 == 0}">
                         </tr>
                         <tr>
                      </c:if>
                    <td>
						<div>
							<img width="250" height="150" src="${pageContext.request.contextPath}/imgs/${ic.item.itemImg}">
						</div>
						<div>
							<a href="${pageContext.request.contextPath}/mall/InsertOrders?itemId=${ic.item.itemId}">
								${ic.item.itemName}
							</a>
						</div>
					</td>			
                </c:forEach>
                <c:if test="${list.size() % 5 != 0 }">
                        <c:forEach begin="${list.size() % 5}" end="4" step="1">
                           <td>&nbsp;</td>
                        </c:forEach>
                         <!-- <td>&nbsp;</td> -->
                </c:if>
            </tr>   
            </table>
      </center>      
      </div>      
      </body>
</html>