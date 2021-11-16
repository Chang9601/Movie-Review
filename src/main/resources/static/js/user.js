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

var user = {
	exec: function() {
		$("#btn-signup").on("click", ()=> {
			this.signup();
		});
		$("#btn-login").on("click", ()=> {
			this.login();
		});
		$("#btn-upd-user").on("click", ()=> {
			this.update();
		});
		$("#btn-del-user").on("click", ()=> {
			this.delete();
		});
	},
	
	signup: function() {
		var idRegex = /^(?=(.*[a-z])+)(?=(.*[0-9])+).{5,10}$/;
		var pwRegex = /^(?=(.*[a-z])+)(?=(.*[A-Z])+)(?=(.*[0-9])+)(?=(.*[!@#$%^&*]+)).{7,14}$/;
		var emailRegex = /[a-zA-Z0-9]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,4})/;
			
		var id = $("#id").val();
		var pw = $("#pw").val();
		var cpw = $("#cpw").val();
		var email = $("#email").val();
						
		var user = {
			id: id,
			pw: pw,
			email: email
		}

		if(!idRegex.test(id) || !ckId(id)) {
			$("#idCk").html("5~10자의 영문 소문자와 숫자만 사용 가능합니다. 각각 적어도 1개 이상 포함하세요.").css("color", "red");
			return false;
		}else $("#idCk").html("");
						
		$.ajax({
			url: "/recmv/api/user/ckDupId",
			type: "GET",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {id: id}	
		}).done(function(resp) {
			console.log(resp);
			if(resp === id) {
				$("#idCk").html("이미 사용중인 아이디입니다.").css("color", "red");						
				return false;				
			}else $("#idCk").html("");

			if(!pwRegex.test(pw) || !ckPw(pw)) {
				$("#pwCk").html("7~14자의 영문 대소문자, 숫자, 특수문자(!@#$%^&*)만 사용 가능합니다. 각각 적어도 1개 이상 포함하세요.").css("color", "red");
				return false;
			}else $("#pwCk").html("");

			if(cpw !== pw) {
				$("#cpwCk").html("비밀번호가 일치하지 않습니다.").css("color", "red");
				return false;
			}else $("#cpwCk").html("");
			
			if(!emailRegex.test(email)) {
				$("#emailCk").html("이메일이 형식에 맞지 않습니다.").css("color", "red");
				return false;
			}else $("#emailCk").html("");
	
			location.replace("./login");
			$.ajax({			
				url: "/recmv/api/user/signup",
				type: "POST",
				contentType: "application/json; charset=UTF-8",
				data: JSON.stringify(user),	
			}).done(function(resp) {
				console.log(resp);	
				alert("회원가입이 완료되었습니다.");											
				location.replace("./login");	
			}).fail(function(err) {
				alert(JSON.stringify(err));
			});					
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});				
	},
	
	login: function() {
		var id = $("#id").val();
		var pw = $("#pw").val();
		
		var user = {
			id: id,
			pw: pw	
		};
					
		if(id === "") {
			$("#idCk").html("아이디를 입력하세요.").css("color", "red");
			return false;
		}else $("#idCk").html("");			 

		if(pw === "") {
			$("#pwCk").html("비밀번호를 입력하세요.").css("color", "red");
			return false;
		}else $("#pwCk").html("");
					
		$.ajax({
			url: "/recmv/api/user/login",
			type: "POST",
			contentType: "application/json; charset=UTF-8",
			data: JSON.stringify(user)
		}).done(function(resp) {
			console.log(resp);
			if(resp !== id){
				alert("아이디 혹은 비밀번호가 일치하지 않습니다.");
				return false;				
			}	
			alert("로그인이 완료되었습니다.")								
			location.replace("/recmv");
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});		
	},
	
	update: function() {
		var pwRegex = /^(?=(.*[a-z])+)(?=(.*[A-Z])+)(?=(.*[0-9])+)(?=(.*[!@#$%^&*]+)).{7,14}$/;
		var emailRegex = /[a-zA-Z0-9]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,4})/;
				
		var id = $("#id").val();
		var ppw = $("#ppw").val();
		var pw = $("#pw").val();
		var cpw = $("#cpw").val();
		var email = $("#email").val();
		
		var user = {
			id: id,
			pw: pw,
			email: email
		};		

		if(!pwRegex.test(pw) || !ckPw(pw)) {
			$("#pwCk").html("7~14자의 영문 대소문자, 숫자, 특수문자(!@#$%^&*)만 사용 가능합니다. 각각 적어도 1개 이상 포함하세요.").css("color", "red");
			return false;
		}else $("#pwCk").html("");		
		
		if(pw === ppw) {
			$("#pwCk").html("현재 비밀번호와 다른 비밀번호를 입력하세요.").css("color", "red");
			return false;
		}else $("#pwCk").html("");
		
		if(pw !== cpw) {
			$("#cpwCk").html("비밀번호가 일치하지 않습니다.").css("color", "red");
			return false;			
		}else $("#cpwCk").html("");

		if(!emailRegex.test(email)) {
			$("#emailCk").html("이메일이 형식에 맞지 않습니다.").css("color", "red");
			return false;
		}else $("#emailCk").html("");
		
		$.ajax({
			url: "/recmv/api/user/update",
			type: "PUT",
			contentType: "application/json; charset=UTF-8",
			data: JSON.stringify(user),
		}).done(function(resp) {
			console.log(resp);
			alert("수정이 완료되었습니다.");			
			location.replace("/recmv/user/read");			
		}).fail(function(err) {
			alert(JSON.stringify(err))				
		});
	},
	
	delete: function() {
		var id = $("#id").val();
		var pw = $("#pw").val();
		var rpw = $("#rpw").val();
				
		var user = {
			id: id,
			pw: pw
		};
		
		if(pw === "") {
			$("#pwCk").html("비밀번호를 입력하세요.").css("color", "red");
			return false;
		}else $("#pwCk").html("");

		if(pw !== rpw){
			$("#pwCk").html("비밀번호가 일치하지 않습니다.").css("color", "red");
			return false;	
		}else $("#pwCk").html("");	
				
		var con = confirm("탈퇴하시겠습니까?");	
		if(con === false) return false; 
		
		$.ajax({
			url: "/recmv/api/user/delete",
			type: "DELETE",
			contentType: "application/json; charset=UTF-8",				
			data: JSON.stringify(user)
		}).done(function(resp){
			console.log(resp);
			alert("탈퇴가 완료되었습니다.");		
			location.replace("/recmv");									
		}).fail(function(err){
			alert(JSON.stringify(err));
		});				
	}
};

user.exec();