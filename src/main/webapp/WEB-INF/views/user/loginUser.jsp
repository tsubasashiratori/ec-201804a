<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-material-button-color-master/dist/cb-bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-material-button-color-master/dist/cb-materialbtn.min.css">
<!--<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css2/arange.css">-->
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


<meta charset="UTF-8">
<title>ユーザログイン</title>
</head>
<%@ include file="../common/userHeader.jsp" %>
<body>
<div class ="bg">
<div class="whole">
	<div align="center">
	<h2  style="color:#ffffff;">ログイン</h2><br>
	<span class="text-danger"><form:errors path="userLoginForm.*"/></span>
	
	
	<form:form class="form-inline" modelAttribute="userLoginForm" action="${pageContext.request.contextPath}/user/login">
	
	<table>
	<tr><td><div class="div"><p class="message">メールアドレス：</p></div></td><td><form:input class="form-control" id="InputEmail" path="email" placeholder="メール・アドレス"/></td></tr>
	<tr><td><p class="message">パスワード：</p></td><td><form:password class="form-control" path="password" placeholder="パスワード"/></td></tr>
	</table>
		<!-- 丸みのあるボタン -->
		<br>
				<div class="col-lg-12 text-center">
					<input type="submit" class="btn_circle_arange" value="ログイン"><br>
				</div>
				<br>
	</form:form>
	<div class="word" >
	<p class="message">-------- Energy de cafeの新しいお客様ですか？--------</p>
	<form action="${pageContext.request.contextPath}/user/form" method="get">
	<input type="submit" value="新規登録はこちら"  style=WIDTH:300px>
	</form>
	</div>
	</div>
</div>
</div>

</body>
</html>