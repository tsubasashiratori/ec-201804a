<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="../common/userHeader.jsp"%>
<body>


			<h1>ショッピングカート一覧</h1>


				<c:forEach var="orders" items="${orderList}">
	<c:choose>
		<c:when test="${orders.orderItemList.size()==0}">
<p>ショッピングカートに商品がありません</p>
		</c:when>
		<c:otherwise>
			<table border=1>
				<tr>
					<th colspan="2">商品名</th>
					<th>価格</th>
					<th>個数</th>
					<th></th>
				</tr>
					<c:forEach var="orderItem" items="${orders.orderItemList}">
						<tr>
							<td><c:out value="${orderItem.item.imagePath}" /><br></td>
							<td><c:out value="${orderItem.item.name}" /><br></td>
							<td><c:out value="${orderItem.item.price}" /><br></td>
							<td><c:out value="${orderItem.quantity}" /></td>
							<td><form:form modelAttribute="deleteShoppingCartForm"
									action="${pageContext.request.contextPath}/user/toDeleteShoppingCart">
									<button type="submit" name="orderItemId"
										value="${orderItem.id}">削除</button>
								</form:form> <br></td>
						</tr>
					</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
				</c:forEach>

			<br> <br>
			<c:forEach var="orders" items="${orderList}">
				<c:choose>
		<c:when test="${orders.orderItemList.size()!=0}">
				<a href="${pageContext.request.contextPath }/user/viewPaymentDetail?orderId=${orders.id}">決済へ</a>
		</c:when>
		</c:choose>
			</c:forEach>
</body>
</html>