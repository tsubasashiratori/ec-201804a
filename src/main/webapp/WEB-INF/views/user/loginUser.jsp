<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css2/arange.css">


<link href="https://fonts.googleapis.com/css?family=Josefin+Slab:600i" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Shrikhand" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">

<meta charset="UTF-8">
<%@ include file="../common/userHeader.jsp"%>
<title>ユーザログイン</title>
</head>
<body>
	<script>
		$(window).on('load resize', function() {
			// navbarの高さを取得する
			var height = $('.navbar').height();
			// bodyのpaddingにnavbarんぼ高さを設定する
			$('body').css('padding-top', height);
		});
	</script>
	<div class="bg">
		<div class="whole">
			<h2 style="color: #ffffff;" class="login">login</h2>
			<br>
			<div align="center">
				<span class="text-danger">
				<form:errors path="userLoginForm.*" style="color: #ffffff;">
				
				</form:errors><br>	</span>


				<form:form class="form-inline" modelAttribute="userLoginForm"
					action="${pageContext.request.contextPath}/user/login">
					<br>		
					<table >
						<tr>
							<td align="center"><div class="div">
									<p class="message login2">mail-address</p>
								</div></td>
						</tr>
						<tr>
							<td align="center"><form:input class="form-control login4"
									id="InputEmail" path="email" placeholder="mail-address" /></td>
						</tr>
						<tr>
							<td align="center" style=height:50px valign="bottom"><p class="message login2" >password</p></td>
						</tr>
						<tr>
							<td><form:password class="form-control login4" path="password"
									placeholder="password" align="center" valign="top"/></td>
						</tr>
						<tr>
							<td colspan="2">
								<!-- 丸みのあるボタン --> <br>
								<div class="col-lg-12 text-center">
									<input type="submit" class="btn_circle_arange login3" value="push"><br>
								</div> <br>
							</td>
						</tr>
					</table>
				</form:form>
				<br>
				<table>
					<tr>
						<td>

							<p class="message">-------- Energy de cafeの新しいお客様ですか？--------</p>
						</td>
					</tr>
					<tr>
						<td>
							<form action="${pageContext.request.contextPath}/user/form"
								method="get">
								<input type="submit" value="新規登録はこちら" style="WIDTH: 300px"
									class="btn btn-default">
							</form>
						</td>
					</tr>
				</table>

			</div>
		</div>
	</div>
<%@ include file="../common/userFooterForPresentation.jsp" %>

</body>

</html>