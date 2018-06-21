<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品編集画面</title>
</head>
<%@ include file="../common/adminHeader.jsp"%>
<body>
商品詳細の編集<br>
<form:form modelAttribute="editItemForm" action="${pageContext.request.contextPath}/admin/editItem">
	<form:hidden path="id"/>
	商品名<form:input path="name" value="${form.name}"/><br>
	値段<form:input path="price" value="${form.price}"/><br>
	説明<form:input path="description" value="${form.description}"/><br>
	画像<form:input path="imagePath" value="${form.imagePath}"/><br>
	<%-- id<form:input path="id" value="${form.id}"/><br> --%>
	削除<form:checkbox path="deleted" value="${form.deleted}"/><br>
	<input type="submit" value="編集">
</form:form>
</body>
</html>