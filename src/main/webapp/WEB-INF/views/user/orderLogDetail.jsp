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
<div align="center">

<h2>注文詳細画面</h2><br>
<h4>注文情報</h4>
<table border="1" class="table"  style="WIDTH: 500px">
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
				  </table><br>
				  
				  <h4>決算情報</h4>
	<table border="1" class="table"  style="WIDTH: 500px"> 
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
    </table><br>
    
    
    <table border="1" class="table"  style="WIDTH: 500px">
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
				  </table><br>
     <a href="${pageContext.request.contextPath}/user/orderLogList">注文一覧に戻る</a>
 </div>
<%@ include file="../common/userFooterForPresentation.jsp" %>
</body>
</html>