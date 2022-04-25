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
		
		let comment = {
			content: content
		}

		$.ajax({
			url: `/recmv/api/reviews/${reviewId}/comments/${id}`,
			type: 'PUT',
			contentType: 'application/json; charset=UTF-8',
			data: JSON.stringify(comment),
		}).done(function(res) { // 응답 결과
			alert('댓글수정 완료');
			window.location.reload();
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	}
};

comment.exec();