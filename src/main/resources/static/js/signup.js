var ckId = function(id) {
	var regex = /[a-z0-9]/;
	for(var i in id)
		if(!regex.test(id[i])) return false;
	return true;
};

var ckPw = function(pw) {
	var regex = /[a-zA-Z0-9!@#$%^&*]/;
	for(var i in pw)
		if(!regex.test(pw[i])) return false;
	return true;	
};

$(document).ready(function() {
	var idRegex = /^(?=(.*[a-z])+)(?=(.*[0-9])+).{5,10}$/;
	var pwRegex = /^(?=(.*[a-z])+)(?=(.*[A-Z])+)(?=(.*[0-9])+)(?=(.*[!@#$%^&*]+)).{7,14}$/;
	var emailRegex = /[a-zA-Z0-9]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,4})/;
			
	$("form").submit(function() {
	//	var idRegex = /^(?=(.*[a-z])+)(?=(.*[0-9])+).{5,10}$/;
		
		var id = $("#id").val();
		var pw = $("#pw").val();
		var cpw = $("#cpw").val();
		var email = $("#email").val();
					
		if(!idRegex.test(id) || !ckId(id)) {
			$("#idCk").html("5~10자의 영문 소문자와 숫자만 사용 가능합니다. 각각 적어도 1개 이상 포함하세요.").css("color", "red");
			return false;
		}else{
			
			$.ajax({
				url: "./ckDupId",
				type: "POST",
				contentType: "application/json; charset=UTF-8",
				data: JSON.stringify(id),
				success: function(ret){
					if(ret === id){	
						$("#idCk").html("이미 사용중인 아이디입니다.").css("color", "red");						
						return false;
					}
				}
			});
			
			$("#idCk").html("사용 가능합니다.").css("color", "green");
		}
		
		if(!pwRegex.test(pw) || !ckPw(pw)) {
			$("#pwCk").html("7~14자의 영문 대소문자, 숫자, 특수문자(!@#$%^&*)만 사용 가능합니다. 각각 적어도 1개 이상 포함하세요.").css("color", "red");
			return false;
		}
		
		if(cpw === "") {
			$("#cpwCk").html("비밀번호가 일치하지 않습니다.").css("color", "red");
			return false;
		}
		
		if(!emailRegex.test(email)) {
			$("#idCk").html("이메일 형식에 맞지 않습니다.").css("color", "red");
			return false;
		}
	});		
});