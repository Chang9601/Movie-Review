var rev = {
	exec: function() {
		$("#btn-write").on("click", () => {
			this.write();
		});
		$("#btn-upd-rev").on("click", () => {
			this.update();
		});
		$("#btn-del-rev").on("click", () => {
			this.delete();
		});
	},

	write: function() {
		var title = $("#title").val();
		var rev = $("#rev").val();

		if (title === "") {
			$("#titleCk").html("제목을 입력하세요.").css("color", "red");
			return false;
		} else $("#titleCk").html("");

		if (rev === "") {
			$("#revCk").html("내용을 입력하세요.").css("color", "red");
			return false;
		} else $("#revCk").html("");
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