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
	<script>
		$(window).on('load resize', function(){
	    // navbarの高さを取得する
	    	var height = $('.navbar').height();
	    // bodyのpaddingにnavbarんぼ高さを設定する
	    	$('body').css('padding-top',height); 
	    
		});
	</script>
<div align="center">
	
	<c:choose>
		<c:when test="${cartNullChecker == true}">
		<font color="red"><c:out value="${nullError}"/></font>
		<hr>
		<p align="center">
		<a href="${pageContext.request.contextPath}/user/ ">商品一覧画面へ戻る</a>
		</p>
		</c:when>
	<c:when test="${cartNullChecker == false}">
	<h2>ご注文内容</h2>
	<hr>
	<form:form action="${pageContext.request.contextPath}/user/toPayment" method="POST">
	<table border="1" align="center" class="table" >
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
					<td><fmt:formatNumber value="${orderItem.item.price}" pattern="￥###,###"/></td>
				
					<td><c:out value="${orderItem.quantity}"/></td>
					<td><fmt:formatNumber value="${orderItem.itemTotalPriceExcludeTax}" pattern="￥###,###"/></td>
					<td><fmt:formatNumber value="${orderItem.itemTotalPriceIncludeTax}" pattern="￥###,###"/></td>
				</tr>
		</c:forEach>

		<tr>
		<td>消費税</td>
		<td align="right" colspan="4"><fmt:formatNumber value="${order.totalPriceTax}" pattern="￥###,###"/></td>

		</tr>
		<tr>
			<td>送料一律</td>
			<td align="right" colspan="4"><fmt:formatNumber value="${order.postage}" pattern="￥###,###"/></td>

		</tr>
		<tr>
			<td>商品合計</td>
			<td align="right" colspan="4"><fmt:formatNumber value="${order.totalPrice}" pattern="￥###,###"/></td>

		</tr>
	</table>
	<br>
	
	<h2 align="center">お届け先</h2>
	<hr>
	お名前：<c:out value="${order.deliveryName}"/><br>
	メールアドレス：<c:out value="${order.deliveryEmail}"/><br>
	住所：<c:out value="${order.deliveryAddress}"/><br>
	電話番号：<c:out value="${order.deliveryTel}"/><br><br>
	<input type="hidden" name="userId" value="${order.userId}">
	<input type="submit" value="確定" class="btn btn-danger" style=WIDTH:100px>
	&nbsp;<br>
	</form:form>
	</c:when>
	</c:choose>
	</div>
	
<%@ include file="../common/userFooterForPresentation.jsp" %>
</body>
</html>