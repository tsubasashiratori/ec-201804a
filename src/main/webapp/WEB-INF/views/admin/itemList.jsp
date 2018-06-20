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
<form action="${pageContext.request.contextPath}/adminAllandSearch/adminFindByName" method="post">
	<input type="text" name="name"><br>
	<input type="submit" value="検索">
</form>

<c:forEach var="item" items="${itemList}">
	<form action="${pageContext.request.contextPath}/admin/adminEditItem" method="post">
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
					<button type="submit" name="" value="edit">編集</button>
					<button type="submit" value="delete">削除</button>
					
				</td>
			</tr>
		</table>
	</form>
</c:forEach>


</body>
</html>