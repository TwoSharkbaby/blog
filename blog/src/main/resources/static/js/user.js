"use strict"

let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
	},

	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8",  // body 데이터가 어떤 타입인지(mine)
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 데이터타입이 버퍼드문자열을 json오브젝으로 변경하여
		}).done(function(resp) { // resp <= 과 같이 담아서 사용하기 위함
			if(resp.status === 500){
				alert("회원가입에 실패했습니다.");
			}else{
				alert("회원가입이 완료되었습니다.");
				location.href = "/";
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	update: function() {

		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8",  // body 데이터가 어떤 타입인지(mine)
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 데이터타입이 버퍼드문자열을 json오브젝으로 변경하여
		}).done(function(resp) { // resp <= 과 같이 담아서 사용하기 위함
			alert("회원수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}

}

index.init();