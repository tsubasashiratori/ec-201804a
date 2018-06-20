<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>管理者メニュー</title>
</head>
<%@ include file="../common/adminHeader.jsp"%>
<body>
<a href="${pageContext.request.contextPath}/insertItem">商品を登録</a>
<a href="${pageContext.request.contextPath}/adminAllandSearch/adminFindAll">商品一覧</a>
<a href="${pageContext.request.contextPath}/adminAllandSearch">注文一覧</a>

<c:if test="${user} == null">
	<a href="${pageContext.request.contextPath}/admin/"></a>
</c:if>
<c:if test="${user} != null">
	<a href="${pageContext.request.contextPath}/admin/logout"></a>
</c:if>



</body>
</html>