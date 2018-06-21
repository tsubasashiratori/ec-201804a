<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ecHeader.css" />
</head>
<body>
	<header>
		<div id="userHeader" align="right">
			<sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
				<sec:authentication var="user" property="principal.user" />
			</sec:authorize>
			<c:choose>
				<c:when test="${user==null}">
					<p>こんにちはゲストさん</p>
					<p>
						<a href="${pageContext.request.contextPath}/user/">ログイン</a>
					</p>
				</c:when>
				<c:otherwise>
					<p>
						こんにちは
						<c:out value="${user.name}" />
						さん
					</p>
					<p>
						<a href="${pageContext.request.contextPath}/user/logout">ログアウト</a>
					</p>
				</c:otherwise>
			</c:choose>
			<p>
				<a href="${pageContext.request.contextPath}/user/toViewShoppingCart">カートの中身を見る</a>
			</p>
		</div>
		<div id="linkHeader" align="left">
			<h1 align="left">
				<a
					href="${pageContext.request.contextPath}/user/ViewAllItemsAndSearchItem/findAllNotDeleted"><img
					src="${pageContext.request.contextPath}/img/rakus.jpg" width="50"
					height="50" alt="ロゴ画像">ＥＣサイトラクス</a>
			</h1>
		</div>
		<div id="title" align="center"></div>
	</header>
</body>