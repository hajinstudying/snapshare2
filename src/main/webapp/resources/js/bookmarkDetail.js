$(document).ready(function() {
            $('.bookmark-link').click(function(event) {
                var bookmarkId = $(this).data('bookmark-id');
                
                $.ajax({
                	type: 'Post',
                    url: '/bookmark/delete',
                    data: { bookmarkId: bookmarkId },
                    success: function(response) {
                        alert('북마크가 성공적으로 삭제되었습니다.');
                        location.reload(); // 예: 페이지 새로고침
                    },
                    error: function(error) {
                        console.error('북마크 삭제 중 오류 발생:', error);
                        alert('북마크 삭제 중 오류가 발생했습니다.');
                    }
                });
            });
        });