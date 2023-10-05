<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Jquery -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/53a8c415f1.js" crossorigin="anonymous"></script>
    <c:set var = "root" value = "${pageContext.request.contextPath}"/>    
<link rel="shortcut icon" href="${root}/resources/img/common/logo_RGB_danal.jpg"/>
<link rel="stylesheet" href="${root}/resources/css/member/join.css">

<title>boardPrj :: Join</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<style>

</style>
<body>
  <%@ include file="../common/header.jsp" %>
  	<form action= "${root}/member/join" method="post" onsubmit="return checkAll();">
	    <div class="wrap">
	        <div class="join">
	            <h2>회원 가입</h2>
	            <div class="join_id">
		            <div class="join_name">
		                <h4>이름</h4>
		                <input type="text" name="name" placeholder="이름 ">
		            </div>
	                <h4 id="join_id">아이디</h4>
	                <div class="check_id">
	                    <input type="text" name="id" placeholder="영문 소문자 5~11자"><i class="bi bi-person"></i>
	                    <button type="button" id="check_id_btn" onclick="idDoubleCheck();"><h3>중복확인</h3></button>
	                </div>
	                <div id="checkIdResult" class="span2 result"></div>
	            <div class="join_pw">
	                <h4>비밀번호</h4>
	                <input type="password" name="pwd" placeholder="비밀번호를 입력해주세요.">
	                <div id="checkPwd1Result"></div>
	            </div>
	            <div class="join_pw2">
	                <h4>비밀번호 재확인</h4>
	                <input type="password" name="pwd2" placeholder="비밀번호를 입력해주세요.">
	                <div id="checkPwdResult2"></div>
	            </div>
	            <div class="submit">
	                <input id="join-submit" type="submit" value="가입하기">
	            </div>
	        </div>
	       </div>
	    </div>
       	<%@ include file="../common/footer.jsp" %>
	</form>
	<script>
	
	    //온서브밋을 위한 변수
	    let idCheckReturn = false;
	    let pwd1CheckReturn = false;
	    let pwd2CheckReturn = false;
	    
	    //아이디 에이잭스
	    function idDoubleCheck(){
	        let idVal = $('input[name="id"]').val();
	        const root = "${pageContext.request.contextPath}";
	        const idjung = /^[a-z]{5,11}$/g; //아이디는 영문 소문자 5~11 글자로 가입하기
	        
	        if(!idjung.test(idVal)) {
	            alert('아이디는 영문 소문자 5~11 글자로 해주세요.')
	        }else{
	            $.ajax({
		            url : root+"/member/idDoubleCheck",
		            type : "post",
		            data : {
		                "id" : idVal
		            		},
		            dataType : "json",
		            success : function(result){
		                if(result == 0){
							//성공했을 때 디자인 변경 및 조건 true로 만듬
		                    idCheckReturn = true;
		                    $('#checkIdResult').text('사용 가능한 아이디입니다.');
		                }else{
		                	//실패시 디자인 적용
		                    $('#checkIdResult').text('중복된 아이디입니다.');
		                }
		            },
		            error : function(){
		               alert('실패~');
		            }
	        	})
	        }
	    }//idDoubleCheck
	    
	    //패스워드 확인
	    $('input[name="pwd2"]').keyup(function(){
	
	        pwd2CheckReturn = false;
	        pwd1Val = $('input[name="pwd"]').val();
	        let pwd2Val = $('input[name="pwd2"]').val();
	  
	        $('#checkPwdResult2').addClass('red');
	       
	        if(pwd2Val != pwd1Val){
	            $('#checkPwdResult2').text('비밀번호가 일치하지 않습니다.');
	        }else{
	            $('#checkPwdResult2').text('위의 비밀번호와 일치합니다');
	            pwd2CheckReturn = true;
	        }
	        
	        // 추가: 비밀번호 입력 여부 체크
	        if (pwd1Val.trim() === "") {
	            pwd1CheckReturn = false;
	        } else {
	            pwd1CheckReturn = true;
	        }
	    })
	    
	    //온서브밋
	    function checkAll() {
	        if (!idCheckReturn) { alert('아이디 중복확인이 필요합니다'); return false; }
	        if (!pwd1CheckReturn) { alert('비밀번호가 입력되지 않았습니다.'); return false; }
	        if (!pwd2CheckReturn) { alert('비밀번호 확인이 입력되지 않았습니다'); return false; }
	        alert('회원가입 성공 !');
	        return true;
	    }
	    
	</script>
</body>
</html>