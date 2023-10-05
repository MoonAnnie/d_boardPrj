<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardPrj :: MAIN</title>
<link href="https://fonts.googleapis.com/css?family=Inter&display=swap" rel="stylesheet" />
<c:set var = "root" value = "${pageContext.request.contextPath}"/>  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<link rel="stylesheet" href="${root}/resources/css/community/main.css">

<link rel="shortcut icon" href="${root}/resources/img/common/logo_RGB_danal.jpg"/>
</head>
<style>

</style>
<body>
	<%@ include file="../common/header.jsp" %>

  <div id="main-img">
    <%-- <img id="main-img-size" src="<c:url value='/resources/img/main/danal2.PNG'/>" height="600px" width="100%"/> --%>
    
	<div class="write-btn">
    	<a href="${root}/community/write_summernote"><input type="button" value="글쓰기" id="write-btn"></a>
    </div>
  
    <div id="second-box-content">
	    <div id="second-box-content-title">번호</div>
	    <div id="second-box-content-title">제목</div>
   	    <div id="second-box-content-title">내용</div>
	    <div id="second-box-content-title">작성자</div>
	    <div id="second-box-content-title">작성일시</div>
	    <div id="second-box-content-title">조회수</div>
	    
       	<c:forEach items="${list}" var="list" begin="0" end="${fn:length(list)}" step="1">
       	<!-- 클릭 시 상세 게시글로 조회 필요 community 폴더 하위에 Detail 페이지 만들기 -->
	  		<div class="title-atag"><a id = "title-atag" href="${root}/community/detail?bno=${list.no}">${list.no}</a></div>
            <div class="title-atag"><a id = "title-atag" href="${root}/community/detail?bno=${list.no}"><c:out value="${list.title}" /></a></div>
            <div class="title-atag"><a id = "title-atag" href="${root}/community/detail?bno=${list.no}"><c:out value="${list.content}" /></a></div>
            <div class="title-atag"><a id = "title-atag" href="${root}/community/detail?bno=${list.no}">${list.name}</a></div>
            <div class="title-atag"><a id = "title-atag" href="${root}/community/detail?bno=${list.no}">${list.enrollDate}</a></div>
            <div class="title-atag"><a id = "title-atag" href="${root}/community/detail?bno=${list.no}">${list.hit}</a></div>
		</c:forEach>
		<!-- 검색 결과 없을 시 검색 결과가 없다는 메세지 -->
		<!-- not empty == msg 가 비어있지 않을 때 실행 -->
		<c:if test="${not empty msg}">
			<div></div>
			<div></div>
    		<div class="no-results-message">${msg}</div>
			<div></div>
			<div></div>
			<div></div>
		</c:if>
  	</div>

	<!-- 페이징 -->
	<div id="paging">
	    <c:choose>
	        <c:when test="${pv.currentPage > 1}">
	            <a href="${root}/community/main?p=${pv.currentPage - 1}">&lt;</a>
	        </c:when>
	        <c:otherwise>
	            <a>&lt;</a>
	        </c:otherwise>
	    </c:choose>
	    <c:forEach var="i" begin="${pv.startPage}" end="${pv.endPage}" step="1">
	        <a id="page-btn" href="${root}/community/main?p=${i}&searchType=${bpvo.getSearchType()}&search=${bpvo.getSearch()}">${i}</a>
	    </c:forEach>
	    
	    <c:choose>
	        <c:when test="${pv.currentPage < pv.totalPages}">
	            <a href="${root}/community/main?p=${pv.currentPage + 1}">&gt;</a>
	        </c:when>
	        <c:otherwise>
	            <a>&gt;</a>
	        </c:otherwise>
	    </c:choose>
	</div>
	<form action="${root}/community/main" method="get" class="search-form">
	    <fieldset class="search-field">
	        <select class="select" name="searchType">
	            <option value="0">제목</option>
	            <option value="1">작성자</option>
	        </select>
	        <input type="text" class="search" name="search">
	        <button id="search-btn" type="submit"><i class="bi bi-search bi"></i></button>
	    </fieldset>
	</form>

  	</div>
    <%@ include file="../common/footer.jsp" %>
</body>
</html>