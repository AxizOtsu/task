<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
<form:form action="post" modelAttribute="form">
</form:form>
<c:if test = "${empty sessionScope.admin_name}" >
<c:redirect url = "/index"/>
</c:if>
<c:if test="${not empty admin_name}">
    <p>${admin_name}さん、こんにちは</p>
  </c:if>

   <br> <a href="select">検索</a>
   <br> <a href="insert">登録</a>
   <br> <a href="update">更新</a>
   <br> <a href="delete">削除</a>
  <br>
  <form action ="logout" method = "post" >
  <input type="submit" value="ログアウト" >
</form>
</body>

</html>