var movie = {
	exec: function() {
		$("#btn-movie-api").on("click", () => {
			this.searchAPI();
		});
		$("#btn-movie-db").on("click", () => {
			this.searchDB();
		});
	},

	searchAPI: function() {
		var queryAPI = $("#query-api").val();

		if (queryAPI === "") {
			$("#api-ck").html("검색할 영화의 제목을 입력하세요.").css("color", "red");
			return false;
		} else $("#api-ck").html("");

		$.ajax({
			url: "/recmv/api/movie/searchMovieAPI",
			type: "GET",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: { query: queryAPI }
		}).done(function(resp) {
			console.log(resp);
			window.open(`/recmv/movie/searchMovieDB?query=${queryAPI}`);
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	},

	searchDB: function() {
		var queryDB = $("#query-db").val();

		if (queryDB === "") {
			$("#db-ck").html("리뷰를 작성할 영화의 제목을 입력하세요.").css("color", "red");
			return false;
		} else $("#db-ck").html("");

		window.open(`/recmv/movie/searchMovieDB?query=${queryDB}`);
	},
};

movie.exec();