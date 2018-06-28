<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>マイページ</title>
</head>
<%@ include file="../common/userHeader.jsp"%>

<body>
	<script>
		$(window).on('load resize', function() {
			// navbarの高さを取得する
			var height = $('.navbar').height();
			// bodyのpaddingにnavbarんぼ高さを設定する
			$('body').css('padding-top', height);
		});
	</script>
	<br>
	<h2 align="center">マイページ</h2>
	<div align="center">
	<br>
		<a href="${pageContext.request.contextPath}/user/updateForm"><span
			class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;ユーザ登録情報を変更する
			
		</a><br>
		<br> <a
			href="${pageContext.request.contextPath}/user/orderLogList"> <span
			class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;
			注文履歴を確認する</a><br>
		<br> <a href="${pageContext.request.contextPath}/user/"> <span
			class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;
			トップページに戻る</a>
	</div>
	<br>
	<h2 align="center">アカウント情報</h2>
	<br>
	<div align="center">
		<strong><c:out value="${user.name}"></c:out></strong>&nbsp;さんのアカウント情報
	</div>
	<br>
	<table border="1" align="center" class="table table-hover"
		style="WIDTH: 800px">
		<tr>
			<td align="center" valign="top">名前</td>
			<td align="center" valign="top"><c:out value="${user.name}" /></td>
		</tr>
		<tr>
			<td align="center" valign="top">メールアドレス</td>
			<td align="center" valign="top"><c:out value="${user.email}" /></td>
		</tr>
		<tr>
			<td align="center" valign="top">郵便番号</td>
			<td align="center" valign="top"><c:out value="${user.zipCode}" /></td>
		</tr>
		<tr>
			<td align="center" valign="top">住所</td>
			<td align="center" valign="top"><c:out value="${user.address}" /></td>
		</tr>
		<tr>
			<td align="center" valign="top">電話番号</td>
			<td align="center" valign="top"><c:out value="${user.telephone}" /></td>
		</tr>
	</table>

	<%@ include file="../common/userFooterForPresentation.jsp"%>
</body>
</html>