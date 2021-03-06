let movie = {
	exec: function() {
		$('#btn--movie--search').on('click', () => {
			let query = $('#query').val();

			if (query === '') {
				alert('검색할 영화의 제목을 입력하세요.');
				return false;
			}

			$.ajax({
				url: `/recmv/movies/api?query=${query}`,
				type: 'GET',
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			}).done(function(res) {
				location.replace(`/recmv/movies?query=${query}`);
			}).fail(function(err) {
				alert(JSON.stringify(err));
			});
		});
	}
};

movie.exec();