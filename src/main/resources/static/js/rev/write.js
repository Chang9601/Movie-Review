$(document).ready(function() {
	$("form").submit(function() {
		var title = $("#title").val();
		var content = $("#content").val();
				
		if(title === "") {
			$("#pwCk").html("제목을 입력하세요.").css("color", "red");
			return false;
		}

		if(content === ""){
			$("#pwCk").html("내용을 입력하세요.").css("color", "red");
			return false;			
		}
	});		
});