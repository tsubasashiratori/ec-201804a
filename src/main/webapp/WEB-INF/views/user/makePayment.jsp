<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>決済画面</title>
</head>
<%@ include file="../common/userHeader.jsp" %>
<body>
	<h2 align="center">ご注文内容</h2>
	<hr>
	<form:form action="${pageContext.request.contextPath}/user/toPayment" method="POST">
	<table border="1" width="350"  align="center">
		<tr>
			<th>商品名</th>
			<th>価格</th>
			<th>個数</th>
			<th>税抜き価格</th>
			<th>小計</th>
		</tr>
		<c:forEach var="orderItem" items="${order.orderItemList}">
				<tr>
					<td><c:out value="${orderItem.item.name}"/></td>
					<td>&yen;<c:out value="${orderItem.item.price}"/></td>
					<td><c:out value="${orderItem.quantity}"/></td>
					<td>&yen;<c:out value="${orderItem.itemTotalPriceExcludeTax}"/></td>
					<td>&yen;<c:out value="${orderItem.totalPrice}"/></td>
				</td>
				</tr>
		</c:forEach>

		<tr>
		<td>消費税</td>
		<td align="right" colspan="4">&yen;<c:out value="${order.totalPriceTax}"/></td>

		</tr>
		<tr>
			<td>送料一律</td>
			<td align="right" colspan="4">&yen;<c:out value="${order.postage}"/></td>

		</tr>
		<tr>
			<td>商品合計</td>
			<td align="right" colspan="4">&yen;<c:out value="${order.totalPriceIncludeTaxAndPostage}"/></td>

		</tr>
	</table>
	<br>
	
	<h2 align="center">お届け先</h2>
	<hr><div align="center">
	お名前：<c:out value="${order.deliveryName}"/><br>
	メールアドレス：<c:out value="${order.deliveryEmail}"/><br>
	住所：<c:out value="${order.deliveryAddress}"/><br>
	電話番号：<c:out value="${order.deliveryTel}"/><br><br>
	<input type="hidden" name="orderId" value="${order.id}">
	<input type="submit" value="確定">
	</form:form></div>
	
	
	
</body>
</html>