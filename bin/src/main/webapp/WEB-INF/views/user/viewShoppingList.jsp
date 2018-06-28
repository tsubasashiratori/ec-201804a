<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../common/userHeader.jsp"%>
<%@ page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">


<title>商品一覧</title>
</head>
<body>
	<script>
		$(window).on('load resize', function() {
			// navbarの高さを取得する
			var height = $('.navbar').height();
			// bodyのpaddingにnavbarんぼ高さを設定する
			$('body').css('padding-top', height);
		});
	</script>




	<div align="center">
		<h2>商品一覧</h2>


		<form action="${pageContext.request.contextPath}/user/" method="get">
			<div>
				<br> <input type="submit" value="全件表示" class="btn btn-default">
			</div>
		</form>
		<form:form modelAttribute="viewAllItemsAndSearchItemForm"
			action="${pageContext.request.contextPath}/user/findByNameNotDeleted"
			method="post" align="center">
			<form:errors path="name" element="div" cssStyle="color:red" />


			<table align="center">
				<tr>
					<td><form:input path="name" class="form-control search" /></td>
					<td><input type="submit" value="検索する"
						class="btn btn-default submit"></td>
			</table>
		</form:form>
		<br>
				<br> 
		<hr>
		<c:choose>
			<c:when test="${Item==true}">

				<c:if test="${pageNum>1}">
					<a
						href="${pageContext.request.contextPath}/user/findAllNotDeletedByPageNum?pageNum=${pageNum-1}">
						前へ </a>
				</c:if>
				<c:if test="${page>=1}">
				<c:forEach begin="0" end="${page-1}" step="1" varStatus="status">
					<c:choose>
						<c:when test="${status.index+1 != pageNum }">
							<a
								href="${pageContext.request.contextPath}/user/findAllNotDeletedByPageNum?pageNum=${status.index+1}">
								<c:out value="${status.index+1}" />
							</a>
						</c:when>
						<c:otherwise>
							<c:out value="${status.index+1}" />
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</c:if>
				<c:if test="${pageNum<page}">
					<a
						href="${pageContext.request.contextPath}/user/findAllNotDeletedByPageNum?pageNum=${pageNum+1}">
						次へ </a>
				</c:if>
			</c:when>
			<c:when test="${searchItem==true}">

				<c:if test="${pageNum>1}">
					<a
						href="${pageContext.request.contextPath}/user/findByNameNotDeletedPageNum?pageNum=${pageNum-1}&name=${name}">
						前へ </a>
				</c:if>
				<c:if test="${page>=1}">
					<c:forEach begin="0" end="${page-1}" step="1" varStatus="status">
						<c:choose>
							<c:when test="${status.index+1 != pageNum }">
								<a
									href="${pageContext.request.contextPath}/user/findByNameNotDeletedPageNum?pageNum=${status.index+1}&name=${name}">
									<c:out value="${status.index+1}" />
								</a>
							</c:when>
							<c:otherwise>
								<c:out value="${status.index+1}" />
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
				<c:if test="${pageNum<page}">
					<a
						href="${pageContext.request.contextPath}/user/findByNameNotDeletedPageNum?pageNum=${pageNum+1}&name=${name}">
						次へ </a>
				</c:if>
			</c:when>
		</c:choose>
		<br> <br>
		

		<c:choose>
			<c:when test="${itemList.size()==0}">
				<p align="center">商品がありません</p>
			</c:when>
			<c:when test="${notDisplay==true}">
				<p align="center">商品がありません</p>
			</c:when>
			<c:otherwise>

				<table class="table-striped view" style="WIDTH: 800px">
					<tr>
						<th width="150"><font size="5" >画像</font></th>
						<th width="150"><font size="5">商品名</font></th>
						<th width="150"><font size="5">価格</font><th>
					</tr>

					<c:forEach var="item" items="${itemList}">

						<tr>
							<td><img
								src="${pageContext.request.contextPath}/img/${item.imagePath}"

								width="80" height="200" ></td>
							<td style=WIDTH:360px align="center"><a
								href="${pageContext.request.contextPath}/user/viewDetail?id=${item.id}">
								<font size="4"><c:out value="${item.name}"/></font>
								</a></td>

								<td style=WIDTH:360px align="center"><font size="4"><fmt:formatNumber value="${item.price}"
										pattern="￥###,###" /></font></td>
							</tr>
						</c:forEach>
</table>

			</c:otherwise>

		</c:choose>

		<br><br>

		<hr>
		<c:choose>
			<c:when test="${Item==true}">

				<c:if test="${pageNum>1}">
					<a
						href="${pageContext.request.contextPath}/user/findAllNotDeletedByPageNum?pageNum=${pageNum-1}">
						前へ </a>
				</c:if>
					<c:if test="${page>=1}">
				<c:forEach begin="0" end="${page-1}" step="1" varStatus="status">
					<c:choose>
						<c:when test="${status.index+1 != pageNum }">
							<a
								href="${pageContext.request.contextPath}/user/findAllNotDeletedByPageNum?pageNum=${status.index+1}">
								<c:out value="${status.index+1}" />
							</a>
						</c:when>
						<c:otherwise>
							<c:out value="${status.index+1}" />
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</c:if>
				<c:if test="${pageNum<page}">
					<a
						href="${pageContext.request.contextPath}/user/findAllNotDeletedByPageNum?pageNum=${pageNum+1}">
						次へ </a>
				</c:if>
			</c:when>
			<c:when test="${searchItem==true}">

				<c:if test="${pageNum>1}">
					<a
						href="${pageContext.request.contextPath}/user/findByNameNotDeletedPageNum?pageNum=${pageNum-1}&name=${name}">
						前へ </a>
				</c:if>
				<c:if test="${page>=1}">
					<c:forEach begin="0" end="${page-1}" step="1" varStatus="status">
						<c:choose>
							<c:when test="${status.index+1 != pageNum }">
								<a
									href="${pageContext.request.contextPath}/user/findByNameNotDeletedPageNum?pageNum=${status.index+1}&name=${name}">
									<c:out value="${status.index+1}" />
								</a>
							</c:when>
							<c:otherwise>
								<c:out value="${status.index+1}" />
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
				<c:if test="${pageNum<page}">
					<a
						href="${pageContext.request.contextPath}/user/findByNameNotDeletedPageNum?pageNum=${pageNum+1}&name=${name}">
						次へ </a>
				</c:if>
			</c:when>
		</c:choose>
		<br> <br>



		<c:choose>
			<c:when test="${itemListTop5Count==null}">
				<p align="center"></p>
			</c:when>

			<c:otherwise>
				<h3>観覧数ランキングTOP5</h3>
				<br>
				<table border="1" align="center" class="table table-striped lank"
					style="WIDTH: 800px">
					<tr>
						<th align="center">順位</th>
						<th width="150" align="center">画像</th>
						<th width="150" align="center">商品名</th>
						<th width="150" align="center">価格</th>
					</tr>

					<c:forEach var="itemTop5Count" items="${itemListTop5Count}"
						varStatus="status">
						<tr>
							<td style="WIDTH: 50px"><c:out value="${status.count}" /></td>
							<td style="WIDTH: 150px"><img
								src="${pageContext.request.contextPath}/img/${itemTop5Count.imagePath}"
								width="75" height="200"></td>
							<td><a
								href="${pageContext.request.contextPath}/user/viewDetail?id=${itemTop5Count.id}">
									<c:out value="${itemTop5Count.name}" />
							</a></td>
							<td><fmt:formatNumber value="${itemTop5Count.price}"
									pattern="￥###,###" />
						</tr>
					</c:forEach>

				</table>
			</c:otherwise>

		</c:choose>



	</div>
	<%@ include file="../common/userFooterForPresentation.jsp"%>

</body>
</html>