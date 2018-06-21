<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>注文一覧画面</title>
</head>
<%@ include file="../common/userHeader.jsp" %>
<body>
	<div align="center">

<h1>注文一覧画面</h1>
<font color="#ff0000">注文はありません。</font>
<table border="1">
      <tr>
        <th nowrap>注文番号</th>
        <th nowrap>日付</th>
        <th nowrap>利用者名</th>
        <th nowrap>現在のステータス</th>
        <th nowrap>総計(税込)</th>
      </tr>
      <c:forEach var="$order" items="${orderList}">
      <tr>
        <td><a href="admin/viewOrderDetail?id =${order.id}"><c:out value="${order.orderNumber}"/></a></td>
        <td><c:out value="${order.orderDate}"/></td>
        <td><c:out value="${order.user.userName}"/></td>
        <td>
        <c:choose>
        	<c:when test="${order.status == 0}">
        		購入前
        	</c:when>
        	<c:when test="${order.status == 1}">
        		未入金
        	</c:when>
        	<c:when test="${order.status == 2}">
        		入金済み
        	</c:when>
        	<c:when test="${order.status == 3}">
        		発送済み
        	</c:when>
        	<c:when test="${order.status == 9}">
        		キャンセル
        	</c:when>	
        </c:choose>
        </td>
        <td><c:out value="${order.totalPrice}"/>円</td>
      </tr>
      </c:forEach>
    </table>
    <br>
    <a href="./administerMenu.html">メニューに戻る</a>
</div>
	
</body>
</html>