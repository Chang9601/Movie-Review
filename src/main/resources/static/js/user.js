// username 유효성
const validateUsername = (username) => {
	const regex = /[a-z0-9]/;
	for (let i in username) {
		if (!regex.test(username[i]))
			return false;
	}
	
	return true;
};

// password 유효성
const validatePassword = (password) => {
	const regex = /[a-zA-Z0-9!@#$%^&*]/;
	for (let i in password) {
		if (!regex.test(password[i]))
			return false;
	}
	
	return true;
};

// email 유효성
const validateEmail = (email) => {
	const regex = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,4})$/;

	if (!regex.test(email)) {
		$('#email--ok').html('이메일이 형식에 맞지 않습니다.').css('color', 'red');
		return false;
	} else $('#email--ok').html('');

	return true;
};

// user 유효성 콜백
const validateUserCallback = (username, password, confirmPassword, email) => {
	const usernameRegex = /^(?=(.*[a-z])+)(?=(.*[0-9])+).{5,10}$/;
	const passwordRegex = /^(?=(.*[a-z])+)(?=(.*[A-Z])+)(?=(.*[0-9])+)(?=(.*[!@#$%^&*]+)).{7,14}$/;
	const emailRegex = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,4})$/;

	if (!usernameRegex.test(username) || !validateUsername(username)) {
		$('#username--ok').html('5~10자의 영문 소문자와 숫자만 사용 가능합니다. 각각 적어도 1개 이상 포함하세요.').css('color', 'red');
		return false;
	} else $('#username--ok').html('');

	if (!passwordRegex.test(password) || !validatePassword(password)) {
		$('#password--ok').html('7~14자의 영문 대소문자, 숫자, 특수문자(!@#$%^&*)만 사용 가능합니다. 각각 적어도 1개 이상 포함하세요.').css('color', 'red');
		return false;
	} else $('#password--ok').html('');

	if (confirmPassword !== password) {
		$('#confirm--password--ok').html('비밀번호가 일치하지 않습니다.').css('color', 'red');
		return false;
	} else $('#confirm--password--ok').html('');

	if (!emailRegex.test(email)) {
		$('#email--ok').html('이메일이 형식에 맞지 않습니다.').css('color', 'red');
		return false;
	} else $('#email--ok').html('');

	return true;
}

// user 유효성
const validateUser = (username, password, confirmPassword, email) => {
	return new Promise((resolve, reject) => {
		if (validateUserCallback(username, password, confirmPassword, email)) {
			resolve('사용자 유효성 성공');
		} else {
			reject('사용자 유효성 실패');
		}
	});
};

// 비동기적으로 유효성을 검증하는 함수
const validate = async (username, password, confirmPassword, email) => {
	try {
		let validateUserResult = await validateUser(username, password, confirmPassword, email);
		console.log(validateUserResult);

		let validateDuplicateUserResult = await fetch(`/recmv/duplicate/?username=${username}`);
		let validateDuplicateUserResultJson = await validateDuplicateUserResult.json();
		console.log(validateDuplicateUserResultJson);

		if (validateDuplicateUserResultJson.data === username) {
			alert('이미 사용 중인 아이디입니다.');
			return;
		}

		// form의 속성값 변경
		let fr = $('form[role="join--form"]');

		fr.attr('action', '/recmv/join');
		fr.attr('method', 'post');
		fr.submit();

	} catch (e) {
		console.error(e);
	}
}

let user = {
	exec: function() {
		$('#btn--join').on('click', () => {
			this.join();
		});

		$('#btn--login').on('click', (event) => {
			if (!this.login())
				event.preventDefault();
		});

		$('#btn--user--update').on('click', () => {
			this.update();
		});

		$('#btn--user--delete').on('click', () => {
			this.delete();
		});
	},

	join: function() {
		let username = $('#username').val();
		let password = $('#password').val();
		let confirmPassword = $('#confirm--password').val();
		let email = $('#email').val();

		validate(username, password, confirmPassword, email);
	},

	login: function() {
		let username = $('#username').val();
		let password = $('#password').val();

		if (username === '') {
			$('#username--ok').html('아이디를 입력하세요.').css('color', 'red');
			return false;
		} else $('#username--ok').html('');

		if (password === '') {
			$('#password--ok').html('비밀번호를 입력하세요.').css('color', 'red');
			return false;
		} else $('#password--ok').html('');

		return true;
	},

	update: function() {
		const passwordRegex = /^(?=(.*[a-z])+)(?=(.*[A-Z])+)(?=(.*[0-9])+)(?=(.*[!@#$%^&*]+)).{7,14}$/;
		const emailRegex = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,4})$/;
	
		let id = $('#id').val();
		let username = $('#username').val();
		let password = $('#password').val();
		let confirmPassword = $('#confirm--password').val();
		let email = $('#email').val();

		if (!passwordRegex.test(password) || !validatePassword(password)) {
			$('#password--ok').html('7~14자의 영문 대소문자, 숫자, 특수문자(!@#$%^&*)만 사용 가능합니다. 각각 적어도 1개 이상 포함하세요.').css('color', 'red');
			return false;
		} else $('#password--ok').html('');

		if (confirmPassword !== password) {
			$('#confirm--password--ok').html('비밀번호가 일치하지 않습니다.').css('color', 'red');
			return false;
		} else $('#confirm--password--ok').html('');

		if (!emailRegex.test(email)) {
			$('#email--ok').html('이메일이 형식에 맞지 않습니다.').css('color', 'red');
			return false;
		} else $('#email--ok').html('');

		// CSRF 토큰
		let token = $('meta[name="_csrf"]').attr('content');
		let header = $('meta[name="_csrf_header"]').attr('content');
		
		let user = {
			username: username,
			password: password,
			email: email
		};
		
		console.log(JSON.stringify(user));
		
		$.ajax({
			url: `/recmv/api/users/${id}/update`,
			type: 'PUT',
			contentType: 'application/json; charset=UTF-8',
			data: JSON.stringify(user),
			beforeSend: function(xhr) { // 데이터 전송 전 HTTP 헤더 설정
				xhr.setRequestHeader(header, token);
			}			
		}).done(function(res) { // 응답 결과
			alert('회원수정 완료');
			location.replace(`/recmv/users?username=${res.data}`);
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	},

	delete: function() {
		let id = $('#id').val();
		let password = $('#password').val();
		
		if (password === '') {
			$('#password--ok').html('비밀번호를 입력하세요.').css('color', 'red');
			return false;
		} else $('#password--ok').html('');
		
		let ok = confirm('정말로 탈퇴하시겠습니까?');
		
		if (!ok) return;

		// CSRF 토큰
		let token = $('meta[name="_csrf"]').attr('content');
		let header = $('meta[name="_csrf_header"]').attr('content');
				
		let user = {
			password: password
		};

		$.ajax({
			url: `/recmv/api/users/${id}/delete`,
			type: 'DELETE',
			contentType: 'application/json; charset=UTF-8',
			data: JSON.stringify(user),	
			beforeSend: function(xhr) { // 데이터 전송 전 HTTP 헤더 설정
				xhr.setRequestHeader(header, token);
			}					
		}).done(function(res) {
			if(res.data === 0) {
				alert('회원탈퇴 완료');
				location.replace('/recmv/');
			} else {
				alert('비밀번호가 일치하지 않습니다.');
			}
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	}
};

user.exec();