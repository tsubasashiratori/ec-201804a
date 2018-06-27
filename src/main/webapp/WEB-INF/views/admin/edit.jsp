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
<div align="center">
		<h2>商品編集画面</h2>
		<div>編集したい商品の情報を入力してください。</div>
		<br>

<form:form modelAttribute="editItemForm" action="${pageContext.request.contextPath}/admin/editItem" align="center" enctype="multipart/form-data">
	<form:hidden path="id"/>
	<!-- idをhiddenで埋め込んでいる -->
	
			<form:errors path="name" element="div" cssStyle="color:red" align="center"></form:errors>
			商品名：<form:input path="name" value="${form.name}"/><br>
			<p><small></small></p>
			<br>
			
			<form:errors path="price" element="div" cssStyle="color:red" align="center"></form:errors>
			価格：<form:input path="price" value="${form.price}"/><br>
			<p><small></small></p>
			<br>
			
			<form:errors path="description" element="div" cssStyle="color:red" align="center"></form:errors>
			説明：<form:textarea path="description" value="${form.description}" rows="5" cols="40"/><br>
			<p><small></small></p>
			<br>
			
			<form:errors path="imagePath" element="div" cssStyle="color:red" align="center"></form:errors>
			画像：<input type="file" name="imagePath" accept="image/jpeg">
			<p><small></small></p>
			<br>
			現在の画像<br>
			<img src="${pageContext.request.contextPath}/img/${imagePath}"
							width="200" height="125" /><br>
			削除:<form:checkbox path="deleted" value="${form.deleted}"/><br>
			<p><small></small></p>
	         <input type="submit" value="編集" align="center">
    </form:form>
</body>
</html>