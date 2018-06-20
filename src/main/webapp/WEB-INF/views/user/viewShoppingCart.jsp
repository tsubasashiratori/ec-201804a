<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../common/userHeader.jsp"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>


	<div align="center">
		<h2>商品一覧</h2>

		<form
			action="${pageContext.request.contextPath}/ViewAllItemsAndSearchItem/findAllNotDeleted"
			method="post">
			<input type="submit" value="全件表示">
		</form>
		<br>
		<form:form modelAttribute="viewAllItemsAndSearchItemForm"
			action="${pageContext.request.contextPath}/ViewAllItemsAndSearchItem/findByNameNotDeleted"
			method="post">
			<form:errors path="name" element="div" cssStyle="color:red" /> 
			<form:input path="name"/><input type="submit" value="検索する"
				align="center"><br>
		</form:form>
		<br>
		<table border="1" align="center">
				<tr>
					<th colspan="2" width="150">商品名</th>
					<th width="150">価格</th>

				</tr>
			<c:forEach var="item" items="${itemList}">
				<tr>
					<td><img
						src="${pageContext.request.contextPath}/img/${item.imagePath}"
						width="150" height="125"></a></td>
					<td><a
						href="${pageContext.request.contextPath}/ViewDetail?id=${item.id}">
							<c:out value="${item.name}"></c:out>
					</a></td>
					<td><fmt:formatNumber value="${item.price}" pattern="￥###,###" />
				</tr>
			</c:forEach>
		</table>



	</div>
</body>
</html>