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
		<form:form modelAttribute="insertItemForm" action="${pageContext.request.contextPath}/admin/insert" method="post" enctype="multipart/form-data">
		
			<table class="table" style=WIDTH:700px>
			<tr><td>商品名：<br><p><small><form:errors path="name" cssStyle="color:red" /></small></p></td>
			<td><form:input path="name" class="form-control register"/></td></tr><br>
			
			<tr><td>価格 ：<p><small><form:errors path="price" cssStyle="color:red" /></small></p></td>
			<td><form:input path="price" class="form-control register"/></td></tr>
			
			<tr><td>説明 ：<p><small><form:errors path="description" cssStyle="color:red" /></small></p></td>
			<td><form:textarea path="description" cols="40" rows="5" class="form-control"/></td></tr>
			
			<tr><td>画像 ：

				<font size="2" color="red">※ファイルはjpegファイルを指定してください</font>
				
			<p><small><form:errors path="imagePath" cssStyle="color:red" /></small></p></td>
			<td><input type="file" name="imagePath" accept="image/jpeg"></td>
			
			
			</tr>
			</table>
					
			
			<p><small><form:errors path="imagePath" cssStyle="color:red" /></small></p>
			<br>
			<label class="checkbox-inline">
			<form:checkbox path="deleted" />利用者商品一覧に表示しない<br>
			</label><br><br>
			
			<input type="submit" value="登録" class="btn btn-default"> <br>
		</form:form>
		<br> <a href="${pageContext.request.contextPath}/admin/menu">管理者メニュー画面に戻る</a>
	</div>
	
</body>
</html>