$(document).ready(function() {
	$("form").submit(function() {
		var id = $("#id").val();
		var pw = $("#pw").val();
		var user = {
			id: id,
			pw: pw	
		};
					
		if(id === "") {
			$("#idCk").html("아이디를 입력하세요.").css("color", "red");
			return false;
		}			 
		
		if(pw === "") {
			$("#pwCk").html("비밀번호를 입력하세요.").css("color", "red");
			return false;
		}	
		
		
		$.ajax({
			url: "./ckUser",
			type: "POST",
			contentType: "application/json;charset=UTF-8",
			data: JSON.stringify(user),
			success: function(ret){		
				if(ret !== id){
					//alert("결과: " + ret);					
					alert("아이디 혹은 비밀번호가 일치하지 않습니다.");
				//	$("#userCk").html("아이디 혹은 비밀번호가 일치하지 않습니다.").css("color", "red");			
					return false;
				}
			}
		});
	});		
});