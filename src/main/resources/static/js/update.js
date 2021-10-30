$(document).ready(function() {
	var emailRegex = /[a-zA-Z0-9]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,4})/;
			
	$("form").submit(function() {
		var pw = $("#pw").val();
		var email = $("#email").val();
		
		if(pw === "") {
			$("#pwCk").html("비밀번호를 입력하세요.").css("color", "red");
			return false;
		}else $("#pwCk").html("").css("color", "green");
		
		if(!emailRegex.test(email)) {
			$("#emailCk").html("형식에 맞지 않습니다.").css("color", "red");
			return false;
		}else $("#emailCk").html("사용 가능합니다.").css("color", "green"); 
	});		
});