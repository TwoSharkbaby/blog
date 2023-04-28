<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container mt-5">

	<form>
		<input type="hidden" id="id" value="${principal.user.id}" />
		<div class="mb-3">
			<label for="username" class="form-label">Username</label> <input type="text" class="form-control" id="username" value="${principal.user.username}" readonly>
		</div>

		<c:if test="${empty principal.user.oauth}">
			<div class="mb-3">
				<label for="password" class="form-label">Password</label> <input type="password" class="form-control" id="password">
			</div>
		</c:if>

		<div class="mb-3">
			<label for="email" class="form-label">Email</label> <input type="text" class="form-control" id="email" value="${principal.user.email}" readonly>
		</div>
	</form>

	<button id="btn-update" class="btn btn-primary">회원수정완료</button>

</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>