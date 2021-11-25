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

var admin = {
	exec: function() {
		$("#btn-admin-upd-user").on("click", ()=> {
			this.update();
		});
		$("#btn-admin-del-user").on("click", ()=> {
			this.delete();
		});
	},
		
	update: function() {
		var pwRegex = /^(?=(.*[a-z])+)(?=(.*[A-Z])+)(?=(.*[0-9])+)(?=(.*[!@#$%^&*]+)).{7,14}$/;
		var emailRegex = /[a-zA-Z0-9]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,4})/;
				
		var num = $("#num").val();
		var pw = $("#pw").val();
		var email = $("#email").val();
		
		var user = {
			pw: pw,
			email: email
		};		

		if(!pwRegex.test(pw) || !ckPw(pw)) {
			$("#pw-ck").html("7~14자의 영문 대소문자, 숫자, 특수문자(!@#$%^&*)만 사용 가능합니다. 각각 적어도 1개 이상 포함하세요.").css("color", "red");
			return false;
		}else $("#pw-ck").html("");		

		if(!emailRegex.test(email)) {
			$("#email-ck").html("이메일이 형식에 맞지 않습니다.").css("color", "red");
			return false;
		}else $("#email-ck").html("");
		
		$.ajax({
			url: `/recmv/api/admin/update/${num}`,
			type: "PUT",
			contentType: "application/json; charset=UTF-8",
			data: JSON.stringify(user),
		}).done(function(resp) {
			console.log(resp);
			alert("관리자 회원수정이 완료되었습니다.");			
			location.replace("/recmv/admin/users");			
		}).fail(function(err) {
			alert(JSON.stringify(err))				
		});
	},
	
	delete: function() {
		var num = $("#num").text();		
		
		var msg = confirm("삭제하시겠습니까?");
		if(msg == false)
			return false;
		
		$.ajax({
			url: `/recmv/api/admin/delete/${num}`,
			type: "DELETE",
		}).done(function(resp){
			console.log(resp);
			alert("관리자 회원탈퇴가 완료되었습니다.");		
			location.replace("/recmv/admin/users");									
		}).fail(function(err){
			alert(JSON.stringify(err));
		});				
	}
};

admin.exec();