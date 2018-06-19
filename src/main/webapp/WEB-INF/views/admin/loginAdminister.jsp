<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>管理者ログイン</title>
</head>
<%@ include file="../common/userHeader.jsp"%>
<body>
	<div align="center">
		<h2>ログイン</h2>

		<form:form modelAttribute="adminUserLoginForm"
			action="${pageContext.request.contextPath}/admin/login">
		メールアドレス：<form:input path="email" />
			<br>
		パスワード：<form:input path="password" />
			<br>
			<input type="submit" value="ログイン">
			<br>
		</form:form>
	</div>
</body>
</html>