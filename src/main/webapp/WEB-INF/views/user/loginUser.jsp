<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザログイン</title>
</head>
<body>
	<div align="center">
	<h2>ログイン</h2>
	
	<form:form modelAttribute="userLoginForm" action="${pageContext.request.contextPath}/user/login">
		メールアドレス：<form:input path="mailAddress"/><br>
		パスワード：<form:input path="password"/><br>
		<input type="submit" value="ログイン"><br>
	</form:form>
	
	<a href="ユーザ登録を行うコントローラ">新規登録はこちら</a>
	</div>
</body>
</html>