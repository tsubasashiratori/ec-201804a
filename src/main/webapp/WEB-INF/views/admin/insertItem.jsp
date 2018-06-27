<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品登録画面</title>
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
		<h2>商品登録画面</h2>
		<div>新規で登録したい商品の情報を入力してください。</div>
		<br>
	<form:errors path="ItemForm*."/>
		<form:form modelAttribute="insertItemForm" action="${pageContext.request.contextPath}/admin/insert"
			 method="post" enctype="multipart/form-data">
			商品名：<form:input path="name"/><br>
			<p><small><form:errors path="name" cssStyle="color:red" /></small></p>
			<br>
			価格 ：<form:input path="price"/><br>
			<p><small><form:errors path="price" cssStyle="color:red" /></small></p>
			<br>
			説明 ：<form:textarea path="description" cols="40" rows="5"/><br>
			<p><small><form:errors path="description" cssStyle="color:red" /></small></p>
			<br>
			画像 ：<input type="file" name="imagePath" accept="image/jpeg"/><br>
			<div>
				<font size="2" color="red">※ファイルはjpegファイルを指定してください</font>
			</div>
			<p><small><form:errors path="imagePath" cssStyle="color:red" /></small></p>
			<br>
			<form:checkbox path="deleted"/>利用者商品一覧に表示しない<br>
			<input type="submit" value="登録"><br>
		</form:form>
		<br> <a href="${pageContext.request.contextPath}/admin/menu">管理者メニュー画面に戻る</a>
	</div>
	
</body>
</html>