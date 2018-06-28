<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>利用者情報変更</title>
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
	
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<div align="center">
	<h1>利用者情報変更</h1>
	<br>
	<br>お客様の情報を入力し、「お客様情報を変更する」ボタンをクリックしてください。
	<br>
	<font color="blue">※情報を変更した場合は一度ログアウトされます。<br>
	　　　情報を確認したい場合は再度ログインしてください。</font>
	<br>
	<br>

<form:errors path="updateUserForm.*" />
	<form:form modelAttribute="updateUserForm" action="${pageContext.request.contextPath}/user/update" method="post">
		<form:hidden path="id"/>
		<br>
		<div class="table-recponsive">

		<table class="table table-bordered table-striped" style=WIDTH:400px>

			<tr>
				<td><p><small><form:errors path="name" cssStyle="color:red" /></small></p>
				名前</td>
				<td><form:input path="name" class="form-control"/></td>

			</tr>
			<tr>
				<td><p><small><form:errors path="zipCode" cssStyle="color:red"/></small></p>
				郵便番号</td>
				<td>〒<form:input path="zipCode" size="8" maxlength="8" onKeyUp="AjaxZip3.zip2addr(this,'','address','address');" class="form-control"/></td>
			</tr>
			<tr>
				<td><p><small><form:errors path="address" cssStyle="color:red"/></small></p>
				住所</td>
				<td><form:input path="address" class="form-control"/></td>

			</tr>
			<tr>
				<td><p><small><form:errors path="telHead" cssStyle="color:red"/></small></p>
				電話番号
				</td>
				<td>
				<table>
				<tr><td><form:input path="telHead" size="4" maxlength="4" class="form-control register3" /></td>
				<td>-</td>
				<td><form:input path="telBody" size="4" maxlength="4" class="form-control register3" /></td>
				<td>-</td>
				<td><form:input path="telTeil" size="4" maxlength="4" class="form-control register3" /></td>
				
				</table>
				</td>
			</tr>
		</table>
		</div>

		<br> <br>
		<table><tr><td>
		<input type="submit"  value="お客様情報を変更する" class="btn btn-default">&nbsp;</td>
	</form:form>
	<form action="${pageContext.request.contextPath}/user/updateForm">
	<td><input type="submit"  value="変更内容を元に戻す" class="btn btn-default"></td></tr></table></form><br>

	<a href="${pageContext.request.contextPath}/user/myPage">マイページに戻る</a>
</div>
<%@ include file="../common/userFooterForPresentation.jsp" %>

</body>
</html>