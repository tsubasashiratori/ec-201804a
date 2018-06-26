<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../common/userHeader.jsp"%>
<%@ page import="java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">


<title>商品一覧</title>
</head>
<body>

	<script>
		$(window).on('load resize', function(){
	    // navbarの高さを取得する
	    	var height = $('.navbar').height();
	    // bodyのpaddingにnavbarんぼ高さを設定する
	    	$('body').css('padding-top',height); 
		});
	</script>


<div class="bg2">

	<div align="center">
		<h2>商品一覧</h2>

		<form
			action="${pageContext.request.contextPath}/user/ViewAllItemsAndSearchItem/findAllNotDeleted"
			method="get">
			
			<input type="submit" value="全件表示" >
			
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
		
				<table  class="table-striped" style=WIDTH:800px >
					<tr>
						<th width="150">画像</th>
						<th width="150">商品名</th>
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
				<table border="1" align="center"  class="table table-bordered" style=WIDTH:800px >
					<tr>
						<th align="center">順位</th>
						<th width="150" align="center">画像</th>
						<th width="150" align="center">商品名</th>
						<th width="150" align="center">価格</th>
					</tr>
					
					<c:forEach var="itemTop5Count" items="${itemListTop5Count}"  varStatus="status">
						<tr>
							<td style=WIDTH:50px><c:out value="${status.count}"/></td>
							<td style=WIDTH:150px><img
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
	</div>
</body>
</html>