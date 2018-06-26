<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../common/userHeader.jsp"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-material-button-color-master/dist/cb-bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-material-button-color-master/dist/cb-materialbtn.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/arange.css">
<meta http-equiv="content-type" charset="utf-8">
<title>Bootstrap Sample1aaaaaa</title>
<!-- jQuery読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- BootstrapのCSS読み込み -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- BootstrapのJS読み込み -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<title>商品一覧</title>
</head>
<body>


	<div align="center">
		<h2>商品一覧</h2>

		<form
			action="${pageContext.request.contextPath}/user/ViewAllItemsAndSearchItem/findAllNotDeleted"
			method="get">
			<div class="square_btn">
			<input type="submit" value="全件表示" >
			</div>
		</form>
		<br>
		<form:form modelAttribute="viewAllItemsAndSearchItemForm"
			action="${pageContext.request.contextPath}/user/ViewAllItemsAndSearchItem/findByNameNotDeleted"
			method="post" align="center">
			<form:errors path="name" element="div" cssStyle="color:red" />
			<form:input path="name" />
			<input type="submit" value="検索する">
			<br>
		</form:form>
		<br>
	
		<c:choose>
			<c:when test="${itemList.size()==0}">
				<p align="center">商品がありません</p>
			</c:when>

			<c:otherwise>
		
				<table border="1" align="center"  >
					<tr>
						<th colspan="2" width="150">商品名</th>
						<th width="150">価格</th>
					</tr>

					<c:forEach var="item" items="${itemList}">
						<tr>
							<td><img
								src="${pageContext.request.contextPath}/img/${item.imagePath}"
								width="150" height="125"></td>
							<td><a
								href="${pageContext.request.contextPath}/user/viewDetail?id=${item.id}">
								<c:out value="${item.name}"/>
								</a></td>
							<td><fmt:formatNumber value="${item.price}" pattern="￥###,###" />
						</tr>
					</c:forEach>

				</table>
				
			</c:otherwise>

		</c:choose>
		<br><br>
				<c:choose>
			<c:when test="${itemListTop5Count==null}">
				<p align="center"></p>
			</c:when>

			<c:otherwise>
			観覧数ランキングTOP5
				<table border="1" align="center">
					<tr>
						<th colspan="2" width="150">商品名</th>
						<th width="150">価格</th>
					</tr>

					<c:forEach var="itemTop5Count" items="${itemListTop5Count}">
						<tr>
							<td><img
								src="${pageContext.request.contextPath}/img/${itemTop5Count.imagePath}"
								width="150" height="125"></td>
							<td><a
								href="${pageContext.request.contextPath}/user/viewDetail?id=${itemTop5Count.id}">
								<c:out value="${itemTop5Count.name}"/>
								</a></td>
							<td><fmt:formatNumber value="${itemTop5Count.price}" pattern="￥###,###" />
						</tr>
					</c:forEach>

				</table>
			</c:otherwise>

		</c:choose>
	</div>
</body>
</html>