<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
	
	<!-- ------------------------------------------------------------------------------- -->
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-material-button-color-master/dist/cb-bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-material-button-color-master/dist/cb-materialbtn.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css2/arange.css">
<meta http-equiv="content-type" charset="utf-8">
<title>ユーザログイン</title>
<!-- jQuery読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- BootstrapのCSS読み込み -->
<link href="${pageContext.request.contextPath}/css2/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- BootstrapのJS読み込み -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

	
	<!-- ------------------------------------------------------------------------------- -->
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