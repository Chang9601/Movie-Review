let comment = {
	exec: function() {
		$('#btn--comment--create').on('click', () => {
			let reviewId = $('#review--id').val();
			let content = $('#content').val();
		
			console.log(reviewId);
			if (content === '') {
				alert('내용을 입력하세요.');
				return false;
			}

			let fr = $('form[role="form"]');

			fr.attr('action', `/recmv/api/reviews/${reviewId}/comments`);
			fr.attr('method', 'post');
			fr.submit();

/*			$.ajax({
				url: `/recmv/api/reviews/${reviewId}/comments`,
				type: '',
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			}).done(function(res) {
				location.replace(`/recmv/movies?query=${query}`);
			}).fail(function(err) {
				alert(JSON.stringify(err));
			});*/
		});
	}
};

comment.exec();