<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>
		検索したいデータ情報を入力してください<br> ※全て空白の場合は全検索を行います
	</p>

	<c:if test="${not empty msg}">
		<span class="require">${msg}</span>
	</c:if>

	<form:form action="list" modelAttribute="form" method = "get">
		<fieldset>
			<div>
				<label>ID</label>
				<form:input path="id" />
			</div>
			<div>
				<label>名前</label>
				<form:input path="name"  />
			</div>
			<div>
				<label>TEL</label>
				<form:input path="tel" />
			</div>
		</fieldset>
		<form:button>検索</form:button>
	</form:form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>
