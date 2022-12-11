<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container mb-3">

	<form>
		<div class="mb-3">
		 	<label for="title" class="form-label">Title</label> 
			<h3><c:out value="${board.title}"/></h3>
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">Content</label>
			<div>${board.content}</div>
		</div>
	</form>
	
	<div class="mb-3">
		글번호 : <span id="id"><i><c:out value="${board.id}"/> </i></span>
		작성자 : <span><i><c:out value="${board.user.username}"/> </i></span>
	</div>
	
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${board.user.id == principal.user.id}">
	<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
	<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>

</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>