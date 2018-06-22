<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>管理者メニュー画面</title>
</head>
<%@ include file="../common/adminHeader.jsp"%>
<body>
<div align ="center">
    <h2>管理者メニュー画面</h2>

        <a href="/admin/insertItem">商品を登録</a><br><br>
        <a href="/admin/adminFindAll">商品一覧</a><br><br>
        <a href="/admin/viewOrderList">注文一覧</a>
        <br>
        <br>
        <br>
        <a href="/admin/logout">ログアウト</a>
</div>
</body>
</html>