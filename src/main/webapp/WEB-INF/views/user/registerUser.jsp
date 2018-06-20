<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>利用者登録画面</title>
</head>
<body>
<header>
	<div id="userHeader" align="right">
		<p>こんにちはゲストさん</p>
		<p><a href="userLogin.html">ログイン</a></p>
		<p><a href="viewShoppingCart.html">カートの中身を見る</a></p>
	</div>
	<div id="linkHeader" align="left"></div>
		<h1 align ="left"><a href="itemList.html"><img src="../img/rakus.jpg" width="50"
			height="50" alt="ロゴ画像">ＥＣサイトラクス</a></h1>
		<div id="title" align="center">
	</div>
</header>
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<div align="center">
	<h1>新規利用者登録画面</h1>
	<br>
	<br> お客様の情報を入力し、「お客様情報を登録する」ボタンをクリックしてください。
	<br>
	<br>

<form:errors path="UserForm.*" />
	<form:form modelAttribute="registerUserForm" action="${pageContext.request.contextPath}/regist/registerUser" method="post">
		<br>
		<table border="">
			<tr>
				<td><p><form:errors path="name" cssStyle="color:red" /></p>
				名前</td>
				<td><form:input path="name" /></td>

			</tr>
			<tr>
				<td><p><form:errors path="email" cssStyle="color:red"/></p>
				<p id="mailId2" style="display: none; color: red;">アドレスが不正です</p>
				メールアドレス</td>
				<td><form:input path="email"/></td>

			</tr>
			<tr>
				<td><p><form:errors path="zipCode" cssStyle="color:red"/></p>
				郵便番号</td>
				<td><form:input path="zipCode" onKeyUp="AjaxZip3.zip2addr(this,'','address','address');"/></td>
			</tr>
			<tr>
				<td><p><form:errors path="address" cssStyle="color:red"/></p>
				住所</td>
				<td><form:input path="address"/></td>

			</tr>
			<tr>
				<td><p><form:errors path="telephone" cssStyle="color:red"/></p>
				電話番号
				</td>
				<td><form:input path="telephone"/></td>

			</tr>
			<tr>
				<td><p><form:errors path="password" cssStyle="color:red"/></p>
				パスワード<br>
				<font color="red"><small>8桁以上16桁以下で設定してください</small></font></td>
				<td><form:password path="password"/></td>

			</tr>
			<tr>
				<td><p><form:errors path="checkPassword" cssStyle="color:red"/></p>
				確認用パスワード<br>
				<font color="red"><small>設定したパスワードを再度入力してください</small></font>
				</td>
				<td><form:password path="checkPassword"/></td>

			</tr>
		</table>

		<br> <br><input type="submit" value="お客様情報を登録する">   <input type="reset" value="入力内容をクリアする">
	</form:form>
	
</div>
</body>
</html>