
$(document).ready(function() {
    $('#replySubmitBtn').click(function() {
        var replyContent = $('#replyInput').val(); 

        // AJAX 호출
        $.ajax({
            type: 'Get',
            url: '/reply/create', 
            contentType: 'application/json',
            data: {replyContent},
            success: function(response) {
                // 성공적으로 댓글이 생성되면 화면에 추가
                var replyHtml = '<div class="reply">' + replyContent + '</div>';
                $('.scroll-container').append(replyHtml);

                // 입력 폼 비우기
                $('#replyInput').val('');
            },
            error: function(error) {
                console.error('Error creating reply:', error);
                alert('댓글 생성에 실패했습니다.');
            }
        });
    });
});

