<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container mb-3">

	<form>
		<div class="mb-3">
			<label for="username" class="form-label">Username</label> <input
				type="text" class="form-control" id="username">
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">Password</label> <input
				type="password" class="form-control" id="password">
		</div>
		<div class="mb-3">
			<label for="email" class="form-label">Email</label> <input
				type="email" class="form-control" id="email">
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">회원가입하기</button>

</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>