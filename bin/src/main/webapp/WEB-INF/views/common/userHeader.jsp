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
<!-- jQuery読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- BootstrapのCSS読み込み -->
<link href="${pageContext.request.contextPath}/css2/bootstrap.min.css"
	rel="stylesheet"> 
	 <link href="${pageContext.request.contextPath}/css2/bootstrap.css"
	rel="stylesheet"> 
<!-- jQuery読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- BootstrapのJS読み込み -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/userHeader.css" />
	
	<!-- ------------------------------------------------------------------------------- -->
<head>
</head>
<body>
	<sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
				<sec:authentication var="user" property="principal.user" />
			</sec:authorize>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<!--  logoimg -->
				<a class="navbar-brand" href="${pageContext.request.contextPath}/user/">
				</a>
				
				<!-- toggle -->
				<button type="button" class="navbar-toggle" 
				data-toggle="collapse" data-target="#top-nav">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
			
			<!-- top menu -->
			<div class="collapse navbar-collapse" id="top-nav">
				
				<!-- main navbar -->
				<ul class="nav navbar-nav">
					<li>
						<a href="${pageContext.request.contextPath}/user/">
							トップページ
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/user/">
							商品一覧
						</a>
					</li>
				</ul>
				
				<!-- right navbar -->
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${user==null}">
							<p class="navbar-text">
								こんにちはゲストさん
							</p>
							<li>
								<a href="${pageContext.request.contextPath}/user/toViewShoppingCart">
									<span class="glyphicon glyphicon-shopping-cart"></span>
									ショッピングカートを見る
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/user/loginForm">
									<span class="glyphicon glyphicon-log-in"></span>
									ログイン
								</a>
							</li>
						</c:when>
						<c:otherwise>
							<p class="navbar-text">
								こんにちは
								<c:out value="${user.name}" />
								さん
							</p>
							<li>
								<a href="${pageContext.request.contextPath}/user/toViewShoppingCart">
									<span class="glyphicon glyphicon-shopping-cart"></span>
									ショッピングカートを見る
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/user/myPage">
									<span class="glyphicon glyphicon-user"></span>
									マイページ
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/user/logout">
									<span class="glyphicon glyphicon-log-out"></span>
									ログアウト
								</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
				
			</div>
		</div>
		<!-- end container -->
	</nav>
<!-- end nav -->
</body>
</html>