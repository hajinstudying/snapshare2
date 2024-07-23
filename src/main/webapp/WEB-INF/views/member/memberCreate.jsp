<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 컨텍스트패스(진입점폴더) 변수 설정 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #f8f9fd;
    }
    .container {
        display: flex;
        max-width: 900px;
        width: 100%;
        background-color: #fff;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
        overflow: hidden;
    }
    .left-panel {
        width: 50%;
        padding: 40px;
        background-color: #f8f9fd;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    .left-panel h1 {
        font-size: 24px;
        margin-bottom: 20px;
        color: #333;
    }
    .left-panel p {
        font-size: 16px;
        color: #666;
        margin-bottom: 40px;
        text-align: center;
    }
    .left-panel img {
        max-width: 100%;
        height: auto;
    }
    .right-panel {
        width: 50%;
        padding: 40px;
        background-color: #fff;
    }
    .right-panel h2 {
        font-size: 22px;
        margin-bottom: 20px;
        color: #333;
    }
    form div {
        margin-bottom: 15px;
        text-align: left;
    }
    form div label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
    }
    form div input[type="text"], form div input[type="password"], form div input[type="email"] {
        width: calc(100% - 20px);
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    .button-group {
        display: flex;
        justify-content: space-between;
    }
    .button-group button, .button-group input[type="submit"] {
        width: 48%;
        padding: 10px;
        background-color: #6200ea;
        border: none;
        border-radius: 4px;
        color: #fff;
        font-size: 16px;
        cursor: pointer;
        text-align: center;
    }
    .button-group button:hover, .button-group input[type="submit"]:hover {
        background-color: #3700b3;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="left-panel">
            <h1>Hello,</h1>
            <p>Hello designer, welcome to the registration page. Please fill out the form on the side to get more complete features. Please click login below if you already have an account!</p>
        </div>
        <div class="right-panel">
            <h2>SIGN UP</h2>
            <form id="regForm" action="<c:url value='/member/create'/>" method="post">
    <div>
        <label for="name">Name</label>
        <input type="text" name="name" id="name">
    </div>
    <div>
        <label for="email">Email Address</label>
        <input type="email" name="email" id="email">
    </div>
    <div>
        <label for="password">Enter Password</label>
        <input type="password" name="password" id="password">
    </div>
    <div>
        <label for="pwdConfirm">Retype Password</label>
        <input type="password" id="pwdConfirm">
    </div>
    <div class="button-group">
        <input type="submit" id="btnSubmit" value="REGISTER">
        <button type="button" onclick="location.href='<c:url value='/login'/>'">LOGIN</button>
    </div>
</form>

        </div>
    </div>
    <script>
    const form = document.getElementById("regForm");
    form.addEventListener("submit", function(event) {
        event.preventDefault();
        const nameInput = document.getElementById("name");
        const emailInput = document.getElementById("email");
        const pwdInput = document.getElementById("password");
        const pwdConfirmInput = document.getElementById("pwdConfirm");

        const name = nameInput.value.trim();  // Ensure name is trimmed

        // Validate other fields as before
        const email = emailInput.value.trim();
        const pwd = pwdInput.value.trim();
        const pwdConfirm = pwdConfirmInput.value.trim();

        // Add your validation checks here

        // Check if name is empty
        if (name === '') {
            alert('Name is required.');
            nameInput.focus();
            return;
        }

        // Set name field value
        nameInput.value = name;  // Ensure name is set properly

        // Submit the form
        form.submit();
    });

    </script>    
</body>
</html>
