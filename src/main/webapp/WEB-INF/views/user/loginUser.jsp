<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<%@ include file="../common/userHeader.jsp" %>
<title>ユーザログイン</title>
</head>
<body>
	<script>
		$(window).on('load resize', function(){
	    // navbarの高さを取得する
	    	var height = $('.navbar').height();
	    // bodyのpaddingにnavbarんぼ高さを設定する
	    	$('body').css('padding-top',height); 
		});
	</script>
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