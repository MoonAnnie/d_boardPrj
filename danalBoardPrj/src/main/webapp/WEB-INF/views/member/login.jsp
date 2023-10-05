<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<c:set var = "root" value = "${pageContext.request.contextPath}"/>
<link rel="shortcut icon" href="${root}/resources/img/common/logo_RGB_danal.jpg"/>
<link rel="stylesheet" href="${root}/resources/css/member/login.css">
<title>boardPrj :: Login</title>
<style>
  
</style>
</head>
<body>
  <%@ include file="../common/header.jsp" %>
  	<form action= "${root}/member/login" method="post">
	    <div class="wrap">
	        <div class="login">
	            <h2>Hello There !</h2>
	            <div class="login_id">
	                <input type="text" name="id" required placeholder="아이디를 입력하세요">
	            </div>
	            <div class="login_pw">
	               <input type="password" name="pwd" required placeholder="비밀번호를 입력하세요">
	            </div>
	            <div class="login_etc">
	            </div>
	            <div class="submit">
	                <input type="submit" value="로그인" onclick="loginSubmit();">
	            </div>
	            <div class="etc">
	                <a href="">비밀번호 찾기</a>
	                <a href="${root}/member/join">회원가입</a>
	            </div>
	        </div>
	    </div>
  	</form>
  	
  	<script>
  	function loginSubmit(){
  		alert('로그인 성공!');
  		return true;
  	}
  	</script>
  	
  <%@ include file="../common/footer.jsp" %>
</body>
</html>