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
	<script>
		$(window).on('load resize', function(){
	    // navbarの高さを取得する
	    	var height = $('.navbar').height();
	    // bodyのpaddingにnavbarんぼ高さを設定する
	    	$('body').css('padding-top',height); 
		});
	</script>
	
<div align="center">
	<h2>商品一覧</h2>
<c:if test="${success!=null}"><c:out value="${success}"/></c:if>
<c:if test="${delete!=null}"><c:out value="${delete}"/></c:if>
<c:if test="${redisplay!=null}"><c:out value="${redisplay}"/></c:if>
</div>

<br>
<form action="${pageContext.request.contextPath}/admin/adminFindAll" method="get" align="center">
	
	<input type="submit" value="全件表示" align="center" class="btn btn-default">
</form><br>
<form:form modelAttribute="adminViewAllAndSearchItemForm" action="${pageContext.request.contextPath}/admin/adminFindByName" method="post" align="center">
	<form:errors path="name" element="div" cssStyle="color:red" align="center"/>
	<table align="center">
	<tr><td><div style=WIDTH:200px>
	<form:input path="name" class="form-control" />
	</div></td><td>
	<input type="submit" value="検索する" class="btn btn-default">
	</td></tr></table>
</form:form>

<br>
	<c:choose>
		<c:when test="${itemList==null}">
			<p align="center">商品がありません</p>
		</c:when>
		<c:otherwise>

			<table border="1" align="center" class="table itemList" style=WIDTH:800px>

				<tr>
					<th colspan="2" width="300">商品名</th>
					<th width="100">価格</th>
					<th width="300">商品説明</th>
					<th width="75">操作</th>
				</tr>
				<c:forEach var="item" items="${itemList}">
					<tr>
						<td><img
							src="${pageContext.request.contextPath}/img/${item.imagePath}"
							width="75" height="200" /></td>
						<td width="100"><c:out value="${item.name}" /></td>
						<td><fmt:formatNumber value="${item.price}"
								pattern="￥###,###" /></td>
						<td><c:out value="${item.description}"></c:out></td>
						<td><form
								action="${pageContext.request.contextPath}/admin/viewEditItem"
								method="get" align="center">
								<button type="submit" name="itemId" value="${item.id}" class="btn btn-default">編　集</button>
							</form> <br> <c:choose>
								<c:when test="${item.deleted}">
									<form
										action="${pageContext.request.contextPath}/admin/deleteItem"
										method="get" align="center">
										<button type="submit" name="id" value="${item.id}" class="btn btn-default">再表示</button>
									</form>
								</c:when>
								<c:otherwise>
									<form
										action="${pageContext.request.contextPath}/admin/deleteItem"
										method="get" align="center">
										<button type="submit" name="id" value="${item.id}" class="btn btn-default">削　除</button>
									</form>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	
</body>
</html>