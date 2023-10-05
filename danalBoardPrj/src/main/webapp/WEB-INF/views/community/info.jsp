<%@page import="com.danal.djmoon.community.page.PageVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%PageVo pv = (PageVo)request.getAttribute("pv");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>YeSS :: COMMUNITY</title>
<link href="https://fonts.googleapis.com/css?family=Inter&display=swap" rel="stylesheet" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath}"/>  
<link rel="shortcut icon" href="${root}/resources/img/common/earth.png"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

<link rel="stylesheet" href="${root}/resources/css/community/info.css">

</head>
<style>

</style>
<body>
	<%@ include file="../common/header.jsp" %>

  <div id="main-img">
    <img id="main-img-size" src="<c:url value='/resources/img/community/comm_main_img_big.jpeg'/>" height="600px" width="100%"/>
    <span id="community-info">
      <div class="content">
        <h2>Information</h2>
        <h2>Information</h2>
      </div>
    </span>

  <div class="main-box">
      <div id="header-bot">
          <div id="navi">
              <div id="none-left"></div>
              <div id="navi-home"><a href="/yess/community/main">HOME</a></div>
              <div id="navi-info"><a href="/yess/community/info">정보 게시판</a></div>
              <div id="navi-qna"><a href="/yess/community/qna">문의 게시판</a></div>
              <div id="navi-chat"><a href="/yess/community/chat">잡담 게시판</a></div>
              <div id="navi-photo">사진 게시판</div>
              <div id="none-right"></div>
          </div>
      </div>
      <br>
      <div class="main-content">
        <div id="top-hashtag">
          <br>
          <div id="top-hashtag-title"><span id="top-hashtag-title2"># 인기 해시태그</span>&nbsp;</div>
          <div id="second-box-title-line"></div>
          <br>
          <div id="top-hashtag-list">
            #꿀팁
          </div>
          <br>
          <div id="top-hashtag-list">
            #분리수거
          </div>
          <br>
          <div id="top-hashtag-list">
            #어려움
          </div>
          <br>
          <div id="top-hashtag-list">
            #배달용기
          </div>
          <br>
          <div id="top-hashtag-list">
            #라벨프리
          </div>
          <div id="ad">
        	<br><br>
	        <div>
	        	<a href="${root}/whereTo"><img src="<c:url value='/resources/img/community/community_banner_whereToYess.png'/>" height="100%" width="100%"></a>
	        </div>
	        <br>
   	        <div id="loginUser"><br>
            	<div id="top-hashtag-title"><span id="top-hashtag-title2">현재 접속 중...</span>&nbsp;</div>
          		<div id="second-box-title-line"></div>
            	<div id="current-loginMember">
            	지구수비대
            	<br>
            	백대장
            	<br>
            	보개미
            	<br>
            	피글렛S2
            	<br>
            	션녀
            	<br>
            	요정지운
            	<br>
            	<c:if test="${loginMember !=null}">
            	<span>${loginMember.nick} <span id="myId">(나)</span></span>
            	</c:if>
            	</div>
          		<br>
	        </div>
        </div>
       </div>
          <div class="second-box">
          <div id="second-box-title">- 정보 게시판&nbsp;<img src="<c:url value='/resources/img/community/lightbulb.png'/>" height="30px" width="30px"></div>
            <br>
            <div id="article-table">
            <div id="first-box-title-line"></div>
            <div id="second-box-content">
              <div id="second-box-content-title">번호</div>
              <div id="second-box-content-title">유형</div>
              <div id="second-box-content-title">제목</div>
              <div id="second-box-content-title">작성자</div>
              <div id="second-box-content-title">작성일시</div>
              <div id="second-box-content-title">조회수</div>
              
              	<c:forEach items="${list}" var="list" begin="0" end="${fn:length(list)}" step="1">
					  <div><a id = "title-atag" href="${root}/community/infoDetail?no=${list.no}">${list.no}</a></div>
		              <div><a id = "title-atag" href="${root}/community/infoDetail?no=${list.no}">${list.name}</a></div>
		              <div><a id = "title-atag" href="${root}/community/infoDetail?no=${list.no}">${list.title}</a></div>
		              <div><a id = "title-atag" href="${root}/community/infoDetail?no=${list.no}">${list.nick}</a></div>
		              <%-- <div><a id = "title-atag" href="${root}/community/infoDetail?no=${list.no}"><fmt:formatDate value="${list.enrollDate}" pattern="yyyy-MM-dd"/></a></div> --%>		              
		              <div><a id = "title-atag" href="${root}/community/infoDetail?no=${list.no}">${list.enrollDate}</a></div>
		              <div><a id = "title-atag" href="${root}/community/infoDetail?no=${list.no}">${list.hit}</a></div>
				</c:forEach>
            </div>
            
            </div>
            <br><br>
            <div class="write-btn">
            <a href="/yess/community/write_summernote"><input type="button" value="글쓰기" id="write-btn"></a>
            </div>
            <div id="paging">
		    	<a><i class="fa-solid fa-chevron-left"></i></a>
		    	<c:forEach var="i" begin="${pv.startPage }" end="${pv.endPage }" step="1">
		    		<a id="page-btn" href="${root}/community/info?p=${i}">${i}</a>
		    	</c:forEach>
		        <a><i class="fa-solid fa-chevron-right"></i></a>
            </div>
            
            <br>
            <form action="" method="get" class="search-form">
		        <fieldset class="search-field">
		            <select class="select" name="type">
		            	<option value="0">제목</option>
		                <option value="1">작성자</option>
		            </select>
		            <input type="text" class="search" name="search">
		            <button type="submit" onsubmit="search()"><i class="bi bi-search bi"></i></button>
		        </fieldset>
	        </form>
          </div>
    </div>
    
  </div>
      <%@ include file="../common/footer.jsp" %>
  </div>
  
</body>
</html>