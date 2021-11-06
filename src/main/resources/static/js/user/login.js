$(document).ready(function() {
	$("#btn-login").on("click", ()=> {
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
			url: "/recmv/api/user/ckUser",
			type: "POST",
			contentType: "application/json; charset=UTF-8",
			data: JSON.stringify(user)
		}).done(function(resp){
			console.log(resp);
			if(resp !== id){
				alert("아이디 혹은 비밀번호가 일치하지 않습니다.");
				return false;				
			}
			
			$.ajax({
				url: "/recmv/api/user/login",
				type: "POST",
				contentType: "application/json; charset=UTF-8",
				data: JSON.stringify(user)
			}).done(function(resp){
				console.log(resp);
				if(resp == null){
					location.href = "./login";					
				}
				location.replace("./main");
			}).fail(function(err){
				alert(JSON.stringify(err));				
			});
		}).fail(function(err){
			alert(JSON.stringify(err));
		});
	});		
});