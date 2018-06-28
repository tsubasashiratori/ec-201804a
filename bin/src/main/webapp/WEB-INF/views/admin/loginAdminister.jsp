<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>管理者ログイン</title>
</head>
<%@ include file="../common/adminHeader.jsp"%>
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
		<h2>ログイン</h2>

		<form:errors path="adminUserLoginForm.*" cssStyle="color:red" element="div"/>
		<form:form modelAttribute="adminUserLoginForm"
			action="${pageContext.request.contextPath}/admin/login">
			
		<table>
		<tr><p>テストログイン用メールアドレスは「admin@rakus.co.jp」です。</p></tr>
		<tr><p>テストログイン用パスワードは「rakus2018」です。</p></tr>
	<tr><td><div class="div"><p>メールアドレス：</p></div></td><td><form:input class="form-control" id="InputEmail" path="email" placeholder="メール・アドレス"/></td></tr>
	<tr><td><p>パスワード：</p></td><td><form:password class="form-control" path="password" placeholder="パスワード"/></td></tr>
	</table>
		<!-- 丸みのあるボタン -->
		<br>
				<div class="col-lg-12 text-center">
					<input type="submit" class="btn_circle_arange" value="ログイン"><br>
				</div>
				<br>
			
		</form:form>
	
	</div>
</body>
</html>