<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/adminHeader.css" />
</head>
<body>
	<header>
		<div id="userHeader" align="right">
			<c:choose>
				<c:when test="${adminUser==null}">
					<p>こんにちは管理者さん</p>	
					<p><a href="${pageContext.request.contextPath}/admin/">ログイン</a></p>
				</c:when>
				<c:otherwise>
					<p>こんにちは<c:out value="${adminUser.name}"/>さん</p>
					<p><a href="${pageContext.request.contextPath}/admin/logout">ログアウト</a></p>
				</c:otherwise>	
			</c:choose>
		</div>
		<div id="linkHeader" align="left">
			<h1 align="left">
				<a href="../user/itemList.html"><img src="../img/rakus.jpg"
					width="50" height="50" alt="ロゴ画像">ＥＣサイトラクス</a>
			</h1>
			<div id="title" align="center"></div>
		</div>
	</header>