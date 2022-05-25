<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="rootPath"/>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>CookCook</title>

<!-- 공용 -->
<link rel="stylesheet" href="${rootPath}/resources/css/reset.css" />
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<script	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js"></script>
<!-- 공용 끝 -->

<!-- 조도성 -->
<link rel="stylesheet" href="${rootPath}/resources/css/main.css?ver=20220518-002" />
<script src="${rootPath}/resources/js/home.js?ver=2022-05-18-018"></script>
<script src="${rootPath}/resources/js/log.js?ver=2022-05-13-001"></script>
<!-- 조도성 끝-->

<!-- 안혁 -->
<script src="${rootPath}/resources/js/log.js?ver=2022-05-09-003" /></script>
<link rel="stylesheet" href="${rootPath}/resources/css/ahn/log.css" />
<link rel="stylesheet" href="${rootPath}/resources/css/ahn/detail.css" />
<!-- 안혁끝 -->


<!-- 장준영 -->
<link rel="stylesheet" href="${rootPath}/resources/css/chang/list.css" />
<link rel="stylesheet" href="${rootPath}/resources/css/chang/registerContent.css" />
<script src="${rootPath}/resources/js/chang/list.js?ver=2022-05-13-004"></script>
<!-- 장준영 끝 -->
<script>const rootPath = "${rootPath}"</script>
</head>