$(document).ready(function() {
	$("form").submit(function() {
		var id = $("#id").val();
		var pw = $("#pw").val();
					
		if(id === "") {
			$("#idCk").html("아이디를 입력하세요.").css("color", "red");
			return false;
		}			 
		
		if(pw === "") {
			$("#pwCk").html("비밀번호를 입력하세요.").css("color", "red");
			return false;
		}	
	});		
});