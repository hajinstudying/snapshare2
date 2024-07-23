<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 컨텍스트패스(진입점폴더) 변수 설정 -->
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
    body, html {
        margin: 0;
        padding: 0;
        font-family: 'Arial', sans-serif;
        background-color: #f0f2f5;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .container {
        display: flex;
        width: 900px;
        height: 500px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
        overflow: hidden;
    }
    .form-container {
        background: #fff;
        padding: 50px;
        flex: 1;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    .form-container h3 {
        margin-bottom: 20px;
        font-size: 24px;
    }
    .form-container form {
        width: 100%;
    }
    .form-container input[type="text"],
    .form-container input[type="password"] {
        width: 100%;
        padding: 15px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .form-container .button-container {
        display: flex;
        width: 100%;
    }
    .form-container input[type="submit"],
    .form-container input[type="reset"] {
        flex: 1;
        padding: 15px;
        margin: 10px 5px;
        border: none;
        border-radius: 5px;
        background-color: #007bff;
        color: #fff;
        cursor: pointer;
        font-size: 16px;
    }
    .form-container input[type="reset"] {
        background-color: #dc3545;
    }
    .form-container input[type="submit"]:hover {
        background-color: #0056b3;
    }
    .form-container input[type="reset"]:hover {
        background-color: #c82333;
    }
    .form-container .error-message {
        color: red;
        margin-top: 10px;
    }
    .info-container {
        background: #007bff;
        color: #fff;
        flex: 1;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        padding: 50px;
    }
    .info-container h2 {
        font-size: 36px;
        margin-bottom: 20px;
    }
    .info-container p {
        font-size: 18px;
        margin-bottom: 40px;
    }
    .info-container a {
        color: #fff;
        text-decoration: none;
        border: 2px solid #fff;
        padding: 10px 20px;
        border-radius: 5px;
        transition: background-color 0.3s ease;
    }
    .info-container a:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h3>로그인</h3>
            <form action="<c:url value='/login'/>" method="post">
                <input type="text" id="memberId" name="memberId" placeholder="아이디" value="${memberVo.memberId}" required>
                <input type="password" id="password" name="password" placeholder="비밀번호" value="${memberVo.password}" required>
                <div class="button-container">
                    <input type="submit" value="로그인">
                    <input type="reset" value="다시쓰기">
                </div>
            </form>
            <c:if test="${not empty error }">
                <span class="error-message">${error }</span>
            </c:if>
        </div>
        <div class="info-container">
            <h2>Hello, Friend!</h2>
            <p>Enter your personal details and start your journey with us</p>
            <a href="<c:url value='/member/create'/>">SIGN UP</a>
        </div>
    </div>
</body>
</html>
