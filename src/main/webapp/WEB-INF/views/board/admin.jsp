<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="now" value="<%= new java.util.Date() %>" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Board List</title>
<link rel="stylesheet" type="text/css"
   href="<c:url value='/resources/css/admin.css' />?v=${now}" />
</head>
<body>
   <div class="container">
      <div class="sidebar">
         <h2>SNAPSHARE</h2>
         <nav>
            <a href="<c:url value='/logout' />">Logout</a>
         </nav>
      </div>
      <div class="main-content">
         <div class="top-bar">
            <div class="user-profile">
               <span>ADMIN</span>
            </div>
         </div>
         <div class="dashboard">
            <form action="<c:url value='/board/search'/>" method="get"
               class="search-form">
               <input type="text" name="keyword" placeholder="Enter Tag Keyword" />
               <input type="submit" value="Search" />
            </form>
            <c:if test="${empty boardList}">
               <p>No posts available.</p>
            </c:if>
            <c:if test="${not empty boardList}">
               <table>
                  <caption>Board List</caption>
                  <colgroup>
                     <col width="100" />
                     <col width="150" />
                     <col width="150" />
                     <col width="200" />
                     <col width="150" />
                  </colgroup>
                  <thead>
                     <tr>
                        <th>Post</th>
                        <th>Board ID</th>
                        <th>Author</th>
                        <th>Filename</th>
                        <th>Date</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach var="board" items="${boardList}" varStatus="idx">
                        <tr>
                           <td><c:out value="${idx.index + 1}" /></td>
                           <td><a
                              href="<c:url value='/board/detail/${board.boardId}'/>"><c:out
                                    value="${board.boardId}" /></a></td>
                           <td><c:out value="${board.memberId}" /></td>
                           <td><c:out value="${board.fileName}" /></td>
                           <td><c:choose>
                                 <c:when test="${not empty board.regDate}">
                                    <fmt:formatDate value="${board.regDate}"
                                       pattern="yyyy-MM-dd HH:mm:ss" />
                                 </c:when>
                                 <c:otherwise>
                                    <c:out value="No date available" />
                                 </c:otherwise>
                              </c:choose></td>
                        </tr>
                     </c:forEach>
                     <tr>
                        <td align="center" colspan="5">${page_navigator}</td>
                     </tr>
                  </tbody>
               </table>
            </c:if>
            <br> <a href="<c:url value='/board/create'/>"
               class="create-post">게시물 작성</a>
         </div>
      </div>
   </div>
</body>
</html>
