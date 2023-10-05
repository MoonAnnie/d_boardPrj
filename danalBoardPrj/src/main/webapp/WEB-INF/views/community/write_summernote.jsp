<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardPrj :: COMMUNITY</title>
<link href="https://fonts.googleapis.com/css?family=Inter&display=swap" rel="stylesheet" />

<!--<script src="https://unpkg.com/@yaireo/tagify/dist/tagify.polyfills.min.js"></script>-->
<link href="https://unpkg.com/@yaireo/tagify/dist/tagify.css" rel="stylesheet" type="text/css" />  
  
<script src="http//code.jquery.com/jquery-1.11.0.min.js"></script>

<c:set var = "root" value = "${pageContext.request.contextPath}"/>  
<link rel="shortcut icon" href="${root}/resources/img/common/logo_RGB_danal.jpg"/>

<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${root}/resources/css/summernote/summernote-lite.css">
<link rel="stylesheet" href="${root}/resources/css/community/write_summernote.css">
<!-- include summernote css/js-->
</head>
<style>

</style>
<body>
	<%@ include file="../common/header.jsp" %>

  <div class="main-box">
      <div class="main-content">
          <div class="second-box">
          	<form id="article-form" action="" method="post" enctype="multipart/form-data">
          		<br><br><br>
          		<div id="post-area">
	          		<div id="post-background">
						<div id="category"><br></div><br>
						<input type= "text" name="title" id="title" placeholder="&nbsp;&nbsp;제목을 입력해 주세요."> 
						<input type= "hidden" name="memberNo" id="memberNo" value="${loginMember.no}">   
						<br><br>
						<textarea id="summernote" name="content" onclick="checkContent()" placeholder="&nbsp;&nbsp;내용을 입력해 주세요."style="resize:none;"></textarea>
		        		<br>
	        		</div>
        		</div>
        		<div class="write-btn">
	        		<input id="write-btn" type="button" onclick="submit()" value="작성하기">
	        		<input id="write-btn" type="button" onclick="cancel()" value="취소하기">
        		</div>
          	</form>
          </div>

		<!-- 게시글 작성 시 정규식 => 서버 측에서 관리하는 방법으로 수정 -->          
<!--           <script>
		    function checkContent() {
		        var contentTextarea = document.getElementById("summernote");
		        var filteredContent = contentTextarea.value.replace(/[<>&]/g, "");
		        contentTextarea.value = filteredContent;
		    }
		</script> -->
          
          <script type="text/javascript">
	          function cancel() {
	        	  location.href="/djmoon/community/main";
	          }
	          function submit() {
	        	  location.href="/djmoon/community/main";
	        	  alert("게시글 작성 성공 !");
	          }
          </script>
          
          <script>
	        // 알림창을 띄우는 함수
	        function showAlert(message) {
	            alert(message);
	            window.location.href = "<%= request.getContextPath() %>/community/main";
	        }
	
	        // JSP에서 전달한 메시지를 기반으로 알림창 띄우기
	        <% if (request.getAttribute("msg") != null) { %>
	            showAlert("<%= request.getAttribute("msg") %>");
	        <% } %>
		   </script>
		    
    </div>
      
  </div>
      <%@ include file="../common/footer.jsp" %>
  
</body>
</html>