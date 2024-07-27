<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="now" value="<%= new java.util.Date() %>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/header.css' />?v=${now}" />

	<header class="header">
	 <div class="login">
            <c:if test="${not empty sessionScope.memberVo}">
				<a class="home headerBtn" href="<c:url value='/logout'/>">로그아웃</a>
			</c:if>
			<c:if test="${empty sessionScope.memberVo}">
				<a class="home headerBtn" href="<c:url value='/login'/>">로그인</a>
			</c:if>
            <a class="create headerBtn" href="<c:url value='/board/create'/>">만들기</a>
            <a class="home headerBtn" href="<c:url value='/bookmark/list'/>">북마크</a>
            
        </div>
        
        <div class="search-box">
        	<form action="<c:url value='/board/search'/>" method="get">
	            <i class="fa-solid fa-magnifying-glass" id="searchBtn"></i>
	            <input type="text"  name="keyword" placeholder="검색할 태그를 입력하세요" id="searchInput">
            </form>
        </div>
       <div class="logo">
            <a href="/">SNAPSHARE</a>
        </div>
    </header>
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        const searchBox = document.querySelector('.search-box');
        const searchInput = searchBox.querySelector('input');
		
        // 엔터키를 누르면 검색 메소드 실행시키는 메소드
        function handleKeyPress(event) {
            if (event.keyCode === 13) { // 엔터키
                searchBoards();
            }
        }
		
        // 검색 메소드
        function searchBoards() {
            var keyword = document.getElementById("searchInput").value;
            // fetchBoards 메소드 호출
            fetchBoards(keyword);
        }
		
        function fetchBoards(keyword) {
            fetch('searchBoards.jsp?search=' + encodeURIComponent(keyword))
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                })
                .catch(error => console.error('Error fetching boards:', error));
        }
    });
    </script>
	