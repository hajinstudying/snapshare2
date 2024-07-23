$(document).ready(function() {
    $('.bookmark-link').on('click', function(event) {
        event.preventDefault(); 

        var $bookmarkLink = $(this); 
        var boardId = $bookmarkLink.data('board-id'); 
        var memberId = $bookmarkLink.data('member-id'); 

        console.log('boardId:', boardId); 
        console.log('memberId:', memberId); 

        if (!memberId) {
            alert('북마크를 하려면 로그인해야 합니다.');
            return;
        }

        // 북마크 생성 요청 AJAX
        $.ajax({
            type: 'POST',
            url: '/bookmark/create', // 북마크 생성 URL
            data: {
                boardId: boardId,
                memberId: memberId,
            },
            success: function(response) {
                alert('북마크가 성공적으로 생성되었습니다!'); // 성공 메시지 표시
                window.location.href = '/bookmark/list';
            },
            error: function(xhr, status, error) {
                console.error('북마크 생성 중 오류 발생:', error);
                alert('북마크 생성에 실패했습니다.');
            }
        });
    });
});
