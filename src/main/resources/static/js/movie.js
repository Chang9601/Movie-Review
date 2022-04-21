document.querySelector('#btn--movie').addEventListener('click', () => {
	let query = $('#movie--api').val();

	if (query === '') {
		alert('검색할 영화의 제목을 입력하세요.');
		return false;
	}

	$.ajax({
		url: `/recmv/movie/api?query=${query}`,
		type: 'GET',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	}).done(function(res) {
		location.replace(`/recmv/movies?query=${query}`);
	}).fail(function(err) {
		alert(JSON.stringify(err));
	});
});