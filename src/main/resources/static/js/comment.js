let comment = {
	exec: function() {
		$('#btn--comment--create').on('click', () => {
			this.create();
		});

		$('#btn--comment--update').on('click', () => {
			this.update();
		});
	},

	create: function() {
		let reviewId = $('#review--id').val();
		let content = $('#content').val();

		if (content === '') {
			alert('내용을 입력하세요.');
			return false;
		}

		let fr = $('form[role="comment--form"]');

		fr.attr('action', `/recmv/api/reviews/${reviewId}/comments`);
		fr.attr('method', 'post');
		fr.submit();
	},

	update: function(id, reviewId) {
		let content = $(`.comment--content${id}`).val();

		if (content === '') {
			alert('내용을 입력하세요.');
			return false;
		}

		// CSRF 토큰
		let token = $('meta[name="_csrf"]').attr('content');
		let header = $('meta[name="_csrf_header"]').attr('content');
				
		let comment = {
			content: content
		}

		$.ajax({
			url: `/recmv/api/reviews/${reviewId}/comments/${id}`,
			type: 'PUT',
			contentType: 'application/json; charset=UTF-8',
			data: JSON.stringify(comment),
			beforeSend: function(xhr) { // 데이터 전송 전 HTTP 헤더 설정
				xhr.setRequestHeader(header, token);
			}			
		}).done(function(res) { // 응답 결과
			alert('댓글수정 완료');
			location.reload();
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	},
	
	delete: function(id, reviewId) {
		let ok = confirm('정말로 삭제하시겠습니까?');

		if (!ok) return;		

		// CSRF 토큰
		let token = $('meta[name="_csrf"]').attr('content');
		let header = $('meta[name="_csrf_header"]').attr('content');
				
		$.ajax({
			url: `/recmv/api/reviews/${reviewId}/comments/${id}`,
			type: 'DELETE',
			beforeSend: function(xhr) { // 데이터 전송 전 HTTP 헤더 설정
				xhr.setRequestHeader(header, token);
			}			
		}).done(function(res) { // 응답 결과
			alert('댓글삭제 완료');
			location.reload();
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	}
};

comment.exec();