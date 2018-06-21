<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="../common/userHeader.jsp" %>
<body>
	<form:form modelAttribute="deleteShoppingCartForm"
		action="${pageContext.request.contextPath}/user/toDeleteShoppingCart">
		<center>
			<h1>ショッピングカート一覧</h1>
			<table border=1>
				<tr>
					<th colspan="2">商品名</th>
					<th>価格</th>
					<th>個数</th>
					<th></th>
				</tr>
				<c:forEach var="orders" items="${orderList}">
					<c:forEach var="orderItem" items="${orders.orderItemList}">
						<tr>
							<td><c:out value="${orderItem.item.imagePath}" /><br></td>
							<td><c:out value="${orderItem.item.name}" /><br></td>
							<td><c:out value="${orderItem.item.price}" /><br></td>
							<td><c:out value="${orderItem.quantity}" /></td>
							<td><button type="submit" name="orderItemId"
									value="${orderItem.id}">削除</button>
								<br></td>
						</tr>
					</c:forEach>
				</c:forEach>

			</table>
			<br>
			<br>
			<c:forEach var="orders" items="${orderList}">
				<a href="${pageContext.request.contextPath }/user/viewPaymentDetail?orderId=${orders.id}">決済へ</a>
			</c:forEach>
	</form:form>
	</center>
</body>
</html>