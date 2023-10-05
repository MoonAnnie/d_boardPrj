<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardPrj :: COMMUNITY</title>
<link href="https://fonts.googleapis.com/css?family=Inter&display=swap" rel="stylesheet" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath}"/>  
<link rel="shortcut icon" href="${root}/resources/img/common/logo_RGB_danal.jpg"/>
<!-- jQuery와 SweetAlert2를 로드하는 코드 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="${root}/resources/css/community/edit.css">

</head>
<style>

</style>

<body>
	<%@ include file="../common/header.jsp" %>

  <div id="main-img">

  <div class="main-box">
      <br>
      <div class="main-content">
          <div class="second-box">
          
			<form id="article-form" action="/djmoon/community/edit" method="post"enctype="multipart/form-data">
           	<input type="hidden" name="no" value="${vo.no}">
       		<div class="write-btn">
        		<button id="submit-btn" type="button" onclick="submit()">수정</button>
            	<button id="cancel-btn" type="button" onclick="cancel()">취소</button>
       		</div>
          	<div id="post-title">
          		제목: <input type="text" name = "title" value="${vo.title}" id="edit-title">
          	</div>
          	<br>
          	<div class="article-info">
          		<div>
          			<div id="enroll-date">
          			작성자 : ${loginMember.name} /
          			작성일자 : ${vo.enrollDate} / 조회수 : ${vo.hit}
          			</div>
          		</div>
          	</div>
          	<div id="title-line"></div>
          	<br>
          	<div class="article-rule">
          		<p>
          		<br>
          		자유롭게 글을 작성하는 게시판입니다.
          		<br>
          		게시글 규정에 어긋나는 글은 무통보 삭제 처리가 되오니 주의 바랍니다 ^_^
          		<br>
          		</p>
          	</div>
          	<br>
          	<div class="article-content">
          		<textarea id="summernote" name="content" style=" height: 300; resize:none;">${vo.content}</textarea>
          	</div><br>
	        		<br>
	        		<br>
	        		<br>
          	</form>
  
          </div>
  
    </div>
    
	<script type="text/javascript">
        function cancel() {
            location.href = "/djmoon/community/edit";
        }

        function submit() {
            alert("게시글 수정이 완료되었습니다.");

            // 2초 후에 페이지 리다이렉트
            setTimeout(function () {
                location.href = "/djmoon/community/edit";
            }, 2000);
        }
    </script>
          
<!--       <script>
		
		$('#summernote').summernote({
		       placeholder: '자유롭게 글을 작성하는 게시판입니다. <br>게시글 규정에 어긋나는 글은 무통보 삭제 처리가 되오니 주의 바랍니다~^^',
		       tabsize: 2,
		       height: 330,
		       disableResizeEditor: true //높이 조절 불가능하게
		     });
	 </script> -->
	 
	
  </div>
      <%@ include file="../common/footer.jsp" %>
  </div>
  

  
</body>
</html>