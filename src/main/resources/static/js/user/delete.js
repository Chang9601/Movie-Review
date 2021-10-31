$(document).ready(function() {
	$("form").submit(function() {
		var pw = $("#pw").val();
		var rpw = $("#rpw").val();
				
		if(pw === "") {
			$("#pwCk").html("비밀번호를 입력하세요.").css("color", "red");
			return false;
		}

		if(pw !== rpw){
			$("#pwCk").html("비밀번호가 일치하지 않습니다.").css("color", "red");
			return false;			
		}else{
			var con = confirm("탈퇴하시겠습니까?");	
			if(con === false) return false; 
		}				
	});		
});