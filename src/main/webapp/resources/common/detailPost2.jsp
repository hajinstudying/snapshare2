<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
    <div class="detailPost-container">
        <div class="post-box">
        	<div class="aaa">
            <!-- Post image -->
            <div class="img-box">
                <img id="clickImage" src="<c:url value='/resources/image/1.jpg'/>" alt="Post Image">
            </div>
            <!-- Post info -->
            <div class="info-box">
                <!-- Menu -->
                <div class="post-menu sticky">
                    <div class="share-button">
                        <a href="#"><i class="fa-solid fa-arrow-up-from-bracket"></i></a>
                    </div>
                    <div class="more-button">
                        <a href="#"><i class="fa-solid fa-ellipsis"></i></a>
                    </div>
                </div>
                <!-- Comments section -->
                <div class="reply-container">
                    <div class="user-info">
                        <p>User1</p>
                    </div>
                    <div class="reply">
                        <h4>댓글</h4>
                    </div>
                    <div class="comments">
                        <div class="reply-box">댓글입니다.</div>
                        <div class="reply-box">댓글입니다.</div>
                        <div class="reply-box">댓글입니다.</div>
                        <div class="reply-box">댓글입니다.</div>
                        <div class="reply-box">댓글입니다.</div>
                        <div class="reply-box">댓글입니다.</div>
                        <div class="reply-box">댓글입니다.</div>
                        <div class="reply-box">댓글입니다.</div>
                        <div class="reply-box">댓글입니다.</div>
                        <div class="reply-box">댓글입니다.</div>
                        <div class="reply-box">댓글입니다.</div>
                        <div class="reply-box">댓글입니다.</div>
                        <!-- More comments... -->
                    </div>
                </div>
                <!-- Comment input -->
                <div class="reply-insert sticky">
                    <hr>
                    <div class="reply-menu">
                        <h3>댓글 #개</h3>
                    </div>
                    <div class="input-box">
                        <input type="text" placeholder="Enter your comment">
                        <button>Submit</button>
                    </div>
                </div>
            </div>
            
            </div>
            
        </div>
    </div>
    <div class="more">
        <h3>더 찾아보기</h3>
    </div>
