<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>
		削除を行うデータのIDを入力してください<br> <span class="required"></span>は必須です
	</p>
<c:if test="${not empty msg}">
		<span class="require">${msg}</span>
</c:if>
	<form:form action="deleteConfirm" method="post" modelAttribute="form">
		<fieldset>
			<div>
				<label class="required">ID</label><form:input path="id" />
			</div>
		</fieldset>
		<input type="submit" value="確認">
	</form:form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>