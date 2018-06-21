<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品一覧＜管理者＞</title>
</head>
<%@ include file="../common/adminHeader.jsp"%>
<body>
<br>
<form action="${pageContext.request.contextPath}/admin/adminFindByName" method="post">
	<input type="text" name="name"><br>
	<input type="submit" value="検索">
</form>

<c:forEach var="item" items="${itemList}">
		<table border="1">
			<tr>
				<th>画像</th>
				<td><c:out value="${item.imagePath}"/></td>
			</tr>
			<tr>
				<th>商品名</th>
				<td><c:out value="${item.name}"/></td>
			</tr>
			<tr>
				<th>値段</th>
				<td><c:out value="${item.price}"/></td>
			</tr>
			<tr>
				<th>説明</th>
				<td><c:out value="${item.description}"/></td>
			</tr>
			<tr>
				<th>操作</th>
				<td>
	<form action="${pageContext.request.contextPath}/admin/viewEditItem?itemId=${item.id}" method="post">
					<button type="submit" name="edit" value="${item.id}">編集</button>
	</form>
	<c:choose>
		<c:when test="${item.deleted}">
			<form action="${pageContext.request.contextPath}/admin/deleteItem" method="post">
				<button type="submit" name="redisplay" value="${item.deleted}">再表示</button>
			</form>
		</c:when>
		<c:otherwise>
			<form action="${pageContext.request.contextPath}/admin/deleteItem" method="post">
				<button type="submit" name="delete" value="${item.deleted}">削除</button>
			</form>
		</c:otherwise>
	</c:choose>
					
				</td>
			</tr>
		</table>
</c:forEach>

</body>
</html>