const validateReview = (title, content, rating) => {
	if (title === '') {
		$('#title--ok').html('제목을 입력하세요.').css('color', 'red');
		return false;
	} else $('#title--ok').html('');

	if (rating === '') {
		$('#rating--ok').html('평점을 입력하세요.').css('color', 'red');
		return false;
	} else if (rating > 5.0 || rating < 0.0) {
		$('#rating--ok').html("범위 안에 평점을 입력하세요.").css('color', 'red');
		return false;
	}
	else $('#rating--ok').html('');

	if (content === '') {
		$('#content--ok').html('내용을 입력하세요.').css('color', 'red');
		return false;
	} else $('#content--ok').html('');
};

let review = {
	exec: function() {
		$('#btn--write').on('click', () => {
			this.write();
		});

		$("#btn-upd-rev").on("click", () => {
			this.update();
		});
		$("#btn-del-rev").on("click", () => {
			this.delete();
		});
		$("#btn-like-rev").on("click", () => {
			this.like();
		});
	},

	write: function() {
		let title = $('#title').val(); // 리뷰 제목
		let content = $('#content').val(); // 리뷰 내용
		let rating = $('#rating').val(); // 평점
		let movieId = $('#movie--id').val(); // 영화 키
		
		if(!validateReview(title, content, rating))
			return;
		
/*		var userNum = $("#user-num").val();
		var movieNum = $("#movie-num").val();
		var image = $("#image").val();
		var id = $("#id").val();
		var movie = $("#movie").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var rating = $("#rating").val();

		var rev = {
			userNum: userNum,
			movieNum: movieNum,
			image: image,
			id: id,
			movie: movie,
			title: title,
			content: content,
			rating: rating
		};*/

/*		$.ajax({
			url: "/recmv/api/rev/ckDupRev",
			type: "GET",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: { userNum: userNum, movieNum: movieNum, }
		}).done(function(resp) {
			console.log(resp);
			if (resp !== 0) {
				alert("이미 리뷰를 작성하셨습니다.");
				return false;
			}

			if (title === "") {
				$("#title-ck").html("제목을 입력하세요.").css("color", "red");
				return false;
			} else $("#title-ck").html("");

			if (rating === "") {
				$("#rating-ck").html("별점을 입력하세요.").css("color", "red");
				return false;
			} else if (rating > 5 || rating < 0.0) {
				$("#rating-ck").html("범위 안에 별점을 입력하세요.").css("color", "red");
				return false;
			}
			else $("#rating-ck").html("");

			if (content === "") {
				$("#content-ck").html("내용을 입력하세요.").css("color", "red");
				return false;
			} else $("#content-ck").html("");

			$.ajax({
				url: "/recmv/api/rev/write",
				type: "POST",
				contentType: "application/json; charset=UTF-8",
				data: JSON.stringify(rev)
			}).done(function(resp) {
				console.log(resp);
				alert("리뷰작성이 완료되었습니다.")
				location.replace("/recmv/rev/main");
			}).fail(function(err) {
				alert(JSON.stringify(err));
			});
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});*/
	},

	update: function() {
		var num = $("#num").val();
		var title = $("#title").val();
		var movie = $("#movie").val();
		var content = $("#content").val();
		var rating = $("#rating").val();

		var rev = {
			num: num,
			title: title,
			movie: movie,
			content: content,
			rating: rating
		};

		if (title === "") {
			$("#title-ck").html("제목을 입력하세요.").css("color", "red");
			return false;
		} else $("#title-ck").html("");

		if (rating === "") {
			$("#rating-ck").html("별점을 입력하세요.").css("color", "red");
			return false;
		} else if (rating > 5 || rating < 0.0) {
			$("#rating-ck").html("범위 안에 별점을 입력하세요.").css("color", "red");
			return false;
		}
		else $("#rating-ck").html("");

		if (content === "") {
			$("#content-ck").html("내용을 입력하세요.").css("color", "red");
			return false;
		} else $("#content-ck").html("");

		$.ajax({
			url: `/recmv/api/rev/update/${num}`,
			type: "PUT",
			contentType: "application/json; charset=UTF-8",
			data: JSON.stringify(rev)
		}).done(function(resp) {
			console.log(resp);
			alert("리뷰수정이 완료되었습니다.")
			location.replace(`/recmv/rev/read/${num}`);
		}).fail(function() {
			alert(JSON.stringify(err));
		});
	},

	delete: function() {
		var num = $("#revNum").text();

		$.ajax({
			url: `/recmv/api/rev/delete/${num}`,
			type: "DELETE",
		}).done(function(resp) {
			console.log(resp);
			alert("리뷰삭제가 완료되었습니다.")
			location.replace("/recmv/rev/main");
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
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