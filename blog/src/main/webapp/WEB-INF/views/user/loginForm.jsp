<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container mb-3">

	<form action="/auth/loginProc" method="post">
		<div class="mb-3">
			<label for="username" class="form-label">Username</label> <input
				type="text" class="form-control" id="username" name="username">
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">Password</label> <input
				type="password" class="form-control" id="password" name="password">
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=68114e2f495b7d88ffc76ef4fe63f1d2&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_button.png"></a>
	</form>
	

</div>

<%@ include file="../layout/footer.jsp"%>