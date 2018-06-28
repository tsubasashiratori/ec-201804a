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
	<h2>新規利用者登録画面</h2>
	<br>
	<br> お客様の情報を入力し、「お客様情報を登録する」ボタンをクリックしてください。
	<br>
	<br>

<form:errors path="UserForm.*" />
	<form:form modelAttribute="registerUserForm" action="${pageContext.request.contextPath}/user/register" method="post">
		<br>
		
		<table class="table-striped" style=WIDTH:600px>
			<tr >
				<td class="p-3"><p><small><form:errors path="name" cssStyle="color:red" /></small></p>
				名前<br>
				<font color="grey"><small>例：山田太郎</small></font></td>
				<td><form:input path="name" class="form-control register"/></td>

			</tr>
			<tr>
				<td><p><small><form:errors path="email" cssStyle="color:red"/></small></p>
				メールアドレス<br>
				<font color="grey"><small>例：abc@abc.co.jp</small></font></td>
				<td><form:input path="email" class="form-control register"/></td>

			</tr>
			<tr>
				<td><p><small><form:errors path="zipCode" cssStyle="color:red"/></small></p>
				郵便番号<br>
				<font color="grey"><small>例：111-1111</small></font></td>
				<td>〒<form:input path="zipCode" size="8" maxlength="8" onKeyUp="AjaxZip3.zip2addr(this,'','address','address');" class="form-control register"/></td>
			</tr>
			<tr>
				<td><p><small><form:errors path="address" cssStyle="color:red"/></small></p>
				住所<br>
				<font color="grey"><small>例：東京都新宿1-2-3</small></font></td>
				<td><form:input path="address" class="form-control register"/></td>

			</tr>
			<tr>
				<td><p><small><form:errors path="telHead" cssStyle="color:red"/></small></p>
				電話番号<br>
				<font color="grey"><small>例：080-1234-5678</small></font>
				</td>
				<td>
				<table>
				<tr>
				<td><form:input path="telHead" size="4" maxlength="4" class="form-control register2"/></td>
				<td>&nbsp;-&nbsp; </td>
				<td><form:input path="telBody" size="4" maxlength="4" class="form-control register2"/></td>
				<td> &nbsp;- &nbsp;</td>
				<td><form:input path="telTeil" size="4" maxlength="4" class="form-control register2"/></td>
				</tr>
				</table>
				</td>

			</tr>
			<tr>
				<td><p><small><form:errors path="password" cssStyle="color:red"/></small></p>
				パスワード<br>
				<font color="grey"><small>※8桁以上16桁以下で設定してください</small></font></td>
				<td><form:password path="password" class="form-control register"/></td>

			</tr>
			<tr>
				<td><p><small><form:errors path="checkPassword" cssStyle="color:red"/></small></p>
				確認用パスワード<br>
				<font color="grey"><small>※設定したパスワードを再度入力してください</small></font>
				<small></small>
				</td>
				<td><form:password path="checkPassword" class="form-control register"/><small></small></td>

			</tr>
		</table>

		<br> <br><input type="submit" value="お客様情報を登録する" class="btn btn-default"><br><br>
	</form:form>
	<form action="${pageContext.request.contextPath}/user/form"  onsubmit="return confirm('入力内容を削除してもよろしいですか？')">
	<input type="submit" value="入力内容をクリアする" class="btn btn-default">
	</form>
<br><br>
</div>
<%@ include file="../common/userFooterForPresentation.jsp" %>

</body>
</html>