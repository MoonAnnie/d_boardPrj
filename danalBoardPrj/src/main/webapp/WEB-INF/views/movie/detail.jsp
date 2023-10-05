<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardPrj :: MOVIE</title>
<link href="https://fonts.googleapis.com/css?family=Inter&display=swap" rel="stylesheet" />
<c:set var = "root" value = "${pageContext.request.contextPath}"/>  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<link rel="stylesheet" href="${root}/resources/css/movie/main.css">

<link rel="shortcut icon" href="${root}/resources/img/common/logo_RGB_danal.jpg"/>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
		
		<br>	
		<h2><img src="${root}/resources/img/movie/netflix.PNG" style="width:25px; height:25px; margin-left: 130px;">&nbsp;TOP Movies on Netflix on<span id="current-time"></span></h2>
		
		<!-- 다시 정리해본 것 -->
		<script>
		  // 현재 날짜를 가져오고 업데이트하는 함수
		  function updateCurrentDate() {
		    const currentTimeElement = document.getElementById("current-time");
		    if (currentTimeElement) {
		      const today = new Date();
		      const options = { year: 'numeric', month: 'long', day: 'numeric' };
		      currentTimeElement.textContent = today.toLocaleDateString('en-US', options);
		    }
		  }
		
		  // 페이지가 로드될 때 날짜 업데이트
		  document.addEventListener("DOMContentLoaded", function() {
		    updateCurrentDate();
		  });
		</script>
		
	    <div id="second-box-content">
	    <div id="second-box-content-title">#</div>
   	    <div id="second-box-content-title"></div>
	    <div id="second-box-content-title">TITLE</div>
   	    <div id="second-box-content-title">POINTS</div>
	    <div id="second-box-content-title">CHANGE</div>
	    <div id="second-box-content-title">COUNTRIES</div>
	    <div id="second-box-content-title">⌀/COUNTRY</div>
  	    <div id="second-box-content-title">DAYS</div>
  	    <div id="second-box-content-title">⌀/DAY</div>
   	    <div id="second-box-content-title">TOTAL</div>
  
       	<%-- <c:forEach items="${list}" var="list" begin="0" end="${fn:length(list)}" step="1"> --%>
       	<!-- 클릭 시 상세 게시글로 조회 필요 community 폴더 하위에 Detail 페이지 만들기 -->
       	<%-- dataList 출력 --%>
		<c:forEach items="${dataList}" var="data" varStatus="status">
		    <div class="title-atag">
		        <a id="title-atag" href=""><c:out value="${data}" /></a>
		    </div>
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
	            <a href="${root}/movie/main?p=${pv.currentPage - 1}">&lt;</a>
	        </c:when>
	        <c:otherwise>
	            <a>&lt;</a>
	        </c:otherwise>
	    </c:choose>
	    <c:forEach var="i" begin="${pv.startPage}" end="${pv.endPage}" step="1">
	        <a id="page-btn" href="${root}/movie/main?p=${i}&searchType=${bpvo.getSearchType()}&search=${bpvo.getSearch()}">${i}</a>
	    </c:forEach>
	    
	    <c:choose>
	        <c:when test="${pv.currentPage < pv.totalPages}">
	            <a href="${root}/movie/main?p=${pv.currentPage + 1}">&gt;</a>
	        </c:when>
	        <c:otherwise>
	            <a>&gt;</a>
	        </c:otherwise>
	    </c:choose>
	</div>
	<form action="${root}/movie/main" method="get" class="search-form">
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