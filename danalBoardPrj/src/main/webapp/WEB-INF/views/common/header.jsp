<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.danal.djmoon.member.vo.MemberVo"%>
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="shortcut icon" href="${root}/resources/img/common/logo_RGB_danal.jpg"/>
<link rel="stylesheet" href="${root}/resources/css/common/header.css">

<c:set var = "root" value = "${pageContext.request.contextPath}" />
<style>
</style>
    <header>
        <div class="left-head-menu">
            <a class="open-menu-button" id="omb" href="javascript:openSideMenu()">&square;</a>          
            <a class="close-menu-button" id="cmb" href="javascript:closeSideMenu()">X</i></a>
            <a href="${root}/community/main" class="left-head-menu-button">커뮤니티</a>
            <a href="${root}/movie/main" class="left-head-menu-button">영화 순위</a>
            <a href="${root}/movie/synopsis" class="left-head-menu-button">영화 줄거리</a>
            <div class="below-menu-line"></div>
        </div>
        <a href="${root}/main" class="center-head-menu"><img src="${root}/resources/img/common/logo_RGB_danal.jpg"/></a>
		        <div class="right-head-menu">
				        <c:choose>
				        	<c:when test="${not empty loginMember}">
					            <div id="login-id">${loginMember.name}님</div>				                	
				        	</c:when>
					    	<c:otherwise>
					            <a href="${root}/member/login" class="right-head-menu-btn" onMouseOver="this.innerHTML='로그인'" onMouseOut="this.innerHTML='LOGIN'">LOGIN</a>     
   					            <a href="${root}/member/join" class="right-head-menu-btn" onMouseOver="this.innerHTML='회원가입'" onMouseOut="this.innerHTML='JOIN'">JOIN</a>     	    	
					    	</c:otherwise>
				        </c:choose>
			            <c:if test="${not empty loginMember}">
			            	<a href="${root}/member/logout" id="logoutBtn" onMouseOver="this.innerHTML='로그아웃'" onMouseOut="this.innerHTML='LOGOUT'" onclick="logoutCheck();">LOGOUT</a>
			            </c:if>
        		</div>
    </header>
    <script>
    function logoutCheck(){
    	 if (!confirm("로그아웃 하시겠습니까 ?")) {
             alert("로그아웃 하지 않습니다.");
             return false;
         } else {
             alert("로그아웃 되었습니다.");
         }
    }
    
    </script>
