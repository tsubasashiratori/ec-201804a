<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../common/userHeader.jsp"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% %>
<DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
</head>

<body>
	<div align="center">
	<h2 align="center">商品詳細</h2>
	
	<table border="1" align="center">
		<tr>
			<td colspan="2" rowspan="3"><img src="../img/pc.jpg" width="150"
				height="150" alt="商品画像">
			</td>
			<th>商品名：</th>
			<td align="center"><c:out value="${item.name }"/></td>
		</tr>
		<tr>
			<th>価格：</th>
			<td align="center">
			<fmt:formatNumber value="${item.price}" pattern="￥###,###" /></td>
		</tr>
		<tr>
			<th>商品説明：</th>
			<td><c:out value="${item.description}"/></td>
		</tr>
	</table>
	<br>
		<a href = "${pageContext.request.contextPath}/ViewAllItemsAndSearchItem/findAllNotDeleted">商品一覧に戻る</a>
</body>
</html>