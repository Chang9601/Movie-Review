$(document).ready(function() {
	var emailRegex = /[a-zA-Z0-9]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,4})/;
			
	$("form").submit(function() {
		var pw = $("#pw").val();
		var rpw = $("#rpw").val();
		var email = $("#email").val();
		
		//alert("실제: " + rpw);
		
		if(pw === "") {
			$("#pwCk").html("비밀번호를 입력하세요.").css("color", "red");
			return false;
		}
		
		if(pw !== rpw){
			$("#pwCk").html("비밀번호가 일치하지 않습니다.").css("color", "red");
			return false;			
		}

		if(!emailRegex.test(email)) {
			$("#emailCk").html("형식에 맞지 않습니다.").css("color", "red");
			return false;
		}
	});		
});