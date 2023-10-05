<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardPrj :: COMMUNITY</title>
<link href="https://fonts.googleapis.com/css?family=Inter&display=swap" rel="stylesheet" />
<c:set var = "root" value = "${pageContext.request.contextPath}"/>  
<link rel="shortcut icon" href="${root}/resources/img/common/logo_RGB_danal.jpg"/>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet" href="${root}/resources/css/community/detail.css">
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
          <div class="editDelete-btn">
			<%-- <c:if test="${loginMember.no == vo.memberNo}"> 컨트롤러에서 제어하는 방법으로 변경 --%>
			<c:if test="${isMyPosts}">
				<td id="edit"><a href="/djmoon/community/edit?no=${vo.no}"><input type="button" value="수정" id="write-btn"></a></td>
				<c:if test="${isMyPosts && !isAdmin}">
					<td id="delete"><a href="/djmoon/community/delete?no=${vo.no}"><input type="button" value="삭제" id="write-btn"></a></td>
				</c:if>
          	</c:if>
			<%-- <c:if test="${loginMember.name == '관리자'}"> 컨트롤러에서 제어하는 방법으로 변경 --%>
          	<c:if test="${isAdmin}">
			    <td id="delete"><a href="/djmoon/community/delete?no=${vo.no}"><input type="button" value="삭제" id="write-btn"></a></td>
			</c:if>
          </div>
          	<div id="post-title">
          		<%-- ${vo.title} --%><!-- XSS 공격 대비 위해 아래로 수정 -->
          		<c:out value="${vo.title}"/>
          	</div>
          	<br>
          	<div class="article-info">
          		<div>
          			<div id="enroll-date">
          			작성자 : ${vo.name} /
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
          		<%-- ${vo.content} --%><!-- XSS 공격 대비 위해 아래로 수정 -->
          		<c:out value="${vo.content}"/>
          	</div>
          	<br><br>
          	<br>
          		<div id="title-line2"></div>
          		<br>
          		<div class="comment-title">
          			<c:if test="${loginMember != null}">
          				댓글
          			</c:if>
          			<c:if test="${loginMember == null}">
          				* 댓글은 로그인 후 작성이 가능합니다.
          			</c:if>
          		</div>
          		<br>
				<!-- 게시글 아래 댓글 부분 -->
				<!-- 로그인 해야지만 댓글 작성 가능 -->
				<c:if test="${loginMember !=null}">
	          		<div class="cmt_container">
					<div class="form-table">
							<input type="hidden" value="${vo.no}" name="commNo">
							<c:if test="${loginMember !=null}">
								<input type="hidden" value="${loginMember.no}" name="writerNo">
							</c:if>
							<section class="cmt_inp">
							<span class="cmt_w" id="cmtWriter"> 
							<c:if test="${loginMember !=null}">
								작성자 : ${loginMember.name}
							</c:if>
							</span>
							<div class="cmt_txt">
							    <textarea name="comment" id="cmt_content" cols="100" rows="2" style="height: 70px; width: 800px; resize:none; font-size: 16px; margin-top: 10px; margin-left: 45px;" placeholder="댓글을 입력해 주세요 :)"></textarea>
							    <button id="cmt_btn"><span>등록</span></button>
							</div>
							</section>
							
							<!-- 댓글 작성하는 스크립트 -->
							<script>
								$('#cmt_btn').click(function(){
										//JSON으로 전달할 파라미터 변수 선언
										const commno = '${vo.no}';
										const cmtWriter = $('input[name=writerNo]').val();
										const cmtContent = $('#cmt_content').val();
			
										if(cmtWriter == ""){
											alert('로그인 후 이용해주세요.');
											return;
										}else if(cmtContent == ""){
											alert('내용을 입력해주세요.');
											return;
										}
									
										$.ajax({
											url:"/djmoon/community/cmt",
											type:"get",
											data: {
												"commNo" : commno ,
												"writer" : cmtWriter ,
												"comment" : cmtContent
											},
											success : function(result){
												location.reload();
												const objList = JSON.parse(result);
													$('#cmt_wrap').prepend(
															'<div class="cmt_box">'
															+'<div class="cmt2Writer" style="font-size : 14px; height:40px; line-height:30px">' + objList.writer + '</div>'
															+'<div class="cmt2Content" style = "height:70px; borderL:0; line-height:50px" >' + objList.cmt + '</div>'
															+'<span><a href="/djmoon/community/CmtEdit">'+ '수정' + '</a></span>'
															+' '
															+'<span><a href="/djmoon/community/CmtDelete">'+ '삭제' + '</a></span>'
															+'<div style="border-bottom:1px solid #aaa; width:860px; height:5px;"></div>'
															+'</div>'
													);
												alert('댓글 작성 성공');
											},
											error : function(){
												alert('로그인 후 사용해 주세요');
											}
										});
								});
							</script>
							
						</div>
					</div>
				</c:if>
				<!-- cmt container-->
       	  		<br>
				<div id="cmt_wrap">
 				<%-- <c:if test="${cvo !=null}">
	              	<c:forEach items="${cvo}" var="cvo" begin="0" end="${fn:length(cvo)}" step="1">
					  	<div class="cmt_box">
					  		<div>
								<input type="hidden" value="${cvo.cmtNo}" name="cmtNo">
								<div class="cmt2Writer">
								${cvo.name} &nbsp; 
								<span id="cmt-enrollDate">${cvo.enrollDate}</span>
									<c:if test="${loginMember.no == cvo.writer || loginMember.name == '관리자'}">
										<a id="cmt-delete" href="/djmoon/community/CmtDelete?cmtNo=${cvo.cmtNo}&bno=${vo.no}">삭제하기</a>
					          		</c:if>
								</div>
							</div>
							<br>
							<div class="cmt2Content">
							&emsp;&emsp;${cvo.cmt}<!-- XSS 공격 대비 위해 아래로 수정 -->
							<c:out value="${cvo.cmt}"/>
					  		</div>
							</div>
						<div style="border-bottom:1px solid #aaa; width:910px; height:5px;"></div>
						<br>
					</c:forEach>
				</c:if> --%>
				
				<!-- 여기서부터 수정 내용 / 컨트롤러에서 제어하는 방법으로 변경 -->
				<c:if test="${cvo != null}">
				    <c:forEach items="${cvo}" var="cvo" varStatus="cvoStatus" begin="0" end="${fn:length(cvo)}" step="1">
				        <div class="cmt_box">
				            <div>
				                <input type="hidden" value="${cvo.cmtNo}" name="cmtNo">
				                <div class="cmt2Writer">
				                    ${cvo.name} &nbsp;
				                    <span id="cmt-enrollDate">${cvo.enrollDate}</span>
				                    <c:if test="${isAdmin || isMyCmts[cvoStatus.index]}">
				                        <a id="cmt-delete" href="/djmoon/community/CmtDelete?cmtNo=${cvo.cmtNo}&bno=${vo.no}">삭제하기</a>
				                    </c:if>
				                </div>
				            </div>
				            <br>
				            <div class="cmt2Content">
				                &emsp;&emsp;<c:out value="${cvo.cmt}"/>
				            </div>
				        </div>
				        <div style="border-bottom:1px solid #aaa; width:910px; height:5px;"></div>
				        <br>
				    </c:forEach>
				</c:if>
				<!-- 여기까지가 수정 내용 -->
				</div>
				<!-- 여기까지가 댓글 -->
				
          </div><!-- second-box -->
          
          <!-- url로 jsp 직접 접근 막기 -->
          <%
			 String strReferer = request.getHeader("referer"); //현재 페이지 오기 전  URL 정보
			 
			 if(strReferer == null){
			%>
			 <script language="javascript">
			  alert("*URL을 통한 직접 접근은 불가합니다.\n\n정상적인 경로를 통해 다시 접근해 주세요.");
			  document.location.href="main";
			 </script>
			<%
			  return;
			 }
		%>
          
    </div>
  </div>
      <%@ include file="../common/footer.jsp" %>
  </div>
  
</body>
</html>