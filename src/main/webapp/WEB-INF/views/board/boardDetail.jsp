<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- now : 현재 시간의 시분초를 now 변수에 세팅 --%>
<c:set var="now" value="<%=new java.util.Date()%>" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList.jsp</title>
<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/board.css' />?v=${now}" /> --%>
<style>
.container1 {
	display: flex;
	border-radius: 30px;
	width: 1061px;
	margin: 20px auto;
	/* box-shadow: 0 0 6px rgba(0, 0, 0, 0.1); */
	box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 20px 0px;
}

.menu-box a {
	display: flex;
	justify-content: center;
	align-items: center;
}

.img-box {
	width: 50%;
	height: auto;
}

.img-box img {
	width: 100%;
	height: auto;
	border-radius: 30px 0 0 30px;
}

.info-container {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	width: 50%;
	height: auto;
}

.menu-box, .reply-box {
	background-color: #f1f1f1;
	padding: 10px;
	width: 100%;
	text-align: center;
}

.new-container {
	flex-grow: 1;
	height: 100%;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	/* position: relative; */
	padding-left: 30px;
}

.menu-box {
	display: flex;
	justify-content: flex-start; /* Align items to the left */
	align-items: center; /* Center items vertically */
	height: 90px;
	position: sticky;
	top: 0;
	align-self: flex-start;
	z-index: 1;
	padding: 0 0 0 20px;
	background-color: white;
	border-radius: 0 30px 0 0;
}

.more-btn, .share-btn {
	transition: background-color 0.3s ease;
	border-radius: 50%;
	width: 50px;
	height: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
	text-align: center;
	margin-right: 10px;
}

.menu-box a {
	display: inline-block;
	color: black;
	text-decoration: none;
	width: 100%;
	height: 100%;
}

.menu-box .share-btn:hover, .menu-box .more-btn:hover {
	background-color: #f0f0f0;
}

.scroll-container {
	overflow: auto;
	max-height: 100vh;
}

.reply {
    margin-bottom: 15px;
}

.reply-insert {
    border-top: 1px solid #E9E9E9;
    background-color: white;
    border-radius: 0 0 30px 0;
    position: sticky;
    bottom: 0;
    padding: 0;
    text-align: center;
}
  
.reply-insert-box {
    padding: 20px; /* 좀 더 여백 추가 */
    display: flex; /* 입력 폼과 버튼을 옆으로 나란히 배치하기 위해 flex 사용 */
    align-items: center; /* 세로 중앙 정렬 */
}

.input-box {
    flex: 1; /* 입력 폼이 나머지 공간을 모두 차지하도록 설정 */
    display: flex; /* 내부 요소들을 옆으로 나란히 배치하기 위해 flex 사용 */
    height: 60px; /* 높이를 더 크게 설정 */
    border: 2.5px solid;
	border-color: #E9E9E9;
	border-radius: 30px;
}

.input-box input {
    width: 100%;
    height: 100%;
    border-radius: 30px;
    border-width: 0;
    padding: 0 20px; /* 텍스트 왼쪽에 여백 추가 */
    font-size: 16px; /* 폰트 사이즈 조정 */
}

#replySubmitBtn {
    height: 60px; /* 입력 폼과 높이 맞춤 */
    border: none;
    background-color: #007bff; /* 입력 버튼 색상 */
    color: white;
    cursor: pointer;
    border-radius: 30px;
    padding: 0 20px; /* 버튼의 왼쪽 오른쪽 패딩 추가 */
    margin-left: 10px; /* 입력 폼과 버튼 사이 여백 */
    font-size: 16px; /* 폰트 사이즈 조정 */
}

.more {
	display: flex;
	justify-content: center; /* 가로 중앙 정렬 */
	align-items: center; /* 세로 중앙 정렬 */
	width: 100%;
	padding: 0 100px;
	margin-top: 40px;
	margin-bottom: 16px;
}

.ccc {
	padding: 20px 0 0 20px;
}

.ccc h3 {
	float: left;
}

.back {
	position: absolute;
	top: 110px;
	left: 20px;
}

.aa {
	height: 100%;
}

/* 태그 스타일 */
#tagBox {
	
	padding: 5px;
	
	padding: 0 0 20px 0;
}

.tag {
	display: inline-block;
	background-color: #e0e0e0;
	color: #333333;
	padding: 5px;
	border: none;
	border-radius: 30px;
	margin-right: 3px;
}
</style>
</head>

<body>
	<c:if test="${not empty message}">
		<script>
			alert('${message}');
		</script>
	</c:if>

	<div>
		<div class="container1">
			<div class="back">
				<i class="fa-solid fa-arrow-left-long" id="backButton"> 뒤로가기</i>
			</div>
			<div class="img-box">
				<img src="<c:url value='${filePath }/${fileName }'/>" />
			</div>
			<div class="info-container">
				<div class="new-container">
					<div class="menu-box">
						<div class="share-btn">
							<a href="#"><i class="fa-solid fa-arrow-up-from-bracket"></i></a>
						</div>
						<div class="more-btn">
							<a href="#"><i class="fa-solid fa-ellipsis"></i></a>
						</div>
					</div>
					<div class="aa">
						<div class="scroll-container">
							<!-- 태그를 표시할 요소 -->
							<div id="tagBox"></div>
							<div class="user-infoBox">
								<h3>
									<c:out value="${memberId }" />
								</h3>
							</div>
							<h4 style="margin-bottom: 20px;">댓글</h4>
							<c:choose>
								<c:when test="${not empty replyList}">
									<c:forEach items="${replyList}" var="reply">
										<div class="reply">${reply.replyContent}</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<p>댓글이 없습니다.</p>
								</c:otherwise>
							</c:choose>
						 </div>
                </div>
            </div>
            <div class="reply-insert">
                <div class="ccc" style="height: 60px;"><h3>어떠셨나요?</h3></div>
                <div class="reply-insert-box">
                    <div class="input-box">
                        <input id="replyInput" type="text" placeholder="댓글을 입력하세요...">
                    </div>
                    <button id="replySubmitBtn">입력</button>
                </div>
            </div>
        </div>
    </div>
    <div class="more"><h3>더 찾아보기</h3></div>
</div>
		<script>
			$(document).ready(
					function() {
						$('#backButton').click(function() {
							$('.a1').hide();
							$('html, body').animate({
								scrollTop : 0
							}, 'fast');
						});

					    // 태그가 들어갈 곳
					    var tagBox = $('#tagBox');

					    // 서버에서 전달받은 tagNames를 JavaScript 배열로 변환
					    var tagNames = [
					        <c:forEach items="${tagNames}" var="tagName" varStatus="status">
					            "${tagName}"<c:if test="${!status.last}">,</c:if>
					        </c:forEach>
					    ];

					    // 각 태그를 span 태그로 추가
					    tagNames.forEach(function(tagName) {
					        tagBox.append('<span class="tag">' + tagName.trim() + '</span>');
					    });
					    
					    
					});
		</script>
</body>
</html>