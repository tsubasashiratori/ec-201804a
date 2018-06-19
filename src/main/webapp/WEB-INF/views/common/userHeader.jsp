<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ecHeader.css" />
</head>
<body>
	<header>
		<div id="userHeader" align="right">
			<p>
			<c:choose>
				<c:when test="${user==null}">
					こんにちはゲストさん	
				</c:when>
				<c:otherwise>
					こんにちは<c:out value="${user.name}"/>さん
				</c:otherwise>	
			</c:choose>
			</p>
			<p>
				<a href="${pageContext.request.contextPath}/user/">ログイン</a>
			</p>
			<p>
				<a href="viewShoppingCart.html">カートの中身を見る</a>
			</p>
		</div>
		<div id="linkHeader" align="left">
			<h1 align="left">
				<a href="itemList.html"><img
					src="${pageContext.request.contextPath}/img/rakus.jpg" width="50"
					height="50" alt="ロゴ画像">ＥＣサイトラクス</a>
			</h1>
		</div>
		<div id="title" align="center"></div>
	</header>
</body>