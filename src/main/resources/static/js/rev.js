var rev = {
	exec: function() {
		$("#btn-write").on("click", () => {
			this.write();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-delete").on("click", () => {
			this.delete();
		});
	},

	write: function() {
		var userId = $("#userId").val();
		var movie = $("#movie").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var rating = $("#rating").val();
		
		var rev = {
			userId: userId,
			movie: movie,
			title: title,
			content: content,
			rating: rating
		};

		if (title === "") {
			$("#titleCk").html("제목을 입력하세요.").css("color", "red");
			return false;
		} else $("#titleCk").html("");

		if (content === "") {
			$("#contentCk").html("내용을 입력하세요.").css("color", "red");
			return false;
		} else $("#contentCk").html("");
		
		$.ajax({
			url: "/recmv/api/rev/write",
			type: "POST",
			contentType: "application/json; charset=UTF-8",
			data: JSON.stringify(rev)
		}).done(function(resp){
			console.log(resp);
			location.replace("./main");			
		}).fail(function(err){
			alert(JSON.stringify(err));			
		});
	},

	update: function() {
		var form = $("form");
		var id = $("#id").val();
		var userId = $("#userId").val();

		if (id !== userId) {
			alert("수정권한이 없습니다.");
			return false;
		}

		form.attr("action", "./update");
		form.attr("method", "get");
		form.submit();
	},
	
	delete: function() {
		var form = $("form");
		var id = $("#id").val();
		var userId = $("#userId").val();

		if (id !== userId) {
			alert("수정권한이 없습니다.");
			return false;
		}

		form.attr("action", "./delete");
		form.submit();
	},	
};

rev.exec();