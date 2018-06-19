<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>
	<header>
		<div id="userHeader" align="right">
			<p>こんにちはゲストさん</p>
			<p>
				<a href="userLogin.html">ログイン</a>
			</p>
			<p>
				<a href="viewShoppingCart.html">カートの中身を見る</a>
			</p>
		</div>
		<div id="linkHeader" align="left">
			<h1 align="left">
				<a href="itemList.html">ＥＣサイトラクス</a>
			</h1>
			<div id="title" align="center"></div>
	</header>

	<div align="center">
		<h2>商品一覧</h2>

		<form action="${pageContext.request.contextPath}/ViewAllItemsAndSearchItem/findAllNotDeleted" method="post">
			<input type="submit" value="全件表示">
		</form><br>
		<form
			action="${pageContext.request.contextPath}/ViewAllItemsAndSearchItem/findByNameNotDeleted"
			method="post">
			<input type="text" name="name" /><input type="submit" value="検索する"
				align="center"><br>
		</form>
		<br>
		<table border="1" align="center">
			<c:forEach var="item" items="${itemList}">
				<tr>
					<th colspan="2">商品名</th>
					<th>価格</th>

				</tr>
				<tr>
					<td><img
						src="${pageContext.request.contextPath}/img/item.imagepath"
						width="150" height="125"></a></td>
					<td><a href="${pageContext.request.contextPath}?item.id"><c:out
								value="${item.name}"></c:out></a></td>
					<td><fmt:formatNumber value="${item.price}" pattern="###,###"/>
				</tr>
			</c:forEach>
		</table>



	</div>
</body>
</html>