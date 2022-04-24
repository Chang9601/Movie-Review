const validateReview = (title, content, rating) => {
	if (title === '') {
		$('#title--ok').html('제목을 입력하세요.').css('color', 'red');
		return false;
	} else $('#title--ok').html('');

	if (rating === '') {
		$('#rating--ok').html('평점을 입력하세요.').css('color', 'red');
		return false;
	} else if (rating > 5.0 || rating < 0.0) {
		$('#rating--ok').html('범위 안에 평점을 입력하세요.').css('color', 'red');
		return false;
	} else $('#rating--ok').html('');

	if (content === '') {
		$('#content--ok').html('내용을 입력하세요.').css('color', 'red');
		return false;
	} else $('#content--ok').html('');

	return true;
};

let review = {
	exec: function() {
		$('#btn--review--create').on('click', () => {
			this.create();
		});

		$('#btn--review--update').on('click', () => {
			this.update();
		});

		$('#btn--review--delete').on('click', () => {
			this.delete();
		});

		$('#btn--review--search').on('click', () => {
			this.search();
		});


		$("#btn-like-rev").on("click", () => {
			this.like();
		});
	},

	create: function() {
		let title = $('#title').val(); // 리뷰 제목
		let content = $('#content').val(); // 리뷰 내용
		let rating = $('#rating').val(); // 평점
		let movieId = $('#movie--id').val(); // 영화 키

		if (!validateReview(title, content, rating))
			return;

		// form의 속성값 변경
		let fr = $('form[role="form"]');

		fr.attr('action', `/recmv/api/movies/${movieId}/reviews`);
		fr.attr('method', 'post');
		fr.submit();
	},

	update: function() {
		let title = $('#title').val(); // 리뷰 제목
		let content = $('#content').val(); // 리뷰 내용
		let rating = $('#rating').val(); // 평점
		let id = $('#id').val(); // 리뷰 키

		if (!validateReview(title, content, rating))
			return;

		let review = {
			title: title,
			content: content,
			rating: rating
		};

		$.ajax({
			url: `/recmv/api/reviews/${id}/update`,
			type: 'PUT',
			contentType: 'application/json; charset=UTF-8',
			data: JSON.stringify(review),
		}).done(function(res) { // 응답 결과
			alert('리뷰수정 완료');
			location.replace(`/recmv/reviews/${res.data}`);
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	},

	delete: function() {
		let id = $('#id').val(); // 리뷰 키
		let ok = confirm('정말로 삭제하시겠습니까?');

		if (!ok) return;

		$.ajax({
			url: `/recmv/api/reviews/${id}`,
			type: 'DELETE',
		}).done(function(res) {
			alert('리뷰삭제 완료');
			location.replace('/recmv/reviews');
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	},

	search: function() {
		let query = $('#query').val();
		let choice = $('#choice').val();
		
		if (query === '') {
			alert('검색할 리뷰의 제목 혹은 내용을 입력하세요.');
			return false;
		}
		
		let fr = $('form[role="form"]');

		fr.attr('action', `/recmv/reviews/search?choice=${choice}&query=${query}`);
		fr.attr('method', 'get');
		fr.submit();
		
/*		$.ajax({
			url: `/recmv/reviews/search?query=${query}&choice=${choice}`,
			type: 'GET',
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		}).done(function(res) {
			location.replace(`/recmv/movies?query=${query}`);
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});*/
	},

	like: function() {
		var id = $("#sessionId").text();
		var userNum = $("#userNum").text();
		var revNum = $("#revNum").text();

		if (id === "") {
			var msg = confirm("로그인이 필요합니다. 로그인 하시겠습니까?");
			if (msg === true) location.replace("/recmv/user/login");
			return false;
		}

		$.ajax({
			url: "/recmv/api/rev/ckDupRecom",
			type: "GET",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: { userNum: userNum, revNum: revNum, }
		}).done(function(resp) {
			console.log(resp);
			if (resp !== 0) {
				alert("이미 리뷰를 추천하셨습니다.");
				return false;
			}

			$.ajax({
				url: "/recmv/api/rev/recom",
				type: "POST",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				data: { userNum: userNum, revNum: revNum, }
			}).done(function(resp) {
				console.log(resp);
				alert("리뷰를 추천하셨습니다.");
			}).fail(function(err) {
				alert(JSON.stringify(err));
			});
		}).fail(function(err) {
			alert(JSON.parse(err));
		});
	}
};

review.exec();