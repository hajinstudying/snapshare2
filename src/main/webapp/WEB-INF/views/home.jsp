<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="now" value="<%= new java.util.Date() %>" />
<html>
<head>
    <title>ShapShare</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/board.css' />?v=${now}" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/sidebar.css' />?v=${now}" />
    <script src="https://kit.fontawesome.com/6e1082867c.js" crossorigin="anonymous"></script> <!-- 아이콘 -->
    <script src="<c:url value='/resources/js/bookmarkCreate.js' />"></script>

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- 자바스크립트 파일 추가 -->
    <script src="<c:url value='/resources/js/bookmarkCreate.js' />"></script>
</head>
<body>
    <jsp:include page="/resources/common/header.jsp" />
    <jsp:include page="/resources/common/sidebar.jsp" />
    <!-- Container for the detailed post -->
    <div id="detailPost" style="display:none;"></div>

    <div class="container">
        <c:forEach items="${boardList}" var="board" varStatus="i">
            <div class="box">
                <!-- 북마크 버튼 -->
               <div class="bookmark-button">
    			<a href="<c:url value='/bookmark/create' />" class="bookmark-link" data-board-id="${board.boardId}" 
					data-member-id="${memberVo.memberId}">
       			 <i class="fa-solid fa-bookmark"></i>
					    </a>
					</div>

                <img class="clickImage" src="${filePath}/${board.fileName}" data-board-id="${board.boardId}" />
                <div class="download-button"><a href="#"><i class="fa-solid fa-download"></i></a></div> <!-- 다운로드 버튼 -->
                <div class="share-button"><a href="#"><i class="fa-solid fa-arrow-up-from-bracket"></i></a></div> <!-- 공유 버튼 -->
                <div class="more-button"><a href="#"><i class="fa-solid fa-ellipsis"></i></a></div> <!-- 더보기 버튼 -->
            </div>
        </c:forEach>
    </div>

    <script>  
$(document).ready(function() {
	// 사이드바 토글
	var sidebarToggle = document.getElementById('sidebarToggle');
    var sidebar = document.querySelector('.sidebar');
    var container = document.querySelector('.container');

    sidebarToggle.addEventListener('click', function() {
        sidebar.classList.toggle('active');
        container.classList.toggle('sidebar-active');
    });
	
	// 상세페이지 토글
    $('.clickImage').on('click', function() {  
        var boardId = $(this).data('board-id');  

        $.ajax({  
            type: 'GET',  
            url: '<c:url value="/board/detail/" />' + boardId,  
            success: function(response) {  
                 
                $('#detailPost').html(response).show();  
                
                // 페이지 맨위로 이동 
                $('html, body').animate({scrollTop: 0}, 'fast');  
            },  
            error: function(xhr, status, error) {  
                console.error('Error occurred while fetching board details:', error);  
            }  
        });  
    });  
});  
</script>
<script src="/resources/js/sidebar.js"></script>
</body>
</html>