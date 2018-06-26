<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/topMenu.css" />
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<!--  logoimg -->
				<a class="navbar-brand" href="${pageContext.request.contextPath}
					/user/ViewAllItemsAndSearchItem/findAllNotDeleted">
					<img src="${pageContext.request.contextPath}/img/rakus.jpg" 
					 alt="ロゴ画像" id="logo-img"><span class="text-danger">ＥＣサイトラクス</span>ＥＣサイトラクス
				</a>
				
				<!-- toggle -->
				<button type="button" class="navbar-toggle" 
				data-toggle="collapse" data-target="top-nav">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
			
			<!-- top menu -->
			<div class="collapse navbar-collapse" id="top-nav">
				
				<!-- main navbar -->
				<ul class="navbar-nav">
					<li>
						<a href="${pageContext.request.contextPath}/user/ViewAllItemsAndSearchItem/findAllNotDeleted">
							トップページ
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/user/ViewAllItemsAndSearchItem/findAllNotDeleted">
							商品一覧
						</a>
					</li>
				</ul>
				
				<!-- right navbar -->
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${user==null}">
							<p>こんにちはゲストさん</p>
							<li>
								<a href="${pageContext.request.contextPath}/user/"><span class="glyphicon-log-in">ログイン</span></a>
							</li>
						</c:when>
						<c:otherwise>
							<p>
								こんにちは
								<c:out value="${user.name}" />
								さん
							</p>
							<li>
								<a href="${pageContext.request.contextPath}/user/logout"><span class="glyphicon-log-out">ログアウト</span></a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
				
			</div>
		</div>
		<!-- end container -->
	</nav>
<!-- end nav -->

<!-- jQuery読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- BootstrapのJS読み込み -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>