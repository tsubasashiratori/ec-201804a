<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>500エラー</title>
</head>
<%@ include file="../common/userHeader.jsp" %>
<body>
<div class="bg2">
	<script>
		$(window).on('load resize', function(){
	    // navbarの高さを取得する
	    	var height = $('.navbar').height();
	    // bodyのpaddingにnavbarんぼ高さを設定する
	    	$('body').css('padding-top',height); 
		});
	</script>
	<div align="center">
	<h2>時間をおいてアクセスしてください</h2>
	
	<img src="../img/image-161.jpg" width ="700" height="400"><br>
	<a href="${pageContext.request.contextPath}/user//">商品一覧へ戻る</a><br>
	<a href="${pageContext.request.contextPath}/user/form">新規登録はこちら</a>
	</div>
<%@ include file="../common/userFooterForPresentation.jsp" %>
</div>
</body>
</html>