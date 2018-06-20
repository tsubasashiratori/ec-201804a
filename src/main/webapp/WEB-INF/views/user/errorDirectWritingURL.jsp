<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>500エラー</title>
</head>
<%@ include file="../common/userHeader.jsp" %>
<body>
	<div align="center">
	<h2>時間をおいてアクセスしてください</h2>
	
	<img src="../img/image-161.jpg" width ="700" height="400"><br>
	<a href="${pageContext.request.contextPath}/ViewAllItemsAndSearchItem/findAllNotDeleted/">商品一覧へ戻る</a><br>
	<a href="${pageContext.request.contextPath}/regist/">新規登録はこちら</a>
	</div>
</body>
</html>