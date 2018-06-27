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
		メールアドレス：<form:input path="email" />
			<br>
		パスワード：<form:password path="password" />
			<br>
			<input type="submit" value="ログイン">
			<br>
		</form:form>
	</div>
</body>
</html>