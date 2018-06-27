<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>管理者メニュー画面</title>
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
	
<div align ="center">
    <h2>管理者メニュー画面</h2>

        <a href="${pageContext.request.contextPath}/admin/insertItem">商品を登録</a><br><br>
        <a href="${pageContext.request.contextPath}/admin/adminFindAll">商品一覧</a><br><br>
        <a href="${pageContext.request.contextPath}/admin/viewOrderList">注文一覧</a><br><br>
        <a href="${pageContext.request.contextPath}/admin/form">管理者登録</a><br><br>
        <br>
        <a href="${pageContext.request.contextPath}/admin/logout">ログアウト</a>
</div>
</body>
</html>