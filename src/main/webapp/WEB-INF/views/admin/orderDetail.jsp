<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>注文詳細画面</title>
</head>
<%@ include file="../common/adminHeader.jsp" %>
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

<h1>注文詳細画面</h1>
<div class="table-responsive">
<table class="table table-striped" style="WIDTH: 500px">
				  <tr>
				    <th nowrap>注文NO</th>
				    <td><c:out value="${order.orderNumber}"></c:out>
				    </td>
				  </tr>
				  <tr>
				    <th nowrap>名前</th>
				    <td><c:out value="${order.deliveryName}"></c:out>
				    </td>
				  </tr>
				  <tr>
				    <th nowrap>アドレス</th>
				    <td><c:out value="${order.deliveryEmail}"></c:out></td>
				  </tr>
				  <tr>
				    <th nowrap>住所</th>
				    <td><c:out value="${order.deliveryAddress}"/></td>
				  </tr>
				  <tr>
				    <th nowrap>TEL</th>
				    <td><c:out value="${order.deliveryTel}"/></td>
				  </tr>
				  </table></div><br>
				  
<div class="table-responsive">
<table class="table table-striped" style="WIDTH: 500px">
      <tr>
        <th nowrap>商品</th>
        <th nowrap>価格</th>
        <th nowrap>&nbsp;</th>
        <th nowrap>個数</th>
       <th nowrap>金額</th>
      </tr>
      <c:forEach var="orderItem" items="${order.orderItemList}">
      <tr>
        <td><c:out value="${orderItem.item.name}"/></td>
        <td><fmt:formatNumber value="${orderItem.item.price}" pattern="###,###円"/></td>
        <td>×</td>
        <td><c:out value="${orderItem.quantity}"/></td>
        <td><fmt:formatNumber value="${orderItem.itemTotalPriceExcludeTax}" pattern="###,###円"/></td>
      </tr>
      </c:forEach>
    </table></div><br>
    
    
<div class="table-responsive">
<table class="table table-striped" style="WIDTH: 500px">
				  <tr>
				    <th nowrap>小計</th>
				    <td><fmt:formatNumber value="${order.totalPriceExcludeTax}" pattern="###,###円"/></td>
				  </tr>
				  <tr>
				    <th nowrap>税</th>
				    <td><fmt:formatNumber value="${order.totalPriceTax}" pattern="###,###円"/></td>
				  </tr>
				  <tr>
				    <th nowrap>支払い方法</th>
				    <td>銀行振込</td>
				  </tr>
				  <tr>
				    <th nowrap>送料一律</th>
				    <td><fmt:formatNumber value="${order.postage}" pattern="###,###円"/></td>
				  </tr>
				  <tr>
				    <th nowrap> 総計</th>
				    <td><fmt:formatNumber value="${order.totalPriceIncludeTaxAndPostage}" pattern="###,###円"/></td>
				  </tr>
				  </table></div><br>


<div class="table-responsive">
<table class="table table-striped" style="WIDTH: 500px">
      <tr>
        <th nowrap>現在のステータス</th>
        <th nowrap>ステータス変更</th>
      </tr>
      <tr>
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
        <td><form action="${pageContext.request.contextPath}/admin/updateStatus" name="statusSubmit" method="GET" onsubmit="return check()" onreset="return kakunin()">
          <c:choose>
        	<c:when test="${order.status == 0}">
        		<select name="status">
					<option value="0" selected>購入前(変更非推奨)</option>
					<option value="1">未入金</option>
					<option value="2">入金済み</option>
					<option value="3">発送済み</option>
					<option value="9">キャンセル</option>
				</select>
        	</c:when>
        	
        	<c:when test="${order.status == 1}">
        		<select name="status">
					<option value="0">購入前(変更非推奨)</option>
					<option value="1" selected>未入金</option>
					<option value="2">入金済み</option>
					<option value="3">発送済み</option>
					<option value="9">キャンセル</option>
				</select>
        	</c:when>
        	
        	<c:when test="${order.status == 2}">
        		<select name="status">
					<option value="0">購入前(変更非推奨)</option>
					<option value="1">未入金</option>
					<option value="2" selected>入金済み</option>
					<option value="3">発送済み</option>
					<option value="9">キャンセル</option>
				</select>
        	</c:when>
        	
        	<c:when test="${order.status == 3}">
        		<select name="status">
					<option value="0">購入前(変更非推奨)</option>
					<option value="1">未入金</option>
					<option value="2">入金済み</option>
					<option value="3" selected>発送済み</option>
					<option value="9">キャンセル</option>
				</select>
        	</c:when>
        	
        	<c:when test="${order.status == 9}">
        		<select name="status">
					<option value="0">購入前(変更非推奨)</option>
					<option value="1">未入金</option>
					<option value="2">入金済み</option>
					<option value="3">発送済み</option>
					<option value="9" selected>キャンセル</option>
				</select>
        	</c:when>	
        </c:choose>

<input type="hidden" name="orderId" value="${order.id}">
<input class="btn" type="submit" value="更新">

</form></td>
      </tr>
    </table></div><br>
<font color="red"><c:out value="${updateMessage}"/></font>

<br>
<br>
    <a href="${pageContext.request.contextPath}/admin/viewOrderList">注文一覧に戻る</a>

</body>
</html>