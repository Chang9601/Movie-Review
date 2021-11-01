$(document).ready(function() {
	$("form").submit(function() {
		var title = $("#title").val();
		var rev = $("#rev").val();
		
				
		if(title === "") {
			$("#titleCk").html("제목을 입력하세요.").css("color", "red");
			return false;
		}

		if(rev === ""){
			$("#revCk").html("내용을 입력하세요.").css("color", "red");
			return false;			
		}
	});		
});