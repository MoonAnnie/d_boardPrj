<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardPrj :: Main</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath}"/>  
<link rel="stylesheet" href="${root}/resources/css/main/main.css">
<link rel="shortcut icon" href="${root}/resources/img/common/logo_RGB_danal.jpg"/>
<style>

</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
    <main>        
        <div class="main-img" >
            <div id="main-img" ><img src="${root}/resources/img/main/danal2.PNG"/></div>
        </div>
    </main>
    <%@ include file="../common/footer.jsp" %>

</body>
</html>