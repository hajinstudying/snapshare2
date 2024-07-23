function createBookmark(element) {
    var boardId = element.getAttribute('data-board-id');
    var memberId = element.getAttribute('data-member-id');

    fetch('<c:url value="/create" />', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ boardId: boardId, memberId: memberId })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('북마크가 추가되었습니다.');
        } else {
            alert('북마크 추가에 실패했습니다.');
        }
    })
    .catch(error => console.error('Error:', error));
}