<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="now" value="<%= new java.util.Date() %>" />


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Board Create</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Bootstrap Icons CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.3/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Tagify CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tagify/4.0.0/tagify.css">
    <!-- Custom CSS -->
   	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/boardCreate.css' />?v=${now}" />
    
</head>
<body>
<jsp:include page="/resources/common/header.jsp" />
<div class="container create-container">
    <div class="row">
        <!-- Image Preview Section -->
        <div class="col-md-6">
            <div id="image-preview" class="image-preview">
                <i class="bi bi-box-arrow-in-up icon"></i>
                <img id="image-preview-img" src="#" alt="Image Preview">
                <!-- 오버레이 추가 -->
                <div id="overlay"></div>
            </div>
        </div>
        <!-- 업로드 & 태그 -->
        <div class="col-md-6">
            <form id="boardForm" action="${contextPath}/board/create" method="post"
            enctype="multipart/form-data">
                <div class="form-group text-center mb-3">
                    <label for="formFile" class="form-label">이미지 올리기</label>
                </div>
                <div class="form-group col-9 mx-auto mb-3">
                    <input class="form-control image-upload" type="file" name="file" id="formFile" >
                </div>
                <div class="form-group col-9 mx-auto mb-3">
                    <input type="text" class="form-control" name="tags" id="tags" placeholder="태그 추가하기">
                </div>
                <div class="d-grid gap-2 col-6 mx-auto mt-5">
                    <button type="submit" class="btn btn-secondary">공유하기</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<!-- Tagify JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/tagify/4.0.0/tagify.min.js"></script>
<!-- Custom JS -->
<script>
$(document).ready(function() {
    // 이미지 프리뷰 클릭 시 파일 업로드 버튼 클릭
    $('#image-preview-img').click(function() {
        $('#overlay').click(); // 오버레이 클릭
    });

    // 오버레이 클릭 시 파일 업로드 버튼 클릭
    $('#overlay').click(function() {
        $('.image-upload').click(); // 파일 업로드 버튼 클릭
    });

    $(".image-upload").change(function() {
        var input = this;
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#image-preview-img').attr('src', e.target.result).show();
                $('#image-preview').css('background-color', 'transparent');
                $('#image-preview').css('border-radius', '0');
                $('#image-preview .icon').hide();
            }
            reader.readAsDataURL(input.files[0]);
        } else {
            $('#image-preview-img').hide();
            $('#image-preview .icon').show();
            $('#image-preview').css('background-color', '#f0f0f0');
            $('#image-preview').css('border-radius', '30px');
        }
    });

    // Tagify 초기화
    var input = document.querySelector('input[name=tags]');
    var tagify = new Tagify(input, {
        originalInputValueFormat: valuesArr => valuesArr.map(item => item.value).join(',')
    });
    
 	// 폼 제출 시 태그 값을 hidden input으로 추가
    $("#boardForm").submit(function() {
        var tagsValue = tagify.value.map(tag => tag.value).join(',');
        $('<input>').attr({
            type: 'hidden',
            name: 'tagList',
            value: tagsValue
        }).appendTo('#boardForm');
    });
});
</script>
</body>
</html>
