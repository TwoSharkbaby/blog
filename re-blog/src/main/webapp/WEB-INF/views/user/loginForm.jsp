<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container mt-5">

	<form action="/auth/loginProc" method="post">
		<div class="mb-3">
			<label for="username" class="form-label">Username</label> <input type="text" class="form-control" id="username" name="username">
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">Password</label> <input type="password" class="form-control" id="password" name="password">
		</div>
		<div class="mb-3 form-check">
			<label class="form-check-label" for="remember">Remember Me</label> <input type="checkbox" class="form-check-input" id="remember" name="remember"> 
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=1eb3f873da90b74d1341e933e7988121&redirect_uri=http://localhost:9000/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_btn.png"></a>
	</form>



</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>