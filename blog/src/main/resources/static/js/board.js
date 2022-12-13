"use strict"

let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-reply-save").on("click", () => {
			this.replySave();
		});
	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8",  // body 데이터가 어떤 타입인지(mine)
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 데이터타입이 버퍼드문자열을 json오브젝으로 변경하여
		}).done(function(resp) { // resp <= 과 같이 담아서 사용하기 위함
			alert("글 작성이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	deleteById: function() {
		let id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 데이터타입이 버퍼드문자열을 json오브젝으로 변경하여
		}).done(function(resp) { // resp <= 과 같이 담아서 사용하기 위함
			alert("글 삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	update: function() {
		let id = $("#id").val();

		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8",  // body 데이터가 어떤 타입인지(mine)
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 데이터타입이 버퍼드문자열을 json오브젝으로 변경하여
		}).done(function(resp) { // resp <= 과 같이 담아서 사용하기 위함
			alert("글 수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	replySave: function() {
		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val()
		};
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8",  // body 데이터가 어떤 타입인지(mine)
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 데이터타입이 버퍼드문자열을 json오브젝으로 변경하여
		}).done(function(resp) { // resp <= 과 같이 담아서 사용하기 위함
			alert("댓글 작성이 완료되었습니다.");
			location.href = `/board/${data.boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	replyDelete: function(boardId, replyId) {
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 데이터타입이 버퍼드문자열을 json오브젝으로 변경하여
		}).done(function(resp) { // resp <= 과 같이 담아서 사용하기 위함
			alert("댓글 삭제가 완료되었습니다.");
			location.href = `/board/${boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

}

index.init();