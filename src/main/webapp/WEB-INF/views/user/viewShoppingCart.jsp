<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ショッピングカート一覧</title>
</head>
<%@ include file="../common/userHeader.jsp"%>
<body>
	<script>
		$(window).on('load resize', function(){
	    // navbarの高さを取得する
	    	var height = $('.navbar').height();
	    // bodyのpaddingにnavbarんぼ高さを設定する
	    	$('body').css('padding-top',height); 
		});
	</script>
	
	<center>
		<h2>ショッピングカート一覧</h2>
		<c:if test="${damyOrder.id==-1}">ショッピングカートに商品がありません</c:if>
		<c:forEach var="orders" items="${orderList}">
			<c:choose>
				<c:when test="${orders.orderItemList.size()==0}">
					<p>ショッピングカートに商品がありません</p>
				</c:when>
				<c:otherwise>
					<table class="table" border="1">
						<tr>
							<th colspan="2">商品名</th>
							<th>価格</th>
							<th>個数</th>
							<th></th>
						</tr>

						<c:forEach var="orderItem" items="${orders.orderItemList}">
							<tr>
								<td><img
									src="${pageContext.request.contextPath}/img/${orderItem.item.imagePath}"
									width="150" height="125"></td>
								<td><a
									href="${pageContext.request.contextPath}/user/viewDetail?id=${orderItem.item.id}">
										<c:out value="${orderItem.item.name}" />
								</a></td>
								<td><c:out value="${orderItem.item.price}" /></td>
								<td><c:out value="${orderItem.quantity}" /></td>
								<td><form:form modelAttribute="deleteShoppingCartForm"
										action="${pageContext.request.contextPath}/user/toDeleteShoppingCart">
										<button type="submit" name="orderItemId"
											value="${orderItem.id}">削除</button>
									</form:form></td>
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
					<a
						href="${pageContext.request.contextPath }/user/viewPaymentDetail">決済へ</a>
				</c:when>
			</c:choose>
		</c:forEach>
<%@ include file="../common/userFooterForPresentation.jsp" %>

</body>
</html>