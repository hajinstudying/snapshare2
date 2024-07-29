<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="now" value="<%= new java.util.Date() %>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/header.css' />?v=${now}" />
<!-- fontawesome -->
<script src="https://kit.fontawesome.com/6e1082867c.js" crossorigin="anonymous"></script>
<!-- Bootstrap Icons CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.3/font/bootstrap-icons.css" rel="stylesheet">

<header class="header">
	<div class="btns">
		<c:if test="${not empty sessionScope.memberVo}">
			<a class="home headerBtn" id="sidebarToggle">
			<i class="bi bi-person-circle"></i></i></a>
		</c:if>
		<c:if test="${empty sessionScope.memberVo}">
			<a class="home headerBtn" href="<c:url value='/login'/>">
			<i class="bi bi-person-fill-up"></i></a>
		</c:if>
		<a class="create headerBtn" href="<c:url value='/board/create'/>">
		<i class="bi bi-pencil-square"></i></a>
		<a class="home headerBtn" href="<c:url value='/bookmark/list'/>">
		<i class="fa-solid fa-bookmark"></i></a>
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