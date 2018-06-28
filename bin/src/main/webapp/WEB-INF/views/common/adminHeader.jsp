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
<!-- jQuery読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- BootstrapのJS読み込み -->
 <link href="${pageContext.request.contextPath}/css2/bootstrap.css"
	rel="stylesheet"> 
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<head>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/adminHeader.css" />
	
	<!-- ------------------------------------------------------------------------------- -->
<head>
</head>
<body>
	<sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
	<sec:authentication var="adminUser" property="principal.user" />
	</sec:authorize>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<!--  logoimg -->
				<a class="navbar-brand" href="${pageContext.request.contextPath}
					/admin/menu">
					<img src="${pageContext.request.contextPath}/img/rakus.jpg" 
					id="logo-img">
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
					<c:choose>
						<c:when test="${adminUser==null}">
							<ul class="nav navbar-nav navbar-right">
								<p class="navbar-text">
									管理者メニューを表示するにはログインしてください
								</p>
								<li>
									<a href="${pageContext.request.contextPath}/admin/">
										<span class="glyphicon glyphicon-log-in"></span>
										ログイン
									</a>
								</li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul class="nav navbar-nav">
								<li>
									<a href="${pageContext.request.contextPath}/admin/insertItem">
										商品登録
									</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/admin/adminFindAll">
										商品一覧
									</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/admin/viewOrderList">
										注文一覧
									</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/admin/form">
										管理者登録
									</a>
								</li>
							</ul>
				
							<!-- right navbar -->
							<ul class="nav navbar-nav navbar-right">
								<p class="navbar-text">
									こんにちは
									<c:out value="${adminUser.name}" />
									さん
								</p>
								<li>
									<a href="${pageContext.request.contextPath}/admin/logout">
										<span class="glyphicon glyphicon-log-out"></span>
										ログアウト
									</a>
								</li>
							</ul>
						</c:otherwise>
					</c:choose>				
			</div>
		</div>
		<!-- end container -->
	</nav>
<!-- end nav -->
</body>
</html>
	
	
				<sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
				<sec:authentication var="adminUser" property="principal.user" />
			</sec:authorize>