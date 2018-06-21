<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form modelAttribute="insertShoppingCartForm" action="${pageContext.request.contextPath}/toInsertShoppingCart"> 
個数:<form:select path="quantity" >
<form:options items="${list}"/>
</form:select>
<input type="submit" value="追加">
</form:form>
</body>
</html>