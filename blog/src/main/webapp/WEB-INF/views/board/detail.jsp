<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container mb-3">

	<form>
		<div class="mb-3">
			<label for="title" class="form-label">Title</label>
			<h3>
				<c:out value="${board.title}" />
			</h3>
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">Content</label>
			<div>${board.content}</div>
		</div>
	</form>

	<div class="mb-3">
		글번호 : <span id="id"><i><c:out value="${board.id}" /> </i></span> 작성자
		: <span><i><c:out value="${board.user.username}" /> </i></span>
	</div>

	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${board.user.id == principal.user.id}">
		<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>

	<hr />

	<div class="card mb-3">
		<form>
			<input type="hidden" id="userId" value="<c:out value='${principal.user.id}'/>">
			<input type="hidden" id="boardId" value="<c:out value='${board.id}'/>">
			<div class="card-body">
				<textarea class="form-control" rows="1" id="reply-content"></textarea>
			</div>
			<div class="card-footer">
				<button type="button" class="btn btn-primary" id="btn-reply-save">등록</button>
			</div>
		</form>
	</div>

	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul class="list-group" id="reply-box">
			<c:forEach var="reply" items="${board.reply}">
				<li class="list-group-item d-flex justify-content-between"
					id="reply-${reply.id}">
					<div>
						<c:out value="${reply.content}" />
					</div>
					<div class="d-flex">
						<div class="fst-italic">
							<c:out value="${reply.user.username}" />
							&nbsp;
						</div>
						<button onclick="index.replyDelete(${board.id},${reply.id})" class="badge text-bg-dark">삭제</button>
					</div>
				</li>
			</c:forEach>
		</ul>

	</div>

</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>