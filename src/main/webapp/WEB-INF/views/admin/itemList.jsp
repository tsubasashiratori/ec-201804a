<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品一覧＜管理者＞</title>
</head>
<%@ include file="../common/adminHeader.jsp"%>
<body>
<br>
<form action="${pageContext.request.contextPath}/admin/adminFindAll" method="get" align="center">
			<input type="submit" value="全件表示" align="center">
		</form><br>
<form:form modelAttribute="editItemForm" action="${pageContext.request.contextPath}/admin/adminFindByName" method="post" align="center">
	<form:errors path="name" element="div" cssStyle="color:red" align="center"/>
	<form:input path="name"/>
	
	<input type="submit" value="検索">
	
</form:form>

<br>
<c:forEach var="item" items="${itemList}">
		<table border="1" align="center">
			<tr>
				<th>画像</th>
				<td><img src="../img/${item.imagePath}" width="200"/></td>
			</tr>
			<tr>
				<th>商品名</th>
				<td><c:out value="${item.name}"/></td>
			</tr>
			<tr>
				<th>値段</th>
				<td><fmt:formatNumber value="${item.price}" pattern="￥###,###"/></td>
			</tr>
			<tr>
				<th>説明</th>
				<td><c:out value="${item.description}"/></td>
			</tr>
			<tr>
				<th>操作</th>
				<td>
	<form action="${pageContext.request.contextPath}/admin/viewEditItem" method="get">
					<button type="submit" name="itemId" value="${item.id}" >編集</button>
	</form>
	<c:choose>
		<c:when test="${item.deleted}">
			<form action="${pageContext.request.contextPath}/admin/deleteItem" method="get">
				<button type="submit" name="id" value="${item.id}" >再表示</button>
			</form>
		</c:when>
		<c:otherwise>
			<form action="${pageContext.request.contextPath}/admin/deleteItem" method="get">
				<button type="submit" name="id" value="${item.id}">削除</button>
			</form>
		</c:otherwise>
	</c:choose>
					
				</td>
			</tr>
		</table>
		<br>
</c:forEach>

</body>
</html>