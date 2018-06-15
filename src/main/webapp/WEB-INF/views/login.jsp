<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
   <c:if test="${not empty msg}">
   <span class="require">${msg}</span>
  </c:if>
  <form:form action="login" modelAttribute="form" method="POST">
   <div> ID <form:input path ="id"/><br>
   </div>
   <div>
    パスワード<form:input path ="pass"/><br>
    <form:button>ログイン</form:button>
  	</div>
  </form:form>

<div>
  <a href="index">TOP画面へ</a>
</div>
</body>
</html>
