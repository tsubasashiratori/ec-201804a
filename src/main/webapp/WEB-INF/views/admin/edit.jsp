<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品編集画面</title>
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
		<h2>商品編集画面</h2>
		<div>編集したい商品の情報を入力してください。</div>
		<font size="2" color="blue">※画像の選択がない場合は現在の画像のまま登録されます。</font>
		<br>

<form:form modelAttribute="editItemForm" action="${pageContext.request.contextPath}/admin/editItem" align="center" enctype="multipart/form-data">
	<form:hidden path="id"/>
	<!-- idをhiddenで埋め込んでいる -->
	
			<table class="table" style=WIDTH:700px align="center">
			<tr><td>商品名：<br>
			<p><small><form:errors path="name" element="div" cssStyle="color:red" align="center"/></small></p></td>
			<td><form:input path="name" value="${form.name}"/></td></tr><br>
			
			<tr><td>価格：<br>
			<p><small><form:errors path="price" element="div" cssStyle="color:red" align="center"/></small></p></td>
			<td><form:input path="price" value="${form.price}"/></td></tr><br>
			
			<tr><td>説明：<br>
			<p><small><form:errors path="description" element="div" cssStyle="color:red" align="center"/></small></p></td>
			<td><form:textarea path="description" value="${form.description}" rows="5" cols="40"/></td></tr><br>
			
			
			<tr><td>画像：<br>
			<p><small><form:errors path="imagePath" element="div" cssStyle="color:red" align="center"/></small></p></td>
			<td><div align="center"><input type="file" name="imagePath" accept="image/jpeg"></div></td>
			</table>
			現在の画像<br>
			<img src="${pageContext.request.contextPath}/img/${imagePath}" width="75" height="200" /><br>
			<br>
			<label>削除:<form:checkbox path="deleted" value="${form.deleted}"/></label><br><br>
			
	         <input type="submit" value="編集" align="center">
    </form:form>
    </div><br><br>
</body>
</html>