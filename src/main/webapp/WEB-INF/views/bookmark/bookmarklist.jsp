<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="now" value="<%= new java.util.Date() %>" />

<html>
<head>
    <title>ShapShare</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/board.css' />?v=${now}" />
    <script src="https://kit.fontawesome.com/6e1082867c.js" crossorigin="anonymous"></script> <!-- 아이콘 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- jQuery -->
    <script src="<c:url value='/resources/js/bookmarkDetail.js' />"></script> <!-- 자바스크립트 파일 추가 -->
</head>
<body>
    <jsp:include page="/resources/common/header.jsp" />

   <div class="container">
    <c:forEach items="${bookmarkList}" var="bookmark">
      <div class="box">
    <!-- 북마크 버튼 -->
               <div class="bookmark-button">
    			<a href="#" class="bookmark-link" data-bookmark-id="${bookmark.bookmarkId}" >
       			 <i class="fa-solid fa-bookmark"></i>
					    </a>
					</div>
        <img src="${filePath}/${bookmark.fileName}" alt="Bookmark Image">
</div>

    </c:forEach>
</div>

</body>
</html>