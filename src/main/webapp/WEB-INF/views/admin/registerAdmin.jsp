<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>新規管理者登録画面</title>
</head>
<%@ include file="../common/adminHeader.jsp" %>
<body>
<div align="center">
	<h1>新規管理者登録画面</h1>
	<br>
	<br>情報を入力し、「管理者情報を登録する」ボタンをクリックしてください。
	<br>
	<br>

<form:errors path="AdminForm.*" />
	<form:form modelAttribute="registerAdminForm" action="${pageContext.request.contextPath}/registAdmin/register" method="post">
		<br>
		<table border="">
			<tr>
				<td><p><small><form:errors path="name" cssStyle="color:red" /></small></p>
				名前</td>
				<td><form:input path="name" /></td>

			</tr>
			<tr>
				<td><p><small><form:errors path="email" cssStyle="color:red"/></small></p>
				メールアドレス</td>
				<td><form:input path="email"/></td>

			</tr>
			<tr>
				<td><p><small><form:errors path="password" cssStyle="color:red"/></small></p>
				パスワード<br>
				<font color="grey"><small>※8桁以上16桁以下で設定してください</small></font></td>
				<td><form:password path="password"/></td>

			</tr>
			<tr>
				<td><p><small><form:errors path="checkPassword" cssStyle="color:red"/></small></p>
				確認用パスワード<br>
				<font color="grey"><small>※設定したパスワードを再度入力してください</small></font>
				</td>
				<td><form:password path="checkPassword"/></td>

			</tr>
		</table>

		<br> <br><input type="submit" value="管理者情報を登録する">   <input type="reset" value="入力内容をクリアする">
	</form:form>
</div>
</body>
</html>