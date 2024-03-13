// 댓글 입력 필드의 상태를 확인하고, 버튼을 활성화 또는 비활성화하는 함수
function toggleButton() {
    // 입력 필드와 버튼 요소를 가져옵니다.
    var commentInput = document.getElementById('comment-input');
    var submitButton = document.getElementById('submit-button');

    // 입력 필드에 텍스트가 있는지 확인하고, 버튼의 disabled 속성을 설정합니다.
    submitButton.disabled = commentInput.value.trim() === '';
}

// 페이지 로드 시 이벤트 리스너를 추가합니다.
document.addEventListener('DOMContentLoaded', function() {
    // 'input' 이벤트에 'toggleButton' 함수를 바인드합니다.
    document.getElementById('comment-input').addEventListener('input', toggleButton);

    // 페이지 로드 시에도 한 번 상태를 확인합니다.
    toggleButton();
});
