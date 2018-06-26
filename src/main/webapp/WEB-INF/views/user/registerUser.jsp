<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>利用者登録画面</title>
</head>
<%@ include file="../common/userHeader.jsp" %>
<body>
	<script>
		$(window).on('load resize', function(){
	    // navbarの高さを取得する
	    	var height = $('.navbar').height();
	    // bodyのpaddingにnavbarんぼ高さを設定する
	    	$('body').css('padding-top',height); 
		});
	</script>
	
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<div align="center">
	<h1>新規利用者登録画面</h1>
	<br>
	<br> お客様の情報を入力し、「お客様情報を登録する」ボタンをクリックしてください。
	<br>
	<br>

<form:errors path="UserForm.*" />
	<form:form modelAttribute="registerUserForm" action="${pageContext.request.contextPath}/user/register" method="post">
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
				<td><p><small><form:errors path="zipCode" cssStyle="color:red"/></small></p>
				郵便番号</td>
				<td>〒<form:input path="zipCode" size="8" maxlength="8" onKeyUp="AjaxZip3.zip2addr(this,'','address','address');"/></td>
			</tr>
			<tr>
				<td><p><small><form:errors path="address" cssStyle="color:red"/></small></p>
				住所</td>
				<td><form:input path="address"/></td>

			</tr>
			<tr>
				<td><p><small><form:errors path="telHead" cssStyle="color:red"/></small></p>
				電話番号
				</td>
				<td><form:input path="telHead" size="4" maxlength="4"/>
				- <form:input path="telBody" size="4" maxlength="4"/>
				- <form:input path="telTeil" size="4" maxlength="4"/>
				</td>

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

		<br> <br><input type="submit" value="お客様情報を登録する">   <input type="reset" value="入力内容をクリアする">
	</form:form>
	
</div>
</body>
</html>