<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>マイページ</title>
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
	
    <h2>マイページ</h2>
    <a href="${pageContext.request.contextPath}/user/updateForm">ユーザ登録情報を変更する</a><br><br>
    <a href="${pageContext.request.contextPath}/user/buyLog">注文履歴を確認する</a><br><br>
    <br>
    <a href="${pageContext.request.contextPath}/user/ViewAllItemsAndSearchItem/findAllNotDeleted">トップページに戻る</a>
</body>
</html>