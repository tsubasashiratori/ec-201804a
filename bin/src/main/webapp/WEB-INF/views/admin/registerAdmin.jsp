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
	<script>
		$(window).on('load resize', function(){
	    // navbarの高さを取得する
	    	var height = $('.navbar').height();
	    // bodyのpaddingにnavbarんぼ高さを設定する
	    	$('body').css('padding-top',height); 
		});
	</script>
	
<div align="center">
	<h1>新規管理者登録画面</h1>
	<br>
	<br>情報を入力し、「管理者情報を登録する」ボタンをクリックしてください。
	<br>
	<br>

<form:errors path="AdminUserForm.*" />
	<form:form modelAttribute="registerAdminForm" action="${pageContext.request.contextPath}/admin/register" 
	method="post">
		<br>
		<div class="table-responsive">
		<table class="table" style=WIDTH:600px >
			<tr>
				<td><p><small><form:errors path="name" cssStyle="color:red" /></small></p>
				名前</td>
				<td><form:input path="name" class="form-control"/></td>

			</tr>
			<tr>
				<td><p><small><form:errors path="email" cssStyle="color:red"/></small></p>
				メールアドレス</td>
				<td><form:input path="email" class="form-control"/></td>

			</tr>
			<tr>
				<td><p><small><form:errors path="password" cssStyle="color:red"/></small></p>
				パスワード<br>
				<font color="grey"><small>※8桁以上16桁以下で設定してください</small></font></td>
				<td><form:password path="password" class="form-control"/></td>

			</tr>
			<tr>
				<td><p><small><form:errors path="checkPassword" cssStyle="color:red"/></small></p>
				確認用パスワード<br>
				<font color="grey"><small>※設定したパスワードを再度入力してください</small></font>
				</td>
				<td><form:password path="checkPassword" class="form-control"/></td>

			</tr>
		</table>
		</div>

		<br> <br><input type="submit" value="管理者情報を登録する" class="form-control" style=WIDTH:200px><br><br>
	</form:form>
	<form action="${pageContext.request.contextPath}/admin/form" onsubmit="return confirm('入力内容を削除してもよろしいですか？')">
	<input type="submit" value="入力内容をクリアする" class="form-control" style=WIDTH:200px>
	</form>
</div>
</body>
</html>