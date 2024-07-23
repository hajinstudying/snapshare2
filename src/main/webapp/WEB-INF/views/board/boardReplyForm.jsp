<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>댓글 페이지</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <h2>댓글 페이지</h2>
    
    <div id="replyList">
        <!-- 댓글 목록이 표시될 자리 -->
    </div>
    
    <h3>댓글 작성</h3>
    <div>
        <label for="title">제목</label>
        <input type="text" id="title" name="title" required>
    </div> 
    <div>
        <label for="memberId">작성자</label>
        <input type="text" id="memberId" name="memberId" value="${sessionScope.member.memberId}" required readonly>                    
    </div>
    <div>
        <label for="content">내용</label>
        <textarea id="content" name="content" cols="80" rows="5"></textarea>
    </div> 
    <div>
        <button onclick="createReply()">작성</button>
        <button onclick="clearForm()">초기화</button>
    </div>
    
    <!-- 자바스크립트 파일 추가 -->
    <script src="<c:url value='/resources/js/reply.js' />"></script>
</body>
</html>
